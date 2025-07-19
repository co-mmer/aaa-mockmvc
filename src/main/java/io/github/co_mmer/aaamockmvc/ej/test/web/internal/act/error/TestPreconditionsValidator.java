package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.section.TestStepImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestPreconditionsValidator {

  private static final String ARRANGE_MESSAGE_CONTEXT =
      "Arrange error: Arrange phase already started";

  private static final String ACT_MESSAGE_CONTEXT =
      "Act error: No arrange step configured. "
          + "Call arrange().get|post|put|patch|delete|head|options(...) before act().perform()";

  private static final String ACT_MESSAGE_PERFORM =
      "Act error: No 'arrange()' step configured. "
          + "Call 'arrange().get|post|put|patch|delete|head|options(...)' "
          + "before 'act().perform()'";

  private static final String ASSERTS_MESSAGE_CONTEXT =
      "Assert error: No 'arrange()' step configured. "
          + "Call arrange().get|post|put|patch|delete|head|options(...) before 'asserts()'";
  private static final String ASSERTS_MESSAGE_ACT =
      "Assert error: No 'act()' step configured. Call 'act().perform()' before 'asserts()'";

  private static final String ANSWER_MESSAGE_CONTEXT =
      "Answer error: No arrange step configured. Call arrange().get|post|put|patch|delete|head|options(...) before 'answer()'";

  private static final String ANSWER_MESSAGE_ACT =
      "Answer error: No 'act()' step configured. Call 'act().perform()' before 'answer()'";

  public static void act(TestAAAContext context) {
    if (context == null) {
      throw new IllegalStateException(ACT_MESSAGE_CONTEXT);
    }
  }

  public static void act(TestStepImpl context) {
    if (context == null) {
      throw new IllegalStateException(ACT_MESSAGE_CONTEXT);
    }
  }

  public static void actPerform(TestAAAContext context) {
    if (context.getArrangeResult() == null) {
      var step = createStepPrefix(context);
      throw new IllegalStateException(step + ACT_MESSAGE_PERFORM);
    }
  }

  private static String createStepPrefix(TestAAAContext context) {
    return context.getStep() == null
        ? StringUtils.EMPTY
        : "Step '%s' · ".formatted(context.getStep().name());
  }

  public static void asserts(TestAAAContext context) {
    if (context == null) {
      throw new IllegalStateException(ASSERTS_MESSAGE_CONTEXT);
    }
    if (context.getActResult() == null) {
      var step = createStepPrefix(context);
      throw new IllegalStateException(step + ASSERTS_MESSAGE_ACT);
    }
  }

  public static void answer(TestAAAContext context) {
    if (context == null) {
      throw new IllegalStateException(ANSWER_MESSAGE_CONTEXT);
    }
    if (context.getActResult() == null) {
      var step = createStepPrefix(context);
      throw new IllegalStateException(step + ANSWER_MESSAGE_ACT);
    }
  }

  public static void arrange(TestAAAContext context) {
    if (context.getArrangeResult() != null) {
      var step = createStepPrefix(context);
      throw new IllegalStateException(step + ARRANGE_MESSAGE_CONTEXT);
    }
  }

  public static void arrange(TestStepImpl context) {
    if (context != null) {
      throw new IllegalStateException(ARRANGE_MESSAGE_CONTEXT);
    }
  }

  public static void asserts(TestStepImpl context) {
    if (context == null) {
      throw new IllegalStateException(ASSERTS_MESSAGE_CONTEXT);
    }
    if (context.getContext().getActResult() == null) {
      throw new IllegalStateException(ASSERTS_MESSAGE_ACT);
    }
  }

  public static void answer(TestStepImpl context) {
    if (context == null) {
      throw new IllegalStateException(ANSWER_MESSAGE_CONTEXT);
    }
  }
}
