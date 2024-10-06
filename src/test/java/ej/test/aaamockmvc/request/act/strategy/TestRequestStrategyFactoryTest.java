package ej.test.aaamockmvc.request.act.strategy;

import static ej.test.aaamockmvc.request.model.TestRequestType.DELETE;
import static ej.test.aaamockmvc.request.model.TestRequestType.GET;
import static ej.test.aaamockmvc.request.model.TestRequestType.HEAD;
import static ej.test.aaamockmvc.request.model.TestRequestType.OPTIONS;
import static ej.test.aaamockmvc.request.model.TestRequestType.PATCH;
import static ej.test.aaamockmvc.request.model.TestRequestType.POST;
import static ej.test.aaamockmvc.request.model.TestRequestType.PUT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ej.test.aaamockmvc.request.model.TestRequestType;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestRequestStrategyFactoryTest {

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
