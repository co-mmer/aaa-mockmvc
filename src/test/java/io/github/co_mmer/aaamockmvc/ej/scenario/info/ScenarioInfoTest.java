package io.github.co_mmer.aaamockmvc.ej.scenario.info;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.eq;

import io.github.co_mmer.aaamockmvc.ej.scenario.info.data.ScenarioDataActInfo;
import io.github.co_mmer.aaamockmvc.ej.scenario.info.data.ScenarioDataInfo;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.TestRequestStrategy;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.head.TestArrange1InfoHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.head.TestArrangeInfoHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class ScenarioInfoTest {

  private MockedStatic<TestRequestStrategyFactory> mockTestRequestStrategyFactory;
  private TestRequestStrategy mockTestRequestStrategy;

  @BeforeEach
  void setUp() {
    this.mockTestRequestStrategy = Mockito.mock(TestRequestStrategy.class);
    this.mockTestRequestStrategyFactory = Mockito.mockStatic(TestRequestStrategyFactory.class);
    this.mockTestRequestStrategyFactory
        .when(() -> TestRequestStrategyFactory.resolve(TestRequestType.HEAD))
        .thenReturn(this.mockTestRequestStrategy);
  }

  @AfterEach
  void clean() {
    this.mockTestRequestStrategyFactory.close();
  }

  @ParameterizedTest
  @MethodSource("useCase")
  void GIVEN_useCase_WHEN_perform_THEN_return_expected_data(
      TestAct1 arrangePerform, TestRequestDto requestDTO) {
    // Arrange
    var captor = ArgumentCaptor.forClass(TestRequestDto.class);

    // Act
    arrangePerform.actPerform();

    // Assert
    this.mockTestRequestStrategyFactory.verify(
        () -> TestRequestStrategyFactory.resolve(eq(TestRequestType.HEAD)));
    Mockito.verify(mockTestRequestStrategy).apply(captor.capture());

    var captorValue = captor.getValue();
    assertThat(captorValue.getUrl().getUri(), is(requestDTO.getUrl().getUri()));
    assertThat(captorValue.getUrl().getParam(), is(requestDTO.getUrl().getParam()));

    assertThat(captorValue.getHead().getKeyValue(), is(requestDTO.getHead().getKeyValue()));
    assertThat(captorValue.getHead().getAccepts(), is(requestDTO.getHead().getAccepts()));
    assertThat(captorValue.getHead().getContentTypes(), is(requestDTO.getHead().getContentTypes()));

    assertThat(captorValue.getBody().getContentType(), is(nullValue()));
    assertThat(captorValue.getBody().getContent(), is(nullValue()));
    assertThat(captorValue.getBody().getFiles(), is(empty()));
  }

  private static Stream<Arguments> useCase() {
    return Arrays.stream(ScenarioDataInfo.values())
        .map(data -> Arguments.of(data.getAct(), data.getRequestDto()));
  }

  @ParameterizedTest
  @MethodSource("useCaseHead")
  void GIVEN_useCaseHead_WHEN_getClass_THEN_return_expected_class(
      TestArrange1InfoHead testArrange1InfoHead) {
    // Assert
    assertThat(testArrange1InfoHead.getClass(), is(TestArrangeInfoHeadImpl.class));
  }

  private static Stream<Arguments> useCaseHead() {
    return Stream.of(
        Arguments.of(ScenarioDataActInfo.HEAD_ARRANGE__PARAM_KEY_VALUE_1__HEAD),
        Arguments.of(ScenarioDataActInfo.HEAD_ARRANGE__PARAM_KEY_VALUE_2__HEAD),
        Arguments.of(ScenarioDataActInfo.HEAD_ARRANGE__PARAM_KEY_VALUE_MAP__HEAD));
  }
}
