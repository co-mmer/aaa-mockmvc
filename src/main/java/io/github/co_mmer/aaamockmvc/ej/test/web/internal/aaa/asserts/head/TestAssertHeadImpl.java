package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;

@Since("1.0.0")
public final class TestAssertHeadImpl implements TestAssertHead {

  private final HeaderAssertion assertion;

  @Since("2.0.0")
  public TestAssertHeadImpl(@NonNull TestAAAContext context) {
    this.assertion =
        new HeaderAssertion(new HeaderValue(context.getActResult().headers()), context.getStep());
  }

  @Override
  @NonNull
  public TestAssertHead containsKey(@NonNull String expectedKey) {
    this.assertion.containsKey(new HeaderName(expectedKey));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead doesNotContainKey(@NonNull String notExpectedKey) {
    this.assertion.doesNotContainKey(new HeaderName(notExpectedKey));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead containsEntry(@NonNull String expectedKey, @NonNull String expectedValue) {
    this.assertion.containsEntry(new HeaderName(expectedKey), new HeaderEntryValue(expectedValue));
    return this;
  }

  @Override
  @NonNull
  public TestAssertHead containsEntryExactly(
      @NonNull String expectedKey, @NonNull String... expectedValue) {
    this.assertion.containsEntryExactly(
        new HeaderName(expectedKey), new HeaderEntryValues(expectedValue));
    return this;
  }
}
