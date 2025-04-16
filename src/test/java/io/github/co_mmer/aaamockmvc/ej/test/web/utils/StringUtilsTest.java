package io.github.co_mmer.aaamockmvc.ej.test.web.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class StringUtilsTest {

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\t", "\n", "   "})
  void GIVEN_blank_WHEN_isBlank_THEN_return_true(String input) {
    assertTrue(StringUtils.isBlank(input));
  }

  @ParameterizedTest
  @ValueSource(strings = {"test", " a ", "0", "false"})
  void GIVEN_value_WHEN_isBlank_THEN_return_false(String input) {
    assertFalse(StringUtils.isBlank(input));
  }

  @Test
  void GIVEN_empty_WHEN_EMPTY_THEN_return_true() {
    assertEquals("", StringUtils.EMPTY);
  }

  @Test
  void GIVEN_empty_array_WHEN_EMPTY_ARRAY_THEN_return_true() {
    assertEquals("[]", StringUtils.EMPTY_ARRAY);
  }

  @Test
  void GIVEN_empty_object_WHEN_EMPTY_OBJECT_THEN_return_true() {
    assertEquals("{}", StringUtils.EMPTY_OBJECT);
  }
}
