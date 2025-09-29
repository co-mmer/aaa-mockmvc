package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssertReason.reasonOf;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.isBlank;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestActFailedErrorFormatter {

  @Since("2.0.0")
  public static String createMessage(TestStepDto step, TestArrangeResult arrange, Throwable cause) {
    return """
        %sACT failed: %s %s
        %s
        Cause: %s: %s
        """
        .formatted(
            step == null ? EMPTY : reasonOf(step) + "\n",
            arrange.getUrl().getMethod(),
            arrange.getUrl().getUri(),
            arrange.asMessage(),
            cause.getClass().getSimpleName(),
            getMessage(cause));
  }

  private static String getMessage(Throwable throwable) {
    var message = throwable.getMessage();
    return isBlank(message) ? "<no message>" : message;
  }
}
