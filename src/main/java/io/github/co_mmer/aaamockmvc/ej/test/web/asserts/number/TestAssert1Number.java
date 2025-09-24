package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

/**
 * Entry point for numeric content assertions.
 *
 * <p><b>What it does:</b> Provides nullity, equality, sign, and parity checks for numeric payloads
 * captured during {@code act().perform()}. Works with common {@link Number} subtypes (e.g. {@link
 * Integer}, {@link Long}, {@link Short}, {@link Byte}, {@link Double}, {@link Float}, {@link
 * java.math.BigInteger}, {@link java.math.BigDecimal}).
 *
 * <p><b>Semantics:</b>
 *
 * <ul>
 *   <li>Comparisons are <em>numeric</em>, not type-based (e.g., {@code 1} equals {@code 1.0}).
 *   <li>Signed zeros {@code +0.0} and {@code -0.0} are treated as zero for sign checks.
 *   <li>Parity checks ({@code isEven}/{@code isOdd}) require an integer value (no fractional part);
 *       otherwise the assertion fails.
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange().get("/api/count");
 * act().perform();
 * asserts().content().asInteger()   // or asDouble()/asFloat()/...
 *     .isNotNull()
 *     .isPositive()
 *     .isNotEqualTo(0);
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code act().perform()} has been executed; assertions operate on the
 * stored snapshot.
 *
 * @since 2.0.0
 */
public interface TestAssert1Number
    extends OperationNegative,
        OperationNonNegative,
        OperationPositive,
        OperationNonPositive,
        OperationParity,
        OperationZero,
        OperationEquals {

  /**
   * Asserts that the number is {@code null}.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssertLNumber isNull();

  /**
   * Asserts that the number is not {@code null}.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert2Number isNotNull();
}
