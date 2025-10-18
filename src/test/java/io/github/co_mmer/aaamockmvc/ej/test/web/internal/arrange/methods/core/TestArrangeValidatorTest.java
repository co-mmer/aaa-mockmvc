package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_PDF;

import org.junit.jupiter.api.Test;

class TestArrangeValidatorTest {

  @Test
  void GIVEN_list_with_items_WHEN_nonNullAccepts_THEN_throw_not_exception() {
    assertDoesNotThrow(
        () -> TestArrangeValidator.nonNullAccepts(APPLICATION_JSON, APPLICATION_PDF));
  }

  @Test
  void GIVEN_list_with_null_items_WHEN_nonNullAccepts_THEN_throw_NullPointerException() {
    // Act
    var exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> TestArrangeValidator.nonNullAccepts(APPLICATION_JSON, null));

    // Assert
    assertThat(exception.getMessage(), is("Accepts must not contain null values"));
  }
}
