package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CollectionValueTest {

  @Test
  void GIVEN_null_collection_WHEN_create_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(IllegalArgumentException.class, () -> new CollectionValue<String>(null));

    // Assert
    assertThat(exception.getMessage(), is("Collection must not be null."));
  }

  @Test
  void GIVEN_null_element_WHEN_create_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(
            IllegalArgumentException.class, () -> new CollectionValue<>(Arrays.asList("A", null)));

    // Assert
    assertThat(exception.getMessage(), is("Collection must not contain null elements."));
  }

  @Test
  void GIVEN_null_varargs_WHEN_create_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(IllegalArgumentException.class, () -> CollectionValue.of((String[]) null));

    // Assert
    assertThat(exception.getMessage(), is("Collection must not be null."));
  }

  @Test
  void GIVEN_null_vararg_element_WHEN_create_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(IllegalArgumentException.class, () -> CollectionValue.of("A", (String) null));

    // Assert
    assertThat(exception.getMessage(), is("Collection must not contain null elements."));
  }

  @Test
  void GIVEN_empty_list_WHEN_create_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new CollectionValue<String>(List.of());

    // Assert
    assertThat(result.values(), is(List.of()));
    assertThat(result.comparison(), is(List.of()));
  }

  @Test
  void GIVEN_list_WHEN_create_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new CollectionValue<>(List.of("B", "A", "A"));

    // Assert
    assertThat(result.values(), is(List.of("B", "A", "A")));
    assertThat(result.comparison(), is(List.of("B", "A", "A")));
  }

  @Test
  void GIVEN_set_WHEN_create_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new CollectionValue<>(Set.of("B", "A"));

    // Assert
    assertThat(result.values(), is(Set.of("A", "B")));
    assertThat(result.comparison(), is(Set.of("A", "B")));
  }

  @Test
  void GIVEN_unicode_list_WHEN_create_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new CollectionValue<>(List.of("e\u0301", "é"));

    // Assert
    assertThat(result.values(), is(List.of("e\u0301", "é")));
    assertThat(result.comparison(), is(List.of("é", "é")));
  }

  @Test
  void GIVEN_unicode_set_WHEN_create_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new CollectionValue<>(Set.of("e\u0301", "é"));

    // Assert
    assertThat(result.values(), is(Set.of("e\u0301", "é")));
    assertThat(result.comparison(), is(Set.of("é")));
  }

  @Test
  void GIVEN_empty_varargs_WHEN_create_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = CollectionValue.<String>of();

    // Assert
    assertThat(result.values(), is(List.of()));
    assertThat(result.comparison(), is(List.of()));
  }

  @Test
  void GIVEN_varargs_WHEN_create_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = CollectionValue.of("B", "A", "A");

    // Assert
    assertThat(result.values(), is(List.of("B", "A", "A")));
    assertThat(result.comparison(), is(List.of("B", "A", "A")));
  }

  @Test
  void GIVEN_list_WHEN_input_changes_THEN_snapshot_remains_unchanged() {
    // Arrange
    var values = new ArrayList<>(List.of("A", "B"));
    var value = new CollectionValue<>(values);

    // Act
    values.clear();

    // Assert
    assertThat(value.values(), is(List.of("A", "B")));
    assertThat(value.comparison(), is(List.of("A", "B")));
  }

  @Test
  void GIVEN_set_WHEN_input_changes_THEN_snapshot_remains_unchanged() {
    // Arrange
    var values = new LinkedHashSet<>(List.of("A", "B"));
    var value = new CollectionValue<>(values);

    // Act
    values.clear();

    // Assert
    assertThat(value.values(), is(Set.of("A", "B")));
    assertThat(value.comparison(), is(Set.of("A", "B")));
  }

  @Test
  void GIVEN_varargs_WHEN_input_changes_THEN_snapshot_remains_unchanged() {
    // Arrange
    var values = new String[] {"A", "B"};
    var value = CollectionValue.of(values);

    // Act
    values[0] = "C";

    // Assert
    assertThat(value.values(), is(List.of("A", "B")));
    assertThat(value.comparison(), is(List.of("A", "B")));
  }
}
