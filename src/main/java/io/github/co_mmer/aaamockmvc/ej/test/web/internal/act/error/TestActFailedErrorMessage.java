package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
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
        + safeMsg(cause);
  }

  private static String safeMsg(Throwable throwable) {
    var m = (throwable != null ? throwable.getMessage() : null);
    return (m == null || m.isBlank()) ? "<no message>" : m.trim();
  }
}
