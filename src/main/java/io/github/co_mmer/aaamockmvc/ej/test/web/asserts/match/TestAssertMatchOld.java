package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Utility class for asserting that a collection or single object matches a set of conditions
 * according to a specified match type (ALL, ANY, NONE). This class provides overloaded methods to
 * handle both collections and single objects, allowing flexible assertions in unit tests or
 * validation logic.
 *
 * <p>Usage examples:
 *
 * <pre>{@code
 * // For a collection of objects:
 * TestAssertMatch.assertMatch(TestAssertMatchType.ALL, myList, List.of(cond1, cond2));
 *
 * // For a single object:
 * TestAssertMatch.assertMatch(TestAssertMatchType.ANY, myObject, List.of(cond1, cond2));
 * }</pre>
 *
 * @since 1.6.0
 */
@Deprecated(since = "1.6", forRemoval = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAssertMatchOld {

  /**
   * Asserts that a collection of objects matches the given conditions according to the specified
   * match type.
   *
   * <ul>
   *   <li><b>ALL:</b> All conditions must be satisfied for every element in the collection.
   *   <li><b>ANY:</b> At least one condition must be satisfied for every element in the collection.
   *   <li><b>NONE:</b> None of the conditions should be satisfied for any element in the
   *       collection.
   * </ul>
   *
   * @param matchType The type of match to perform ({@link TestAssertMatchType#ALL}, {@link
   *     TestAssertMatchType#ANY}, {@link TestAssertMatchType#NONE}).
   * @param actual The collection of objects to check.
   * @param conditions The conditions to apply to each element in the collection. Each condition is
   *     a {@link Predicate} that tests a single element.
   * @param <T> The type of objects in the collection.
   * @throws AssertionError If the assertion fails according to the match type and conditions.
   * @since 1.6.0
   */
  @Deprecated(since = "1.6", forRemoval = true)
  public static <T> void assertMatch(
      @NonNull TestAssertMatchType matchType,
      @NonNull Collection<T> actual,
      @NonNull Collection<Predicate<T>> conditions) {

    var matches =
        switch (matchType) {
          case ALL ->
              actual.stream()
                  .allMatch(item -> conditions.stream().allMatch(cond -> cond.test(item)));
          case ANY ->
              actual.stream()
                  .anyMatch(item -> conditions.stream().anyMatch(cond -> cond.test(item)));
          case NONE ->
              actual.stream()
                  .noneMatch(item -> conditions.stream().anyMatch(cond -> cond.test(item)));
        };

    assertThat(matchType.getReason() + " Actual: " + actual, matches, is(true));
  }

  /**
   * Asserts that a single object matches the given conditions according to the specified match
   * type.
   *
   * <ul>
   *   <li><b>ALL:</b> All conditions must be satisfied for the object.
   *   <li><b>ANY:</b> At least one condition must be satisfied for the object.
   *   <li><b>NONE:</b> None of the conditions should be satisfied for the object.
   * </ul>
   *
   * @param matchType The type of match to perform ({@link TestAssertMatchType#ALL}, {@link
   *     TestAssertMatchType#ANY}, {@link TestAssertMatchType#NONE}).
   * @param actual The object to check.
   * @param conditions The conditions to apply to the object. Each condition is a {@link Predicate}
   *     that tests the object.
   * @param <T> The type of the object.
   * @throws AssertionError If the assertion fails according to the match type and conditions.
   * @since 1.6.0
   */
  @Deprecated(since = "1.6", forRemoval = true)
  public static <T> void assertMatch(
      @NonNull TestAssertMatchType matchType,
      @NonNull T actual,
      @NonNull Collection<Predicate<T>> conditions) {

    assertMatch(matchType, List.of(actual), conditions);
  }
}
