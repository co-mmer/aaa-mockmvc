/**
 * Assertions for HTTP response headers.
 *
 * <p><b>Scope:</b> Provides fluent checks on the headers captured during {@code act().perform()}.
 * Header names are treated case-insensitively and multi-value headers are supported. Assertions
 * operate on the immutable response snapshot; no additional I/O is performed.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertion failures throw {@link java.lang.AssertionError}.
 *   <li>Null arguments are not permitted; passing {@code null} will result in {@link
 *       java.lang.NullPointerException}.
 *   <li>Multi-value headers can be matched by single entry checks or by exact equality including
 *       order.
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/foo");
 *
 * act()
 *   .perform();
 *
 * asserts()
 *   .headers()
 *   .containsKey("Content-Type")
 *   .containsEntry("Content-Type", "application/json")
 *   .doesNotContainKey("X-Debug");
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead}.
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head;
