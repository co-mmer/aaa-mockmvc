package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RequestValidationTest {

  private static final String MESSAGE = "Validation failed";

  @Nested
  class RequireNonNull {

    @Test
    @SuppressWarnings("all")
    void shouldThrowIllegalArgumentExceptionWhenValueIsNull() {
      assertValidationFails(() -> RequestValidation.requireNonNull(null, MESSAGE));
    }

    @Test
    @SuppressWarnings("all")
    void shouldNotThrowWhenValueIsPresent() {
      assertDoesNotThrow(() -> RequestValidation.requireNonNull(new Object(), MESSAGE));
    }
  }

  @Nested
  class RequireNonEmptyString {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void shouldThrowIllegalArgumentExceptionWhenValueIsBlank(String value) {
      assertValidationFails(() -> RequestValidation.requireNonEmpty(value, MESSAGE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "value", " value ", "0"})
    void shouldNotThrowWhenValueContainsNonWhitespaceCharacters(String value) {
      assertDoesNotThrow(() -> RequestValidation.requireNonEmpty(value, MESSAGE));
    }

    @Test
    @SuppressWarnings("all")
    void shouldThrowNullPointerExceptionWhenValueIsNull() {
      assertThrows(
          NullPointerException.class, () -> RequestValidation.requireNonEmpty(null, MESSAGE));
    }
  }

  @Nested
  class RequireNonBlankList {

    @Test
    @SuppressWarnings("all")
    void shouldThrowIllegalArgumentExceptionWhenListIsNull() {
      assertValidationFails(() -> RequestValidation.requireNonBlank(null, MESSAGE));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenListIsEmpty() {
      assertValidationFails(() -> RequestValidation.requireNonBlank(List.of(), MESSAGE));
    }

    @Test
    void shouldNotThrowWhenListContainsValues() {
      assertDoesNotThrow(() -> RequestValidation.requireNonBlank(List.of("value"), MESSAGE));
    }

    @Test
    void shouldNotThrowWhenListContainsNullElement() {
      assertDoesNotThrow(
          () ->
              RequestValidation.requireNonBlank(
                  java.util.Collections.singletonList(null), MESSAGE));
    }
  }

  @Nested
  class RequireNonEmptyVarargs {

    @Test
    @SuppressWarnings("all")
    void shouldThrowIllegalArgumentExceptionWhenArrayIsNull() {
      assertValidationFails(() -> RequestValidation.requireNonEmpty(MESSAGE, (Object[]) null));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenNoValuesAreProvided() {
      assertValidationFails(() -> RequestValidation.requireNonEmpty(MESSAGE));
    }

    @Test
    void shouldNotThrowWhenSingleValueIsProvided() {
      assertDoesNotThrow(() -> RequestValidation.requireNonEmpty(MESSAGE, 1));
    }

    @Test
    @SuppressWarnings("all")
    void shouldNotThrowWhenMultipleValuesAreProvided() {
      assertDoesNotThrow(() -> RequestValidation.requireNonEmpty(MESSAGE, 1, 2, 3));
    }

    @Test
    @SuppressWarnings("all")
    void shouldNotThrowWhenProvidedValueIsNull() {
      assertDoesNotThrow(() -> RequestValidation.requireNonEmpty(MESSAGE, new Object[] {null}));
    }
  }

  private static void assertValidationFails(Executable executable) {
    var exception = assertThrows(IllegalArgumentException.class, executable);
    assertEquals(MESSAGE, exception.getMessage());
  }
}
