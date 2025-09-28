package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestAssert {

  @Since("2.0.0")
  public static <T> void assertThat(TestStepDto step, T actual, Matcher<? super T> matcher) {
    MatcherAssert.assertThat(reasonOf(step), actual, matcher);
  }

  @Since("2.0.0")
  public static <T> void assertThat(
      TestStepDto step, String what, T actual, Matcher<? super T> matcher) {
    MatcherAssert.assertThat(reasonOf(step, what), actual, matcher);
  }

  @Since("2.0.0")
  private static String reasonOf(TestStepDto step) {
    return step != null ? String.format("Step '%s'", step.name()) : "";
  }

  @Since("2.0.0")
  private static String reasonOf(TestStepDto step, String what) {
    return reasonOf(step) + " ⇒ " + what;
  }
}
