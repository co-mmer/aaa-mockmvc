package io.github.co_mmer.aaamockmvc.ej.test.web.act.component;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_NULL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_NULL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_NULL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.component.TestComponentHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestHeadDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestComponentHeadTest {

  private MockHttpServletRequestBuilder mockRequestBuilder;

  @BeforeEach
  void setUp() {
    this.mockRequestBuilder = mock(MockHttpServletRequestBuilder.class);
  }

  @ParameterizedTest()
  @MethodSource("provideNull")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_provideNull_WHEN_apply_THEN_throw_NullPointerException(
      MockHttpServletRequestBuilder builder, TestRequestHeadDto testRequestHeadDto) {

    assertThrows(
        NullPointerException.class,
        () -> TestComponentHead.apply(builder, testRequestHeadDto));
  }

  private static Stream<Arguments> provideNull() {
    return Stream.of(
        Arguments.of(null, mock(TestRequestHeadDto.class)),
        Arguments.of(mock(MockHttpServletRequestBuilder.class), null),
        Arguments.of(null, null));
  }

  @Test
  void GIVEN_head_accept_null_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_ACCEPT_NULL);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_accept_empty_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_ACCEPT_EMPTY);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_accept_1_WHEN_apply_THEN_verify_header() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_ACCEPT_1);

    // Assert
    verify(this.mockRequestBuilder).accept(APPLICATION_JSON);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_accept_2_WHEN_apply_THEN_verify_header() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_ACCEPT_2);

    // Assert
    verify(this.mockRequestBuilder).accept(APPLICATION_JSON);
    verify(this.mockRequestBuilder).accept(APPLICATION_XML);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_contentType_null_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_CONTENT_TYPE_NULL);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_contentType_empty_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_CONTENT_TYPE_EMPTY);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_contentType_1_WHEN_apply_THEN_verify_contentType() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_CONTENT_TYPE_1);

    // Assert
    verify(this.mockRequestBuilder).contentType(APPLICATION_JSON);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_contentType_2_WHEN_apply_THEN_verify_contentType() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_CONTENT_TYPE_2);

    // Assert
    verify(this.mockRequestBuilder).contentType(APPLICATION_JSON);
    verify(this.mockRequestBuilder).contentType(APPLICATION_XML);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_keyValue_null_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_KEY_VALUE_NULL);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_keyValue_empty_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_KEY_VALUE_EMPTY);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_keyValue_1_WHEN_apply_THEN_verify_header() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_KEY_VALUE_1);

    // Assert
    verify(this.mockRequestBuilder).header(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_head_keyValue_2_WHEN_apply_THEN_verify_header() {
    // Act
    TestComponentHead.apply(this.mockRequestBuilder, TEST_REQUEST_HEAD_KEY_VALUE_2);

    // Assert
    verify(this.mockRequestBuilder).header(TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);
    verify(this.mockRequestBuilder).header(TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }
}
