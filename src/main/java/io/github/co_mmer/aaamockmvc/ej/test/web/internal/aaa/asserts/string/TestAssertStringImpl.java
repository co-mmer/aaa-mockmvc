package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert2String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssertLString;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Since("1.0.0")
@RequiredArgsConstructor
public final class TestAssertStringImpl
    implements TestAssert1String, TestAssert2String, TestAssertLString {

  private final TestAAAContext context;

  @Override
  public TestAssert2String isNotEmpty() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).notToBeEmpty();
    return this;
  }

  @Override
  public TestAssertLString isEmpty() {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand()).toBeEmpty();
    return this;
  }

  @Override
  public TestAssert2String hasLength(int expectedLength) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toHaveLength(AssertValue.expectedLength(expectedLength));
    return this;
  }

  @Override
  public TestAssertLString isEqualTo(@NonNull String expectedString) {
    AAAAssert.expect(this.context.getStep(), this.context.getAssertOperand())
        .toEqual(AssertValue.expectedString(expectedString));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
