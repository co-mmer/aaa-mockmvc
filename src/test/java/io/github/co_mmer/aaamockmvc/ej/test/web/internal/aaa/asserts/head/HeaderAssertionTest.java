package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class HeaderAssertionTest {

  private static final String HEADER_NAME = "X-Test";
  private static final List<String> VALUES_EMPTY = List.of();
  private static final List<String> VALUES_A = List.of("A");
  private static final List<String> VALUES_A_B = List.of("A", "B");
  private static final List<String> VALUES_A_A_B = List.of("A", "A", "B");
  private static final HeaderName EXPECTED_NAME = new HeaderName(HEADER_NAME);
  private static final HeaderName MISSING_NAME = new HeaderName("X-Missing");
  private static final HeaderName COMPOSED_UNICODE_NAME = new HeaderName("é");
  private static final HeaderEntryValue EXPECTED_VALUE_A = new HeaderEntryValue("A");
  private static final HeaderEntryValue EXPECTED_VALUE_B = new HeaderEntryValue("B");
  private static final HeaderEntryValue EXPECTED_COMPOSED_VALUE = new HeaderEntryValue("ö");
  private static final HeaderEntryValues EXPECTED_VALUES_EMPTY = new HeaderEntryValues();
  private static final HeaderEntryValues EXPECTED_VALUES_A = new HeaderEntryValues("A");
  private static final HeaderEntryValues EXPECTED_VALUES_A_B = new HeaderEntryValues("A", "B");
  private static final HeaderEntryValues EXPECTED_VALUES_B_A = new HeaderEntryValues("B", "A");
  private static final HeaderEntryValues EXPECTED_VALUES_B_A_A =
      new HeaderEntryValues("B", "A", "A");
  private static final HeaderEntryValues EXPECTED_VALUES_A_B_B =
      new HeaderEntryValues("A", "B", "B");
  private static final HeaderEntryValues EXPECTED_COMPOSED_VALUES = new HeaderEntryValues("ö");
  private static final HeaderValue ACTUAL_EMPTY = new HeaderValue(Map.of());
  private static final HeaderValue ACTUAL_EMPTY_VALUES =
      new HeaderValue(Map.of(HEADER_NAME, VALUES_EMPTY));
  private static final HeaderValue ACTUAL_A = new HeaderValue(Map.of(HEADER_NAME, VALUES_A));
  private static final HeaderValue ACTUAL_A_B = new HeaderValue(Map.of(HEADER_NAME, VALUES_A_B));
  private static final HeaderValue ACTUAL_A_A_B =
      new HeaderValue(Map.of(HEADER_NAME, VALUES_A_A_B));
  private static final HeaderValue ACTUAL_DECOMPOSED_UNICODE =
      new HeaderValue(Map.of("e\u0301", List.of("o\u0308")));

  private static final String CONTAINS_KEY_ASSERTION_MESSAGE =
      "Assertion: The response must contain header 'X-Test'.";
  private static final String MISSING_KEY_ASSERTION_MESSAGE =
      "Assertion: The response must contain header 'X-Missing'.";
  private static final String ABSENT_KEY_ASSERTION_MESSAGE =
      "Assertion: Header 'X-Test' must be absent from the response.";
  private static final String ENTRY_ASSERTION_MESSAGE =
      "Assertion: Header 'X-Test' must include the expected value; additional values are allowed.";
  private static final String EXACT_ENTRY_ASSERTION_MESSAGE =
      "Assertion: Header 'X-Test' must contain exactly the expected values with the same occurrence counts; value order is ignored.";
  private static final String INVALID_EMPTY_VALUES_MESSAGE =
      "containsEntryExactly() requires at least one expected header value.";
  private static final String STEP_NAME = "Load headers";

  private void assertFailure(
      AssertionFailedError exception, Object expected, Object actual, String assertionMessage) {
    assertThat(exception.getExpected().getValue(), is(expected));
    assertThat(exception.getActual().getValue(), is(actual));
    assertThat(exception.getMessage(), containsString(assertionMessage));
  }

  @Nested
  class containsKey {

    @Test
    void GIVEN_present_key_WHEN_containsKey_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsKey(EXPECTED_NAME));
    }

    @Test
    void GIVEN_missing_key_WHEN_containsKey_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.containsKey(MISSING_NAME));

      // Assert
      assertFailure(
          exception, MISSING_NAME.value(), ACTUAL_A.values(), MISSING_KEY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_headers_WHEN_containsKey_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.containsKey(EXPECTED_NAME));

      // Assert
      assertFailure(
          exception, EXPECTED_NAME.value(), ACTUAL_EMPTY.values(), CONTAINS_KEY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_key_with_empty_values_WHEN_containsKey_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY_VALUES, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsKey(EXPECTED_NAME));
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_containsKey_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_DECOMPOSED_UNICODE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsKey(COMPOSED_UNICODE_NAME));
    }
  }

  @Nested
  class doesNotContainKey {

    @Test
    void GIVEN_missing_key_WHEN_doesNotContainKey_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.doesNotContainKey(MISSING_NAME));
    }

    @Test
    void GIVEN_empty_headers_WHEN_doesNotContainKey_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.doesNotContainKey(EXPECTED_NAME));
    }

    @Test
    void GIVEN_present_key_WHEN_doesNotContainKey_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.doesNotContainKey(EXPECTED_NAME));

      // Assert
      assertFailure(
          exception, EXPECTED_NAME.value(), ACTUAL_A.values(), ABSENT_KEY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_key_with_empty_values_WHEN_doesNotContainKey_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY_VALUES, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.doesNotContainKey(EXPECTED_NAME));

      // Assert
      assertFailure(
          exception,
          EXPECTED_NAME.value(),
          ACTUAL_EMPTY_VALUES.values(),
          ABSENT_KEY_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class containsEntry {

    @Test
    void GIVEN_matching_value_WHEN_containsEntry_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsEntry(EXPECTED_NAME, EXPECTED_VALUE_A));
    }

    @Test
    void GIVEN_additional_values_WHEN_containsEntry_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsEntry(EXPECTED_NAME, EXPECTED_VALUE_A));
    }

    @Test
    void GIVEN_different_value_WHEN_containsEntry_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntry(EXPECTED_NAME, EXPECTED_VALUE_B));

      // Assert
      assertFailure(exception, EXPECTED_VALUE_B.value(), VALUES_A, ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_missing_key_WHEN_containsEntry_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntry(EXPECTED_NAME, EXPECTED_VALUE_A));

      // Assert
      assertFailure(exception, EXPECTED_VALUE_A.value(), null, ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_values_WHEN_containsEntry_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY_VALUES, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntry(EXPECTED_NAME, EXPECTED_VALUE_A));

      // Assert
      assertFailure(exception, EXPECTED_VALUE_A.value(), VALUES_EMPTY, ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_containsEntry_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_DECOMPOSED_UNICODE, null);

      // Act & Assert
      assertDoesNotThrow(
          () -> assertion.containsEntry(COMPOSED_UNICODE_NAME, EXPECTED_COMPOSED_VALUE));
    }
  }

  @Nested
  class containsEntryExactly {

    @Test
    void GIVEN_matching_values_WHEN_containsEntryExactly_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_A_B));
    }

    @Test
    void GIVEN_different_order_WHEN_containsEntryExactly_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_B_A));
    }

    @Test
    void GIVEN_same_repetitions_WHEN_containsEntryExactly_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A_A_B, null);

      // Act & Assert
      assertDoesNotThrow(
          () -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_B_A_A));
    }

    @Test
    void GIVEN_different_repetitions_WHEN_containsEntryExactly_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A_A_B, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_A_B_B));

      // Assert
      assertFailure(
          exception, EXPECTED_VALUES_A_B_B.values(), VALUES_A_A_B, EXACT_ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_additional_actual_values_WHEN_containsEntryExactly_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A_B, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_A));

      // Assert
      assertFailure(
          exception, EXPECTED_VALUES_A.values(), VALUES_A_B, EXACT_ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_missing_actual_value_WHEN_containsEntryExactly_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_A_B));

      // Assert
      assertFailure(
          exception, EXPECTED_VALUES_A_B.values(), VALUES_A, EXACT_ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_missing_key_WHEN_containsEntryExactly_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_A));

      // Assert
      assertFailure(exception, EXPECTED_VALUES_A.values(), null, EXACT_ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_actual_values_WHEN_containsEntryExactly_THEN_assertion_failed() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_EMPTY_VALUES, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_A));

      // Assert
      assertFailure(
          exception, EXPECTED_VALUES_A.values(), VALUES_EMPTY, EXACT_ENTRY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_expected_values_WHEN_containsEntryExactly_THEN_invalid_assertion() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_A, null);

      // Act
      var exception =
          assertThrows(
              InvalidAssertionException.class,
              () -> assertion.containsEntryExactly(EXPECTED_NAME, EXPECTED_VALUES_EMPTY));

      // Assert
      assertThat(exception.getMessage(), is(INVALID_EMPTY_VALUES_MESSAGE));
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_containsEntryExactly_THEN_success() {
      // Arrange
      var assertion = new HeaderAssertion(ACTUAL_DECOMPOSED_UNICODE, null);

      // Act & Assert
      assertDoesNotThrow(
          () -> assertion.containsEntryExactly(COMPOSED_UNICODE_NAME, EXPECTED_COMPOSED_VALUES));
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new HeaderAssertion(ACTUAL_EMPTY, new TestStepDto(STEP_NAME));

    // Act
    var exception =
        assertThrows(AssertionFailedError.class, () -> assertion.containsKey(EXPECTED_NAME));

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertFailure(
        exception, EXPECTED_NAME.value(), ACTUAL_EMPTY.values(), CONTAINS_KEY_ASSERTION_MESSAGE);
  }
}
