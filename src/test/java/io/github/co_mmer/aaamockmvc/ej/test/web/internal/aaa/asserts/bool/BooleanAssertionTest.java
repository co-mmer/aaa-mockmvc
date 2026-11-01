package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class BooleanAssertionTest {

  private static final String NULL_ASSERTION_MESSAGE =
      "Assertion: The response Boolean value must be null.";
  private static final String NOT_NULL_ASSERTION_MESSAGE =
      "Assertion: The response Boolean value must not be null.";
  private static final String TRUE_ASSERTION_MESSAGE =
      "Assertion: The response Boolean value must be true.";
  private static final String FALSE_ASSERTION_MESSAGE =
      "Assertion: The response Boolean value must be false.";
  private static final String EQUALITY_ASSERTION_MESSAGE =
      "Assertion: The response Boolean value must equal the expected value.";
  private static final String STEP_NAME = "Load flag";

  @Nested
  class isNull {

    @Test
    void GIVEN_true_WHEN_isNull_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(true), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNull);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) null));
      assertThat(exception.getActual().getValue(), is((Object) true));
      assertThat(exception.getMessage(), containsString(NULL_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_false_WHEN_isNull_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(false), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNull);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) null));
      assertThat(exception.getActual().getValue(), is((Object) false));
      assertThat(exception.getMessage(), containsString(NULL_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_null_WHEN_isNull_THEN_success() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(null), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNull);
    }
  }

  @Nested
  class isNotNull {

    @Test
    void GIVEN_true_WHEN_isNotNull_THEN_success() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(true), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotNull);
    }

    @Test
    void GIVEN_false_WHEN_isNotNull_THEN_success() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(false), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotNull);
    }

    @Test
    void GIVEN_null_WHEN_isNotNull_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(null), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNotNull);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "not null"));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(NOT_NULL_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class isTrue {

    @Test
    void GIVEN_true_WHEN_isTrue_THEN_success() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(true), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isTrue);
    }

    @Test
    void GIVEN_false_WHEN_isTrue_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(false), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isTrue);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) true));
      assertThat(exception.getActual().getValue(), is((Object) false));
      assertThat(exception.getMessage(), containsString(TRUE_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_null_WHEN_isTrue_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(null), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isTrue);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) true));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(TRUE_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class isFalse {

    @Test
    void GIVEN_true_WHEN_isFalse_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(true), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isFalse);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) false));
      assertThat(exception.getActual().getValue(), is((Object) true));
      assertThat(exception.getMessage(), containsString(FALSE_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_false_WHEN_isFalse_THEN_success() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(false), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isFalse);
    }

    @Test
    void GIVEN_null_WHEN_isFalse_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(null), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isFalse);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) false));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(FALSE_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_true_WHEN_isEqualTo_true_THEN_success() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(true), null);
      var expected = BooleanValue.expected(true);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(expected));
    }

    @Test
    void GIVEN_true_WHEN_isEqualTo_false_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(true), null);
      var expected = BooleanValue.expected(false);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) false));
      assertThat(exception.getActual().getValue(), is((Object) true));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_false_WHEN_isEqualTo_true_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(false), null);
      var expected = BooleanValue.expected(true);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) true));
      assertThat(exception.getActual().getValue(), is((Object) false));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_false_WHEN_isEqualTo_false_THEN_success() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(false), null);
      var expected = BooleanValue.expected(false);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(expected));
    }

    @Test
    void GIVEN_null_WHEN_isEqualTo_true_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(null), null);
      var expected = BooleanValue.expected(true);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) true));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_null_WHEN_isEqualTo_false_THEN_assertion_failed() {
      // Arrange
      var assertion = new BooleanAssertion(new BooleanValue(null), null);
      var expected = BooleanValue.expected(false);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) false));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new BooleanAssertion(new BooleanValue(false), new TestStepDto(STEP_NAME));

    // Act
    var exception = assertThrows(AssertionFailedError.class, assertion::isTrue);

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertThat(exception.getMessage(), containsString(TRUE_ASSERTION_MESSAGE));
  }
}
