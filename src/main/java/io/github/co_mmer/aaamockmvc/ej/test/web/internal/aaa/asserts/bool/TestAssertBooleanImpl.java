package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert2Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert3Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Since("2.0.0")
@RequiredArgsConstructor
public class TestAssertBooleanImpl
    implements TestAssert1Boolean, TestAssert2Boolean, TestAssert3Boolean {

  private final TestAAAContext context;

  @Override
  public TestAssert3Boolean isNull() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).toBeAbsent();
    return this;
  }

  @Override
  public TestAssert2Boolean isNotNull() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).toBePresent();
    return this;
  }

  @Override
  public TestAssert3Boolean isEqualTo(@NonNull Boolean expectedBoolean) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toEqual(AssertValue.expectedBoolean(expectedBoolean));
    return this;
  }

  @Override
  public TestAssert3Boolean isTrue() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toEqual(AssertValue.expectedBoolean(Boolean.TRUE));
    return this;
  }

  @Override
  public TestAssert3Boolean isFalse() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toEqual(AssertValue.expectedBoolean(Boolean.FALSE));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
