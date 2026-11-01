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

class DomainValidationTest {

  private static final String ANY_MESSAGE = "Validation failed";

  @Nested
  class RequireNonNull {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_value_WHEN_requireNonNull_THEN_return_meaningful_message() {
      assertValidationFails(() -> DomainValidation.requireNonNull(null, ANY_MESSAGE));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_present_value_WHEN_requireNonNull_THEN_do_not_throw() {
      assertDoesNotThrow(() -> DomainValidation.requireNonNull(new Object(), ANY_MESSAGE));
    }
  }

  @Nested
  class RequireNonEmptyString {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void GIVEN_blank_value_WHEN_requireNonEmpty_THEN_return_meaningful_message(String value) {
      assertValidationFails(() -> DomainValidation.requireNonEmpty(value, ANY_MESSAGE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "value", " value ", "0"})
    void GIVEN_non_blank_value_WHEN_requireNonEmpty_THEN_do_not_throw(String value) {
      assertDoesNotThrow(() -> DomainValidation.requireNonEmpty(value, ANY_MESSAGE));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_value_WHEN_requireNonEmpty_THEN_throw_NullPointerException() {
      assertThrows(
          NullPointerException.class, () -> DomainValidation.requireNonEmpty(null, ANY_MESSAGE));
    }
  }

  @Nested
  class RequireNonBlankList {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_string_WHEN_requireNonBlank_THEN_return_meaningful_message() {
      assertValidationFails(() -> DomainValidation.requireNonBlank((String) null, ANY_MESSAGE));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_list_WHEN_requireNonBlank_THEN_return_meaningful_message() {
      assertValidationFails(() -> DomainValidation.requireNonBlank((List<?>) null, ANY_MESSAGE));
    }

    @Test
    void GIVEN_empty_list_WHEN_requireNonBlank_THEN_return_meaningful_message() {
      assertValidationFails(() -> DomainValidation.requireNonBlank(List.of(), ANY_MESSAGE));
    }

    @Test
    void GIVEN_list_with_values_WHEN_requireNonBlank_THEN_do_not_throw() {
      assertDoesNotThrow(() -> DomainValidation.requireNonBlank(List.of("value"), ANY_MESSAGE));
    }

    @Test
    void GIVEN_list_with_null_element_WHEN_requireNonBlank_THEN_do_not_throw() {
      assertDoesNotThrow(
          () ->
              DomainValidation.requireNonBlank(
                  java.util.Collections.singletonList(null), ANY_MESSAGE));
    }
  }

  @Nested
  class RequireNonEmptyVarargs {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_array_WHEN_requireNonEmpty_THEN_return_meaningful_message() {
      assertValidationFails(() -> DomainValidation.requireNonEmpty(ANY_MESSAGE, (Object[]) null));
    }

    @Test
    void GIVEN_no_values_WHEN_requireNonEmpty_THEN_return_meaningful_message() {
      assertValidationFails(() -> DomainValidation.requireNonEmpty(ANY_MESSAGE));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_single_value_WHEN_requireNonEmpty_THEN_do_not_throw() {
      assertDoesNotThrow(() -> DomainValidation.requireNonEmpty(ANY_MESSAGE, 1));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_multiple_values_WHEN_requireNonEmpty_THEN_do_not_throw() {
      assertDoesNotThrow(() -> DomainValidation.requireNonEmpty(ANY_MESSAGE, 1, 2, 3));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_element_WHEN_requireNonEmpty_THEN_do_not_throw() {
      assertDoesNotThrow(() -> DomainValidation.requireNonEmpty(ANY_MESSAGE, new Object[] {null}));
    }
  }

  private static void assertValidationFails(Executable executable) {
    var exception = assertThrows(IllegalArgumentException.class, executable);

    assertEquals(ANY_MESSAGE, exception.getMessage());
  }
}
