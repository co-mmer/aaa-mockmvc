package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MapValueTest {

  private static final Map<String, String> MAP_EMPTY = Map.of();
  private static final Map<String, String> MAP_A_1 = Map.of("A", "1");
  private static final Map<String, String> MAP_DECOMPOSED_UNICODE = Map.of("e\u0301", "o\u0308");
  private static final Map<String, String> MAP_COMPOSED_UNICODE = Map.of("é", "ö");
  private static final String NULL_MAP_MESSAGE = "Map must not be null.";
  private static final String KEY_COLLISION_MESSAGE =
      "Key collision after NFC normalization: \"e\u0301\" [U+0065 U+0301]"
          + " and \"é\" [U+00E9] both become \"é\" [U+00E9]";

  @Test
  void GIVEN_null_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> new MapValue<>(null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_MAP_MESSAGE));
  }

  @Test
  void GIVEN_empty_map_WHEN_constructor_THEN_empty_values_are_preserved() {
    // Act
    var result = new MapValue<>(MAP_EMPTY);

    // Assert
    assertThat(result.values(), is(MAP_EMPTY));
    assertThat(result.comparison(), is(MAP_EMPTY));
  }

  @Test
  void GIVEN_map_WHEN_constructor_THEN_entries_are_preserved() {
    // Act
    var result = new MapValue<>(MAP_A_1);

    // Assert
    assertThat(result.values(), is(MAP_A_1));
    assertThat(result.comparison(), is(MAP_A_1));
  }

  @Test
  void GIVEN_numeric_entries_WHEN_constructor_THEN_comparison_uses_text() {
    // Act
    var result = new MapValue<>(Map.of(1, 2));

    // Assert
    assertThat(result.values(), is(Map.of(1, 2)));
    assertThat(result.comparison(), is(Map.of("1", "2")));
  }

  @Test
  void GIVEN_decomposed_unicode_WHEN_constructor_THEN_only_comparison_is_normalized() {
    // Act
    var result = new MapValue<>(MAP_DECOMPOSED_UNICODE);

    // Assert
    assertThat(result.values(), is(MAP_DECOMPOSED_UNICODE));
    assertThat(result.comparison(), is(MAP_COMPOSED_UNICODE));
  }

  @Test
  void GIVEN_null_key_WHEN_constructor_THEN_null_key_is_preserved() {
    // Arrange
    Map<String, String> values = Collections.singletonMap(null, "A");

    // Act
    var result = new MapValue<>(values);

    // Assert
    assertThat(result.values(), is(values));
    assertThat(result.comparison(), is(values));
  }

  @Test
  void GIVEN_null_value_WHEN_constructor_THEN_null_value_is_preserved() {
    // Arrange
    Map<String, String> values = Collections.singletonMap("A", null);

    // Act
    var result = new MapValue<>(values);

    // Assert
    assertThat(result.values(), is(values));
    assertThat(result.comparison(), is(values));
  }

  @Test
  void GIVEN_map_WHEN_input_changes_THEN_snapshot_remains_unchanged() {
    // Arrange
    var values = new LinkedHashMap<>(MAP_A_1);
    var value = new MapValue<>(values);

    // Act
    values.put("A", "2");
    values.put("B", "3");

    // Assert
    assertThat(value.values(), is(MAP_A_1));
    assertThat(value.comparison(), is(MAP_A_1));
  }

  @Test
  void GIVEN_keys_colliding_after_normalization_WHEN_constructor_THEN_throw_domain_error() {
    // Arrange
    var values = new LinkedHashMap<String, String>();
    values.put("e\u0301", "1");
    values.put("é", "2");

    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> new MapValue<>(values));

    // Assert
    assertThat(exception.getMessage(), is(KEY_COLLISION_MESSAGE));
  }
}
