package io.github.co_mmer.aaamockmvc.ej.test;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.BASE_URI;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = WebApplicationContext.class)
class AAAMockMvcTestSupportTest extends AAAMockMvcTestSupport {

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
  void WHEN_step_THEN_delegates_to_aaaMockMvc_step() {
    // Arrange
    Runnable runnable = () -> arrange().get(BASE_URI);

    // Act
    step("test", runnable);

    // Assert
    verify(this.aaaMockMvc).step("test", runnable);
  }
}
