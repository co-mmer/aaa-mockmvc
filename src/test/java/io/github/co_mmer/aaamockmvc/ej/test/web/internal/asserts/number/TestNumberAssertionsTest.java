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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TestNumberAssertionsTest {

  @Nested
  class Nullity {

    @Test
    void GIVEN_null_WHEN_isNull_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertNull(null, "isNull()"));
    }

    @Test
    void GIVEN_nonNull_WHEN_isNull_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertNull(123, "isNull()"));

      // Assert
      assertThat(ex.getMessage(), containsString("expected <null>"));
    }

    @Test
    void GIVEN_nonNull_WHEN_isNotNull_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertNotNull(0, "isNotNull()"));
    }

    @Test
    void GIVEN_null_WHEN_isNotNull_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertNotNull(null, "isNotNull()"));

      // Assert
      assertThat(ex.getMessage(), containsString("expected non-null"));
    }
  }

  @Nested
  class Sign {

    private static Stream<Number> zeroValues() {
      return Stream.of(
          0, 0L, 0D, 0F, BigDecimal.ZERO, BigInteger.ZERO, Short.valueOf("0"), Byte.valueOf("0"));
    }

    static Stream<Number> oneValues() {
      return Stream.of(
          1, 1L, 1D, 1F, BigDecimal.ONE, BigInteger.ONE, Short.valueOf("1"), Byte.valueOf("1"));
    }

    private static Stream<Number> minusOneValues() {
      return Stream.of(
          -1,
          -1L,
          -1D,
          -1F,
          BigDecimal.valueOf(-1),
          BigInteger.valueOf(-1),
          Short.valueOf("-1"),
          Byte.valueOf("-1"));
    }

    static Stream<Number> zeroAndMinusOneValues() {
      return Stream.concat(zeroValues(), minusOneValues());
    }

    @ParameterizedTest
    @MethodSource("oneValues")
    void GIVEN_1_WHEN_isPositive_THEN_success(Number n) {
      // Act & Assert
      assertDoesNotThrow(() -> assertPositive(n));
    }

    @ParameterizedTest
    @MethodSource("zeroAndMinusOneValues")
    void GIVEN_zero_minus1_WHEN_isPositive_THEN_fail(Number n) {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertPositive(n));

      // Assert
      assertThat(ex.getMessage(), containsString("positive number (> 0)"));
    }

    @Test
    void GIVEN_minusZeroDouble_WHEN_isNegative_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertNegative(-0.0d));

      // Assert
      assertThat(ex.getMessage(), containsString("negative number (< 0)"));
      assertThat(ex.getMessage(), containsString("-0.0"));
    }

    @ParameterizedTest
    @MethodSource("minusOneValues")
    void GIVEN_minus1_WHEN_isNegative_THEN_success(Number n) {
      // Act & Assert
      assertDoesNotThrow(() -> assertNegative(n));
    }

    @Test
    void GIVEN_0_WHEN_isNonPositive_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertNonPositive(0));
    }

    @ParameterizedTest
    @MethodSource("oneValues")
    void GIVEN_1_WHEN_isNonPositive_THEN_fail(Number n) {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertNonPositive(n));

      // Assert
      assertThat(ex.getMessage(), containsString("non-positive number (<= 0)"));
    }

    @ParameterizedTest
    @MethodSource("zeroValues")
    void GIVEN_0_WHEN_isNonNegative_THEN_success(Number n) {
      // Act & Assert
      assertDoesNotThrow(() -> assertNonNegative(n));
    }

    @ParameterizedTest
    @MethodSource("minusOneValues")
    void GIVEN_minus1_WHEN_isNonNegative_THEN_fail(Number n) {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertNonNegative(n));

      // Assert
      assertThat(ex.getMessage(), containsString("non-negative number (>= 0)"));
    }
  }

  @Nested
  class Zero {

    private static Stream<Number> zeroValues() {
      return Stream.of(
          0, 0L, 0D, 0F, BigDecimal.ZERO, BigInteger.ZERO, Short.valueOf("0"), Byte.valueOf("0"));
    }

    private static Stream<Number> minusZeroValues() {
      return Stream.of(
          -0,
          -0L,
          -0.0D,
          -0.0F,
          BigDecimal.valueOf(-0),
          BigInteger.valueOf(-0),
          Short.valueOf("-0"),
          Byte.valueOf("-0"));
    }

    @ParameterizedTest
    @MethodSource("zeroValues")
    void GIVEN_0_WHEN_isZero_THEN_success(Number n) {
      // Act & Assert
      assertDoesNotThrow(() -> assertZero(n));
    }

    @ParameterizedTest
    @MethodSource("minusZeroValues")
    void GIVEN_minusZeroDouble_WHEN_isZero_THEN_success(Number n) {
      // Act & Assert
      assertDoesNotThrow(() -> assertZero(n));
    }

    @Test
    void GIVEN_1_WHEN_isZero_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertZero(1));

      // Assert
      assertThat(ex.getMessage(), containsString("Expected zero (== 0)"));
    }
  }

  @Nested
  class Equality {

    @Test
    void GIVEN_1_and_1dot0_WHEN_isEqualTo_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertEqual(1, 1.0));
    }

    @Test
    void GIVEN_1_and_2_WHEN_isEqualTo_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertEqual(1, 2));

      // Assert
      assertThat(ex.getMessage(), containsString("Expected number == 2"));
    }

    @Test
    void GIVEN_1_and_2_WHEN_isNotEqualTo_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertNotEqual(1, 2));
    }

    @Test
    void GIVEN_1_and_1dot000_WHEN_isNotEqualTo_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertNotEqual(1, 1.000));

      // Assert
      assertThat(ex.getMessage(), containsString("both were equal"));
    }
  }

  @Nested
  class Parity {

    @Test
    void GIVEN_2_WHEN_isEven_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertEven(2, "isEven()"));
    }

    @Test
    void GIVEN_3_WHEN_isEven_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertEven(3, "isEven()"));

      // Assert
      assertThat(ex.getMessage(), containsString("even integer"));
    }

    @Test
    void GIVEN_2dot0_WHEN_isEven_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertEven(2.0, "isEven()"));
    }

    @Test
    void GIVEN_2dot5_WHEN_isEven_THEN_fail_requiresInteger() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertEven(2.5, "isEven()"));

      // Assert
      assertThat(ex.getMessage(), containsString("requires an integer value"));
    }

    @Test
    void GIVEN_3_WHEN_isOdd_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> assertOdd(3, "isOdd()"));
    }

    @Test
    void GIVEN_2_WHEN_isOdd_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertOdd(2, "isOdd()"));

      // Assert
      assertThat(ex.getMessage(), containsString("odd integer"));
    }

    @Test
    void GIVEN_3dot2_WHEN_isOdd_THEN_fail_requiresInteger() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> assertOdd(3.2, "isOdd()"));

      // Assert
      assertThat(ex.getMessage(), containsString("requires an integer value"));
    }
  }

  @Nested
  class require {

    @Test
    void GIVEN_2_WHEN_requireNumber_THEN_success() {
      // Act & Assert
      assertDoesNotThrow(() -> requireNumber(2, "isEven()"));
    }

    @Test
    void GIVEN_2_WHEN_requireNumber_THEN_return_2() {
      // Act
      var number = requireNumber(2, "isEven()");

      // Assert
      assertThat(number, is(2));
    }

    @Test
    void GIVEN_null_WHEN_isEven_THEN_fail() {
      // Act
      var ex = assertThrows(AssertionError.class, () -> requireNumber(null, "isEven()"));

      // Assert
      assertThat(ex.getMessage(), containsString("isEven"));
    }
  }
}
