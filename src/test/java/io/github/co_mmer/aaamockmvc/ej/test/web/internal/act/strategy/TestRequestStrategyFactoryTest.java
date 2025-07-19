package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.TRACE;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;

class TestRequestStrategyFactoryTest {

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_resolve_THEN_throwNullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestStrategyFactory.resolve(null));
  }

  @ParameterizedTest
  @MethodSource("useCase")
  void GIVEN_method_WHEN_resolve_THEN_return_expected_strategy(
      HttpMethod method, Class<TestRequestStrategy> expectedStrategy) {

    // Act
    var strategy = TestRequestStrategyFactory.resolve(method);

    // Assert
    assertThat(strategy.getClass(), is(expectedStrategy));
  }

  private static Stream<Arguments> useCase() {
    return Stream.of(
        Arguments.of(GET, TestRequestGetStrategy.class),
        Arguments.of(POST, TestRequestPostStrategy.class),
        Arguments.of(PUT, TestRequestPutStrategy.class),
        Arguments.of(PATCH, TestRequestPatchStrategy.class),
        Arguments.of(DELETE, TestRequestDeleteStrategy.class),
        Arguments.of(HEAD, TestRequestHeadStrategy.class),
        Arguments.of(OPTIONS, TestRequestOptionsStrategy.class));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_Unsupported_method_WHEN_resolve_THEN_throwIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> TestRequestStrategyFactory.resolve(TRACE));
  }
}
