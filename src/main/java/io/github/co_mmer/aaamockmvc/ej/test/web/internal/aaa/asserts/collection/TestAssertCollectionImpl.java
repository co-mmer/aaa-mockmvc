package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert2Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert3Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert4Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert5Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertLCollection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.Conditions;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
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
  private final CollectionAssertion<E> assertion;

  @Since("2.1.0")
  public TestAssertCollectionImpl(
      @NonNull TestAAAContext context, @NonNull CollectionValue<E> actual) {
    this.context = context;
    this.assertion = new CollectionAssertion<>(actual, context.getStep());
  }

  @Override
  @NonNull
  public TestAssert2Collection<E> isNotEmpty() {
    this.assertion.isNotEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssertLCollection isEmpty() {
    this.assertion.isEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Collection<E> hasSize(int expectedSize) {
    this.assertion.hasSize(expectedSize);
    return this;
  }

  @NonNull
  public TestAssertLCollection isEqualTo(@NonNull Collection<E> expectedCollection) {
    this.assertion.isEqualTo(new CollectionValue<>(expectedCollection));
    return this;
  }

  @NonNull
  public TestAssert3Collection<E> contains(@NonNull Collection<E> expectedElements) {
    this.assertion.contains(new CollectionValue<>(expectedElements));
    return this;
  }

  @SafeVarargs
  @NonNull
  public final TestAssert3Collection<E> contains(@NonNull E... expectedElements) {
    this.assertion.contains(CollectionValue.of(expectedElements));
    return this;
  }

  @NonNull
  public TestAssert3Collection<E> containsAnyOrder(@NonNull Collection<E> expectedCollection) {
    this.assertion.containsAnyOrder(new CollectionValue<>(expectedCollection));
    return this;
  }

  @NonNull
  public TestAssert3Collection<E> notContains(@NonNull Collection<E> unexpectedElements) {
    this.assertion.notContains(new CollectionValue<>(unexpectedElements));
    return this;
  }

  @SafeVarargs
  @NonNull
  public final TestAssert3Collection<E> notContains(@NonNull E... unexpectedElements) {
    this.assertion.notContains(CollectionValue.of(unexpectedElements));
    return this;
  }

  @Override
  @NonNull
  public TestAssert4Collection<E> matchAll(@NonNull Predicate<E> condition) {
    this.assertion.matchAll(new Conditions<>(condition));
    return this;
  }

  @Override
  @NonNull
  public TestAssert4Collection<E> matchAll(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2) {
    this.assertion.matchAll(new Conditions<>(condition1, condition2));
    return this;
  }

  @Override
  @NonNull
  public TestAssert4Collection<E> matchAll(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3) {
    this.assertion.matchAll(new Conditions<>(condition1, condition2, condition3));
    return this;
  }

  @SafeVarargs
  @Override
  @NonNull
  public final TestAssert4Collection<E> matchAll(@NonNull Predicate<E>... conditions) {
    this.assertion.matchAll(new Conditions<>(conditions));
    return this;
  }

  @Override
  @NonNull
  public TestAssert5Collection<E> matchAny(@NonNull Predicate<E> condition) {
    this.assertion.matchAny(new Conditions<>(condition));
    return this;
  }

  @Override
  @NonNull
  public TestAssert5Collection<E> matchAny(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2) {
    this.assertion.matchAny(new Conditions<>(condition1, condition2));
    return this;
  }

  @Override
  @NonNull
  public TestAssert5Collection<E> matchAny(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3) {
    this.assertion.matchAny(new Conditions<>(condition1, condition2, condition3));
    return this;
  }

  @SafeVarargs
  @Override
  @NonNull
  public final TestAssert5Collection<E> matchAny(@NonNull Predicate<E>... conditions) {
    this.assertion.matchAny(new Conditions<>(conditions));
    return this;
  }

  @Override
  @NonNull
  public TestAssertLCollection matchNone(@NonNull Predicate<E> condition) {
    this.assertion.matchNone(new Conditions<>(condition));
    return this;
  }

  @Override
  @NonNull
  public TestAssertLCollection matchNone(
      @NonNull Predicate<E> condition1, @NonNull Predicate<E> condition2) {
    this.assertion.matchNone(new Conditions<>(condition1, condition2));
    return this;
  }

  @Override
  @NonNull
  public TestAssertLCollection matchNone(
      @NonNull Predicate<E> condition1,
      @NonNull Predicate<E> condition2,
      @NonNull Predicate<E> condition3) {
    this.assertion.matchNone(new Conditions<>(condition1, condition2, condition3));
    return this;
  }

  @SafeVarargs
  @Override
  @NonNull
  public final TestAssertLCollection matchNone(@NonNull Predicate<E>... conditions) {
    this.assertion.matchNone(new Conditions<>(conditions));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
