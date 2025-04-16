package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Collection;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Utility class to perform assertions on a list of objects based on given conditions. This class
 * provides methods to check whether the elements in the list meet a set of conditions.
 *
 * <p>It offers three types of matches: - ALL: All conditions must be met for every element in the
 * list. - ANY: At least one condition must be met for every element in the list. - NONE: None of
 * the conditions should be met for any element in the list.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAssertMatchCollection {

  /**
   * Performs a match check on a collection of objects based on the given conditions. The match can
   * be performed in three ways: - ALL: All conditions must be met for every element in the list. -
   * ANY: At least one condition must be met for every element in the list. - NONE: None of the
   * conditions should be met for any element in the list.
   *
   * @param matchType The type of match to perform. Can be one of the following values: {@link
   *     TestAssertMatchType#ALL}, {@link TestAssertMatchType#ANY}, {@link
   *     TestAssertMatchType#NONE}.
   * @param actual The list of objects to be checked.
   * @param conditions The conditions to be applied to each element in the list. Each condition is a
   *     Predicate that tests a single element.
   * @param <T> The type of the objects in the list.
   * @throws AssertionError If the match check fails, an AssertionError is thrown.
   */
  public static <T> void assertMatch(
      @NonNull TestAssertMatchType matchType,
      @NonNull Collection<T> actual,
      @NonNull Collection<Predicate<T>> conditions) {

    var matches =
        switch (matchType) {
          case ALL -> matchAll(conditions, actual);
          case ANY -> matchAny(conditions, actual);
          case NONE -> matchNone(conditions, actual);
        };

    assertThat(matchType.getReason() + " Actual " + actual, matches, is(true));
  }

  private static <T> boolean matchAll(Collection<Predicate<T>> conditions, Collection<T> actual) {
    return actual.stream()
        .allMatch(item -> conditions.stream().allMatch(condition -> condition.test(item)));
  }

  private static <T> boolean matchAny(Collection<Predicate<T>> conditions, Collection<T> actual) {
    return actual.stream()
        .anyMatch(item -> conditions.stream().anyMatch(condition -> condition.test(item)));
  }

  private static <T> boolean matchNone(Collection<Predicate<T>> conditions, Collection<T> actual) {
    return actual.stream()
        .noneMatch(item -> conditions.stream().anyMatch(condition -> condition.test(item)));
  }
}
