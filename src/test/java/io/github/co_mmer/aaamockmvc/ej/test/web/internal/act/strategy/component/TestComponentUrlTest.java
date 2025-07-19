package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_QUERY1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_QUERY_EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_QUERY_NULL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_QUERY_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestParameter.TEST_QUERY_VALUE_1;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestUrlDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestComponentUrlTest {

  private MockHttpServletRequestBuilder mockRequestBuilder;

  @BeforeEach
  void setUp() {
    this.mockRequestBuilder = mock(MockHttpServletRequestBuilder.class);
  }

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_provideNullParameters_WHEN_apply_THEN_throw_NullPointerException(
      MockHttpServletRequestBuilder requestBuilder, TestRequestUrlDto testRequestUrlDto) {

    assertThrows(
        NullPointerException.class,
        () -> TestComponentUrl.apply(requestBuilder, testRequestUrlDto));
  }

  private static Stream<Arguments> provideNullParameters() {
    return Stream.of(
        Arguments.of(null, mock(TestRequestUrlDto.class)),
        Arguments.of(mock(MockHttpServletRequestBuilder.class), null),
        Arguments.of(null, null));
  }

  @Test
  void GIVEN_param_null_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentUrl.apply(this.mockRequestBuilder, TEST_REQUEST_URL_QUERY_NULL);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_param_empty_WHEN_apply_THEN_verifyNoInteractions() {
    // Act
    TestComponentUrl.apply(this.mockRequestBuilder, TEST_REQUEST_URL_QUERY_EMPTY);

    // Assert
    verifyNoInteractions(this.mockRequestBuilder);
  }

  @Test
  void GIVEN_param_1_WHEN_apply_THEN_verify_param() {
    // Act
    TestComponentUrl.apply(this.mockRequestBuilder, TEST_REQUEST_URL_QUERY1);

    // Assert
    verify(this.mockRequestBuilder).param(TEST_QUERY_KEY_1, TEST_QUERY_VALUE_1);
    verifyNoMoreInteractions(this.mockRequestBuilder);
  }
}
