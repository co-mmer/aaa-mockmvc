package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeCollection;
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
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertMatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.lang.NonNull;

@Since("1.4.0")
public final class TestAssertCollectionImpl<E>
    implements TestAssert1Collection<E>,
        TestAssert2Collection<E>,
        TestAssert3Collection<E>,
        TestAssert4Collection<E>,
        TestAssert5Collection<E>,
        TestAssertLCollection {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertCollectionImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssert2Collection<E> isNotEmpty() {
    var actual = this.context.getActResult().contentAsString();
    assertThat(this.context.getStep(), actual, not(anyOf(is(EMPTY), is(EMPTY_ARRAY))));
    return this;
  }

  @Override
  public TestAssertLCollection isEmpty() {
    var actual = this.context.getActResult().contentAsString();
    assertThat(this.context.getStep(), actual, anyOf(is(EMPTY), is(EMPTY_ARRAY)));
    return this;
  }

  @Override
  public TestAssert2Collection<E> hasSize(int expectedSize) {
    var actual = (Collection<?>) this.context.getAssertResult().actualContent();
    assertThat(this.context.getStep(), actual.size(), is(expectedSize));
    return this;
  }

  public TestAssertLCollection isEqualTo(@NonNull Collection<E> expectedCollection) {
    var target = TestAssertType.expectedCollection(expectedCollection).value();

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertResult().actualContent();
    assertThat(
        this.context.getStep(), normalizeCollection(actual), is(normalizeCollection(target)));
    return this;
  }

  public TestAssert3Collection<E> contains(@NonNull Collection<E> expectedElements) {
    var target = TestAssertType.expectedCollection(expectedElements).value();

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertResult().actualContent();
    var normalizeExpected = normalizeCollection(target).toArray(String[]::new);
    var normalizeActual = normalizeCollection(actual);
    assertThat(this.context.getStep(), normalizeActual, hasItems(normalizeExpected));
    return this;
  }

  @SafeVarargs
  public final TestAssert3Collection<E> contains(@NonNull E... expectedElements) {
    var target = TestAssertType.expectedElements(expectedElements).value();
    return contains(List.of(target));
  }

  public TestAssert3Collection<E> containsAnyOrder(@NonNull Collection<E> expectedCollection) {
    var target = TestAssertType.expectedCollection(expectedCollection).value();

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertResult().actualContent();
    var normalizeExpected = normalizeCollection(target);
    var normalizeActual = normalizeCollection(actual);
    assertThat(
        this.context.getStep(), normalizeActual, containsInAnyOrder(normalizeExpected.toArray()));
    return this;
  }

  public TestAssert3Collection<E> notContains(@NonNull Collection<E> unexpectedElements) {
    var target = TestAssertType.unexpectedElements(unexpectedElements).value();

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertResult().actualContent();
    var normalizeExpected = normalizeCollection(target).toArray(String[]::new);
    var normalizeActual = normalizeCollection(actual);
    assertThat(this.context.getStep(), normalizeActual, not(hasItems(normalizeExpected)));
    return this;
  }

  @SafeVarargs
  public final TestAssert3Collection<E> notContains(@NonNull E... unexpectedElements) {
    var target = TestAssertType.unexpectedElements(unexpectedElements).value();
    return notContains(List.of(target));
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
    var checkedConditions = TestAssertType.matchConditions(conditions).value();

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(this.context.getStep(), actual).matchAll(checkedConditions);
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
    var checkedConditions = TestAssertType.matchConditions(conditions).value();

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(this.context.getStep(), actual).matchAny(checkedConditions);
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
    var checkedConditions = TestAssertType.matchConditions(conditions).value();

    @SuppressWarnings("unchecked")
    var actual = (Collection<E>) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(this.context.getStep(), actual).matchNone(checkedConditions);
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
