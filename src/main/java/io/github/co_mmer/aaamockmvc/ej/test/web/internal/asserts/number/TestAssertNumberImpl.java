package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertEqual;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertEven;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertNegative;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertNonNegative;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertNonPositive;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertNotEqual;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertNotNull;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertNull;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertOdd;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertPositive;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.assertZero;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number.TestNumberAssertions.requireNumber;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert10Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert1Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert2Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert3Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert4Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert5Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert6Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert7Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert8Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert9Number;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssertLNumber;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import lombok.NonNull;

@Since("2.0.0")
public final class TestAssertNumberImpl
    implements TestAssert1Number,
        TestAssert2Number,
        TestAssert3Number,
        TestAssert4Number,
        TestAssert5Number,
        TestAssert6Number,
        TestAssert7Number,
        TestAssert8Number,
        TestAssert9Number,
        TestAssert10Number,
        TestAssertLNumber {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAssertNumberImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  private Number actual() {
    return (Number) context.getAssertResult().actualContent();
  }

  @Override
  public TestAssertLNumber isNull() {
    assertNull(actual(), "isNull()");
    return this;
  }

  @Override
  public TestAssert2Number isNotNull() {
    assertNotNull(actual(), "isNotNull()");
    return this;
  }

  @Override
  public TestAssert3Number isPositive() {
    assertPositive(requireNumber(actual(), "isPositive()"));
    return this;
  }

  @Override
  public TestAssert4Number isNonPositive() {
    assertNonPositive(requireNumber(actual(), "isNonPositive()"));
    return this;
  }

  @Override
  public TestAssert5Number isNegative() {
    assertNegative(requireNumber(actual(), "isNegative()"));
    return this;
  }

  @Override
  public TestAssert6Number isNoNegative() {
    assertNonNegative(requireNumber(actual(), "isNoNegative()"));
    return this;
  }

  @Override
  public TestAssert7Number isZero() {
    assertZero(requireNumber(actual(), "isZero()"));
    return this;
  }

  @Override
  public TestAssert8Number isNotEqualTo(@NonNull Number expected) {
    assertNotEqual(requireNumber(actual(), "isNotEqualTo()"), expected);
    return this;
  }

  @Override
  public TestAssert8Number isEqualTo(@NonNull Number expected) {
    assertEqual(requireNumber(actual(), "isEqualTo()"), expected);
    return this;
  }

  @Override
  public TestAssert9Number isEven() {
    assertEven(requireNumber(actual(), "isEven()"), "isEven()");
    return this;
  }

  @Override
  public TestAssert10Number isOdd() {
    assertOdd(requireNumber(actual(), "isOdd()"), "isOdd()");
    return this;
  }

  @Override
  public TestAssertHead headers() {
    return new TestAssertHeadImpl(this.context);
  }
}
