package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.step.TestStepImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.step.TestStepPreconditionsValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestStepPreconditionsValidatorTest {

  private TestStepImpl mockStepImpl;
  private TestAAAContext mockAAAContext;

  @BeforeEach
  void setUp() {
    this.mockStepImpl = mock(TestStepImpl.class);
    this.mockAAAContext = mock(TestAAAContext.class);
    when(this.mockStepImpl.getContext()).thenReturn(this.mockAAAContext);
    when(this.mockAAAContext.getArrangeResult()).thenReturn(null);
  }

  @Test
  void WHEN_act_with_context_without_current_step_THEN_message_has_no_prefix() {
    // Arrange
    when(this.mockAAAContext.getStep()).thenReturn(null);

    // Act
    var exception =
        assertThrows(
            IllegalStateException.class,
            () -> TestStepPreconditionsValidator.act(this.mockStepImpl));

    // Assert
    assertThat(
        exception.getMessage(),
        is(
            "Act error: No 'arrange()' step configured. "
                + "Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
  }

  @Test
  void WHEN_act_with_context_with_current_step_THEN_message_is_prefixed_with_step_name() {
    // Arrange
    var currentStep = mock(TestStepDto.class);
    when(currentStep.name()).thenReturn("MyStep");
    when(this.mockAAAContext.getStep()).thenReturn(currentStep);

    // Act
    var ex =
        assertThrows(
            IllegalStateException.class, () -> TestStepPreconditionsValidator.act(mockStepImpl));

    // Assert
    assertThat(
        ex.getMessage(),
        is(
            "Step 'MyStep' ⇒ Act error: No 'arrange()' step configured. "
                + "Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'"));
  }
}
