package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssertDetail.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.Conditions;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class CollectionAssertion<E> {
  private final CollectionValue<E> actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public CollectionAssertion(@NonNull CollectionValue<E> actual) {
    this(actual, null);
  }

  @Since("2.1.0")
  public CollectionAssertion(@NonNull CollectionValue<E> actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void isEmpty() {
    if (!this.actual.values().isEmpty()) {
      throw failure(
          this.step,
          "The response collection must contain no elements.",
          "empty",
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void isNotEmpty() {
    if (this.actual.values().isEmpty()) {
      throw failure(
          this.step,
          "The response collection must contain at least one element.",
          "not empty",
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void hasSize(int expected) {
    if (this.actual.values().size() != expected) {
      throw failure(
          this.step,
          "The response collection must contain the expected number of elements.",
          expected,
          this.actual.values().size());
    }
  }

  @Since("2.1.0")
  public void isEqualTo(@NonNull CollectionValue<E> expected) {
    if (!this.actual.comparison().equals(expected.comparison())) {
      throw failure(
          this.step,
          "The response collection must equal the expected collection; lists preserve order and repetitions, sets ignore order.",
          expected.values(),
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void contains(@NonNull CollectionValue<E> expected) {
    if (expected.values().isEmpty()) {
      throw new InvalidAssertionException(
          "contains() requires at least one expected element. Use isEmpty() to check for an empty collection.");
    }
    if (!this.actual.comparison().containsAll(expected.comparison())) {
      throw failure(
          this.step,
          "Every expected element must be present in the response collection; additional elements are allowed.",
          expected.values(),
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void notContains(@NonNull CollectionValue<E> expected) {
    if (this.actual.values().isEmpty()) {
      throw new InvalidAssertionException(
          "notContains() requires a non-empty response collection. Use isEmpty() to check for an empty collection.");
    }
    if (expected.comparison().stream().anyMatch(this.actual.comparison()::contains)) {
      throw failure(
          this.step,
          "None of the specified elements may occur in the response collection.",
          expected.values(),
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void containsAnyOrder(@NonNull CollectionValue<E> expected) {
    if (!occurrences(this.actual.comparison()).equals(occurrences(expected.comparison()))) {
      throw failure(
          this.step,
          "The collections must contain exactly the same elements with the same occurrence counts; order is ignored.",
          expected.values(),
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void matchAll(@NonNull Conditions<E> conditions) {
    if (this.actual.values().isEmpty()) {
      throw new InvalidAssertionException(
          "matchAll() requires a non-empty response collection. Use isEmpty() to check for an empty collection.");
    }
    if (!this.actual.values().stream().allMatch(conditions::all)) {
      throw failure(
          this.step,
          "Every response element must satisfy every supplied condition.",
          "matches all conditions",
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void matchAny(@NonNull Conditions<E> conditions) {
    if (!this.actual.values().stream().anyMatch(conditions::any)) {
      throw failure(
          this.step,
          "At least one response element must satisfy at least one supplied condition.",
          "matches any conditions",
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void matchNone(@NonNull Conditions<E> conditions) {
    if (this.actual.values().stream().anyMatch(conditions::any)) {
      throw failure(
          this.step,
          "No response element may satisfy any supplied condition.",
          "matches no conditions",
          this.actual.values());
    }
  }

  @NonNull
  private static Map<String, Integer> occurrences(@NonNull Collection<String> values) {
    Map<String, Integer> counts = new HashMap<>();
    values.forEach(v -> counts.merge(v, 1, Integer::sum));
    return counts;
  }
}
