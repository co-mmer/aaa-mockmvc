package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class MapAssertionTest {

  private static final MapValue<String, String> ACTUAL_MAP_EMPTY = new MapValue<>(Map.of());
  private static final MapValue<String, String> ACTUAL_MAP_A_1 = new MapValue<>(Map.of("A", "1"));
  private static final MapValue<String, String> ACTUAL_MAP_A_1_B_2 =
      new MapValue<>(Map.of("A", "1", "B", "2"));
  private static final MapValue<String, String> ACTUAL_MAP_DECOMPOSED_UNICODE =
      new MapValue<>(Map.of("e\u0301", "o\u0308"));
  private static final MapValue<String, String> ACTUAL_MAP_NULL_VALUE =
      new MapValue<>(Collections.singletonMap("A", null));
  private static final MapValue<String, String> ACTUAL_MAP_NULL_KEY =
      new MapValue<>(Collections.singletonMap(null, "1"));

  private static final MapValue<String, String> EXPECTED_MAP_EMPTY = new MapValue<>(Map.of());
  private static final MapValue<String, String> EXPECTED_MAP_A_1 = new MapValue<>(Map.of("A", "1"));
  private static final MapValue<String, String> EXPECTED_MAP_A_2 = new MapValue<>(Map.of("A", "2"));
  private static final MapValue<String, String> EXPECTED_MAP_B_1 = new MapValue<>(Map.of("B", "1"));
  private static final MapValue<String, String> EXPECTED_MAP_A_1_B_2 =
      new MapValue<>(Map.of("A", "1", "B", "2"));
  private static final MapValue<String, String> EXPECTED_MAP_COMPOSED_UNICODE =
      new MapValue<>(Map.of("é", "ö"));
  private static final MapValue<String, String> EXPECTED_MAP_NULL_VALUE =
      new MapValue<>(Collections.singletonMap("A", null));
  private static final MapValue<String, String> EXPECTED_MAP_NULL_KEY =
      new MapValue<>(Collections.singletonMap(null, "1"));

  private static final String EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response map must contain no entries.";
  private static final String NOT_EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response map must contain at least one entry.";
  private static final String SIZE_ASSERTION_MESSAGE =
      "Assertion: The response map must contain the expected number of entries.";
  private static final String EQUALITY_ASSERTION_MESSAGE =
      "Assertion: The response map must contain exactly the expected key-value pairs; entry order is ignored.";
  private static final String STEP_NAME = "Load map";

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
      var assertion = new MapAssertion<>(ACTUAL_MAP_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isEmpty);
    }

    @Test
    void GIVEN_non_empty_WHEN_isEmpty_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

      // Assert
      assertFailure(exception, "empty", ACTUAL_MAP_A_1.values(), EMPTY_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_non_empty_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotEmpty);
    }

    @Test
    void GIVEN_empty_WHEN_isNotEmpty_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_EMPTY, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNotEmpty);

      // Assert
      assertFailure(exception, "not empty", ACTUAL_MAP_EMPTY.values(), NOT_EMPTY_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class hasSize {

    @Test
    void GIVEN_matching_size_WHEN_hasSize_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1_B_2, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasSize(2));
    }

    @Test
    void GIVEN_empty_matching_size_WHEN_hasSize_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasSize(0));
    }

    @Test
    void GIVEN_different_size_WHEN_hasSize_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1_B_2, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasSize(1));

      // Assert
      assertFailure(exception, 1, ACTUAL_MAP_A_1_B_2.values().size(), SIZE_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_different_size_WHEN_hasSize_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_EMPTY, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasSize(1));

      // Assert
      assertFailure(exception, 1, ACTUAL_MAP_EMPTY.values().size(), SIZE_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_same_entries_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_MAP_A_1));
    }

    @Test
    void GIVEN_different_value_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_MAP_A_2));

      // Assert
      assertFailure(
          exception,
          EXPECTED_MAP_A_2.values(),
          ACTUAL_MAP_A_1.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_different_key_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_MAP_B_1));

      // Assert
      assertFailure(
          exception,
          EXPECTED_MAP_B_1.values(),
          ACTUAL_MAP_A_1.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_additional_entry_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1_B_2, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_MAP_A_1));

      // Assert
      assertFailure(
          exception,
          EXPECTED_MAP_A_1.values(),
          ACTUAL_MAP_A_1_B_2.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_missing_entry_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_MAP_A_1_B_2));

      // Assert
      assertFailure(
          exception,
          EXPECTED_MAP_A_1_B_2.values(),
          ACTUAL_MAP_A_1.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_maps_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_MAP_EMPTY));
    }

    @Test
    void GIVEN_empty_actual_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_EMPTY, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_MAP_A_1));

      // Assert
      assertFailure(
          exception,
          EXPECTED_MAP_A_1.values(),
          ACTUAL_MAP_EMPTY.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_expected_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_MAP_EMPTY));

      // Assert
      assertFailure(
          exception,
          EXPECTED_MAP_EMPTY.values(),
          ACTUAL_MAP_A_1.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_DECOMPOSED_UNICODE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_MAP_COMPOSED_UNICODE));
    }

    @Test
    void GIVEN_equal_null_values_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_NULL_VALUE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_MAP_NULL_VALUE));
    }

    @Test
    void GIVEN_null_value_differs_from_missing_key_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_NULL_VALUE, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_MAP_EMPTY));

      // Assert
      assertFailure(
          exception,
          EXPECTED_MAP_EMPTY.values(),
          ACTUAL_MAP_NULL_VALUE.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_equal_null_keys_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new MapAssertion<>(ACTUAL_MAP_NULL_KEY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_MAP_NULL_KEY));
    }

    @Test
    void GIVEN_different_entry_order_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var actual = new LinkedHashMap<String, String>();
      actual.put("A", "1");
      actual.put("B", "2");

      var expected = new LinkedHashMap<String, String>();
      expected.put("B", "2");
      expected.put("A", "1");

      var assertion = new MapAssertion<>(new MapValue<>(actual), null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(new MapValue<>(expected)));
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new MapAssertion<>(ACTUAL_MAP_A_1, new TestStepDto(STEP_NAME));

    // Act
    var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertThat(exception.getMessage(), containsString(EMPTY_ASSERTION_MESSAGE));
  }
}
