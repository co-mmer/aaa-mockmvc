package io.github.co_mmer.aaamockmvc.ej.test.web.request.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestRequestDtoTest {

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_call_constructor_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class,
        () -> new TestRequestDto(null));
  }
}
