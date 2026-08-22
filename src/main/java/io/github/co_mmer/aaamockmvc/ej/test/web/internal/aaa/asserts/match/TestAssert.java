package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertReason.reasonOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

@Since("2.0.0")
@Deprecated(forRemoval = true)
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
}
