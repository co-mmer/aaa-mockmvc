package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer.TestAnswerImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.TestArrangeImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.step.TestStepImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TestStepImplTest {

  private final TestAAAContext context = Mockito.mock(TestAAAContext.class);
  private final TestStepImpl step = new TestStepImpl(context);

  @Test
  void WHEN_arrange_THEN_return_expected_class() {
    // Act
    var result = this.step.arrange();

    // Assert
    assertThat(result, instanceOf(TestArrangeImpl.class));
  }

  @Test
  void WHEN_act_THEN_return_expected_class() {
    // Act
    var result = this.step.act();

    // Assert
    assertThat(result, instanceOf(TestActImpl.class));
  }

  @Test
  void WHEN_asserts_THEN_return_expected_class() {
    // Act
    var result = this.step.asserts();

    // Assert
    assertThat(result, instanceOf(TestAssertImpl.class));
  }

  @Test
  void WHEN_answer_THEN_return_expected_class() {
    // Act
    var result = this.step.answer();

    // Assert
    assertThat(result, instanceOf(TestAnswerImpl.class));
  }
}
