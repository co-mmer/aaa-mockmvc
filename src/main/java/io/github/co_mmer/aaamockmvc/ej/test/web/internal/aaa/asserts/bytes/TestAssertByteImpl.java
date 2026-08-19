package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssert.assertThat;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_ARRAY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_OBJECT;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert2Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertLByte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import lombok.RequiredArgsConstructor;

@Since("1.4.0")
@RequiredArgsConstructor
public final class TestAssertByteImpl implements TestAssert1Byte, TestAssert2Byte, TestAssertLByte {

  private final TestAAAContext context;

  @Override
  public TestAssert2Byte isNotEmpty() {
    assertThat(
        this.context.getStep(),
        this.context.getActResult().contentAsString(),
        not(anyOf(is(EMPTY), is(EMPTY_ARRAY), is(EMPTY_OBJECT))));
    return this;
  }

  @Override
  public TestAssertLByte isEmpty() {
    assertThat(
        this.context.getStep(),
        this.context.getActResult().contentAsString(),
        anyOf(is(EMPTY), is(EMPTY_ARRAY), is(EMPTY_OBJECT)));
    return this;
  }

  @Override
  public TestAssert2Byte hasLength(int expectedLength) {
    var actResult = this.context.getActResult();
    assertThat(this.context.getStep(), actResult.contentAsBytes().length, is(expectedLength));
    return this;
  }

  @Override
  public TestAssertLByte isEqualTo(byte[] expectedByte) {
    var actResult = this.context.getActResult();
    assertThat(this.context.getStep(), actResult.contentAsBytes(), is(expectedByte));
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
