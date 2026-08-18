package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeObject;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert2Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert3Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert4Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertLClass;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertMatch;
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

  @Since("2.0.0")
  public TestAssertClassImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssert2Class<T> isNotNull() {
    assertThat(this.context.getStep(), getActual(), is(notNullValue()));
    return this;
  }

  private Object getActual() {
    return this.context.getAssertResult().actualContent();
  }

  @Override
  public TestAssertLClass isNull() {
    assertThat(this.context.getStep(), getActual(), is(nullValue()));
    return this;
  }

  @Override
  public TestAssertLClass isEqualTo(@NonNull T expectedResponse) {
    var target = TestAssert.expectedResponse(expectedResponse).value();

    @SuppressWarnings("unchecked")
    T actual = (T) getActual();
    assertThat(this.context.getStep(), actual.getClass(), is(target.getClass()));
    assertThat(this.context.getStep(), normalizeObject(actual), is(normalizeObject(target)));
    return this;
  }

  @Override
  public TestAssert3Class<T> matchAll(@NonNull Predicate<T> condition) {

    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition};
    return this.matchAll(predicates);
  }

  @Override
  public TestAssert3Class<T> matchAll(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition1, condition2};
    return this.matchAll(predicates);
  }

  @Override
  public TestAssert3Class<T> matchAll(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition1, condition2, condition3};
    return this.matchAll(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssert3Class<T> matchAll(@NonNull Predicate<T>... conditions) {
    var checkedConditions = TestAssert.matchConditions(conditions).value();

    @SuppressWarnings("unchecked")
    T actual = (T) getActual();
    TestAssertMatch.assertThat(this.context.getStep(), actual).matchAll(checkedConditions);
    return this;
  }

  @Override
  public TestAssert4Class<T> matchAny(@NonNull Predicate<T> condition) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition};
    return this.matchAny(predicates);
  }

  @Override
  public TestAssert4Class<T> matchAny(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition1, condition2};
    return this.matchAny(predicates);
  }

  @Override
  public TestAssert4Class<T> matchAny(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition1, condition2, condition3};
    return this.matchAny(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssert4Class<T> matchAny(@NonNull Predicate<T>... conditions) {
    var checkedConditions = TestAssert.matchConditions(conditions).value();

    @SuppressWarnings("unchecked")
    T actual = (T) getActual();
    TestAssertMatch.assertThat(this.context.getStep(), actual).matchAny(checkedConditions);
    return this;
  }

  @Override
  public TestAssertLClass matchNone(@NonNull Predicate<T> condition) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition};
    return this.matchNone(predicates);
  }

  @Override
  public TestAssertLClass matchNone(
      @NonNull Predicate<T> condition1, @NonNull Predicate<T> condition2) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition1, condition2};
    return this.matchNone(predicates);
  }

  @Override
  public TestAssertLClass matchNone(
      @NonNull Predicate<T> condition1,
      @NonNull Predicate<T> condition2,
      @NonNull Predicate<T> condition3) {
    @SuppressWarnings("unchecked")
    Predicate<T>[] predicates = new Predicate[] {condition1, condition2, condition3};
    return this.matchNone(predicates);
  }

  @SafeVarargs
  @Override
  public final TestAssertLClass matchNone(@NonNull Predicate<T>... conditions) {
    var checkedConditions = TestAssert.matchConditions(conditions).value();

    @SuppressWarnings("unchecked")
    T actual = (T) getActual();
    TestAssertMatch.assertThat(this.context.getStep(), actual).matchNone(checkedConditions);
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
