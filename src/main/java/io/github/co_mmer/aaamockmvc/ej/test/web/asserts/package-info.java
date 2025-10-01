/**
 * Assertions phase entry point for verifying HTTP responses.
 *
 * <p><b>Scope:</b> Provides the DSL entry point
 * {@link io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert} for performing assertions on
 * the immutable HTTP response snapshot produced by {@code act().perform()}. All response facets can
 * be tested:
 * <ul>
 *   <li><b>Status</b> — exact codes or ranges (e.g. 200, 201, 404, is2xxSuccessful())</li>
 *   <li><b>Headers</b> — presence, key/value matching, multi-value handling</li>
 *   <li><b>Content</b> — raw bytes, strings, booleans, maps, collections, or type-safe POJOs</li>
 * </ul>
 *
 * <p><b>General rules (apply to all methods in this package):</b></p>
 * <ul>
 *   <li>Assertions operate on the stored snapshot of the HTTP response; no further network I/O
 *       is performed.</li>
 *   <li>All assertion failures throw {@link java.lang.AssertionError}.</li>
 *   <li>Null arguments are not permitted; passing {@code null} results in
 *       {@link java.lang.NullPointerException}.</li>
 *   <li>Unless explicitly documented otherwise, string comparisons are normalized using
 *       Unicode Normalization Form C (NFC).</li>
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b></p>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/users/42");
 *
 * act()
 *   .perform();
 *
 * asserts()
 *   .status()
 *   .isOk()
 *   .content()
 *   .asClass(User.class)
 *   .isNotNull()
 *   .headers()
 *   .containsKey("Content-Type");
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is
 * {@link io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert}.
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;
