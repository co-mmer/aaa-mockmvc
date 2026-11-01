package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import org.junit.jupiter.api.Test;

class AAAAssertDetailTest {

  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static final String REASON = "The assertion failed.";
  private static final TestStepDto STEP = new TestStepDto("Create user");
  private static final Object EXPECTED = "expected";
  private static final Object ACTUAL = "actual";

  @Test
  void GIVEN_values_WHEN_failure_THEN_expected_error_is_created() {
    // Act
    var result = AAAAssertDetail.failure(STEP, REASON, EXPECTED, ACTUAL);

    // Assert
    assertThat(
        result.getMessage(),
        equalTo(
            LINE_SEPARATOR
                + "Step:      Create user"
                + LINE_SEPARATOR
                + "Expected:  expected"
                + LINE_SEPARATOR
                + "Actual:    actual"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
    assertThat(result.getExpected().getValue(), equalTo(EXPECTED));
    assertThat(result.getActual().getValue(), equalTo(ACTUAL));
  }

  @Test
  void GIVEN_no_step_WHEN_formatMessage_THEN_step_is_omitted() {
    // Arrange
    var detail = new AAAAssertDetail(null, EXPECTED, ACTUAL, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  expected"
                + LINE_SEPARATOR
                + "Actual:    actual"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_named_step_WHEN_formatMessage_THEN_stripped_step_is_included() {
    // Arrange
    var step = new TestStepDto("  Create user  ");
    var detail = new AAAAssertDetail(step, EXPECTED, ACTUAL, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Step:      Create user"
                + LINE_SEPARATOR
                + "Expected:  expected"
                + LINE_SEPARATOR
                + "Actual:    actual"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_step_without_name_WHEN_formatMessage_THEN_step_is_omitted() {
    // Arrange
    var detail = new AAAAssertDetail(new TestStepDto(null), EXPECTED, ACTUAL, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  expected"
                + LINE_SEPARATOR
                + "Actual:    actual"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_blank_step_name_WHEN_formatMessage_THEN_step_is_omitted() {
    // Arrange
    var detail = new AAAAssertDetail(new TestStepDto("   "), EXPECTED, ACTUAL, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  expected"
                + LINE_SEPARATOR
                + "Actual:    actual"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_null_values_WHEN_formatMessage_THEN_null_is_formatted() {
    // Arrange
    var detail = new AAAAssertDetail(null, null, null, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  null"
                + LINE_SEPARATOR
                + "Actual:    null"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_empty_strings_WHEN_formatMessage_THEN_quotes_are_formatted() {
    // Arrange
    var detail = new AAAAssertDetail(null, "", new StringBuilder(), REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  \"\""
                + LINE_SEPARATOR
                + "Actual:    \"\""
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_byte_arrays_WHEN_formatMessage_THEN_length_and_values_are_formatted() {
    // Arrange
    var bytes = new byte[] {1, -2, 127};
    var detail = new AAAAssertDetail(null, bytes, bytes, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  byte[3] [1, -2, 127]"
                + LINE_SEPARATOR
                + "Actual:    byte[3] [1, -2, 127]"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_primitive_arrays_WHEN_formatMessage_THEN_values_are_formatted() {
    // Arrange
    var expected = new int[] {1, 2, 3};
    var actual = new boolean[] {true, false};
    var detail = new AAAAssertDetail(null, expected, actual, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  [1, 2, 3]"
                + LINE_SEPARATOR
                + "Actual:    [true, false]"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_object_arrays_WHEN_formatMessage_THEN_values_are_formatted() {
    // Arrange
    var expected = new String[] {"first", "second"};
    var actual = new Object[] {"value", null};
    var detail = new AAAAssertDetail(null, expected, actual, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  [first, second]"
                + LINE_SEPARATOR
                + "Actual:    [value, null]"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_nested_arrays_WHEN_formatMessage_THEN_values_are_formatted_recursively() {
    // Arrange
    var expected = new Object[] {new int[] {1, 2}, new Object[] {"value", null}};
    var actual = new Object[] {new byte[] {3, 4}, new String[] {"first", "second"}};
    var detail = new AAAAssertDetail(null, expected, actual, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  [[1, 2], [value, null]]"
                + LINE_SEPARATOR
                + "Actual:    [byte[2] [3, 4], [first, second]]"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }

  @Test
  void GIVEN_regular_values_WHEN_formatMessage_THEN_string_values_are_used() {
    // Arrange
    var detail = new AAAAssertDetail(null, 42, true, REASON);

    // Act
    var result = detail.formatMessage();

    // Assert
    assertThat(
        result,
        equalTo(
            LINE_SEPARATOR
                + "Expected:  42"
                + LINE_SEPARATOR
                + "Actual:    true"
                + LINE_SEPARATOR
                + "Assertion: The assertion failed."));
  }
}
