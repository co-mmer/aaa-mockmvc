package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error.TestPreconditionsValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.section.TestStepImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class TestStepImplTest {

  private final TestAAAContext context = Mockito.mock(TestAAAContext.class);
  private final TestStepImpl step = new TestStepImpl(context);
  private MockedStatic<TestPreconditionsValidator> mocked;

  @BeforeEach
  void setUp() {
    this.mocked = Mockito.mockStatic(TestPreconditionsValidator.class);
  }

  @AfterEach
  void clean() {
    this.mocked.close();
  }

  @Nested
  class ArrangeMethod {

    @Test
    void GIVEN_preconditions_pass_WHEN_arrange_THEN_returns_TestArrange() {
      // Act
      var arrange = step.arrange();

      // Assert
      mocked.verify(() -> TestPreconditionsValidator.arrange(context));
      assertThat(arrange, is(notNullValue()));
      assertThat(arrange, instanceOf(TestArrange.class));
    }

    @Test
    void GIVEN_preconditions_fail_WHEN_arrange_THEN_throwException() {
      // Arrange
      mocked
          .when(() -> TestPreconditionsValidator.arrange(context))
          .thenThrow(new IllegalStateException("Arrange error"));

      // Act
      var exception = assertThrows(IllegalStateException.class, step::arrange);

      // Assert
      assertThat(exception.getMessage(), is("Arrange error"));
      mocked.verify(() -> TestPreconditionsValidator.arrange(context));
    }
  }

  @Nested
  class ActMethod {

    @Test
    void GIVEN_preconditions_pass_WHEN_act_THEN_returns_TestAct() {
      // Act
      TestAct act = step.act();

      // Assert
      mocked.verify(() -> TestPreconditionsValidator.act(context));
      assertThat(act, is(notNullValue()));
      assertThat(act, instanceOf(TestAct.class));
    }

    @Test
    void GIVEN_preconditions_fail_WHEN_act_THEN_throwException() {
      // Arrange
      mocked
          .when(() -> TestPreconditionsValidator.act(context))
          .thenThrow(new IllegalStateException("Act error"));

      // Act
      var ex = assertThrows(IllegalStateException.class, step::act);

      // Assert
      assertThat(ex.getMessage(), is("Act error"));
    }
  }

  @Nested
  class AssertsMethod {

    @Test
    void GIVEN_preconditions_pass_WHEN_asserts_THEN_returns_TestAssert() {
      // Act
      TestAssert asserts = step.asserts();

      // Assert
      mocked.verify(() -> TestPreconditionsValidator.asserts(context));
      assertThat(asserts, is(notNullValue()));
      assertThat(asserts, instanceOf(TestAssert.class));
    }

    @Test
    void GIVEN_preconditions_fail_WHEN_asserts_THEN_throwException() {
      // Arrange
      mocked
          .when(() -> TestPreconditionsValidator.asserts(context))
          .thenThrow(new IllegalStateException("Assert error"));

      // Act
      var ex = assertThrows(IllegalStateException.class, step::asserts);

      // Assert
      assertThat(ex.getMessage(), is("Assert error"));
      mocked.verify(() -> TestPreconditionsValidator.asserts(context));
    }
  }

  @Nested
  class AnswerMethod {

    @Test
    void GIVEN_preconditions_pass_WHEN_answer_THEN_returns_TestAnswer() {
      // Act
      TestAnswer answer = step.answer();

      // Assert
      mocked.verify(() -> TestPreconditionsValidator.answer(context));
      assertThat(answer, is(notNullValue()));
      assertThat(answer, instanceOf(TestAnswer.class));
    }

    @Test
    void GIVEN_preconditions_fail_WHEN_answer_THEN_throwException() {
      // Arrange
      mocked
          .when(() -> TestPreconditionsValidator.answer(context))
          .thenThrow(new IllegalStateException("Answer error"));

      // Act
      var ex = assertThrows(IllegalStateException.class, step::answer);

      // Assert
      assertThat(ex.getMessage(), is("Answer error"));
      mocked.verify(() -> TestPreconditionsValidator.answer(context));
    }
  }
}
