package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@Since("1.6.0")
public class TestAssertMatch<T> {

  private final Collection<T> actual;

  @Since("1.6.0")
  private TestAssertMatch(Collection<T> actual) {
    this.actual = actual;
  }

  @Since("1.6.0")
  public static <T> TestAssertMatch<T> assertThat(T actual) {
    return new TestAssertMatch<>(List.of(actual));
  }

  @Since("1.6.0")
  public static <T> TestAssertMatch<T> assertThat(Collection<T> actual) {
    return new TestAssertMatch<>(actual);
  }

  @Since("1.6.0")
  @SafeVarargs
  public final void matchAll(Predicate<T>... conditions) {
    var matches =
        actual.stream()
            .allMatch(item -> Arrays.stream(conditions).allMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          "Expected all conditions to match for <" + actual + ">, but at least one did not.");
    }
  }

  @Since("1.6.0")
  @SafeVarargs
  public final void matchAny(Predicate<T>... conditions) {
    var matches =
        actual.stream()
            .anyMatch(item -> Arrays.stream(conditions).anyMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          "Expected any condition to match for <" + actual + ">, but none did.");
    }
  }

  @Since("1.6.0")
  @SafeVarargs
  public final void matchNone(Predicate<T>... conditions) {
    var matches =
        actual.stream()
            .noneMatch(item -> Arrays.stream(conditions).anyMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          "Expected none of the conditions to match for <" + actual + ">, but at least one did.");
    }
  }
}
