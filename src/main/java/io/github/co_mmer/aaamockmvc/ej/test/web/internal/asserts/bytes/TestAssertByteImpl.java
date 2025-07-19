package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.bytes;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_ARRAY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_OBJECT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert2Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertLByte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import lombok.NonNull;

@Since("1.4.0")
public final class TestAssertByteImpl implements TestAssert1Byte, TestAssert2Byte, TestAssertLByte {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertByteImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public TestAssert2Byte isNotEmpty() {
    var actual = this.context.getActResult().contentAsString();
    assertThat(actual, not(anyOf(is(EMPTY), is(EMPTY_ARRAY), is(EMPTY_OBJECT))));
    return this;
  }

  @Override
  public TestAssertLByte isEmpty() {
    var actual = this.context.getActResult().contentAsString();
    assertThat(actual, anyOf(is(EMPTY), is(EMPTY_ARRAY), is(EMPTY_OBJECT)));
    return this;
  }

  @Override
  public TestAssert2Byte hasLength(int expectedLength) {
    var actResult = this.context.getActResult();
    assertThat(actResult.contentAsBytes().length, is(expectedLength));
    return this;
  }

  @Override
  public TestAssertLByte isEqualTo(byte[] expectedByte) {
    var actResult = this.context.getActResult();
    assertThat(actResult.contentAsBytes(), is(expectedByte));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
