package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.step;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer.TestAnswerImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.TestArrangeImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
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
    return new TestArrangeImpl(this.context);
  }

  @Override
  public TestAct act() {
    return new TestActImpl(this.context);
  }

  @Override
  public TestAssert asserts() {
    return new TestAssertImpl(this.context);
  }

  @Override
  public TestAnswer answer() {
    return new TestAnswerImpl(this.context);
  }
}
