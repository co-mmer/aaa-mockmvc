package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class StringAssertionTest {

  private static final StringValue ACTUAL_EMPTY = new StringValue("");
  private static final StringValue ACTUAL_A = new StringValue("A");
  private static final StringValue ACTUAL_AB = new StringValue("AB");
  private static final StringValue ACTUAL_WHITESPACE = new StringValue(" ");
  private static final StringValue ACTUAL_DECOMPOSED_E_ACUTE = new StringValue("e\u0301");

  private static final StringValue EXPECTED_EMPTY = new StringValue("");
  private static final StringValue EXPECTED_A = new StringValue("A");
  private static final StringValue EXPECTED_B = new StringValue("B");
  private static final StringValue EXPECTED_LOWERCASE_A = new StringValue("a");
  private static final StringValue EXPECTED_A_WITH_TRAILING_SPACE = new StringValue("A ");
  private static final StringValue EXPECTED_COMPOSED_E_ACUTE = new StringValue("é");

  private static final String EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response text must contain zero characters.";
  private static final String NOT_EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response text must contain at least one character.";
  private static final String LENGTH_ASSERTION_MESSAGE =
      "Assertion: The response text must have the expected length.";
  private static final String EQUALITY_ASSERTION_MESSAGE =
      "Assertion: The response text must equal the expected text.";
  private static final String STEP_NAME = "Load text";

  private void assertFailure(
      AssertionFailedError exception, Object expected, Object actual, String assertionMessage) {
    assertThat(exception.getExpected().getValue(), is(expected));
    assertThat(exception.getActual().getValue(), is(actual));
    assertThat(exception.getMessage(), containsString(assertionMessage));
  }

  @Nested
  class isEmpty {

    @Test
    void GIVEN_empty_WHEN_isEmpty_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isEmpty);
    }

    @Test
    void GIVEN_text_WHEN_isEmpty_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_A, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

      // Assert
      assertFailure(exception, "empty", ACTUAL_A.value(), EMPTY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_whitespace_WHEN_isEmpty_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_WHITESPACE, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

      // Assert
      assertFailure(exception, "empty", ACTUAL_WHITESPACE.value(), EMPTY_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_text_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_A, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotEmpty);
    }

    @Test
    void GIVEN_whitespace_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_WHITESPACE, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotEmpty);
    }

    @Test
    void GIVEN_empty_WHEN_isNotEmpty_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_EMPTY, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNotEmpty);

      // Assert
      assertFailure(exception, "not empty", ACTUAL_EMPTY.value(), NOT_EMPTY_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class hasLength {

    @Test
    void GIVEN_matching_length_WHEN_hasLength_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_AB, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasLength(2));
    }

    @Test
    void GIVEN_empty_matching_length_WHEN_hasLength_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasLength(0));
    }

    @Test
    void GIVEN_different_length_WHEN_hasLength_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_AB, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasLength(1));

      // Assert
      assertFailure(exception, 1, ACTUAL_AB.value().length(), LENGTH_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_different_length_WHEN_hasLength_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_EMPTY, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasLength(1));

      // Assert
      assertFailure(exception, 1, ACTUAL_EMPTY.value().length(), LENGTH_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_whitespace_matching_length_WHEN_hasLength_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_WHITESPACE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasLength(1));
    }

    @Test
    void GIVEN_decomposed_unicode_original_length_WHEN_hasLength_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_DECOMPOSED_E_ACUTE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasLength(2));
    }

    @Test
    void GIVEN_decomposed_unicode_normalized_length_WHEN_hasLength_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_DECOMPOSED_E_ACUTE, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasLength(1));

      // Assert
      assertFailure(
          exception, 1, ACTUAL_DECOMPOSED_E_ACUTE.value().length(), LENGTH_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_same_text_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_A));
    }

    @Test
    void GIVEN_different_text_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_B));

      // Assert
      assertFailure(exception, EXPECTED_B.value(), ACTUAL_A.value(), EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_strings_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_EMPTY));
    }

    @Test
    void GIVEN_empty_actual_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_EMPTY, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_A));

      // Assert
      assertFailure(
          exception, EXPECTED_A.value(), ACTUAL_EMPTY.value(), EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_expected_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_EMPTY));

      // Assert
      assertFailure(
          exception, EXPECTED_EMPTY.value(), ACTUAL_A.value(), EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_different_case_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_LOWERCASE_A));

      // Assert
      assertFailure(
          exception, EXPECTED_LOWERCASE_A.value(), ACTUAL_A.value(), EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_additional_whitespace_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.isEqualTo(EXPECTED_A_WITH_TRAILING_SPACE));

      // Assert
      assertFailure(
          exception,
          EXPECTED_A_WITH_TRAILING_SPACE.value(),
          ACTUAL_A.value(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_DECOMPOSED_E_ACUTE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_COMPOSED_E_ACUTE));
    }

    @Test
    void GIVEN_unicode_mismatch_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new StringAssertion(ACTUAL_DECOMPOSED_E_ACUTE, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_B));

      // Assert
      assertFailure(
          exception,
          EXPECTED_B.value(),
          ACTUAL_DECOMPOSED_E_ACUTE.value(),
          EQUALITY_ASSERTION_MESSAGE);
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new StringAssertion(ACTUAL_A, new TestStepDto(STEP_NAME));

    // Act
    var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertFailure(exception, "empty", ACTUAL_A.value(), EMPTY_ASSERTION_MESSAGE);
  }
}
