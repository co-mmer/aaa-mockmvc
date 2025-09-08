package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Since("2.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestNumberAssertions {

  public static void assertNull(Number raw, String op) {
    assertThat(
        "[" + op + "] expected <null> but was non-null: " + render(raw), raw, is(nullValue()));
  }

  public static void assertNotNull(Number raw, String op) {
    assertThat("[" + op + "] expected non-null but was <null>", raw, is(notNullValue()));
  }

  public static Number requireNumber(Number raw, String op) {
    assertNotNull(raw, op);
    return raw;
  }

  public static void assertPositive(Number n) {
    BigDecimal bd = toBigDecimal(n);
    if (bd.compareTo(BigDecimal.ZERO) <= 0) {
      fail("Expected a positive number (> 0), but was " + annotateZero(n));
    }
  }

  public static void assertNonPositive(Number n) {
    BigDecimal bd = toBigDecimal(n);
    if (bd.compareTo(BigDecimal.ZERO) > 0) {
      fail("Expected a non-positive number (<= 0), but was " + render(n));
    }
  }

  public static void assertNegative(Number n) {
    BigDecimal bd = toBigDecimal(n);
    if (bd.compareTo(BigDecimal.ZERO) >= 0) {
      fail("Expected a negative number (< 0), but was " + annotateZero(n));
    }
  }

  public static void assertNonNegative(Number n) {
    BigDecimal bd = toBigDecimal(n);
    if (bd.compareTo(BigDecimal.ZERO) < 0) {
      fail("Expected a non-negative number (>= 0), but was " + render(n));
    }
  }

  public static void assertZero(Number n) {
    BigDecimal bd = toBigDecimal(n);
    if (bd.compareTo(BigDecimal.ZERO) != 0) {
      fail("Expected zero (== 0), but was " + render(n));
    }
  }

  public static void assertEqual(Number actual, Number expected) {
    BigDecimal a = toBigDecimal(actual);
    BigDecimal e = toBigDecimal(expected);
    if (a.compareTo(e) != 0) {
      fail("Expected number == " + render(expected) + " but was " + render(actual));
    }
  }

  public static void assertNotEqual(Number actual, Number expected) {
    BigDecimal a = toBigDecimal(actual);
    BigDecimal e = toBigDecimal(expected);
    if (a.compareTo(e) == 0) {
      fail("Expected number != " + render(expected) + " but both were equal");
    }
  }

  public static void assertEven(Number n, String op) {
    BigInteger bi = toExactIntegerOrFail(toBigDecimal(n), op);
    if (!bi.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
      fail("Expected an even integer, but was " + bi);
    }
  }

  public static void assertOdd(Number n, String op) {
    BigInteger bi = toExactIntegerOrFail(toBigDecimal(n), op);
    if (!bi.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
      fail("Expected an odd integer, but was " + bi);
    }
  }

  private static BigDecimal toBigDecimal(Number n) {
    if (n instanceof BigDecimal bd) {
      return bd;
    }
    if (n instanceof BigInteger bi) {
      return new BigDecimal(bi);
    }
    if (n instanceof Byte || n instanceof Short || n instanceof Integer || n instanceof Long) {
      return BigDecimal.valueOf(n.longValue());
    }
    if (n instanceof Double || n instanceof Float) {
      double d = n.doubleValue();
      return BigDecimal.valueOf(d);
    }
    return new BigDecimal(n.toString());
  }

  private static BigInteger toExactIntegerOrFail(BigDecimal bd, String op) {
    try {
      return bd.stripTrailingZeros().toBigIntegerExact();
    } catch (ArithmeticException ex) {
      fail("[" + op + "] Parity check requires an integer value, but was " + bd.toPlainString());
      return BigInteger.ZERO; // unreachable
    }
  }

  private static String render(Object o) {
    return String.valueOf(o);
  }

  private static String annotateZero(Number n) {
    if (isNegativeZero(n)) {
      return "-0.0 (signed zero)";
    }
    return render(n);
  }

  private static boolean isNegativeZero(Number n) {
    if (n instanceof Double d) {
      return d == 0.0 && Double.doubleToRawLongBits(d) == Double.doubleToRawLongBits(-0.0d);
    }
    if (n instanceof Float f) {
      return f == 0.0f && Float.floatToRawIntBits(f) == Float.floatToRawIntBits(-0.0f);
    }
    return false;
  }

  static void fail(String msg) {
    throw new AssertionError(msg);
  }
}
