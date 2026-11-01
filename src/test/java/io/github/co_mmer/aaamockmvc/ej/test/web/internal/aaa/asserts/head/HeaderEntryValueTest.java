package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class HeaderEntryValueTest {

  private static final String TEXT = "X-Test";
  private static final String EMPTY_TEXT = "";
  private static final String DECOMPOSED_E_ACUTE = "e\u0301";
  private static final String COMPOSED_E_ACUTE = "é";
  private static final String NULL_MESSAGE = "Header value must not be null.";

  @Test
  void GIVEN_null_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> new HeaderEntryValue(null));

    // Assert
    assertThat(exception.getMessage(), is(NULL_MESSAGE));
  }

  @Test
  void GIVEN_text_WHEN_constructor_THEN_value_and_comparison_are_preserved() {
    // Act
    var result = new HeaderEntryValue(TEXT);

    // Assert
    assertThat(result.value(), is(TEXT));
    assertThat(result.comparison(), is(TEXT));
  }

  @Test
  void GIVEN_empty_text_WHEN_constructor_THEN_value_and_comparison_are_preserved() {
    // Act
    var result = new HeaderEntryValue(EMPTY_TEXT);

    // Assert
    assertThat(result.value(), is(EMPTY_TEXT));
    assertThat(result.comparison(), is(EMPTY_TEXT));
  }

  @Test
  void GIVEN_decomposed_unicode_WHEN_constructor_THEN_value_and_comparison_are_preserved() {
    // Act
    var result = new HeaderEntryValue(DECOMPOSED_E_ACUTE);

    // Assert
    assertThat(result.value(), is(DECOMPOSED_E_ACUTE));
    assertThat(result.comparison(), is(COMPOSED_E_ACUTE));
  }
}
