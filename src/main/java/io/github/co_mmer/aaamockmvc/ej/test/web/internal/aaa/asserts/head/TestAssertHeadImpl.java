package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Note;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;

@Since("1.0.0")
public final class TestAssertHeadImpl implements TestAssertHead {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertHeadImpl(TestAAAContext context) {
    this.context = context;
  }

  @Note("Move to Context")
  private AssertOperand<?, ?> getAssertOperand() {
    return AssertOperand.headers(this.context.getActResult().headers());
  }

  @Override
  public TestAssertHead containsKey(@NonNull String expectedKey) {
    AAAAssert.expect(this.context.getStep(), getAssertOperand())
        .toContainKey(AssertValue.expectedHeaderName(expectedKey));
    return this;
  }

  @Override
  public TestAssertHead doesNotContainKey(@NonNull String notExpectedKey) {
    AAAAssert.expect(this.context.getStep(), getAssertOperand())
        .notToContainKey(AssertValue.unexpectedHeaderName(notExpectedKey));
    return this;
  }

  @Override
  public TestAssertHead containsEntry(@NonNull String expectedKey, @NonNull String expectedValue) {
    AAAAssert.expect(this.context.getStep(), getAssertOperand())
        .toContainEntry(
            AssertValue.expectedHeaderName(expectedKey),
            AssertValue.expectedHeaderValue(expectedValue));
    return this;
  }

  @Override
  public TestAssertHead containsEntryExactly(
      @NonNull String expectedKey, @NonNull String... expectedValue) {
    AAAAssert.expect(this.context.getStep(), getAssertOperand())
        .toContainEntryExactly(
            AssertValue.expectedHeaderName(expectedKey),
            AssertValue.expectedHeaderValues(expectedValue));
    return this;
  }
}
