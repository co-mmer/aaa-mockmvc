package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TestAssertTest {

  private static final TestStepDto STEP = new TestStepDto("Login");

  @Test
  void GIVEN_matchingValue_WHEN_assertThat_THEN_success() {
    // Act & Assert
    assertThat(STEP, 200, is(200));
  }

  @Test
  void GIVEN_nonMatchingValue_WHEN_assertThat_THEN_message_contain_reason() {
    // Arrange
    Executable executable = () -> assertThat(STEP, 500, is(200));

    // Act
    var error = assertThrows(AssertionError.class, executable);

    // Assert
    MatcherAssert.assertThat(error.getMessage(), containsString("Login"));
  }

  @Test
  void GIVEN_what_WHEN_assertThat_THEN_message_contain_reason_and_what() {
    // Arrange
    Executable executable = () -> assertThat(STEP, "status", 404, is(200));

    // Act
    var error = assertThrows(AssertionError.class, executable);

    // Assert
    MatcherAssert.assertThat(error.getMessage(), containsString("Step 'Login' ⇒ status"));
  }

  @Test
  void GIVEN_nullStep_nonMatchingValue_WHEN_assertThat_THEN_message_without_step_reason() {
    // Arrange
    Executable executable = () -> assertThat(null, 5, is(3));

    // Act
    var error = assertThrows(AssertionError.class, executable);

    // Assert
    MatcherAssert.assertThat(error.getMessage(), not(containsString("Step '")));
  }
}
