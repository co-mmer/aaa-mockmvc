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
  void GIVEN_values_WHEN_bodyAbsent_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.bodyAbsent(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body was absent.");
  }

  @Test
  void GIVEN_values_WHEN_bodyPresent_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.bodyPresent(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body was present.");
  }

  @Test
  void GIVEN_values_WHEN_bodyNotEmpty_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.bodyNotEmpty(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body was not empty.");
  }

  @Test
  void GIVEN_values_WHEN_bodyEmpty_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.bodyEmpty(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body was empty.");
  }

  @Test
  void GIVEN_dimension_WHEN_dimensionDiffered_THEN_expected_detail_is_created() {
    // Arrange
    var dimension = "size";

    // Act
    var result = AAAAssertDetail.dimensionDiffered(STEP, EXPECTED, ACTUAL, dimension);

    // Assert
    assertDetail(result, "The response body had a different size.");
  }

  @Test
  void GIVEN_values_WHEN_valueDiffered_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.valueDiffered(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body differed from the expected value.");
  }

  @Test
  void GIVEN_values_WHEN_typeDiffered_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.typeDiffered(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body had a different type.");
  }

  @Test
  void GIVEN_values_WHEN_elementsMissing_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.elementsMissing(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body did not contain every expected element.");
  }

  @Test
  void GIVEN_values_WHEN_unexpectedElement_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.unexpectedElement(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body contained an unexpected element.");
  }

  @Test
  void GIVEN_values_WHEN_elementsDiffered_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.elementsDiffered(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response body contained different elements.");
  }

  @Test
  void GIVEN_values_WHEN_notAllMatched_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.notAllMatched(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "At least one value did not match every condition.");
  }

  @Test
  void GIVEN_values_WHEN_noneMatched_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.noneMatched(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "No value matched any condition.");
  }

  @Test
  void GIVEN_values_WHEN_conditionMatched_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.conditionMatched(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "At least one value matched a condition.");
  }

  @Test
  void GIVEN_values_WHEN_headerKeyMissing_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.headerKeyMissing(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response headers did not contain the expected key.");
  }

  @Test
  void GIVEN_values_WHEN_unexpectedHeaderKey_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.unexpectedHeaderKey(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response headers contained the unexpected key.");
  }

  @Test
  void GIVEN_values_WHEN_headerValueMissing_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.headerValueMissing(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response header did not contain the expected value.");
  }

  @Test
  void GIVEN_values_WHEN_headerValuesDiffered_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.headerValuesDiffered(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response header contained different values.");
  }

  @Test
  void GIVEN_values_WHEN_statusDiffered_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.statusDiffered(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response status differed from the expected status.");
  }

  @Test
  void GIVEN_values_WHEN_statusOutOfRange_THEN_expected_detail_is_created() {
    // Act
    var result = AAAAssertDetail.statusOutOfRange(STEP, EXPECTED, ACTUAL);

    // Assert
    assertDetail(result, "The response status was outside the expected range.");
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
                + "Expected: expected"
                + LINE_SEPARATOR
                + "Actual:   actual"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Step:     Create user"
                + LINE_SEPARATOR
                + "Expected: expected"
                + LINE_SEPARATOR
                + "Actual:   actual"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: expected"
                + LINE_SEPARATOR
                + "Actual:   actual"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: expected"
                + LINE_SEPARATOR
                + "Actual:   actual"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: null"
                + LINE_SEPARATOR
                + "Actual:   null"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: \"\""
                + LINE_SEPARATOR
                + "Actual:   \"\""
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: byte[3] [1, -2, 127]"
                + LINE_SEPARATOR
                + "Actual:   byte[3] [1, -2, 127]"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: [1, 2, 3]"
                + LINE_SEPARATOR
                + "Actual:   [true, false]"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: [first, second]"
                + LINE_SEPARATOR
                + "Actual:   [value, null]"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: [[1, 2], [value, null]]"
                + LINE_SEPARATOR
                + "Actual:   [byte[2] [3, 4], [first, second]]"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
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
                + "Expected: 42"
                + LINE_SEPARATOR
                + "Actual:   true"
                + LINE_SEPARATOR
                + "Reason:   The assertion failed."));
  }

  private static void assertDetail(AAAAssertDetail actual, String reason) {
    assertThat(actual, equalTo(new AAAAssertDetail(STEP, EXPECTED, ACTUAL, reason)));
  }
}
