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

  @Since("2.0.0")
  public static String reasonContentOf(TestStepDto step, String who, String with, String what) {
    var base = reasonOf(step);

    var detailsBuilder = new StringBuilder();
    addWho(who, detailsBuilder);
    addWith(with, detailsBuilder);
    addWhat(what, detailsBuilder);

    var detail = detailsBuilder.toString();
    return StringUtils.isBlank(base) ? detail : base + " ⇒ " + detail;
  }

  private static void addWho(String who, StringBuilder sb) {
    if (StringUtils.isNotBlank(who)) {
      sb.append("'").append(who).append("' — Reason");
    }
  }

  private static void addWith(String with, StringBuilder sb) {
    if (StringUtils.isNotBlank(with)) {
      if (!sb.isEmpty()) {
        sb.append(": ");
      }
      sb.append("Response body ").append("'").append(with).append("'");
    }
  }

  private static void addWhat(String what, StringBuilder sb) {
    if (StringUtils.isNotBlank(what)) {
      sb.append(" ").append(what);
    }
  }
}
