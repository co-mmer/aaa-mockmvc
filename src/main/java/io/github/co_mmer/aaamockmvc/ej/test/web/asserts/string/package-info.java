/**
 * Assertions for deserialized {@link java.util.Map} instances.
 *
 * <p><b>Scope:</b> Provides size, emptiness, and equality checks on maps obtained via
 * {@code content().asMap(K,V)}. The response body has already been deserialized once using the
 * configured mapper and is cached for all subsequent assertions.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertions throw {@link java.lang.AssertionError} on failure.
 *   <li>Unless explicitly documented otherwise, order of entries is ignored; equality is based on
 *       key–value pairs.
 *   <li>String comparisons (for keys or values of type {@link java.lang.String}) are normalized
 *       using Unicode Normalization Form C (NFC).
 *   <li>Null arguments are not permitted; passing {@code null} will result in
 *       {@link java.lang.NullPointerException}.
 * </ul>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;
