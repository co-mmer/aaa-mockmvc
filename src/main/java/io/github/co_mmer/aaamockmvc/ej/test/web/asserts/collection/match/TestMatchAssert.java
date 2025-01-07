package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.match;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Utility class to perform assertions on a list of objects based on given conditions. This class
 * provides methods to check whether the elements in the list meet a set of conditions.
 * <p>
 * It offers three types of matches: - ALL: All conditions must be met for every element in the
 * list. - ANY: At least one condition must be met for every element in the list. - NONE: None of
 * the conditions should be met for any element in the list.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestMatchAssert {

  /**
   * Performs a match check on a list of objects based on the given conditions. The match can be
   * performed in three ways: - ALL: All conditions must be met for every element in the list. -
   * ANY: At least one condition must be met for every element in the list. - NONE: None of the
   * conditions should be met for any element in the list.
   *
   * @param matchType  The type of match to perform. Can be one of the following values:
   *                   {@link TestMatchType#ALL}, {@link TestMatchType#ANY},
   *                   {@link TestMatchType#NONE}.
   * @param actual     The list of objects to be checked.
   * @param conditions The conditions to be applied to each element in the list. Each condition is a
   *                   Predicate that tests a single element.
   * @param <T>        The type of the objects in the list.
   * @throws AssertionError If the match check fails, an AssertionError is thrown.
   */
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
