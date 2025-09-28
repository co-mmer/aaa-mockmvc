package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.validator;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.validator.TestActPreconditionsValidator.verifyPerform;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestActPreconditionsValidatorTest {

  private TestAAAContext mockAAAContext;

  @BeforeEach
  void setUp() {
    this.mockAAAContext = mock(TestAAAContext.class);
    when(this.mockAAAContext.getArrangeResult()).thenReturn(null);
  }

  @Test
  void WHEN_act_with_context_without_current_step_THEN_message_has_no_prefix() {
    // Arrange
    when(this.mockAAAContext.getStep()).thenReturn(null);

    // Act
    var ex = assertThrows(IllegalStateException.class, () -> verifyPerform(this.mockAAAContext));

    // Assert
    var expected =
        """
            Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act().perform()'
            """;
    assertThat(ex.getMessage(), is(expected));
  }

  @Test
  void WHEN_act_with_context_with_current_step_THEN_message_is_prefixed_with_step_name() {
    // Arrange
    var currentStep = mock(TestStepDto.class);
    when(currentStep.name()).thenReturn("MyStep");
    when(this.mockAAAContext.getStep()).thenReturn(currentStep);

    // Act
    var ex = assertThrows(IllegalStateException.class, () -> verifyPerform(this.mockAAAContext));

    // Assert
    var expected =
        """
            Step 'MyStep'
            Act error: No 'arrange()' step configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act().perform()'
            """;
    assertThat(ex.getMessage(), is(expected));
  }
}
