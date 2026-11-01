package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert2Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert3Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert4Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertLClass;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.Conditions;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.function.Predicate;
import org.springframework.lang.NonNull;

@Since("1.0.0")
public final class TestAssertClassImpl<T>
    implements TestAssert1Class<T>,
        TestAssert2Class<T>,
        TestAssert3Class<T>,
        TestAssert4Class<T>,
        TestAssertLClass {

  private final TestAAAContext context;
  private final ObjectAssertion<T> assertion;

  @Since("2.1.0")
  public TestAssertClassImpl(@NonNull TestAAAContext context, @NonNull ObjectValue<T> actual) {
    this.context = context;
    this.assertion = new ObjectAssertion<>(actual, context.getStep());
  }

  @Override
  @NonNull
  public TestAssert2Class<T> isNotNull() {
    this.assertion.isNotNull();
    return this;
  }

  @Override
  @NonNull
  public TestAssertLClass isNull() {
    this.assertion.isNull();
    return this;
  }

  @Override
  @NonNull
  public TestAssertLClass isEqualTo(@NonNull T expectedResponse) {
    this.assertion.isEqualTo(ObjectValue.expected(expectedResponse));
    return this;
  }

  @Override
  @NonNull
  public TestAssert3Class<T> matchAll(@NonNull Predicate<T> condition) {
    this.assertion.matchAll(new Conditions<>(condition));
    return this;
  }

  @Override
  @NonNull
  public TestAssert3Class<T> matchAll(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    this.assertion.matchAll(new Conditions<>(condition1, condition2));
    return this;
  }

  @Override
  @NonNull
  public TestAssert3Class<T> matchAll(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    this.assertion.matchAll(new Conditions<>(condition1, condition2, condition3));
    return this;
  }

  @SafeVarargs
  @Override
  @NonNull
  public final TestAssert3Class<T> matchAll(@NonNull Predicate<T>... conditions) {
    this.assertion.matchAll(new Conditions<>(conditions));
    return this;
  }

  @Override
  @NonNull
  public TestAssert4Class<T> matchAny(@NonNull Predicate<T> condition) {
    this.assertion.matchAny(new Conditions<>(condition));
    return this;
  }

  @Override
  @NonNull
  public TestAssert4Class<T> matchAny(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    this.assertion.matchAny(new Conditions<>(condition1, condition2));
    return this;
  }

  @Override
  @NonNull
  public TestAssert4Class<T> matchAny(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    this.assertion.matchAny(new Conditions<>(condition1, condition2, condition3));
    return this;
  }

  @SafeVarargs
  @Override
  @NonNull
  public final TestAssert4Class<T> matchAny(@NonNull Predicate<T>... conditions) {
    this.assertion.matchAny(new Conditions<>(conditions));
    return this;
  }

  @Override
  @NonNull
  public TestAssertLClass matchNone(@NonNull Predicate<T> condition) {
    this.assertion.matchNone(new Conditions<>(condition));
    return this;
  }

  @Override
  @NonNull
  public TestAssertLClass matchNone(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    this.assertion.matchNone(new Conditions<>(condition1, condition2));
    return this;
  }

  @Override
  @NonNull
  public TestAssertLClass matchNone(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    this.assertion.matchNone(new Conditions<>(condition1, condition2, condition3));
    return this;
  }

  @SafeVarargs
  @Override
  @NonNull
  public final TestAssertLClass matchNone(@NonNull Predicate<T>... conditions) {
    this.assertion.matchNone(new Conditions<>(conditions));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
