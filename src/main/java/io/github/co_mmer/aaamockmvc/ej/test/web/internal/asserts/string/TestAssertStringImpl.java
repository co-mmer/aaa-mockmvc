package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssert.assertThat;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeObject;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert2String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssertLString;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import lombok.NonNull;

@Since("1.0.0")
public final class TestAssertStringImpl
    implements TestAssert1String, TestAssert2String, TestAssertLString {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertStringImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssert2String isNotEmpty() {
    assertThat(this.context.getStep(), getContent().isEmpty(), is(false));
    return this;
  }

  private String getContent() {
    return this.context.getActResult().contentAsString();
  }

  @Override
  public TestAssertLString isEmpty() {
    assertThat(this.context.getStep(), getContent().isEmpty(), is(true));
    return this;
  }

  @Override
  public TestAssert2String hasLength(int expectedLength) {
    assertThat(this.context.getStep(), getContent().length(), is(expectedLength));
    return this;
  }

  @Override
  public TestAssertLString isEqualTo(@NonNull String expectedString) {
    assertThat(
        this.context.getStep(), normalizeObject(getContent()), is(normalizeObject(expectedString)));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
