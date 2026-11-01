package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.Conditions;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class CollectionAssertionTest {

  private static final CollectionValue<String> ACTUAL_LIST_EMPTY = new CollectionValue<>(List.of());
  private static final CollectionValue<String> ACTUAL_LIST_A = new CollectionValue<>(List.of("A"));
  private static final CollectionValue<String> ACTUAL_LIST_A_B =
      new CollectionValue<>(List.of("A", "B"));
  private static final CollectionValue<String> ACTUAL_LIST_A_A_B =
      new CollectionValue<>(List.of("A", "A", "B"));
  private static final CollectionValue<String> ACTUAL_ORDERED_SET_A_B =
      new CollectionValue<>(new LinkedHashSet<>(List.of("A", "B")));
  private static final CollectionValue<String> ACTUAL_SET_A = new CollectionValue<>(Set.of("A"));
  private static final CollectionValue<String> ACTUAL_LIST_DECOMPOSED_E_ACUTE =
      new CollectionValue<>(List.of("e\u0301"));
  private static final CollectionValue<String> ACTUAL_SET_A_B =
      new CollectionValue<>(Set.of("A", "B"));
  private static final CollectionValue<String> ACTUAL_LIST_A_AA =
      new CollectionValue<>(List.of("A", "AA"));

  private static final CollectionValue<String> EXPECTED_LIST_A_B =
      new CollectionValue<>(List.of("A", "B"));
  private static final CollectionValue<String> EXPECTED_LIST_B_A =
      new CollectionValue<>(List.of("B", "A"));
  private static final CollectionValue<String> EXPECTED_LIST_A_B_B =
      new CollectionValue<>(List.of("A", "B", "B"));
  private static final CollectionValue<String> EXPECTED_LIST_B =
      new CollectionValue<>(List.of("B"));
  private static final CollectionValue<String> EXPECTED_ORDERED_SET_B_A =
      new CollectionValue<>(new LinkedHashSet<>(List.of("B", "A")));
  private static final CollectionValue<String> EXPECTED_SET_B = new CollectionValue<>(Set.of("B"));
  private static final CollectionValue<String> EXPECTED_LIST_EMPTY =
      new CollectionValue<>(List.of());
  private static final CollectionValue<String> EXPECTED_LIST_A =
      new CollectionValue<>(List.of("A"));
  private static final CollectionValue<String> EXPECTED_LIST_COMPOSED_E_ACUTE =
      new CollectionValue<>(List.of("é"));
  private static final CollectionValue<String> EXPECTED_LIST_A_A =
      new CollectionValue<>(List.of("A", "A"));
  private static final CollectionValue<String> EXPECTED_LIST_B_C =
      new CollectionValue<>(List.of("B", "C"));
  private static final CollectionValue<String> EXPECTED_LIST_B_A_A =
      new CollectionValue<>(List.of("B", "A", "A"));
  private static final CollectionValue<String> EXPECTED_SET_B_A =
      new CollectionValue<>(Set.of("B", "A"));

  private static final Conditions<String> STARTS_WITH_A_AND_NOT_EMPTY_CONDITIONS =
      new Conditions<>(value -> value.startsWith("A"), value -> !value.isEmpty());
  private static final Conditions<String> STARTS_WITH_A_CONDITION =
      new Conditions<>(value -> value.startsWith("A"));
  private static final Conditions<String> STARTS_WITH_A_AND_LENGTH_TWO_CONDITIONS =
      new Conditions<>(value -> value.startsWith("A"), value -> value.length() == 2);
  private static final Conditions<String> ALWAYS_MATCHING_CONDITION =
      new Conditions<>(value -> true);
  private static final Conditions<String> NO_CONDITIONS = new Conditions<>();
  private static final Conditions<String> EQUALS_C_OR_B_CONDITIONS =
      new Conditions<>(value -> value.equals("C"), value -> value.equals("B"));
  private static final Conditions<String> EQUALS_C_CONDITION =
      new Conditions<>(value -> value.equals("C"));

  private static final String EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response collection must contain no elements.";

  private static final String NOT_EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response collection must contain at least one element.";

  private static final String SIZE_ASSERTION_MESSAGE =
      "Assertion: The response collection must contain the expected number of elements.";

  private static final String EQUALITY_ASSERTION_MESSAGE =
      "Assertion: The response collection must equal the expected collection; lists preserve order and repetitions, sets ignore order.";

  private static final String CONTAINS_ASSERTION_MESSAGE =
      "Assertion: Every expected element must be present in the response collection; additional elements are allowed.";

  private static final String NOT_CONTAINS_ASSERTION_MESSAGE =
      "Assertion: None of the specified elements may occur in the response collection.";

  private static final String ANY_ORDER_ASSERTION_MESSAGE =
      "Assertion: The collections must contain exactly the same elements with the same occurrence counts; order is ignored.";

  private static final String ALL_CONDITIONS_ASSERTION_MESSAGE =
      "Assertion: Every response element must satisfy every supplied condition.";

  private static final String ANY_CONDITION_ASSERTION_MESSAGE =
      "Assertion: At least one response element must satisfy at least one supplied condition.";

  private static final String NO_CONDITION_ASSERTION_MESSAGE =
      "Assertion: No response element may satisfy any supplied condition.";

  private static final String INVALID_CONTAINS_MESSAGE =
      "contains() requires at least one expected element. Use isEmpty() to check for an empty collection.";

  private static final String INVALID_NOT_CONTAINS_MESSAGE =
      "notContains() requires a non-empty response collection. Use isEmpty() to check for an empty collection.";

  private static final String INVALID_ALL_CONDITIONS_MESSAGE =
      "matchAll() requires a non-empty response collection. Use isEmpty() to check for an empty collection.";

  private static final String STEP_NAME = "Load collection";

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
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isEmpty);
    }

    @Test
    void GIVEN_non_empty_WHEN_isEmpty_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

      // Assert
      assertFailure(exception, "empty", ACTUAL_LIST_A.values(), EMPTY_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_non_empty_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotEmpty);
    }

    @Test
    void GIVEN_empty_WHEN_isNotEmpty_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNotEmpty);

      // Assert
      assertFailure(
          exception, "not empty", ACTUAL_LIST_EMPTY.values(), NOT_EMPTY_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class hasSize {

    @Test
    void GIVEN_matching_size_WHEN_hasSize_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasSize(2));
    }

    @Test
    void GIVEN_empty_matching_size_WHEN_hasSize_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasSize(0));
    }

    @Test
    void GIVEN_different_size_WHEN_hasSize_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasSize(1));

      // Assert
      assertFailure(exception, 1, ACTUAL_LIST_A_B.values().size(), SIZE_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_different_size_WHEN_hasSize_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasSize(1));

      // Assert
      assertFailure(exception, 1, ACTUAL_LIST_EMPTY.values().size(), SIZE_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_same_list_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_LIST_A_B));
    }

    @Test
    void GIVEN_different_list_order_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_LIST_B_A));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_B_A.values(),
          ACTUAL_LIST_A_B.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_different_repetitions_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_A_B, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_LIST_A_B_B));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_A_B_B.values(),
          ACTUAL_LIST_A_A_B.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_different_elements_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_LIST_B));

      // Assert
      assertFailure(
          exception, EXPECTED_LIST_B.values(), ACTUAL_LIST_A.values(), EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_same_set_different_order_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_ORDERED_SET_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_ORDERED_SET_B_A));
    }

    @Test
    void GIVEN_different_sets_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_SET_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_SET_B));

      // Assert
      assertFailure(
          exception, EXPECTED_SET_B.values(), ACTUAL_SET_A.values(), EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_collections_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_LIST_EMPTY));
    }

    @Test
    void GIVEN_empty_actual_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(EXPECTED_LIST_A));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_A.values(),
          ACTUAL_LIST_EMPTY.values(),
          EQUALITY_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_DECOMPOSED_E_ACUTE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(EXPECTED_LIST_COMPOSED_E_ACUTE));
    }
  }

  @Nested
  class contains {

    @Test
    void GIVEN_additional_elements_WHEN_contains_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.contains(EXPECTED_LIST_A));
    }

    @Test
    void GIVEN_different_order_WHEN_contains_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.contains(EXPECTED_LIST_B_A));
    }

    @Test
    void GIVEN_repeated_expectation_WHEN_contains_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.contains(EXPECTED_LIST_A_A));
    }

    @Test
    void GIVEN_missing_element_WHEN_contains_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.contains(EXPECTED_LIST_B));

      // Assert
      assertFailure(
          exception, EXPECTED_LIST_B.values(), ACTUAL_LIST_A.values(), CONTAINS_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_actual_WHEN_contains_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.contains(EXPECTED_LIST_A));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_A.values(),
          ACTUAL_LIST_EMPTY.values(),
          CONTAINS_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_expectation_WHEN_contains_THEN_invalid_assertion() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act
      var exception =
          assertThrows(
              InvalidAssertionException.class, () -> assertion.contains(EXPECTED_LIST_EMPTY));

      // Assert
      assertThat(exception.getMessage(), is(INVALID_CONTAINS_MESSAGE));
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_contains_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_DECOMPOSED_E_ACUTE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.contains(EXPECTED_LIST_COMPOSED_E_ACUTE));
    }
  }

  @Nested
  class notContains {

    @Test
    void GIVEN_no_overlap_WHEN_notContains_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.notContains(EXPECTED_LIST_B_C));
    }

    @Test
    void GIVEN_one_overlap_WHEN_notContains_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.notContains(EXPECTED_LIST_B_C));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_B_C.values(),
          ACTUAL_LIST_A_B.values(),
          NOT_CONTAINS_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_expectation_WHEN_notContains_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.notContains(EXPECTED_LIST_EMPTY));
    }

    @Test
    void GIVEN_empty_actual_WHEN_notContains_THEN_invalid_assertion() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act
      var exception =
          assertThrows(
              InvalidAssertionException.class, () -> assertion.notContains(EXPECTED_LIST_A));

      // Assert
      assertThat(exception.getMessage(), is(INVALID_NOT_CONTAINS_MESSAGE));
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_notContains_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_DECOMPOSED_E_ACUTE, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.notContains(EXPECTED_LIST_COMPOSED_E_ACUTE));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_COMPOSED_E_ACUTE.values(),
          ACTUAL_LIST_DECOMPOSED_E_ACUTE.values(),
          NOT_CONTAINS_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class containsAnyOrder {

    @Test
    void GIVEN_different_order_WHEN_containsAnyOrder_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsAnyOrder(EXPECTED_LIST_B_A));
    }

    @Test
    void GIVEN_same_repetitions_WHEN_containsAnyOrder_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsAnyOrder(EXPECTED_LIST_B_A_A));
    }

    @Test
    void GIVEN_different_repetitions_WHEN_containsAnyOrder_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_A_B, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.containsAnyOrder(EXPECTED_LIST_A_B_B));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_A_B_B.values(),
          ACTUAL_LIST_A_A_B.values(),
          ANY_ORDER_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_additional_element_WHEN_containsAnyOrder_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.containsAnyOrder(EXPECTED_LIST_A));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_A.values(),
          ACTUAL_LIST_A_B.values(),
          ANY_ORDER_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_missing_element_WHEN_containsAnyOrder_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.containsAnyOrder(EXPECTED_LIST_A_B));

      // Assert
      assertFailure(
          exception,
          EXPECTED_LIST_A_B.values(),
          ACTUAL_LIST_A.values(),
          ANY_ORDER_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_collections_WHEN_containsAnyOrder_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsAnyOrder(EXPECTED_LIST_EMPTY));
    }

    @Test
    void GIVEN_sets_different_order_WHEN_containsAnyOrder_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_SET_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsAnyOrder(EXPECTED_SET_B_A));
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_containsAnyOrder_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_DECOMPOSED_E_ACUTE, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.containsAnyOrder(EXPECTED_LIST_COMPOSED_E_ACUTE));
    }
  }

  @Nested
  class matchAll {

    @Test
    void GIVEN_all_elements_match_all_WHEN_matchAll_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_AA, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAll(STARTS_WITH_A_AND_NOT_EMPTY_CONDITIONS));
    }

    @Test
    void GIVEN_one_element_fails_WHEN_matchAll_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.matchAll(STARTS_WITH_A_CONDITION));

      // Assert
      assertFailure(
          exception,
          "matches all conditions",
          ACTUAL_LIST_A_B.values(),
          ALL_CONDITIONS_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_one_condition_fails_WHEN_matchAll_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> assertion.matchAll(STARTS_WITH_A_AND_LENGTH_TWO_CONDITIONS));

      // Assert
      assertFailure(
          exception,
          "matches all conditions",
          ACTUAL_LIST_A.values(),
          ALL_CONDITIONS_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_actual_WHEN_matchAll_THEN_invalid_assertion() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act
      var exception =
          assertThrows(
              InvalidAssertionException.class, () -> assertion.matchAll(ALWAYS_MATCHING_CONDITION));

      // Assert
      assertThat(exception.getMessage(), is(INVALID_ALL_CONDITIONS_MESSAGE));
    }

    @Test
    void GIVEN_no_conditions_WHEN_matchAll_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAll(NO_CONDITIONS));
    }
  }

  @Nested
  class matchAny {

    @Test
    void GIVEN_one_element_matches_one_WHEN_matchAny_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAny(EQUALS_C_OR_B_CONDITIONS));
    }

    @Test
    void GIVEN_no_element_matches_WHEN_matchAny_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAny(EQUALS_C_CONDITION));

      // Assert
      assertFailure(
          exception,
          "matches any conditions",
          ACTUAL_LIST_A_B.values(),
          ANY_CONDITION_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_actual_WHEN_matchAny_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.matchAny(ALWAYS_MATCHING_CONDITION));

      // Assert
      assertFailure(
          exception,
          "matches any conditions",
          ACTUAL_LIST_EMPTY.values(),
          ANY_CONDITION_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_no_conditions_WHEN_matchAny_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAny(NO_CONDITIONS));

      // Assert
      assertFailure(
          exception,
          "matches any conditions",
          ACTUAL_LIST_A.values(),
          ANY_CONDITION_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class matchNone {

    @Test
    void GIVEN_no_element_matches_WHEN_matchNone_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchNone(EQUALS_C_CONDITION));
    }

    @Test
    void GIVEN_one_element_matches_one_WHEN_matchNone_THEN_assertion_failed() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A_B, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.matchNone(EQUALS_C_OR_B_CONDITIONS));

      // Assert
      assertFailure(
          exception,
          "matches no conditions",
          ACTUAL_LIST_A_B.values(),
          NO_CONDITION_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_empty_actual_WHEN_matchNone_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_EMPTY, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchNone(ALWAYS_MATCHING_CONDITION));
    }

    @Test
    void GIVEN_no_conditions_WHEN_matchNone_THEN_success() {
      // Arrange
      var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchNone(NO_CONDITIONS));
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new CollectionAssertion<>(ACTUAL_LIST_A, new TestStepDto(STEP_NAME));

    // Act
    var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertThat(exception.getMessage(), containsString(EMPTY_ASSERTION_MESSAGE));
  }
}
