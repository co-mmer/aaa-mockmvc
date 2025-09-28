package io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssertReason.reasonOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAnswerExceptionFormatter {

  @Since("2.0.0")
  public static String createMessage(
      TestStepDto step, String stepName, String content, String simpleName) {
    return """
        %sAnswer step: %s(%s)
        Failure: unable to map response body to '%s'.
        """
        .formatted(createStepPrefix(step), stepName, content, simpleName);
  }

  @Since("2.0.0")
  public static String createMessage(
      TestStepDto step,
      String stepName,
      String content,
      String keySimpleName,
      String valueSimpleName) {
    return """
        %sAnswer step: %s(%s)
        Failure: unable to map response body to 'Map<%s, %s>'.
        """
        .formatted(createStepPrefix(step), stepName, content, keySimpleName, valueSimpleName);
  }

  private static String createStepPrefix(TestStepDto step) {
    return step == null ? StringUtils.EMPTY : reasonOf(step) + "\n";
  }
}
