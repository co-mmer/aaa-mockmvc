package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestBuilder;
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

  private RequestBuilder requestBuilder;

  private TestActResult actResult;

  private TestAssertResult<?> assertResult;
  private TestAnswerResult<?> answerResult;
}
