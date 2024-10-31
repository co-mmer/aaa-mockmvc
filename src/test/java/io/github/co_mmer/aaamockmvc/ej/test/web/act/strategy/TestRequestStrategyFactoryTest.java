package io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy;

import static io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType.DELETE;
import static io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType.GET;
import static io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType.HEAD;
import static io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType.OPTIONS;
import static io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType.PATCH;
import static io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType.POST;
import static io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType.PUT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestRequestStrategyFactoryTest {

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_resolve_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestStrategyFactory.resolve(null));
  }

  @ParameterizedTest
  @MethodSource("useCase")
  void GIVEN_type_WHEN_resolve_THEN_return_expected_strategy(
      TestRequestType requestType, Class<TestRequestStrategy> expectedStrategy) {

    // Act
    var strategy = TestRequestStrategyFactory.resolve(requestType);

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
}
