package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert2Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert3Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;

@Since("2.0.0")
public class TestAssertBooleanImpl
    implements TestAssert1Boolean, TestAssert2Boolean, TestAssert3Boolean {

  private final TestAAAContext context;
  private final BooleanAssertion assertion;

  public TestAssertBooleanImpl(@NonNull TestAAAContext context, @NonNull BooleanValue actual) {
    this.context = context;
    this.assertion = new BooleanAssertion(actual, context.getStep());
  }

  @Override
  @NonNull
  public TestAssert3Boolean isNull() {
    assertion.isNull();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Boolean isNotNull() {
    assertion.isNotNull();
    return this;
  }

  @Override
  @NonNull
  public TestAssert3Boolean isEqualTo(@NonNull Boolean expectedBoolean) {
    assertion.isEqualTo(BooleanValue.expected(expectedBoolean));
    return this;
  }

  @Override
  @NonNull
  public TestAssert3Boolean isTrue() {
    assertion.isTrue();
    return this;
  }

  @Override
  @NonNull
  public TestAssert3Boolean isFalse() {
    assertion.isFalse();
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(context);
  }
}
