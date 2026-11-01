package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.Conditions;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import java.util.Objects;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class ObjectAssertionTest {

  private static final String NULL_ASSERTION_MESSAGE =
      "Assertion: The response object must be null.";
  private static final String NOT_NULL_ASSERTION_MESSAGE =
      "Assertion: The response object must not be null.";
  private static final String EQUALITY_ASSERTION_MESSAGE =
      "Assertion: The response object must equal the expected object.";
  private static final String TYPE_ASSERTION_MESSAGE =
      "Assertion: The response object must have the expected type: expected java.lang.Long, actual java.lang.Integer.";
  private static final String ALL_CONDITIONS_ASSERTION_MESSAGE =
      "Assertion: The response object must satisfy every supplied condition.";
  private static final String ANY_CONDITION_ASSERTION_MESSAGE =
      "Assertion: The response object must satisfy at least one supplied condition.";
  private static final String NO_CONDITION_ASSERTION_MESSAGE =
      "Assertion: The response object must not satisfy any supplied condition.";
  private static final String STEP_NAME = "Load object";

  @Nested
  class isNull {

    @Test
    void GIVEN_null_WHEN_isNull_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNull);
    }

    @Test
    void GIVEN_object_WHEN_isNull_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNull);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) null));
      assertThat(exception.getActual().getValue(), is((Object) "A"));
      assertThat(exception.getMessage(), containsString(NULL_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class isNotNull {

    @Test
    void GIVEN_object_WHEN_isNotNull_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotNull);
    }

    @Test
    void GIVEN_null_WHEN_isNotNull_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNotNull);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "not null"));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(NOT_NULL_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_same_value_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var actual = new ObjectValue<Object>("A");
      var expected = ObjectValue.<Object>expected("A");
      var assertion = new ObjectAssertion<>(actual, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(expected));
    }

    @Test
    void GIVEN_different_value_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new ObjectValue<Object>("A");
      var expected = ObjectValue.<Object>expected("B");
      var assertion = new ObjectAssertion<>(actual, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is(expected.value()));
      assertThat(exception.getActual().getValue(), is(actual.value()));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_null_actual_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new ObjectValue<>(null);
      var expected = ObjectValue.<Object>expected("A");
      var assertion = new ObjectAssertion<>(actual, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is(expected.value()));
      assertThat(exception.getActual().getValue(), is(actual.value()));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_equivalent_unicode_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var actual = new ObjectValue<Object>("é");
      var expected = ObjectValue.<Object>expected("é");
      var assertion = new ObjectAssertion<>(actual, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(expected));
    }

    @Test
    void GIVEN_different_type_same_text_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new ObjectValue<Object>(1);
      var expected = ObjectValue.<Object>expected(1L);
      var assertion = new ObjectAssertion<>(actual, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is(expected.value()));
      assertThat(exception.getActual().getValue(), is(actual.value()));
      assertThat(exception.getMessage(), containsString(TYPE_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class matchAll {

    @Test
    void GIVEN_all_conditions_match_WHEN_matchAll_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("A"), value -> value.length() == 1);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAll(conditions));
    }

    @Test
    void GIVEN_one_condition_fails_WHEN_matchAll_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("A"), value -> value.length() == 2);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAll(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches all conditions"));
      assertThat(exception.getActual().getValue(), is((Object) "A"));
      assertThat(exception.getMessage(), containsString(ALL_CONDITIONS_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_no_condition_matches_WHEN_matchAll_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("B"), value -> value.length() == 2);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAll(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches all conditions"));
      assertThat(exception.getActual().getValue(), is((Object) "A"));
      assertThat(exception.getMessage(), containsString(ALL_CONDITIONS_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_no_conditions_WHEN_matchAll_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions = new Conditions<String>();

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAll(conditions));
    }

    @Test
    void GIVEN_null_accepted_by_condition_WHEN_matchAll_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);
      var conditions = new Conditions<String>(Objects::isNull);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAll(conditions));
    }

    @Test
    void GIVEN_null_rejected_by_condition_WHEN_matchAll_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);
      var conditions = new Conditions<String>(Objects::nonNull);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAll(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches all conditions"));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(ALL_CONDITIONS_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class matchAny {

    @Test
    void GIVEN_one_condition_matches_WHEN_matchAny_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("B"), value -> value.length() == 1);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAny(conditions));
    }

    @Test
    void GIVEN_no_condition_matches_WHEN_matchAny_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("B"), value -> value.length() == 2);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAny(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches any conditions"));
      assertThat(exception.getActual().getValue(), is((Object) "A"));
      assertThat(exception.getMessage(), containsString(ANY_CONDITION_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_no_conditions_WHEN_matchAny_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions = new Conditions<String>();

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAny(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches any conditions"));
      assertThat(exception.getActual().getValue(), is((Object) "A"));
      assertThat(exception.getMessage(), containsString(ANY_CONDITION_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_null_accepted_by_condition_WHEN_matchAny_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);
      var conditions = new Conditions<String>(Objects::isNull);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchAny(conditions));
    }

    @Test
    void GIVEN_null_rejected_by_condition_WHEN_matchAny_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);
      var conditions = new Conditions<String>(Objects::nonNull);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchAny(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches any conditions"));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(ANY_CONDITION_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class matchNone {

    @Test
    void GIVEN_no_condition_matches_WHEN_matchNone_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("B"), value -> value.length() == 2);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchNone(conditions));
    }

    @Test
    void GIVEN_one_condition_matches_WHEN_matchNone_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("B"), value -> value.length() == 1);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchNone(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches no conditions"));
      assertThat(exception.getActual().getValue(), is((Object) "A"));
      assertThat(exception.getMessage(), containsString(NO_CONDITION_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_all_conditions_match_WHEN_matchNone_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions =
          new Conditions<String>(value -> value.startsWith("A"), value -> value.length() == 1);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchNone(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches no conditions"));
      assertThat(exception.getActual().getValue(), is((Object) "A"));
      assertThat(exception.getMessage(), containsString(NO_CONDITION_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_no_conditions_WHEN_matchNone_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), null);
      var conditions = new Conditions<String>();

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchNone(conditions));
    }

    @Test
    void GIVEN_null_matches_condition_WHEN_matchNone_THEN_assertion_failed() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);
      var conditions = new Conditions<String>(Objects::isNull);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.matchNone(conditions));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "matches no conditions"));
      assertThat(exception.getActual().getValue(), is((Object) null));
      assertThat(exception.getMessage(), containsString(NO_CONDITION_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_null_matches_no_condition_WHEN_matchNone_THEN_success() {
      // Arrange
      var assertion = new ObjectAssertion<>(new ObjectValue<String>(null), null);
      var conditions = new Conditions<String>(Objects::nonNull);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.matchNone(conditions));
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new ObjectAssertion<>(new ObjectValue<>("A"), new TestStepDto(STEP_NAME));

    // Act
    var exception = assertThrows(AssertionFailedError.class, assertion::isNull);

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertThat(exception.getMessage(), containsString(NULL_ASSERTION_MESSAGE));
  }
}
