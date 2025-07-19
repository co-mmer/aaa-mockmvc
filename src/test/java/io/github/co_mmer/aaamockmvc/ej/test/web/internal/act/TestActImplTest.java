package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext.mockContext;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error.TestActFailedErrorMessage;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.mapper.TestActResultMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.TestRequestBaseStrategy;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

class TestActImplTest {

  private TestAAAContext context;
  private TestActImpl testAct;

  @BeforeEach
  void setUp() {
    this.context = mockContext();
    this.testAct = new TestActImpl(this.context);
  }

  @Test
  @SneakyThrows
  void WHEN_perform_THEN_expected_methods_are_called() {
    // Arrange
    var requestBuilder = mockRequestBuilder();
    var baseStrategy = mockBaseStrategyReturning(requestBuilder);
    var actResult = Mockito.mock(TestActResult.class);

    var strategyFactory = mockStrategyFactoryReturning(baseStrategy);
    var resultMapper = mockResultMapperReturning(actResult);

    // Act
    this.testAct.perform();

    // Assert
    verify(this.context.getEnvironment().mvc()).perform(requestBuilder);
    assertThat(this.context.getActResult(), is(actResult));

    strategyFactory.close();
    resultMapper.close();
  }

  @Test
  @SneakyThrows
  void GIVEN_throwOnMvcPerform_WHEN_perform_THEN_throwError() {
    // Arrange

    var requestBuilder = mockRequestBuilder();
    var baseStrategy = mockBaseStrategyReturning(requestBuilder);

    var strategyFactory = mockStrategyFactoryReturning(baseStrategy);
    mvcWillThrow(requestBuilder);
    var failedMsg = mockFailedErrorMessageReturning();

    // Act
    var exception = assertThrows(TestActFailedError.class, () -> this.testAct.perform());

    // Assert
    assertThat(exception.getMessage(), is("mockMessage"));

    strategyFactory.close();
    failedMsg.close();
  }

  @Test
  @SneakyThrows
  void GIVEN_no_arrange_WHEN_perform_THEN_throwError() {
    // Arrange
    this.context.setArrangeResult(null);

    var requestBuilder = mockRequestBuilder();
    var baseStrategy = mockBaseStrategyReturning(requestBuilder);

    var strategyFactory = mockStrategyFactoryReturning(baseStrategy);
    mvcWillThrow(requestBuilder);
    var failedMsg = mockFailedErrorMessageReturning();

    // Act
    var exception = assertThrows(IllegalStateException.class, () -> this.testAct.perform());

    // Assert
    assertThat(
        exception.getMessage(),
        is(
            "Step '<unnamed step>' · Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act().perform()'"));

    strategyFactory.close();
    failedMsg.close();
  }

  private MockHttpServletRequestBuilder mockRequestBuilder() {
    return Mockito.mock(MockHttpServletRequestBuilder.class);
  }

  private TestRequestBaseStrategy mockBaseStrategyReturning(MockHttpServletRequestBuilder builder) {
    var strategy = Mockito.mock(TestRequestBaseStrategy.class);
    Mockito.when(strategy.apply(any())).thenReturn(builder);
    return strategy;
  }

  private MockedStatic<TestRequestStrategyFactory> mockStrategyFactoryReturning(
      TestRequestBaseStrategy strategy) {
    var mocked = Mockito.mockStatic(TestRequestStrategyFactory.class);
    mocked.when(() -> TestRequestStrategyFactory.resolve(any())).thenReturn(strategy);
    return mocked;
  }

  private MockedStatic<TestActResultMapper> mockResultMapperReturning(TestActResult result) {
    var mocked = Mockito.mockStatic(TestActResultMapper.class);
    mocked.when(() -> TestActResultMapper.mapTo(any())).thenReturn(result);
    return mocked;
  }

  private MockedStatic<TestActFailedErrorMessage> mockFailedErrorMessageReturning() {
    var mocked = Mockito.mockStatic(TestActFailedErrorMessage.class);
    mocked.when(() -> TestActFailedErrorMessage.build(any(), any())).thenReturn("mockMessage");
    return mocked;
  }

  @SneakyThrows
  private void mvcWillThrow(MockHttpServletRequestBuilder builder) {
    Mockito.when(this.context.getEnvironment().mvc().perform(builder))
        .thenThrow(new Exception("test"));
  }
}
