package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert2Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert3Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert4Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertLClass;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Since("1.0.0")
@RequiredArgsConstructor
public final class TestAssertClassImpl<T>
    implements TestAssert1Class<T>,
    TestAssert2Class<T>,
    TestAssert3Class<T>,
    TestAssert4Class<T>,
    TestAssertLClass {

  private final TestAAAContext context;

  @Override
  public TestAssert2Class<T> isNotNull() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).toBePresent();
    return this;
  }

  @Override
  public TestAssertLClass isNull() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).toBeAbsent();
    return this;
  }

  @Override
  public TestAssertLClass isEqualTo(@NonNull T expectedResponse) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toHaveSameTypeAndValueAs(AssertValue.expectedResponse(expectedResponse))
        .toEqual(AssertValue.expectedResponse(expectedResponse));
    return this;
  }

  @Override
  public TestAssert3Class<T> matchAll(@NonNull Predicate<T> condition) {

    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition};
    return this.matchAll(predicates);
  }

  @Override
  public TestAssert3Class<T> matchAll(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition1, condition2};
    return this.matchAll(predicates);
  }

  @Override
  public TestAssert3Class<T> matchAll(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition1, condition2, condition3};
    return this.matchAll(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssert3Class<T> matchAll(@NonNull Predicate<T>... conditions) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toMatchAll(AssertValue.matchConditions(conditions));
    return this;
  }

  @Override
  public TestAssert4Class<T> matchAny(@NonNull Predicate<T> condition) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition};
    return this.matchAny(predicates);
  }

  @Override
  public TestAssert4Class<T> matchAny(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition1, condition2};
    return this.matchAny(predicates);
  }

  @Override
  public TestAssert4Class<T> matchAny(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition1, condition2, condition3};
    return this.matchAny(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssert4Class<T> matchAny(@NonNull Predicate<T>... conditions) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toMatchAny(AssertValue.matchConditions(conditions));
    return this;
  }

  @Override
  public TestAssertLClass matchNone(@NonNull Predicate<T> condition) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition};
    return this.matchNone(predicates);
  }

  @Override
  public TestAssertLClass matchNone(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition1, condition2};
    return this.matchNone(predicates);
  }

  @Override
  public TestAssertLClass matchNone(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[]{condition1, condition2, condition3};
    return this.matchNone(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssertLClass matchNone(@NonNull Predicate<T>... conditions) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toMatchNone(AssertValue.matchConditions(conditions));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
