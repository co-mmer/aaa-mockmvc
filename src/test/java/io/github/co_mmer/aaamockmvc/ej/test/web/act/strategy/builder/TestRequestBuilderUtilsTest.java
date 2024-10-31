package io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;

class TestRequestBuilderUtilsTest {

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_setMethod_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class,
        () -> TestRequestBuilderUtils.setMethod(null));
  }

  @ParameterizedTest
  @MethodSource("httpMethodsProvider")
  void GIVEN_httpMethod_WHEN_setMethod_THEN_return_processedRequest_with_correct_method(
      HttpMethod method) {

    // Arrange
    var request = new MockHttpServletRequest();

    // Act
    var postProcessor = TestRequestBuilderUtils.setMethod(method);

    // Assert
    var processedRequest = postProcessor.postProcessRequest(request);
    assertThat(processedRequest.getMethod(), is(method.toString()));
  }

  private static Stream<HttpMethod> httpMethodsProvider() {
    return Stream.of(HttpMethod.PUT, HttpMethod.PATCH);
  }
}
