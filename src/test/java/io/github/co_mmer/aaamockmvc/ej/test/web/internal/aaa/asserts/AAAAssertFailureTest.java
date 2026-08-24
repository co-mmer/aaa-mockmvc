package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

final class AAAAssertFailureTest {

  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String REASON = "The assertion failed.";

  private static final TestStepDto STEP_WITHOUT_NAME = new TestStepDto(null);
  private static final TestStepDto BLANK_STEP = new TestStepDto("   ");
  private static final TestStepDto NAMED_STEP = new TestStepDto("  Verify response  ");

  @Test
  void GIVEN_named_step_WHEN_create_THEN_trimmed_step_is_in_message() {
    // Act
    var result = AAAAssertFailure.create(NAMED_STEP, "expected", "actual", REASON);

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Step:     Verify response"
                + LINE_SEPARATOR
                + "Expected: expected"
                + LINE_SEPARATOR
                + "Actual:   actual"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("stepsWithoutVisibleName")
  void GIVEN_step_without_visible_name_WHEN_create_THEN_step_is_omitted(
      String useCase, TestStepDto step) {

    // Act
    var result = AAAAssertFailure.create(step, "expected", "actual", REASON);

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected: expected"
                + LINE_SEPARATOR
                + "Actual:   actual"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  @Test
  void GIVEN_null_values_WHEN_create_THEN_values_are_formatted_as_null() {
    // Act
    var result = AAAAssertFailure.create(null, null, null, REASON);

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected: null"
                + LINE_SEPARATOR
                + "Actual:   null"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("emptyTexts")
  void GIVEN_empty_text_WHEN_create_THEN_text_is_formatted_with_quotes(
      String useCase, CharSequence emptyText) {

    // Act
    var result = AAAAssertFailure.create(null, emptyText, emptyText, REASON);

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected: \"\""
                + LINE_SEPARATOR
                + "Actual:   \"\""
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  @Test
  void GIVEN_byte_array_WHEN_create_THEN_length_and_values_are_formatted() {
    // Arrange
    var bytes = new byte[] {1, -2, 127};

    // Act
    var result = AAAAssertFailure.create(null, bytes, bytes, REASON);

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected: byte[3] [1, -2, 127]"
                + LINE_SEPARATOR
                + "Actual:   byte[3] [1, -2, 127]"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("arrays")
  void GIVEN_array_WHEN_create_THEN_elements_are_formatted_recursively(
      String useCase, Object array, String formattedArray) {

    // Act
    var result = AAAAssertFailure.create(null, array, array, REASON);

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected: "
                + formattedArray
                + LINE_SEPARATOR
                + "Actual:   "
                + formattedArray
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  @Test
  void GIVEN_regular_values_WHEN_create_THEN_string_value_is_used() {
    // Act
    var result = AAAAssertFailure.create(null, 42, true, REASON);

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected: 42"
                + LINE_SEPARATOR
                + "Actual:   true"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  private static Stream<Arguments> stepsWithoutVisibleName() {
    return Stream.of(
        arguments("no step", null),
        arguments("step without name", STEP_WITHOUT_NAME),
        arguments("blank step", BLANK_STEP));
  }

  private static Stream<Arguments> emptyTexts() {
    return Stream.of(
        arguments("empty string", ""), arguments("empty StringBuilder", new StringBuilder()));
  }

  private static Stream<Arguments> arrays() {
    return Stream.of(
        arguments("primitive array", new int[] {1, 2}, "[1, 2]"),
        arguments("object array", new Object[] {"A", null, ""}, "[A, null, \"\"]"),
        arguments(
            "nested array",
            new Object[] {new int[] {1, 2}, new Object[] {"", null}},
            "[[1, 2], [\"\", null]]"));
  }
}
