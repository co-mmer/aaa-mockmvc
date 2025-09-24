/**
 * Assertions for deserialized {@link java.lang.Number} instances.
 *
 * <p><b>Scope:</b> Provides nullability, equality, sign, and parity checks on numeric payloads
 * obtained via {@code content().asInteger()}, {@code asLong()}, {@code asDouble()}, etc. Works with
 * common {@link java.lang.Number} subtypes (e.g. {@link java.lang.Integer}, {@link java.lang.Long},
 * {@link java.lang.Short}, {@link java.lang.Byte}, {@link java.lang.Double}, {@link
 * java.lang.Float}, {@link java.math.BigInteger}, {@link java.math.BigDecimal}). The response body
 * has already been deserialized once using the configured mapper and is cached for all subsequent
 * assertions.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertions throw {@link java.lang.AssertionError} on failure.
 *   <li>Comparisons are numeric, not type-based (e.g., {@code 1} equals {@code 1.0}).
 *   <li>Signed zeros {@code +0.0} and {@code -0.0} are treated as zero for sign checks.
 *   <li>Parity checks ({@code isEven}, {@code isOdd}) require an integer value; non-integers fail.
 *   <li>Null arguments are not permitted; passing {@code null} will result in {@link
 *       java.lang.NullPointerException}.
 * </ul>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number.TestAssert1Number}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.number;
