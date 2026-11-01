package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestQuery;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import java.net.URI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class MockMvcRequestMapperTest {

  private static final URI ANY_PATH = URI.create("/test");

  private static final TextBody ANY_TEXT_BODY = new TextBody("test body");

  private static final MultipartBody ANY_MULTIPART_BODY = new MultipartBody();

  private MockedStatic<MockMvcRequestBuilders> requestBuilders;
  private MockedStatic<MockMvcBodyMapper> bodyMapper;
  private MockedStatic<MockMvcQueryMapper> queryParameterMapper;
  private MockedStatic<MockMvcHeaderMapper> headerMapper;

  @BeforeEach
  void mockCollaborators() {
    requestBuilders = mockStatic(MockMvcRequestBuilders.class);
    bodyMapper = mockStatic(MockMvcBodyMapper.class);
    queryParameterMapper = mockStatic(MockMvcQueryMapper.class);
    headerMapper = mockStatic(MockMvcHeaderMapper.class);
  }

  @AfterEach
  void closeMocks() {
    headerMapper.close();
    queryParameterMapper.close();
    bodyMapper.close();
    requestBuilders.close();
  }

  @Test
  void GIVEN_regular_request_WHEN_map_THEN_create_regular_builder_and_apply_common_mappers() {
    // Arrange
    var source = requestWith(new EmptyBody());
    var builder = givenRegularBuilderFor(source);

    // Act
    var result = MockMvcRequestMapper.map(source);

    // Assert
    requestBuilders.verify(
        () ->
            MockMvcRequestBuilders.request(
                org.springframework.http.HttpMethod.POST, source.path().value()));

    assertThatCommonMappersWereCalledWith(builder, source);
    assertThat(result, is(builder));
  }

  @Test
  void GIVEN_text_body_WHEN_map_THEN_call_text_body_mapper() {
    // Arrange
    var source = requestWith(ANY_TEXT_BODY);
    var builder = givenRegularBuilderFor(source);

    // Act
    MockMvcRequestMapper.map(source);

    // Assert
    bodyMapper.verify(() -> MockMvcBodyMapper.applyText(builder, ANY_TEXT_BODY));
    bodyMapper.verifyNoMoreInteractions();
  }

  @Test
  void GIVEN_empty_body_WHEN_map_THEN_do_not_call_body_mapper() {
    // Arrange
    var source = requestWith(new EmptyBody());
    givenRegularBuilderFor(source);

    // Act
    MockMvcRequestMapper.map(source);

    // Assert
    bodyMapper.verifyNoInteractions();
  }

  @Test
  void GIVEN_null_body_WHEN_map_THEN_do_not_call_body_mapper() {
    // Arrange
    var source = requestWith(null);
    givenRegularBuilderFor(source);

    // Act
    MockMvcRequestMapper.map(source);

    // Assert
    bodyMapper.verifyNoInteractions();
  }

  @Test
  void GIVEN_multipart_body_WHEN_map_THEN_create_multipart_builder_and_apply_mappers() {
    // Arrange
    var source = requestWith(ANY_MULTIPART_BODY);
    var builder = givenMultipartBuilderFor(source);

    // Act
    var result = MockMvcRequestMapper.map(source);

    // Assert
    requestBuilders.verify(
        () ->
            MockMvcRequestBuilders.multipart(
                org.springframework.http.HttpMethod.POST, source.path().value()));

    assertThatCommonMappersWereCalledWith(builder, source);
    bodyMapper.verify(() -> MockMvcBodyMapper.applyMultipart(builder, ANY_MULTIPART_BODY));

    assertThat(result, is(builder));
  }

  private MockHttpServletRequestBuilder givenRegularBuilderFor(Request source) {
    var builder = mock(MockHttpServletRequestBuilder.class);

    requestBuilders
        .when(
            () ->
                MockMvcRequestBuilders.request(
                    org.springframework.http.HttpMethod.POST, source.path().value()))
        .thenReturn(builder);

    return builder;
  }

  private MockMultipartHttpServletRequestBuilder givenMultipartBuilderFor(Request source) {
    var builder = mock(MockMultipartHttpServletRequestBuilder.class);

    requestBuilders
        .when(
            () ->
                MockMvcRequestBuilders.multipart(
                    org.springframework.http.HttpMethod.POST, source.path().value()))
        .thenReturn(builder);

    return builder;
  }

  private void assertThatCommonMappersWereCalledWith(
      MockHttpServletRequestBuilder builder, Request source) {
    queryParameterMapper.verify(() -> MockMvcQueryMapper.apply(builder, source.query().values()));

    headerMapper.verify(() -> MockMvcHeaderMapper.apply(builder, source.headers().values()));
  }

  private static Request requestWith(RequestBody body) {
    var path = new RequestPath();
    path.setValue(ANY_PATH);

    var query = new RequestQuery();
    query.add("query", "value");

    var headers = new RequestHeaders();
    headers.add("X-Test", "value");

    return new Request(HttpMethod.POST, path, query, headers, body);
  }
}
