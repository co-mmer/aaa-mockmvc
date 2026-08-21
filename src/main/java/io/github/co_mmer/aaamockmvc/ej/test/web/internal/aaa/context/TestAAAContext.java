package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer.TestAnswerResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.TestArrangeBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Since("2.0.0")
@Getter
@Setter
@RequiredArgsConstructor
public final class TestAAAContext {

  private final TestEnvironment environment;
  private TestStepDto step;

  private TestArrangeBuilder arrangeBuilder;
  private TestActResult actResult;
  private AssertOperand<?, ?> assertOperand;
  private TestAnswerResult<?> answerResult;

  @Deprecated private TestAssertResult<?> assertResult;
}
