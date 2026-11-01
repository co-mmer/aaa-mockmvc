package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BooleanValueTest {

  @Test
  void GIVEN_true_WHEN_constructor_THEN_value_is_preserved() {
    // Act
    var result = new BooleanValue(true);

    // Assert
    assertThat(result.value(), is(true));
  }

  @Test
  void GIVEN_false_WHEN_constructor_THEN_value_is_preserved() {
    // Act
    var result = new BooleanValue(false);

    // Assert
    assertThat(result.value(), is(false));
  }

  @Test
  void GIVEN_null_WHEN_constructor_THEN_value_is_preserved() {
    // Act
    var result = new BooleanValue(null);

    // Assert
    assertThat(result.value(), is((Boolean) null));
  }

  @Test
  void GIVEN_true_WHEN_expected_THEN_value_is_preserved() {
    // Act
    var result = BooleanValue.expected(true);

    // Assert
    assertThat(result.value(), is(true));
  }

  @Test
  void GIVEN_false_WHEN_expected_THEN_value_is_preserved() {
    // Act
    var result = BooleanValue.expected(false);

    // Assert
    assertThat(result.value(), is(false));
  }

  @Test
  void GIVEN_null_WHEN_expected_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> BooleanValue.expected(null));

    // Assert
    assertThat(exception.getMessage(), is("Expected boolean must not be null."));
  }
}
