package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * Fluent assertion class for verifying that elements in a collection satisfy (or do not satisfy) a
 * set of predicates.
 *
 * <p>Supports assertions for "all", "any", or "none" of the provided conditions.
 *
 * @param <T> the type of the elements to assert
 * @since 1.6.0
 */
public class TestAssertMatch<T> {

  private final Collection<T> actual;

  private TestAssertMatch(Collection<T> actual) {
    this.actual = actual;
  }

  /**
   * Starts an assertion for a single object.
   *
   * @param actual the single object to assert
   * @param <T> the type of the object
   * @return a {@code TestAssertMatch} instance for fluent assertions
   * @since 1.6.0
   */
  public static <T> TestAssertMatch<T> assertThat(T actual) {
    return new TestAssertMatch<>(List.of(actual));
  }

  /**
   * Starts an assertion for a collection of objects.
   *
   * @param actual the collection to assert
   * @param <T> the type of the objects in the collection
   * @return a {@code TestAssertMatch} instance for fluent assertions
   * @since 1.6.0
   */
  public static <T> TestAssertMatch<T> assertThat(Collection<T> actual) {
    return new TestAssertMatch<>(actual);
  }

  /**
   * Asserts that all elements of the collection satisfy all provided conditions.
   *
   * @param conditions one or more predicates that each element must satisfy
   * @throws AssertionError if at least one element does not satisfy all conditions
   * @since 1.6.0
   */
  @SafeVarargs
  public final void matchesAll(Predicate<T>... conditions) {
    var matches =
        actual.stream()
            .allMatch(item -> Arrays.stream(conditions).allMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          "Expected all conditions to match for <" + actual + ">, but at least one did not.");
    }
  }

  /**
   * Asserts that at least one element of the collection satisfies at least one of the provided
   * conditions.
   *
   * @param conditions one or more predicates; at least one must match for at least one element
   * @throws AssertionError if none of the conditions match any element
   * @since 1.6.0
   */
  @SafeVarargs
  public final void matchesAny(Predicate<T>... conditions) {
    var matches =
        actual.stream()
            .anyMatch(item -> Arrays.stream(conditions).anyMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          "Expected any condition to match for <" + actual + ">, but none did.");
    }
  }

  /**
   * Asserts that none of the elements in the collection satisfy any of the provided conditions.
   *
   * @param conditions one or more predicates that no element should satisfy
   * @throws AssertionError if at least one element satisfies any condition
   * @since 1.6.0
   */
  @SafeVarargs
  public final void matchesNone(Predicate<T>... conditions) {
    var matches =
        actual.stream()
            .noneMatch(item -> Arrays.stream(conditions).anyMatch(cond -> cond.test(item)));
    if (!matches) {
      throw new AssertionError(
          "Expected none of the conditions to match for <" + actual + ">, but at least one did.");
    }
  }
}
