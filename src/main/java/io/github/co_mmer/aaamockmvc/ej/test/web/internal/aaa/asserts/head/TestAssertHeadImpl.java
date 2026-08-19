package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import java.util.Map;
import org.hamcrest.Matchers;
import org.springframework.lang.NonNull;

@Since("1.0.0")
public final class TestAssertHeadImpl implements TestAssertHead {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertHeadImpl(TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssertHead containsKey(@NonNull String expectedKey) {
    var expectedValue = AssertValue.expectedHeaderName(expectedKey);
    assertThat(this.context.getStep(), getHeaders(), Matchers.hasKey(expectedValue.value()));
    return this;
  }

  private Map<String, List<String>> getHeaders() {
    return this.context.getActResult().headers();
  }

  @Override
  public TestAssertHead doesNotContainKey(@NonNull String notExpectedKey) {
    var unexpectedValue = AssertValue.unexpectedHeaderName(notExpectedKey);
    assertThat(this.context.getStep(), getHeaders(), not(Matchers.hasKey(unexpectedValue.value())));
    return this;
  }

  @Override
  public TestAssertHead containsEntry(@NonNull String expectedKey, @NonNull String expectedValue) {
    var key = AssertValue.expectedHeaderName(expectedKey);
    var value = AssertValue.expectedHeaderValue(expectedValue);

    var headers = getHeaders();
    assertThat(this.context.getStep(), key.value(), headers, Matchers.hasKey(key.value()));
    assertThat(this.context.getStep(), headers.get(key.value()), hasItem(value.value()));
    return this;
  }

  @Override
  public TestAssertHead containsEntryExactly(
      @NonNull String expectedKey, @NonNull String... expectedValue) {
    var key = AssertValue.expectedHeaderName(expectedKey);
    var values = AssertValue.expectedHeaderValues(expectedValue);

    assertThat(
        this.context.getStep(),
        "at least one expected value required",
        values.value(),
        not(emptyArray()));

    var headers = getHeaders();
    assertThat(this.context.getStep(), key.value(), headers, Matchers.hasKey(key.value()));
    assertThat(
        this.context.getStep(), headers.get(key.value()), containsInAnyOrder(values.value()));
    return this;
  }
}
