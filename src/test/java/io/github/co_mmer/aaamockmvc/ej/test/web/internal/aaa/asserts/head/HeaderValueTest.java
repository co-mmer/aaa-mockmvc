package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HeaderValueTest {

  private static final String HEADER_NAME = "X-Test";
  private static final Map<String, List<String>> HEADERS_EMPTY = Map.of();
  private static final Map<String, List<String>> HEADERS_A_B =
      Map.of(HEADER_NAME, List.of("A", "B"));
  private static final Map<String, List<String>> HEADERS_DECOMPOSED_UNICODE =
      Map.of("e\u0301", List.of("o\u0308"));
  private static final Map<String, List<String>> HEADERS_COMPOSED_UNICODE =
      Map.of("é", List.of("ö"));
  private static final String NULL_HEADERS_MESSAGE = "Headers must not be null.";
  private static final String NULL_ENTRY_MESSAGE = "Header names and values must not be null.";

  @Test
  void GIVEN_null_headers_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> new HeaderValue(null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_HEADERS_MESSAGE));
  }

  @Test
  void GIVEN_null_name_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new HeaderValue(Collections.singletonMap(null, List.of("A"))));

    // Assert
    assertThat(exception.getMessage(), is(NULL_ENTRY_MESSAGE));
  }

  @Test
  void GIVEN_null_values_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new HeaderValue(Collections.singletonMap(HEADER_NAME, null)));

    // Assert
    assertThat(exception.getMessage(), is(NULL_ENTRY_MESSAGE));
  }

  @Test
  void GIVEN_null_value_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new HeaderValue(Map.of(HEADER_NAME, Arrays.asList("A", null))));

    // Assert
    assertThat(exception.getMessage(), is(NULL_ENTRY_MESSAGE));
  }

  @Test
  void GIVEN_empty_headers_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderValue(HEADERS_EMPTY);

    // Assert
    assertThat(result.values(), is(HEADERS_EMPTY));
    assertThat(result.comparison(), is(HEADERS_EMPTY));
  }

  @Test
  void GIVEN_headers_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderValue(HEADERS_A_B);

    // Assert
    assertThat(result.values(), is(HEADERS_A_B));
    assertThat(result.comparison(), is(HEADERS_A_B));
  }

  @Test
  void GIVEN_decomposed_unicode_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderValue(HEADERS_DECOMPOSED_UNICODE);

    // Assert
    assertThat(result.values(), is(HEADERS_DECOMPOSED_UNICODE));
    assertThat(result.comparison(), is(HEADERS_COMPOSED_UNICODE));
  }

  @Test
  void GIVEN_empty_values_WHEN_constructor_THEN_values_and_comparison_are_preserved() {
    // Act
    var result = new HeaderValue(Map.of(HEADER_NAME, List.of()));

    // Assert
    assertThat(result.values(), is(Map.of(HEADER_NAME, List.of())));
    assertThat(result.comparison(), is(Map.of(HEADER_NAME, List.of())));
  }

  @Test
  void GIVEN_headers_WHEN_input_map_changes_THEN_snapshot_remains_unchanged() {
    // Arrange
    var values = new LinkedHashMap<>(HEADERS_A_B);
    var result = new HeaderValue(values);

    // Act
    values.clear();

    // Assert
    assertThat(result.values(), is(HEADERS_A_B));
    assertThat(result.comparison(), is(HEADERS_A_B));
  }

  @Test
  void GIVEN_headers_WHEN_input_list_changes_THEN_snapshot_remains_unchanged() {
    // Arrange
    var values = new ArrayList<>(List.of("A", "B"));
    var result = new HeaderValue(Map.of(HEADER_NAME, values));

    // Act
    values.clear();

    // Assert
    assertThat(result.values(), is(HEADERS_A_B));
    assertThat(result.comparison(), is(HEADERS_A_B));
  }
}
