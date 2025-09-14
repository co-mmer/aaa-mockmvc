package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.validator;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestActPreconditionsValidator {

  private static final String MISSING_ARRANGE_BEFORE_ACT_PERFORM =
      "Act error: No 'arrange()' step configured. "
          + "Call 'arrange().get|post|put|patch|delete|head|options(...)' "
          + "before 'act().perform()'";

  @Since("2.0.0")
  public static void verifyPerform(TestAAAContext context) {
    if (context.getArrangeResult() == null) {
      var message =
          """
              %s%s
              """
              .formatted(createStepPrefix(context), MISSING_ARRANGE_BEFORE_ACT_PERFORM);
      throw new IllegalStateException(message);
    }
  }

  private static String createStepPrefix(TestAAAContext context) {
    return context.getStep() == null
        ? StringUtils.EMPTY
        : "Step '%s'%n".formatted(context.getStep().name());
  }
}
