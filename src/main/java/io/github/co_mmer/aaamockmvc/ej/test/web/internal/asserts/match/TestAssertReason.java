package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestAssertReason {

  @Since("2.0.0")
  public static String reasonOf(TestStepDto step) {
    return step == null ? StringUtils.EMPTY : formatReason(step.name());
  }

  private static String formatReason(String name) {
    var reason = StringUtils.isBlank(name) ? StringUtils.UNSET : name;
    return String.format("Step '%s'", reason);
  }

  @Since("2.0.0")
  public static String reasonOf(TestStepDto step, String what) {
    var reason = reasonOf(step);
    return StringUtils.isBlank(reason) ? what : reason + " ⇒ " + what;
  }
}
