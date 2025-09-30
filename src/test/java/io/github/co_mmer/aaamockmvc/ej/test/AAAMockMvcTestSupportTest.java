package io.github.co_mmer.aaamockmvc.ej.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = WebApplicationContext.class)
class AAAMockMvcTestSupportTest extends AAAMockMvcTestSupport {

  @BeforeEach
  void setUp() {
    var mock = mock(AAAMockMvc.class);
    ReflectionTestUtils.setField(this, "aaaMockMvc", mock);
  }

  @Test
  void WHEN_arrange_THEN_delegates_to_aaaMockMvc_arrange() {
    // Act
    arrange();

    // Assert
    verify(this.aaaMockMvc).arrange();
  }

  @Test
  void WHEN_act_THEN_delegates_to_aaaMockMvc_act() {
    // Act
    act();

    // Assert
    verify(this.aaaMockMvc).act();
  }

  @Test
  void WHEN_asserts_THEN_delegates_to_aaaMockMvc_asserts() {
    // Act
    asserts();

    // Assert
    verify(this.aaaMockMvc).asserts();
  }

  @Test
  void WHEN_answer_THEN_delegates_to_aaaMockMvc_answer() {
    // Act
    answer();

    // Assert
    verify(this.aaaMockMvc).answer();
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_as_stepName_WHEN_step_THEN_throw_NullPointerException() {
    // Arrange
    Runnable runnable = this::arrange;

    // Act & Assert
    assertThrows(NullPointerException.class, () -> step(null, runnable));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_in_block_WHEN_step_THEN_throw_NullPointerException() {
    // Act & Assert
    assertThrows(NullPointerException.class, () -> step("null", null));
  }

  @Test
  void WHEN_step_THEN_delegates_to_aaaMockMvc_step() {
    // Arrange
    Runnable runnable = this::arrange;

    // Act
    var s = step("test", runnable);

    // Assert
    verify(this.aaaMockMvc).step("test", runnable);
    assertThat(s, is(nullValue()));
  }
}
