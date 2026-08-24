package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert2Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert3Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert4Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert5Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertLCollection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
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
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).notToBeEmpty();
    return this;
  }

  @Override
  public TestAssertLCollection isEmpty() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).toBeEmpty();
    return this;
  }

  @Override
  public TestAssert2Collection<E> hasSize(int expectedSize) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toHaveSize(AssertValue.expectedSize(expectedSize));
    return this;
  }

  public TestAssertLCollection isEqualTo(@NonNull Collection<E> expectedCollection) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toEqual(AssertValue.expectedCollection(expectedCollection));
    return this;
  }

  public TestAssert3Collection<E> contains(@NonNull Collection<E> expectedElements) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toContain(AssertValue.expectedCollection(expectedElements));
    return this;
  }

  @SafeVarargs
  public final TestAssert3Collection<E> contains(@NonNull E... expectedElements) {
    var expectedValue = AssertValue.expectedElements(expectedElements);
    return contains(List.of(expectedValue.value()));
  }

  public TestAssert3Collection<E> containsAnyOrder(@NonNull Collection<E> expectedCollection) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toContainExactlyInAnyOrder(AssertValue.expectedCollection(expectedCollection));
    return this;
  }

  public TestAssert3Collection<E> notContains(@NonNull Collection<E> unexpectedElements) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .notToContain(AssertValue.unexpectedElements(unexpectedElements));
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
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toMatchAll(AssertValue.matchConditions(conditions));
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
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toMatchAny(AssertValue.matchConditions(conditions));
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
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toMatchNone(AssertValue.matchConditions(conditions));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
