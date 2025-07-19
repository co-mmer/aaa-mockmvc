package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeObject;
import static org.hamcrest.MatcherAssert.assertThat;
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
    assertThat(this.context.getActResult().contentAsString().isEmpty(), is(false));
    return this;
  }

  @Override
  public TestAssertLString isEmpty() {
    assertThat(this.context.getActResult().contentAsString().isEmpty(), is(true));
    return this;
  }

  @Override
  public TestAssert2String hasLength(int expectedLength) {
    assertThat(this.context.getActResult().contentAsString().length(), is(expectedLength));
    return this;
  }

  @Override
  public TestAssertLString isEqualTo(@NonNull String expectedString) {
    var content = this.context.getActResult().contentAsString();
    assertThat(normalizeObject(content), is(normalizeObject(expectedString)));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
