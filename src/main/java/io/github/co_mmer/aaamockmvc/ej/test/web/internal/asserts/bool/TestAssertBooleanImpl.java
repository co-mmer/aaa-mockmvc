package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.bool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert2Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert3Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import lombok.NonNull;

public class TestAssertBooleanImpl
    implements TestAssert1Boolean, TestAssert2Boolean, TestAssert3Boolean {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertBooleanImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssert3Boolean isNull() {
    assertThat(this.context.getAssertResult().actualContent(), is(nullValue()));
    return this;
  }

  @Override
  public TestAssert2Boolean isNotNull() {
    assertThat(this.context.getAssertResult().actualContent(), is(notNullValue()));
    return this;
  }

  @Override
  public TestAssert3Boolean isTrue() {
    Boolean actual = (Boolean) this.context.getAssertResult().actualContent();
    assertThat(actual, is(Boolean.TRUE));
    return this;
  }

  @Override
  public TestAssert3Boolean isFalse() {
    Boolean actual = (Boolean) this.context.getAssertResult().actualContent();
    assertThat(actual, is(Boolean.FALSE));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
