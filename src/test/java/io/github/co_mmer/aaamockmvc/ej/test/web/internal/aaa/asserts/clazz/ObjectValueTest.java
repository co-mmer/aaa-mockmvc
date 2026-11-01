package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ObjectValueTest {

  @Test
  void GIVEN_null_WHEN_constructor_THEN_value_and_comparison_are_null() {
    // Act
    var result = new ObjectValue<>(null);

    // Assert
    assertThat(result.value(), is((Object) null));
    assertThat(result.comparison(), is((String) null));
  }

  @Test
  void GIVEN_object_WHEN_constructor_THEN_original_value_is_preserved() {
    // Arrange
    var value = Integer.valueOf(42);

    // Act
    var result = new ObjectValue<>(value);

    // Assert
    assertThat(result.value(), is(value));
    assertThat(result.comparison(), is("42"));
  }

  @Test
  void GIVEN_decomposed_unicode_WHEN_constructor_THEN_only_comparison_is_normalized() {
    // Act
    var result = new ObjectValue<>("é");

    // Assert
    assertThat(result.value(), is("é"));
    assertThat(result.comparison(), is("é"));
  }

  @Test
  void GIVEN_empty_string_WHEN_constructor_THEN_empty_value_is_preserved() {
    // Act
    var result = new ObjectValue<>("");

    // Assert
    assertThat(result.value(), is(""));
    assertThat(result.comparison(), is(""));
  }

  @Test
  void GIVEN_null_WHEN_expected_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> ObjectValue.expected(null));

    // Assert
    assertThat(exception.getMessage(), is("Expected object must not be null."));
  }

  @Test
  void GIVEN_object_WHEN_expected_THEN_value_and_comparison_are_preserved() {
    // Act
    var result = ObjectValue.expected(42);

    // Assert
    assertThat(result.value(), is(42));
    assertThat(result.comparison(), is("42"));
  }
}
