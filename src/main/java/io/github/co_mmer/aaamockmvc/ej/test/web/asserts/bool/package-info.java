/**
 * Assertions for deserialized {@link java.lang.Boolean} instances.
 *
 * <p><b>Scope:</b> Provides nullability and truth-value checks on boolean payloads obtained via
 * {@code content().asBoolean()}. The response body has already been deserialized once using the
 * configured mapper and is cached for all subsequent assertions.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertions throw {@link java.lang.AssertionError} on failure.
 *   <li>Unless explicitly documented otherwise, no implicit conversions are performed (e.g.,
 *       strings like {@code "true"} are not accepted).
 *   <li>Null arguments are not permitted; passing {@code null} will result in {@link
 *       java.lang.NullPointerException}.
 * </ul>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool;
