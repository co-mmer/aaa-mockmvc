package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import java.util.Map;
import org.hamcrest.Matchers;

@Since("1.0.0")
public final class TestAssertHeadImpl implements TestAssertHead {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertHeadImpl(TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssertHead containsKey(String expectedKey) {
    assertThat(this.context.getStep(), getHeaders(), Matchers.hasKey(expectedKey));
    return this;
  }

  private Map<String, List<String>> getHeaders() {
    return this.context.getActResult().headers();
  }

  @Override
  public TestAssertHead doesNotContainKey(String notExpectedKey) {
    assertThat(this.context.getStep(), getHeaders(), not(Matchers.hasKey(notExpectedKey)));
    return this;
  }

  @Override
  public TestAssertHead containsEntry(String expectedKey, String expectedValue) {
    var headers = getHeaders();
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

    var headers = getHeaders();
    assertThat(this.context.getStep(), expectedKey, headers, Matchers.hasKey(expectedKey));
    assertThat(this.context.getStep(), headers.get(expectedKey), containsInAnyOrder(expectedValue));
    return this;
  }
}
