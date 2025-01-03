package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.match;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestMatchAssert {

  public static <T> void assertMatch(
      @NonNull TestMatchType matchType,
      @NonNull List<T> actual,
      @NonNull List<Predicate<T>> conditions) {

    var matches =
        switch (matchType) {
          case ALL -> matchAll(conditions, actual);
          case ANY -> matchAny(conditions, actual);
          case NONE -> matchNone(conditions, actual);
        };

    assertThat(matchType.getReason() + " Actual list: " + actual, matches, is(true));
  }

  private static <T> boolean matchAll(List<Predicate<T>> conditions, List<T> actual) {
    return actual.stream()
        .allMatch(item -> conditions.stream().allMatch(condition -> condition.test(item)));
  }

  private static <T> boolean matchAny(List<Predicate<T>> conditions, List<T> actual) {
    return actual.stream()
        .anyMatch(item -> conditions.stream().anyMatch(condition -> condition.test(item)));
  }

  private static <T> boolean matchNone(List<Predicate<T>> conditions, List<T> actual) {
    return actual.stream()
        .noneMatch(item -> conditions.stream().anyMatch(condition -> condition.test(item)));
  }
}
