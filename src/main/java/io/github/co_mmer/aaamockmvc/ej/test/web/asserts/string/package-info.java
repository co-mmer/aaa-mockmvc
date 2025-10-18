/**
 * Assertions for deserialized {@link java.lang.String} instances.
 *
 * <p><b>Scope:</b> Provides string-specific checks such as emptiness, length, and equality on
 * response bodies obtained via {@code content().asString()}. The response body has already been
 * captured once and is cached for all subsequent assertions.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertions throw {@link java.lang.AssertionError} on failure.
 *   <li>String comparisons are normalized using Unicode Normalization Form C (NFC).
 *   <li>Null arguments are not permitted; passing {@code null} will result in {@link
 *       java.lang.NullPointerException}.
 * </ul>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;
