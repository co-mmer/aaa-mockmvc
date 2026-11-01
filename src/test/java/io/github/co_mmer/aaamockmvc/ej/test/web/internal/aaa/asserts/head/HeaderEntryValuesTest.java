package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class HeaderEntryValuesTest {

  private static final String NULL_VALUES_MESSAGE = "Header values must not be null.";
  private static final String NULL_FIRST_VALUE_MESSAGE =
      "Header value at position 1 must not be null.";
  private static final String NULL_SECOND_VALUE_MESSAGE =
      "Header value at position 2 must not be null.";
  private static final List<String> VALUES_A_B = List.of("A", "B");

  @Test
  void GIVEN_null_array_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(IllegalArgumentException.class, () -> new HeaderEntryValues((String[]) null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_VALUES_MESSAGE));
  }

  @Test
  void GIVEN_null_first_value_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(IllegalArgumentException.class, () -> new HeaderEntryValues(null, "A"));

    // Assert
    assertThat(exception.getMessage(), is(NULL_FIRST_VALUE_MESSAGE));
  }

  @Test
  void GIVEN_null_second_value_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(IllegalArgumentException.class, () -> new HeaderEntryValues("A", null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_SECOND_VALUE_MESSAGE));
  }

  @Test
  void GIVEN_multiple_null_values_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(IllegalArgumentException.class, () -> new HeaderEntryValues(null, null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_FIRST_VALUE_MESSAGE));
  }

  @Test
  void GIVEN_empty_values_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderEntryValues();

    // Assert
    assertThat(result.values(), is(List.of()));
    assertThat(result.comparison(), is(List.of()));
  }

  @Test
  void GIVEN_order_and_repetitions_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderEntryValues("B", "A", "A");

    // Assert
    assertThat(result.values(), is(List.of("B", "A", "A")));
    assertThat(result.comparison(), is(List.of("B", "A", "A")));
  }

  @Test
  void GIVEN_empty_value_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderEntryValues("");

    // Assert
    assertThat(result.values(), is(List.of("")));
    assertThat(result.comparison(), is(List.of("")));
  }

  @Test
  void GIVEN_decomposed_unicode_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderEntryValues("e\u0301", "é");

    // Assert
    assertThat(result.values(), is(List.of("e\u0301", "é")));
    assertThat(result.comparison(), is(List.of("é", "é")));
  }

  @Test
  void GIVEN_values_WHEN_input_array_changes_THEN_snapshot_remains_unchanged() {
    // Arrange
    var values = new String[] {"A", "B"};
    var result = new HeaderEntryValues(values);

    // Act
    values[0] = "C";

    // Assert
    assertThat(result.values(), is(VALUES_A_B));
    assertThat(result.comparison(), is(VALUES_A_B));
  }
}
