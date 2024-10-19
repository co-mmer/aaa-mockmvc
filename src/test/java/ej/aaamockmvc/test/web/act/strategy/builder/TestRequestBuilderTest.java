package ej.aaamockmvc.test.web.act.strategy.builder;

import static ej.aaamockmvc.test.testdata.testutil.TestValue.TEST_URI;
import static org.hamcrest.MatcherAssert.assertThat;

import ej.aaamockmvc.test.web.request.model.TestRequestType;
import jakarta.servlet.ServletContext;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

class TestRequestBuilderTest {

  private static final ServletContext SERVLET_CONTEXT = null;

  @Test
  void GIVEN_url_WHEN_get_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.get(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.GET.toString()));
  }

  @Test
  void GIVEN_url_WHEN_post_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.post(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.POST.toString()));
  }

  @Test
  void GIVEN_url_WHEN_postMultipart_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.postMultipart(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.POST.toString()));
  }

  @Test
  void GIVEN_url_WHEN_put_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.put(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.PUT.toString()));
  }

  @Test
  void GIVEN_url_WHEN_putMultipart_THEN_return_expected_request() {
    // Arrange
    var mockRequestPostProcessor = Mockito.mock(RequestPostProcessor.class);
    var mockTestRequestBuilderUtils = Mockito.mockStatic(TestRequestBuilderUtils.class);
    mockTestRequestBuilderUtils
        .when(() -> TestRequestBuilderUtils.setMethod(HttpMethod.PUT))
        .thenReturn(mockRequestPostProcessor);

    var mockMultiPartBuilder = Mockito.mock(MockMultipartHttpServletRequestBuilder.class);

    var mockRequestBuilders = Mockito.mockStatic(MockMvcRequestBuilders.class);
    mockRequestBuilders
        .when(() -> MockMvcRequestBuilders.multipart(TEST_URI))
        .thenReturn(mockMultiPartBuilder);

    // Act
    TestRequestBuilder.putMultipart(TEST_URI);

    // Assert
    Mockito.verify(mockMultiPartBuilder).with(mockRequestPostProcessor);
    mockTestRequestBuilderUtils.close();
    mockRequestBuilders.close();
  }

  @Test
  void GIVEN_url_WHEN_patch_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.patch(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.PATCH.toString()));
  }

  @Test
  void GIVEN_url_WHEN_patchMultipart_THEN_return_expected_request() {
    // Arrange
    var mockRequestPostProcessor = Mockito.mock(RequestPostProcessor.class);
    var mockTestRequestBuilderUtils = Mockito.mockStatic(TestRequestBuilderUtils.class);
    mockTestRequestBuilderUtils
        .when(() -> TestRequestBuilderUtils.setMethod(HttpMethod.PATCH))
        .thenReturn(mockRequestPostProcessor);

    var mockMultiPartBuilder = Mockito.mock(MockMultipartHttpServletRequestBuilder.class);

    var mockRequestBuilders = Mockito.mockStatic(MockMvcRequestBuilders.class);
    mockRequestBuilders
        .when(() -> MockMvcRequestBuilders.multipart(TEST_URI))
        .thenReturn(mockMultiPartBuilder);

    // Act
    TestRequestBuilder.patchMultipart(TEST_URI);

    // Assert
    Mockito.verify(mockMultiPartBuilder).with(mockRequestPostProcessor);
    mockTestRequestBuilderUtils.close();
    mockRequestBuilders.close();
  }

  @Test
  void GIVEN_url_WHEN_delete_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.delete(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.DELETE.toString()));
  }

  @Test
  void GIVEN_url_WHEN_head_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.head(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.HEAD.toString()));
  }

  @Test
  void GIVEN_url_WHEN_options_THEN_return_expected_request() {
    // Act
    var requestBuilder = TestRequestBuilder.options(TEST_URI);

    // Assert
    var request = requestBuilder.buildRequest(SERVLET_CONTEXT);
    assertThat(request.getMethod(), Matchers.is(TestRequestType.OPTIONS.toString()));
  }
}
