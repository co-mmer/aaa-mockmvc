package io.github.co_mmer.aaamockmvc.ej.test;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_URI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer.TestAnswerImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.TestArrangeImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = WebApplicationContext.class)
class AAAMockMvcAbstractTest extends AAAMockMvcAbstract {

  @Test
  void WHEN_arrange_THEN_returnExpectedClass() {
    // Act
    var arrange = arrange();

    // Assert
    assertThat(arrange.getClass(), is(TestArrangeImpl.class));
  }

  @Test
  void WHEN_act_THEN_returnExpectedClass() {
    // Arrange
    arrange();

    // Act
    var act = act();

    // Assert
    assertThat(act.getClass(), is(TestActImpl.class));
  }

  @Test
  void WHEN_asserts_THEN_returnExpectedClass() {
    // Arrange
    arrange().get(TEST_URI);
    act().perform();

    // Act
    var asserts = asserts();

    // Assert
    assertThat(asserts.getClass(), is(TestAssertImpl.class));
  }

  @Test
  void WHEN_answer_THEN_returnExpectedClass() {
    // Arrange
    arrange().get(TEST_URI);
    act().perform();

    // Act
    var answer = answer();

    // Assert
    assertThat(answer.getClass(), is(TestAnswerImpl.class));
  }
}
