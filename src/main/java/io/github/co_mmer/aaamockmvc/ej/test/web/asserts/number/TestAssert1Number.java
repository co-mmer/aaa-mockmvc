package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;

import lombok.NonNull;

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
public interface TestAssert1Number {

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

  /**
   * Asserts that the number is strictly greater than zero ({@code > 0}).
   *
   * <p>Examples: {@code 1}, {@code 1.0} pass; {@code 0}, {@code 0.0}, {@code -0.0}, {@code -1}
   * fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert3Number isPositive();

  /**
   * Asserts that the number is less than or equal to zero ({@code <= 0}).
   *
   * <p>Zero (including {@code +0.0} and {@code -0.0}) and negatives pass; positives fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert4Number isNonPositive();

  /**
   * Asserts that the number is strictly less than zero ({@code < 0}).
   *
   * <p>Note: {@code -0.0} is treated as zero and does <em>not</em> count as negative.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert3Number isNegative();

  /**
   * Asserts that the number is greater than or equal to zero ({@code >= 0}).
   *
   * <p>Zero (including {@code +0.0} and {@code -0.0}) and positives pass; negatives fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert5Number isNoNegative();

  /**
   * Asserts that the number is numerically zero.
   *
   * <p>Both {@code +0.0} and {@code -0.0} are considered zero.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssertLNumber isZero();

  /**
   * Asserts that the actual number is <em>not</em> numerically equal to {@code expected}.
   *
   * <p>Comparison is value-based (e.g., {@code 1} equals {@code 1.0}).
   *
   * @param expected the value it must not equal; must not be {@code null}
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssertLNumber isNotEqualTo(@NonNull Number expected);

  /**
   * Asserts that the actual number is numerically equal to {@code expected}.
   *
   * <p>Comparison is value-based (e.g., {@code 1} equals {@code 1.0}).
   *
   * @param expected the expected value; must not be {@code null}
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssertLNumber isEqualTo(@NonNull Number expected);

  /**
   * Asserts that the actual number is <em>even</em>.
   *
   * <p>Requires an integer value (no fractional part). Non-integer values cause the assertion to
   * fail. Examples: {@code 2}, {@code 2.0} pass; {@code 3}, {@code 2.5} fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert7Number isEven();

  /**
   * Asserts that the actual number is <em>odd</em>.
   *
   * <p>Requires an integer value (no fractional part). Non-integer values cause the assertion to
   * fail. Examples: {@code 3} passes; {@code 2}, {@code 3.1} fail.
   *
   * @return the next step in the fluent assertion chain
   * @since 2.0.0
   */
  TestAssert7Number isOdd();
}
