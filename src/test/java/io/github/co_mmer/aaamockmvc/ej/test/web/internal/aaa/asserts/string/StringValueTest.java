package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StringValueTest {

  private static final String TEXT = "Test";
  private static final String EMPTY_TEXT = "";
  private static final String WHITESPACE_TEXT = " \t\n";
  private static final String DECOMPOSED_E_ACUTE = "e\u0301";
  private static final String COMPOSED_E_ACUTE = "é";
  private static final String NULL_STRING_MESSAGE = "String must not be null.";

  @Test
  void GIVEN_null_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> new StringValue(null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_STRING_MESSAGE));
  }

  @Test
  void GIVEN_text_WHEN_constructor_THEN_original_value_and_comparison_are_preserved() {
    // Act
    var result = new StringValue(TEXT);

    // Assert
    assertThat(result.value(), is(TEXT));
    assertThat(result.comparison(), is(TEXT));
  }

  @Test
  void GIVEN_empty_text_WHEN_constructor_THEN_original_value_and_comparison_are_preserved() {
    // Act
    var result = new StringValue(EMPTY_TEXT);

    // Assert
    assertThat(result.value(), is(EMPTY_TEXT));
    assertThat(result.comparison(), is(EMPTY_TEXT));
  }

  @Test
  void GIVEN_whitespace_WHEN_constructor_THEN_original_value_and_comparison_are_preserved() {
    // Act
    var result = new StringValue(WHITESPACE_TEXT);

    // Assert
    assertThat(result.value(), is(WHITESPACE_TEXT));
    assertThat(result.comparison(), is(WHITESPACE_TEXT));
  }

  @Test
  void
      GIVEN_decomposed_unicode_WHEN_constructor_THEN_original_value_and_comparison_are_preserved() {
    // Act
    var result = new StringValue(DECOMPOSED_E_ACUTE);

    // Assert
    assertThat(result.value(), is(DECOMPOSED_E_ACUTE));
    assertThat(result.comparison(), is(COMPOSED_E_ACUTE));
  }

  @Test
  void GIVEN_composed_unicode_WHEN_constructor_THEN_original_value_and_comparison_are_preserved() {
    // Act
    var result = new StringValue(COMPOSED_E_ACUTE);

    // Assert
    assertThat(result.value(), is(COMPOSED_E_ACUTE));
    assertThat(result.comparison(), is(COMPOSED_E_ACUTE));
  }
}
