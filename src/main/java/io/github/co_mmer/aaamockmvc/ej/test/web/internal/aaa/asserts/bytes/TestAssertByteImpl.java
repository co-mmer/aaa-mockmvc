package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert2Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertLByte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;

@Since("1.4.0")
public final class TestAssertByteImpl implements TestAssert1Byte, TestAssert2Byte, TestAssertLByte {

  private final TestAAAContext context;
  private final ByteAssertion assertion;

  @Since("2.1.0")
  public TestAssertByteImpl(@NonNull TestAAAContext context, @NonNull ByteValue actual) {
    this.context = context;
    this.assertion = new ByteAssertion(actual, context.getStep());
  }

  @Override
  @NonNull
  public TestAssert2Byte isNotEmpty() {
    this.assertion.isNotEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssertLByte isEmpty() {
    this.assertion.isEmpty();
    return this;
  }

  @Override
  @NonNull
  public TestAssert2Byte hasLength(int expectedLength) {
    this.assertion.hasLength(expectedLength);
    return this;
  }

  @Override
  @NonNull
  public TestAssertLByte isEqualTo(@NonNull byte[] expectedByte) {
    this.assertion.isEqualTo(new ByteValue(expectedByte));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
