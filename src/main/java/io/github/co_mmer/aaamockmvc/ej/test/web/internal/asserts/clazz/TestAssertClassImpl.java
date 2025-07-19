package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert2Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert3Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert4Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertLClass;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssertMatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.function.Predicate;
import lombok.NonNull;

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
    assertThat(this.context.getAssertResult().actualContent(), is(notNullValue()));
    return this;
  }

  @Override
  public TestAssert2Class<T> isNull() {
    assertThat(this.context.getAssertResult().actualContent(), is(nullValue()));
    return this;
  }

  @Override
  public TestAssertLClass isEqualTo(@NonNull T expectedResponse) {
    @SuppressWarnings("unchecked")
    T actual = (T) this.context.getAssertResult().actualContent();
    assertThat(actual.getClass(), is(expectedResponse.getClass()));
    assertThat(normalizeObject(actual), is(normalizeObject(expectedResponse)));
    return this;
  }

  @Override
  public TestAssert3Class<T> matchAll(@NonNull Predicate<T> condition) {
    @SuppressWarnings("unchecked")
    T actual = (T) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(actual).matchAll(condition);
    return this;
  }

  @SafeVarargs
  @Override
  public final TestAssert3Class<T> matchAll(@NonNull Predicate<T>... conditions) {
    @SuppressWarnings("unchecked")
    T actual = (T) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(actual).matchAll(conditions);
    return this;
  }

  @Override
  public TestAssert4Class<T> matchAny(@NonNull Predicate<T> condition) {
    @SuppressWarnings("unchecked")
    T actual = (T) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(actual).matchAny(condition);
    return this;
  }

  @SafeVarargs
  @Override
  public final TestAssert4Class<T> matchAny(@NonNull Predicate<T>... conditions) {
    @SuppressWarnings("unchecked")
    T actual = (T) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(actual).matchAny(conditions);
    return this;
  }

  @Override
  public TestAssertLClass matchNone(@NonNull Predicate<T> condition) {
    @SuppressWarnings("unchecked")
    T actual = (T) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(actual).matchNone(condition);
    return this;
  }

  @SafeVarargs
  @Override
  public final TestAssertLClass matchNone(@NonNull Predicate<T>... conditions) {
    @SuppressWarnings("unchecked")
    T actual = (T) this.context.getAssertResult().actualContent();
    TestAssertMatch.assertThat(actual).matchNone(conditions);
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
