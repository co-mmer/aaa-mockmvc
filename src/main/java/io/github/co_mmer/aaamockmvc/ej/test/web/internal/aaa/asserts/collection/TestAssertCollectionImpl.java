package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_ARRAY;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert2Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert3Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert4Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert5Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertLCollection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertMatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Since("1.4.0")
@RequiredArgsConstructor
public final class TestAssertCollectionImpl<E>
    implements TestAssert1Collection<E>,
        TestAssert2Collection<E>,
        TestAssert3Collection<E>,
        TestAssert4Collection<E>,
        TestAssert5Collection<E>,
        TestAssertLCollection {

  private final TestAAAContext context;

  @Override
  public TestAssert2Collection<E> isNotEmpty() {
    var actual = this.context.getAssertOperand().normalizedValue();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: not empty
              Actual:   null
              Reason:   The response body was absent.
              """
                  .strip());
    }

    assertThat(this.context.getStep(), actual, not(anyOf(is(EMPTY), is(EMPTY_ARRAY))));
    return this;
  }

  @Override
  public TestAssertLCollection isEmpty() {
    var actual = (Collection<?>) this.context.getAssertOperand().actual();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: empty
              Actual:   null
              Reason:   The response body was absent.
              """
                  .strip());
    }

    assertThat(this.context.getStep(), actual.size(), is(0));
    return this;
  }

  @Override
  public TestAssert2Collection<E> hasSize(int expectedSize) {
    var actual = (Collection<?>) this.context.getAssertOperand().actual();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: size %d
              Actual:   null
              Reason:   The response body was absent.
              """
                  .formatted(expectedSize)
                  .strip());
    }

    assertThat(this.context.getStep(), actual.size(), is(expectedSize));
    return this;
  }

  public TestAssertLCollection isEqualTo(@NonNull Collection<E> expectedCollection) {
    var expectedValue = AssertValue.expectedCollection(expectedCollection);

    @SuppressWarnings("unchecked")
    var actual = (List<String>) this.context.getAssertOperand().normalizedValue();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: %s
              Actual:   null
              Reason:   The response body was absent.
              """
                  .formatted(expectedCollection)
                  .strip());
    }

    assertThat(this.context.getStep(), actual, is(expectedValue.normalizedValue()));
    return this;
  }

  public TestAssert3Collection<E> contains(@NonNull Collection<E> expectedElements) {
    var expectedValue = AssertValue.expectedCollection(expectedElements);

    @SuppressWarnings("unchecked")
    var actual = (List<String>) this.context.getAssertOperand().normalizedValue();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: contains %s
              Actual:   null
              Reason:   The response body was absent.
              """
                  .formatted(expectedElements)
                  .strip());
    }

    var normalizeExpected = expectedValue.normalizedValue().toArray(String[]::new);
    assertThat(this.context.getStep(), actual, hasItems(normalizeExpected));
    return this;
  }

  @SafeVarargs
  public final TestAssert3Collection<E> contains(@NonNull E... expectedElements) {
    var expectedValue = AssertValue.expectedElements(expectedElements);
    return contains(List.of(expectedValue.value()));
  }

  public TestAssert3Collection<E> containsAnyOrder(@NonNull Collection<E> expectedCollection) {
    var expectedValue = AssertValue.expectedCollection(expectedCollection);

    @SuppressWarnings("unchecked")
    var actual = (List<String>) this.context.getAssertOperand().normalizedValue();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: contains in any order %s
              Actual:   null
              Reason:   The response body was absent.
              """
                  .formatted(expectedCollection)
                  .strip());
    }

    var normalizeExpected = expectedValue.normalizedValue().toArray();
    assertThat(this.context.getStep(), actual, containsInAnyOrder(normalizeExpected));
    return this;
  }

  public TestAssert3Collection<E> notContains(@NonNull Collection<E> unexpectedElements) {
    var unexpectedValue = AssertValue.unexpectedElements(unexpectedElements);

    @SuppressWarnings("unchecked")
    var actual = (List<String>) this.context.getAssertOperand().normalizedValue();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: does not contain %s
              Actual:   null
              Reason:   The response body was absent.
              """
                  .formatted(unexpectedElements)
                  .strip());
    }

    var normalizeExpected = unexpectedValue.normalizedValue().toArray(String[]::new);
    assertThat(this.context.getStep(), actual, not(hasItems(normalizeExpected)));
    return this;
  }

  @SafeVarargs
  public final TestAssert3Collection<E> notContains(@NonNull E... unexpectedElements) {
    var unexpectedValue = AssertValue.unexpectedElements(unexpectedElements);
    return notContains(List.of(unexpectedValue.value()));
  }

  @Override
  public TestAssert4Collection<E> matchAll(@NonNull Predicate<E> condition) {
    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition};
    return matchAll(predicates);
  }

  @Override
  public TestAssert4Collection<E> matchAll(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2) {

    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition1, condition2};
    return matchAll(predicates);
  }

  @Override
  public TestAssert4Collection<E> matchAll(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3) {

    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition1, condition2, condition3};
    return matchAll(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssert4Collection<E> matchAll(@NonNull Predicate<E>... conditions) {
    var expectedValue = AssertValue.matchConditions(conditions);

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertOperand().actual();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: matches all conditions
              Actual:   null
              Reason:   The response body was absent.
              """
                  .strip());
    }

    TestAssertMatch.assertThat(this.context.getStep(), actual).matchAll(expectedValue.value());
    return this;
  }

  @Override
  public TestAssert5Collection<E> matchAny(@NonNull Predicate<E> condition) {
    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition};
    return this.matchAny(predicates);
  }

  @Override
  public TestAssert5Collection<E> matchAny(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition1, condition2};
    return this.matchAny(predicates);
  }

  @Override
  public TestAssert5Collection<E> matchAny(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition1, condition2, condition3};
    return this.matchAny(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssert5Collection<E> matchAny(@NonNull Predicate<E>... conditions) {
    var expectedValue = AssertValue.matchConditions(conditions);

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertOperand().actual();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: matches any conditions
              Actual:   null
              Reason:   The response body was absent.
              """
                  .strip());
    }

    TestAssertMatch.assertThat(this.context.getStep(), actual).matchAny(expectedValue.value());
    return this;
  }

  @Override
  public TestAssertLCollection matchNone(@NonNull Predicate<E> condition) {
    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition};
    return this.matchNone(predicates);
  }

  @Override
  public TestAssertLCollection matchNone(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition1, condition2};
    return this.matchNone(predicates);
  }

  @Override
  public TestAssertLCollection matchNone(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<E>[] predicates = new Predicate[] {condition1, condition2, condition3};
    return this.matchNone(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssertLCollection matchNone(@NonNull Predicate<E>... conditions) {
    var expectedValue = AssertValue.matchConditions(conditions);

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertOperand().actual();

    if (actual == null) {
      throw new AssertionError(
          System.lineSeparator()
              + """
              Expected: matches no conditions
              Actual:   null
              Reason:   The response body was absent.
              """
                  .strip());
    }

    TestAssertMatch.assertThat(this.context.getStep(), actual).matchNone(expectedValue.value());
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
