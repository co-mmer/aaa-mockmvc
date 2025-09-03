package io.github.co_mmer.aaamockmvc.ej.test.web.internal.precondition;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.step.TestStepImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestPreconditionsValidator {

  private static final String MISSING_ARRANGE_BEFORE_ACT =
      "Act error: No 'arrange()' step configured. "
          + "Call 'arrange().get|post|put|patch|delete|head|options(...)' before 'act()'";

  private static final String MISSING_ARRANGE_BEFORE_ACT_PERFORM =
      "Act error: No 'arrange()' step configured. "
          + "Call 'arrange().get|post|put|patch|delete|head|options(...)' "
          + "before 'act().perform()'";

  private static final String MISSING_ARRANGE_ACT_BEFORE_ASSERTS =
      "Assert error: No 'arrange()' / 'act()' steps configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' followed by 'act().perform()' before 'asserts()'";
  private static final String MISSING_ASSERTS_BEFORE_ACT =
      "Assert error: No 'act()' step configured. Call 'act().perform()' before 'asserts()'";

  private static final String MISSING_ARRANGE_ACT_BEFORE_ANSWER =
      "Answer error: No 'arrange()' / 'act()' steps configured. Call 'arrange().get|post|put|patch|delete|head|options(...)' followed by 'act().perform()' before 'answer()'";

  private static final String MISSING_ACT_BEFORE_ANSWER =
      "Answer error: No 'act()' step configured. Call 'act().perform()' before 'answer()'";

  public static void act(TestStepImpl step) {
    verifyActStepInternal(step);
    verifyActStepCustom(step);
  }

  private static void verifyActStepInternal(TestStepImpl step) {
    if (step == null) {
      throw new IllegalStateException(MISSING_ARRANGE_BEFORE_ACT);
    }
  }

  private static void verifyActStepCustom(TestStepImpl step) {
    if (step.getContext().getArrangeResult() == null) {
      var stepPrefix = createStepPrefix(step.getContext());
      throw new IllegalStateException(stepPrefix + MISSING_ARRANGE_BEFORE_ACT);
    }
  }

  public static void actPerform(TestAAAContext context) {
    if (context.getArrangeResult() == null) {
      var stepPrefix = createStepPrefix(context);
      throw new IllegalStateException(stepPrefix + MISSING_ARRANGE_BEFORE_ACT_PERFORM);
    }
  }

  public static void asserts(TestStepImpl step) {
    verifyAssertsStepInternal(step);
    verifyAssertsStepCustom(step);
  }

  private static void verifyAssertsStepInternal(TestStepImpl step) {
    if (step == null) {
      throw new IllegalStateException(MISSING_ARRANGE_ACT_BEFORE_ASSERTS);
    }
  }

  private static void verifyAssertsStepCustom(TestStepImpl step) {
    if (isActResultNull(step)) {
      var stepPrefix = createStepPrefix(step.getContext());
      throw new IllegalStateException(stepPrefix + MISSING_ASSERTS_BEFORE_ACT);
    }
  }

  public static void answer(TestStepImpl step) {
    verifyAnswerStepInternal(step);
    verifyAnswerStepCustom(step);
  }

  private static void verifyAnswerStepInternal(TestStepImpl step) {
    if (step == null) {
      throw new IllegalStateException(MISSING_ARRANGE_ACT_BEFORE_ANSWER);
    }
  }

  private static void verifyAnswerStepCustom(TestStepImpl step) {
    if (isActResultNull(step)) {
      var stepPrefix = createStepPrefix(step.getContext());
      throw new IllegalStateException(stepPrefix + MISSING_ACT_BEFORE_ANSWER);
    }
  }

  private static boolean isActResultNull(TestStepImpl step) {
    return step.getContext().getActResult() == null;
  }

  private static String createStepPrefix(TestAAAContext context) {
    return context.getStep() == null
        ? StringUtils.EMPTY
        : "Step '%s' · ".formatted(context.getStep().name());
  }
}
