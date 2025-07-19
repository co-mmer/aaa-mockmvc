package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class TestAAAContextTest {

  @Test
  @SuppressWarnings({"rawtypes"})
  void WHEN_setters_called_THEN_getters_return_values() {
    // Arrange
    var environment = mock(TestEnvironment.class);
    var context = new TestAAAContext(environment);
    var arrange = new TestArrangeResult();
    var act = mock(TestActResult.class);
    var assertResult = (TestAssertResult) mock(TestAssertResult.class);
    var answerResult = new TestAnswerResult<>("answer");

    // Act
    context.setArrangeResult(arrange);
    context.setActResult(act);
    context.setAssertResult(assertResult);
    context.setAnswerResult(answerResult);

    // Assert
    assertAll(
        () -> assertThat(context.getEnvironment(), is(environment)),
        () -> assertThat(context.getArrangeResult(), is(arrange)),
        () -> assertThat(context.getActResult(), is(act)),
        () -> assertThat(context.getAssertResult(), is(assertResult)),
        () -> assertThat(context.getAnswerResult(), is(answerResult)));
  }
}
