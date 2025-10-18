package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

class TestAnswerResultTest {

  @Test
  void GIVEN_value_WHEN_constructor_THEN_actualContent_is_value() {
    // Arrange
    var value = "test";

    // Act
    var res = new TestAnswerResult<>(value);

    // Assert
    assertThat(res.actualContent(), is(value));
  }

  @Test
  void GIVEN_null_WHEN_constructor_THEN_actualContent_is_null() {
    // Act
    var res = new TestAnswerResult<>(null);

    // Assert
    assertThat(res.actualContent(), is(nullValue()));
  }
}
