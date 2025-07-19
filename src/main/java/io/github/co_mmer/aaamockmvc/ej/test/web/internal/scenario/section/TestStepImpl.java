package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.section;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error.TestPreconditionsValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer.TestAnswerImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.TestArrangeImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.scenario.step.TestStep;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Since("2.0.0")
@RequiredArgsConstructor
public class TestStepImpl implements TestStep {

  private final TestAAAContext context;

  @Override
  public TestArrange arrange() {
    TestPreconditionsValidator.arrange(this.context);
    return new TestArrangeImpl(this.context);
  }

  @Override
  public TestAct act() {
    TestPreconditionsValidator.act(this.context);
    return new TestActImpl(this.context);
  }

  @Override
  public TestAssert asserts() {
    TestPreconditionsValidator.asserts(context);
    return new TestAssertImpl(this.context);
  }

  @Override
  public TestAnswer answer() {
    TestPreconditionsValidator.answer(this.context);
    return new TestAnswerImpl(this.context);
  }
}
