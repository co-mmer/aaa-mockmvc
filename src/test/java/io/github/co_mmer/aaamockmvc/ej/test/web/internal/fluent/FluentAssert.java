package io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent;

import java.util.Optional;

public final class FluentAssert {

  private FluentAssert() {}

  public static <A> void assertThat(A actual, Matcher<? super A> matcher) {
    Optional<String> failure = matcher.match(actual);
    if (failure.isPresent()) {
      throw new AssertionError(failure.get());
    }
  }

  public interface Matcher<A> {

    Optional<String> match(A actual);
  }
}
