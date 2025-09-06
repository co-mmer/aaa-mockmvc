package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestActFailedErrorMessage {

  @Since("2.0.0")
  public static String build(TestArrangeResult arrange, Throwable cause) {
    return "ACT failed: "
        + arrange.getUrl().getMethod()
        + " "
        + arrange.getUrl().getUri()
        + "\n"
        + arrange.asMessage()
        + "\n"
        + "Cause: "
        + cause.getClass().getSimpleName()
        + ": "
        + getMessage(cause);
  }

  private static String getMessage(Throwable throwable) {
    var message = throwable.getMessage();
    return StringUtils.isBlank(message) ? "<no message>" : message;
  }
}
