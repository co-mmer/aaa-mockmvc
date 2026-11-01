package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ByteValueTest {

  @Test
  void GIVEN_null_WHEN_constructor_THEN_throw_domain_error() {
    // Act
    var exception = assertThrows(IllegalArgumentException.class, () -> new ByteValue(null));

    // Assert
    assertThat(exception.getMessage(), is("Bytes must not be null."));
  }

  @Test
  void GIVEN_empty_bytes_WHEN_constructor_THEN_empty_value_is_preserved() {
    // Act
    var result = new ByteValue(new byte[0]);

    // Assert
    assertThat(result.value(), is(new byte[0]));
    assertThat(result.length(), is(0));
  }

  @Test
  void GIVEN_bytes_WHEN_constructor_THEN_values_and_length_are_preserved() {
    // Act
    var result = new ByteValue(new byte[] {1, -2, 127});

    // Assert
    assertThat(result.value(), is(new byte[] {1, -2, 127}));
    assertThat(result.length(), is(3));
  }

  @Test
  void GIVEN_bytes_WHEN_input_is_changed_THEN_value_remains_unchanged() {
    // Arrange
    var bytes = new byte[] {1, 2};
    var value = new ByteValue(bytes);

    // Act
    bytes[0] = 9;

    // Assert
    assertThat(value.value(), is(new byte[] {1, 2}));
  }

  @Test
  void GIVEN_value_WHEN_returned_bytes_are_changed_THEN_value_remains_unchanged() {
    // Arrange
    var value = new ByteValue(new byte[] {1, 2});
    var bytes = value.value();

    // Act
    bytes[0] = 9;

    // Assert
    assertThat(value.value(), is(new byte[] {1, 2}));
  }
}
