package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TextBodyTest {

  private static final String ANY_VALUE = "request content";

  @Test
  void GIVEN_value_WHEN_create_THEN_store_value() {
    // Act
    var body = new TextBody(ANY_VALUE);

    // Assert
    assertThat(body.value(), is(ANY_VALUE));
  }

  @Test
  void GIVEN_empty_value_WHEN_create_THEN_store_empty_value() {
    // Act
    var body = new TextBody("");

    // Assert
    assertThat(body.value(), is(""));
  }

  @Test
  void GIVEN_blank_value_WHEN_create_THEN_store_blank_value() {
    // Act
    var body = new TextBody(" ");

    // Assert
    assertThat(body.value(), is(" "));
  }

  @Test
  void GIVEN_null_value_WHEN_create_THEN_return_meaningful_message() {
    // Act
    var ex = assertThrows(IllegalArgumentException.class, () -> new TextBody(null));

    // Assert
    assertThat(ex.getMessage(), is("value content must not be null"));
  }
}
