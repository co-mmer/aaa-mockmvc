package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertReason.reasonOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Since("1.6.0")
public class TestAssertMatch<T> {

  private final TestStepDto step;
  private final Collection<T> actual;

  @Since("1.6.0")
  private TestAssertMatch(TestStepDto step, Collection<T> actual) {
    this.step = step;
    this.actual = actual;
  }

  @Since("1.6.0")
  public static <T> TestAssertMatch<T> assertThat(TestStepDto step, T actual) {
    return new TestAssertMatch<>(step, List.of(actual));
  }

  @Since("1.6.0")
  public static <T> TestAssertMatch<T> assertThat(TestStepDto step, Collection<T> actual) {
    return new TestAssertMatch<>(step, actual);
  }

  @Since("1.6.0")
  @SafeVarargs
  public final void matchAll(Predicate<T>... conditions) {
    var matches =
        this.actual.stream()
            .allMatch(item -> Arrays.stream(conditions).allMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          reasonOf(
              this.step,
              "Expected all conditions to match for <"
                  + this.actual
                  + ">, but at least one did not."));
    }
  }

  @Since("1.6.0")
  @SafeVarargs
  public final void matchAny(Predicate<T>... conditions) {
    var matches =
        this.actual.stream()
            .anyMatch(item -> Arrays.stream(conditions).anyMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          reasonOf(
              this.step,
              "Expected any condition to match for <" + this.actual + ">, but none did."));
    }
  }

  @Since("1.6.0")
  @SafeVarargs
  public final void matchNone(Predicate<T>... conditions) {
    var matches =
        this.actual.stream()
            .noneMatch(item -> Arrays.stream(conditions).anyMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          reasonOf(
              this.step,
              "Expected none of the conditions to match for <"
                  + this.actual
                  + ">, but at least one did."));
    }
  }
}
