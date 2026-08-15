package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.RequestBuilder;
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
  private TestArrangeResult arrangeResult;
  @Deprecated
  private TestActResult actResult;
  private TestActResult2 actResult2;
  private TestAssertResult<?> assertResult;
  private TestAnswerResult<?> answerResult;

  private RequestBuilder requestBuilder;
}
