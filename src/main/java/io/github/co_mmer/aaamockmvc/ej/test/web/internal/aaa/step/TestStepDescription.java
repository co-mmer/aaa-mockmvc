package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestStepDescription {

  @Since("2.0.2")
  public static String describe(TestStepDto step) {
    return step == null || StringUtils.isBlank(step.name())
        ? StringUtils.EMPTY
        : "Step '%s'".formatted(step.name());
  }
}
