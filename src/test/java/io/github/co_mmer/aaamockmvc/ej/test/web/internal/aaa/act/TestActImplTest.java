package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext.mockContext;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutionResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute.MockMvcExecutor;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description.RequestDescription;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestActImplTest {

  private static final MockMvcExecutionResult ANY_EXECUTION_RESULT =
      new MockMvcExecutionResult(
          200,
          "{\"result\":\"ok\"}",
          new byte[] {1, 2, 3},
          Map.of("Content-Type", List.of("application/json")));
  private static final MockMvcExecutionException ANY_Exception =
      new MockMvcExecutionException("test");
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
    var request = this.context.getArrangeBuilder().build();

    var mockedExecutor = mockStatic(MockMvcExecutor.class);
    mockedExecutor
        .when(() -> MockMvcExecutor.execute(this.context.getEnvironment().mvc(), request))
        .thenReturn(ANY_EXECUTION_RESULT);

    // Act
    var result = this.testAct.perform();

    // Assert
    assertThat(result, sameInstance(this.testAct));

    var actResult = this.context.getActResult();
    assertThat(actResult.status(), is(ANY_EXECUTION_RESULT.status()));
    assertThat(actResult.headers(), is(ANY_EXECUTION_RESULT.headers()));
    assertThat(actResult.contentAsBytes(), is(ANY_EXECUTION_RESULT.contentAsBytes()));
    assertThat(actResult.contentAsString(), is(ANY_EXECUTION_RESULT.contentAsString()));

    mockedExecutor.verify(
        () -> MockMvcExecutor.execute(this.context.getEnvironment().mvc(), request));
    mockedExecutor.close();
  }

  @Test
  void GIVEN_throwException_WHEN_perform_THEN_throw_TestActFailedError() {
    // Arrange
    var request = this.context.getArrangeBuilder().build();

    var mockedExecutor = mockStatic(MockMvcExecutor.class);
    var mockedDescription = mockStatic(RequestDescription.class);

    mockedExecutor
        .when(() -> MockMvcExecutor.execute(this.context.getEnvironment().mvc(), request))
        .thenThrow(ANY_Exception);

    mockedDescription
        .when(() -> RequestDescription.describe(request))
        .thenReturn("RequestDescription");

    // Act
    var ex = assertThrows(TestActFailedError.class, () -> this.testAct.perform());

    // Assert
    assertThat(ex, is(notNullValue()));

    mockedExecutor.verify(
        () -> MockMvcExecutor.execute(this.context.getEnvironment().mvc(), request));
    mockedExecutor.close();
    mockedDescription.close();
  }
}
