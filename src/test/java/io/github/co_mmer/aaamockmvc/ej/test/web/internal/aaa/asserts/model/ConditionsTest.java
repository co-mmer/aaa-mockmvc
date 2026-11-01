package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Objects;
import java.util.function.Predicate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ConditionsTest {

  private static final String VALUE_A = "A";
  private static final String VALUE_B = "B";
  private static final String VALUE_C = "C";
  private static final Predicate<String> MATCHES_A = VALUE_A::equals;
  private static final Predicate<String> MATCHES_B = VALUE_B::equals;
  private static final Predicate<String> HAS_LENGTH_ONE = value -> value.length() == 1;
  private static final Predicate<String> ACCEPTS_NULL = Objects::isNull;
  private static final Predicate<String> REJECTS_NULL = Objects::nonNull;
  private static final Conditions<String> NO_CONDITIONS = new Conditions<>();
  private static final Conditions<String> MATCHES_A_AND_LENGTH_ONE =
      new Conditions<>(MATCHES_A, HAS_LENGTH_ONE);
  private static final Conditions<String> A_AND_B_CONDITIONS =
      new Conditions<>(MATCHES_A, MATCHES_B);
  private static final Conditions<String> NULL_ACCEPTING_CONDITION = new Conditions<>(ACCEPTS_NULL);
  private static final Conditions<String> NULL_REJECTING_CONDITION = new Conditions<>(REJECTS_NULL);
  private static final String NULL_CONDITIONS_MESSAGE = "Match conditions must not be null.";
  private static final String NULL_CONDITION_MESSAGE = "Match conditions must not contain null.";
  private static final String UNEXPECTED_CONDITION_MESSAGE =
      "The next condition must not be evaluated after the result is determined.";

  @Nested
  class constructor {

    @Test
    void GIVEN_null_array_WHEN_constructor_THEN_throw_domain_error() {
      // Arrange
      Predicate<String>[] predicates = null;

      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> new Conditions<>(predicates));

      // Assert
      assertThat(exception.getMessage(), is(NULL_CONDITIONS_MESSAGE));
    }

    @Test
    void GIVEN_null_first_condition_WHEN_constructor_THEN_throw_domain_error() {
      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> new Conditions<>(null, MATCHES_A));

      // Assert
      assertThat(exception.getMessage(), is(NULL_CONDITION_MESSAGE));
    }

    @Test
    void GIVEN_null_later_condition_WHEN_constructor_THEN_throw_domain_error() {
      // Act
      var exception =
          assertThrows(IllegalArgumentException.class, () -> new Conditions<>(MATCHES_A, null));

      // Assert
      assertThat(exception.getMessage(), is(NULL_CONDITION_MESSAGE));
    }

    @Test
    void GIVEN_conditions_WHEN_input_array_changes_THEN_snapshot_remains_unchanged() {
      // Arrange
      var predicates = new ConstantPredicate[] {new ConstantPredicate(true)};
      var conditions = new Conditions<>(predicates);

      // Act
      predicates[0] = new ConstantPredicate(false);

      // Assert
      assertThat(conditions.all(VALUE_A), is(true));
      assertThat(conditions.any(VALUE_A), is(true));
    }
  }

  @Nested
  class all {

    @Test
    void GIVEN_all_conditions_match_WHEN_all_THEN_true() {
      assertThat(MATCHES_A_AND_LENGTH_ONE.all(VALUE_A), is(true));
    }

    @Test
    void GIVEN_one_condition_fails_WHEN_all_THEN_false() {
      assertThat(MATCHES_A_AND_LENGTH_ONE.all(VALUE_B), is(false));
    }

    @Test
    void GIVEN_later_condition_fails_WHEN_all_THEN_false() {
      assertThat(A_AND_B_CONDITIONS.all(VALUE_A), is(false));
    }

    @Test
    void GIVEN_no_conditions_WHEN_all_THEN_true() {
      assertThat(NO_CONDITIONS.all(VALUE_A), is(true));
    }

    @Test
    void GIVEN_null_accepted_by_condition_WHEN_all_THEN_true() {
      assertThat(NULL_ACCEPTING_CONDITION.all(null), is(true));
    }

    @Test
    void GIVEN_null_rejected_by_condition_WHEN_all_THEN_false() {
      assertThat(NULL_REJECTING_CONDITION.all(null), is(false));
    }

    @Test
    void GIVEN_first_condition_fails_WHEN_all_THEN_remaining_conditions_are_not_evaluated() {
      // Arrange
      var conditions = new Conditions<>(MATCHES_A, value -> fail(UNEXPECTED_CONDITION_MESSAGE));

      // Act & Assert
      assertThat(conditions.all(VALUE_B), is(false));
    }

    @Test
    void GIVEN_condition_throws_WHEN_all_THEN_original_exception_is_propagated() {
      // Arrange
      var cause = new IllegalArgumentException("Invalid condition input.");
      Conditions<String> conditions =
          new Conditions<>(
              value -> {
                throw cause;
              });

      // Act
      var exception = assertThrows(IllegalArgumentException.class, () -> conditions.all(VALUE_A));

      // Assert
      assertThat(exception, sameInstance(cause));
    }
  }

  @Nested
  class any {

    @Test
    void GIVEN_first_condition_matches_WHEN_any_THEN_true() {
      assertThat(A_AND_B_CONDITIONS.any(VALUE_A), is(true));
    }

    @Test
    void GIVEN_later_condition_matches_WHEN_any_THEN_true() {
      assertThat(A_AND_B_CONDITIONS.any(VALUE_B), is(true));
    }

    @Test
    void GIVEN_no_condition_matches_WHEN_any_THEN_false() {
      assertThat(A_AND_B_CONDITIONS.any(VALUE_C), is(false));
    }

    @Test
    void GIVEN_no_conditions_WHEN_any_THEN_false() {
      assertThat(NO_CONDITIONS.any(VALUE_A), is(false));
    }

    @Test
    void GIVEN_null_accepted_by_condition_WHEN_any_THEN_true() {
      assertThat(NULL_ACCEPTING_CONDITION.any(null), is(true));
    }

    @Test
    void GIVEN_null_rejected_by_condition_WHEN_any_THEN_false() {
      assertThat(NULL_REJECTING_CONDITION.any(null), is(false));
    }

    @Test
    void GIVEN_first_condition_matches_WHEN_any_THEN_remaining_conditions_are_not_evaluated() {
      // Arrange
      var conditions = new Conditions<>(MATCHES_A, value -> fail(UNEXPECTED_CONDITION_MESSAGE));

      // Act & Assert
      assertThat(conditions.any(VALUE_A), is(true));
    }

    @Test
    void GIVEN_condition_throws_WHEN_any_THEN_original_exception_is_propagated() {
      // Arrange
      var cause = new IllegalArgumentException("Invalid condition input.");
      Conditions<String> conditions =
          new Conditions<>(
              value -> {
                throw cause;
              });

      // Act
      var exception = assertThrows(IllegalArgumentException.class, () -> conditions.any(VALUE_A));

      // Assert
      assertThat(exception, sameInstance(cause));
    }
  }

  private record ConstantPredicate(boolean result) implements Predicate<String> {

    @Override
    public boolean test(String value) {
      return this.result;
    }
  }
}
