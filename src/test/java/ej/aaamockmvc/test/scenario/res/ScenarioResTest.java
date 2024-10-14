package ej.aaamockmvc.test.scenario.res;

import static ej.aaamockmvc.test.web.request.model.TestRequestType.POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;

import ej.aaamockmvc.test.scenario.res.data.ScenarioDataActPost;
import ej.aaamockmvc.test.scenario.res.data.ScenarioDataPost;
import ej.aaamockmvc.test.web.act.TestAct1Perform;
import ej.aaamockmvc.test.web.act.strategy.TestRequestStrategy;
import ej.aaamockmvc.test.web.act.strategy.TestRequestStrategyFactory;
import ej.aaamockmvc.test.web.arrange.res.body.TestArrange1ResBody;
import ej.aaamockmvc.test.web.arrange.res.body.TestArrangeResBodyImpl;
import ej.aaamockmvc.test.web.arrange.res.head.TestArrangeResHeadImpl;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
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

class ScenarioResTest {

  private MockedStatic<TestRequestStrategyFactory> mockTestRequestStrategyFactory;
  private TestRequestStrategy mockTestRequestStrategy;

  @BeforeEach
  void setUp() {
    this.mockTestRequestStrategy = Mockito.mock(TestRequestStrategy.class);
    this.mockTestRequestStrategyFactory = Mockito.mockStatic(TestRequestStrategyFactory.class);
    this.mockTestRequestStrategyFactory
        .when(() -> TestRequestStrategyFactory.resolve(POST))
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
    this.mockTestRequestStrategyFactory.verify(() -> TestRequestStrategyFactory.resolve(eq(POST)));
    Mockito.verify(mockTestRequestStrategy).apply(captor.capture());

    var captorValue = captor.getValue();
    assertThat(captorValue.getUrl().getUri(), is(requestDTO.getUrl().getUri()));
    assertThat(captorValue.getUrl().getParam(), is(requestDTO.getUrl().getParam()));

    assertThat(captorValue.getHead().getKeyValue(), is(requestDTO.getHead().getKeyValue()));
    assertThat(captorValue.getHead().getAccepts(), is(requestDTO.getHead().getAccepts()));
    assertThat(captorValue.getHead().getContentTypes(), is(requestDTO.getHead().getContentTypes()));

    assertThat(captorValue.getBody().getFiles(), is(requestDTO.getBody().getFiles()));
    assertThat(captorValue.getBody().getContent(), is(requestDTO.getBody().getContent()));
    assertThat(captorValue.getBody().getContentType(), is(requestDTO.getBody().getContentType()));
  }

  private static Stream<Arguments> useCase() {
    return Arrays.stream(ScenarioDataPost.values())
        .map(data -> Arguments.of(data.getAct(), data.getRequestDto()));
  }

  @ParameterizedTest
  @MethodSource("useCaseHead")
  void GIVEN_useCaseHead_WHEN_getClass_THEN_return_expected_class(TestArrangeResHeadImpl header) {
    // Assert
    assertThat(header.getClass(), is(TestArrangeResHeadImpl.class));
  }

  private static Stream<Arguments> useCaseHead() {
    return Stream.of(
        Arguments.of(ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_1__HEAD),
        Arguments.of(ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_2__HEAD),
        Arguments.of(ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_MAP__HEAD));
  }

  @ParameterizedTest
  @MethodSource("useCaseBody")
  void GIVEN_useCaseBody_WHEN_getClass_THEN_return_expected_class(TestArrange1ResBody body) {
    // Assert
    assertThat(body.getClass(), is(TestArrangeResBodyImpl.class));
  }

  private static Stream<Arguments> useCaseBody() {
    return Stream.of(
        Arguments.of(ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_1__BODY),
        Arguments.of(ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_2__BODY),
        Arguments.of(ScenarioDataActPost.POST_ARRANGE__PARAM_KEY_VALUE_MAP__BODY));
  }
}
