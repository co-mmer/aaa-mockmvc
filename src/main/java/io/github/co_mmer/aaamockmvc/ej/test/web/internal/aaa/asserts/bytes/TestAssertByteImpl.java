package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert2Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertLByte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Note;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import lombok.RequiredArgsConstructor;

@Since("1.4.0")
@RequiredArgsConstructor
public final class TestAssertByteImpl implements TestAssert1Byte, TestAssert2Byte, TestAssertLByte {

  private final TestAAAContext context;

  @Note("Move to Context")
  private AssertOperand<?, ?> getAssertOperand() {
    return AssertOperand.bytes(this.context.getActResult().contentAsBytes());
  }

  @Override
  public TestAssert2Byte isNotEmpty() {
    AAAAssert.expect(this.context.getStep(), getAssertOperand()).notToBeEmpty();
    return this;
  }

  @Override
  public TestAssertLByte isEmpty() {
    AAAAssert.expect(this.context.getStep(), getAssertOperand()).toBeEmpty();
    return this;
  }

  @Override
  public TestAssert2Byte hasLength(int expectedLength) {
    AAAAssert.expect(this.context.getStep(), getAssertOperand())
        .toHaveSize(AssertValue.expectedLength(expectedLength));
    return this;
  }

  @Override
  public TestAssertLByte isEqualTo(byte[] expectedByte) {
    AAAAssert.expect(this.context.getStep(), getAssertOperand())
        .toEqual(AssertValue.expectedBytes(expectedByte));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
