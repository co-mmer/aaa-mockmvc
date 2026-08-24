package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.TestArrangeBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepValidator;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestStepValidatorTest {

  private static final String MSG_ACT_NO_ARRANGE =
      "Act error: No 'arrange()' step configured. "
          + "Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'";

  private static final String MSG_ASSERTS_NO_ARRANGE_ACT =
      "Assert error: No 'arrange()' / 'act()' steps configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' followed by 'act().perform()' before 'asserts()'";

  private static final String MSG_ANSWER_NO_ARRANGE_ACT =
      "Answer error: No 'arrange()' / 'act()' steps configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' followed by 'act().perform()' before 'answer()'";

  private TestStepImpl mockStep(String stepName, TestArrangeBuilder arrange, TestActResult act) {
    var step = mock(TestStepImpl.class);
    var context = mock(TestAAAContext.class);

    when(step.getContext()).thenReturn(context);

    if (stepName == null) {
      when(context.getStep()).thenReturn(null);
    } else {
      var dto = mock(TestStepDto.class);
      when(dto.name()).thenReturn(stepName);
      when(context.getStep()).thenReturn(dto);
    }

    when(context.getArrangeBuilder()).thenReturn(arrange);
    when(context.getActResult()).thenReturn(act);
    return step;
  }

  private static String prefixed(String stepName, String base) {
    return stepName == null ? base : "Step '" + stepName + "' ⇒ " + base;
  }

  @Nested
  class PreconditionsOfAct {

    @Test
    void WHEN_step_is_null_THEN_internal_message_without_prefix() {
      // Act
      var ex =
          assertThrows(
              IllegalStateException.class, () -> TestStepValidator.preconditionsOfAct(null));

      // Assert
      assertThat(ex.getMessage(), is(MSG_ACT_NO_ARRANGE));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"MyStep"})
    void WHEN_arrange_missing_THEN_custom_message_with_optional_prefix(String stepName) {
      // Arrange
      var step = mockStep(stepName, null, null);

      // Act
      var ex =
          assertThrows(
              IllegalStateException.class, () -> TestStepValidator.preconditionsOfAct(step));

      // Assert
      assertThat(ex.getMessage(), is(prefixed(stepName, MSG_ACT_NO_ARRANGE)));
    }

    @Test
    void WHEN_arrange_present_THEN_no_exception() {
      // Arrange
      var arrange = new TestArrangeBuilder();
      var step = mockStep("Any", arrange, null);

      // Act & Assert
      assertDoesNotThrow(() -> TestStepValidator.preconditionsOfAct(step));
    }
  }

  @Nested
  class PreconditionsOfAsserts {

    @Test
    void WHEN_step_is_null_THEN_internal_message_without_prefix() {
      // Act
      var ex =
          assertThrows(
              IllegalStateException.class, () -> TestStepValidator.preconditionsOfAsserts(null));

      // Assert
      assertThat(ex.getMessage(), is(MSG_ASSERTS_NO_ARRANGE_ACT));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"MyAssertsStep"})
    void WHEN_act_missing_THEN_custom_message_with_optional_prefix(String stepName) {
      // Arrange
      var arrange = new TestArrangeBuilder();
      var step = mockStep(stepName, arrange, null);

      // Act
      var ex =
          assertThrows(
              IllegalStateException.class, () -> TestStepValidator.preconditionsOfAsserts(step));

      // Assert
      assertThat(ex.getMessage(), is(prefixed(stepName, MSG_ASSERTS_NO_ARRANGE_ACT)));
    }

    @Test
    void WHEN_act_present_THEN_no_exception() {
      // Act
      var act = new TestActResult(200, Map.of(), "ok".getBytes(StandardCharsets.UTF_8), "ok");
      var step = mockStep("Any", new TestArrangeBuilder(), act);

      // Assert
      assertDoesNotThrow(() -> TestStepValidator.preconditionsOfAsserts(step));
    }
  }

  @Nested
  class PreconditionsOfAnswer {

    @Test
    void WHEN_step_is_null_THEN_internal_message_without_prefix() {
      // Act
      var ex =
          assertThrows(
              IllegalStateException.class, () -> TestStepValidator.preconditionsOfAnswer(null));

      // Assert
      assertThat(ex.getMessage(), is(MSG_ANSWER_NO_ARRANGE_ACT));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"MyAnswerStep"})
    void WHEN_act_missing_THEN_custom_message_with_optional_prefix(String stepName) {
      // Arrange
      var arrange = new TestArrangeBuilder();
      var step = mockStep(stepName, arrange, null);

      // Act
      var ex =
          assertThrows(
              IllegalStateException.class, () -> TestStepValidator.preconditionsOfAnswer(step));

      // Assert
      assertThat(ex.getMessage(), is(prefixed(stepName, MSG_ANSWER_NO_ARRANGE_ACT)));
    }

    @Test
    void WHEN_act_present_THEN_no_exception() {
      // Arrange
      var act =
          new TestActResult(
              204, Map.of("X-Test", List.of("1")), new byte[0], new String(new byte[0]));
      var step = mockStep(null, new TestArrangeBuilder(), act);

      // Act & Assert
      assertDoesNotThrow(() -> TestStepValidator.preconditionsOfAnswer(step));
    }
  }
}
