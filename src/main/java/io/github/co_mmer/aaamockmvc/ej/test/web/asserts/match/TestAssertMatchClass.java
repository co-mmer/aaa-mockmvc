package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class provides static methods for performing various assertions on a list of conditions. It
 * can be used to check whether a given input satisfies all, any, or none of the provided
 * conditions.
 *
 * <p>The main method is {@link #assertMatch}, which performs the respective check based on the
 * given {@link TestAssertMatchType}.
 *
 * <p>The methods within this class are not intended for direct external use but serve as helper
 * methods for the {@link #assertMatch} method.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAssertMatchClass {

  /**
   * Performs a match check on a list of objects based on the given conditions. The match can be
   * performed in three ways:
   *
   * <ul>
   *   <li>ALL: All conditions must be met for every element in the list.
   *   <li>ANY: At least one condition must be met for every element in the list.
   *   <li>NONE: None of the conditions should be met for any element in the list.
   * </ul>
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
      @NonNull T actual,
      @NonNull List<Predicate<T>> conditions) {

    var matches =
        switch (matchType) {
          case ALL -> matchAll(conditions, actual);
          case ANY -> matchAny(conditions, actual);
          case NONE -> matchNone(conditions, actual);
        };

    assertThat(matchType.getReason() + " Actual: " + actual, matches, is(true));
  }

  private static <T> boolean matchAll(List<Predicate<T>> conditions, T actual) {
    return conditions.stream().allMatch(condition -> condition.test(actual));
  }

  private static <T> boolean matchAny(List<Predicate<T>> conditions, T actual) {
    return conditions.stream().anyMatch(condition -> condition.test(actual));
  }

  private static <T> boolean matchNone(List<Predicate<T>> conditions, T actual) {
    return conditions.stream().noneMatch(condition -> condition.test(actual));
  }
}
