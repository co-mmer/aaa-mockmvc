package ej.test.aaamockmvc.scenario.info;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.eq;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.act.strategy.TestRequestStrategy;
import ej.test.aaamockmvc.request.act.strategy.TestRequestStrategyFactory;
import ej.test.aaamockmvc.request.arrange.info.head.TestArrange1InfoHead;
import ej.test.aaamockmvc.request.arrange.info.head.TestArrangeInfoHeadImpl;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import ej.test.aaamockmvc.scenario.info.data.ScenarioDataActInfo;
import ej.test.aaamockmvc.scenario.info.data.ScenarioDataInfo;
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
      TestAct1Perform arrangePerform, TestRequestDto requestDTO) {
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
