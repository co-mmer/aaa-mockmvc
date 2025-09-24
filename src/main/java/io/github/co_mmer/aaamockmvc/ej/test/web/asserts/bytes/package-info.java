/**
 * Assertions for raw {@code byte[]} response bodies.
 *
 * <p><b>Scope:</b> Provides emptiness, length, and exact equality checks on binary payloads
 * obtained via {@code content().asBytes()}. The response body has already been captured once and is
 * cached for all subsequent assertions.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertions throw {@link java.lang.AssertionError} on failure.
 *   <li>Unless explicitly documented otherwise, checks are performed on the raw byte array as
 *       returned from the HTTP response without transformation.
 *   <li>Empty payloads are defined as either zero-length byte arrays or trimmed textual bodies
 *       equal to {@code "[]"} or {@code "{}"}.
 *   <li>Null arguments are not permitted; passing {@code null} will result in {@link
 *       java.lang.NullPointerException}.
 * </ul>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;
