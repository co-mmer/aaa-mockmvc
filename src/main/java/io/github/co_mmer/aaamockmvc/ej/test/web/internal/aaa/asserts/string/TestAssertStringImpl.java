package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert2String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssertLString;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;

@Since("1.0.0")
public final class TestAssertStringImpl
    implements TestAssert1String, TestAssert2String, TestAssertLString {

  private final TestAAAContext context;
  private final StringAssertion assertion;

  @Since("2.1.0")
  public TestAssertStringImpl(@NonNull TestAAAContext context, @NonNull StringValue actual) {
    this.context = context;
    this.assertion = new StringAssertion(actual, context.getStep());
  }

  @Override
  @NonNull
  public TestAssert2String isNotEmpty() {
    this.assertion.isNotEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssertLString isEmpty() {
    this.assertion.isEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2String hasLength(int expectedLength) {
    this.assertion.hasLength(expectedLength);
    return this;
  }

  @Override
  @NonNull
  public TestAssertLString isEqualTo(@NonNull String expectedString) {
    this.assertion.isEqualTo(new StringValue(expectedString));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
