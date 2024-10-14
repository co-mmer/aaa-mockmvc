package ej.aaamockmvc.test.request.act.strategy;

import static ej.aaamockmvc.test.web.request.model.TestRequestType.DELETE;
import static ej.aaamockmvc.test.web.request.model.TestRequestType.GET;
import static ej.aaamockmvc.test.web.request.model.TestRequestType.HEAD;
import static ej.aaamockmvc.test.web.request.model.TestRequestType.OPTIONS;
import static ej.aaamockmvc.test.web.request.model.TestRequestType.PATCH;
import static ej.aaamockmvc.test.web.request.model.TestRequestType.POST;
import static ej.aaamockmvc.test.web.request.model.TestRequestType.PUT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import ej.aaamockmvc.test.web.act.strategy.TestRequestDeleteStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestGetStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestHeadStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestOptionsStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestPatchStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestPostStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestPutStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestStrategyFactory;
import ej.aaamockmvc.test.web.request.model.TestRequestType;
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
