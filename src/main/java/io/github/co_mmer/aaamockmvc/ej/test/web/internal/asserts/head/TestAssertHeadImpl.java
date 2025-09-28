package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import lombok.NonNull;
import org.hamcrest.Matchers;

@Since("1.0.0")
public final class TestAssertHeadImpl implements TestAssertHead {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertHeadImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssertHead containsKey(String expectedKey) {
    var headers = this.context.getActResult().headers();
    assertThat(this.context.getStep(), headers, Matchers.hasKey(expectedKey));
    return this;
  }

  @Override
  public TestAssertHead doesNotContainKey(String notExpectedKey) {
    var headers = this.context.getActResult().headers();
    assertThat(this.context.getStep(), headers, not(Matchers.hasKey(notExpectedKey)));
    return this;
  }

  @Override
  public TestAssertHead containsEntry(String expectedKey, String expectedValue) {
    var headers = this.context.getActResult().headers();
    assertThat(this.context.getStep(), expectedKey, headers, Matchers.hasKey(expectedKey));
    assertThat(this.context.getStep(), headers.get(expectedKey), hasItem(expectedValue));
    return this;
  }

  @Override
  public TestAssertHead containsEntryExactly(String expectedKey, String... expectedValue) {
    assertThat(
        this.context.getStep(),
        "at least one expected value required",
        expectedValue,
        not(emptyArray()));

    var headers = this.context.getActResult().headers();
    assertThat(this.context.getStep(), expectedKey, headers, Matchers.hasKey(expectedKey));
    assertThat(this.context.getStep(), headers.get(expectedKey), containsInAnyOrder(expectedValue));
    return this;
  }
}
