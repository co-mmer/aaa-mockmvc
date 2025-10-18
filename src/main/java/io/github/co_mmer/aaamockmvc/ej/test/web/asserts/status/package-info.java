/**
 * Assertions for HTTP response status codes.
 *
 * <p><b>Scope:</b> Provides exact and range-based checks on the status captured during {@code
 * actPerform().perform()}. Use this package to verify common statuses (200, 201, 404, …) or status
 * code families (2xx, 3xx, 4xx, 5xx).
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertions throw {@link java.lang.AssertionError} on failure.
 *   <li>Status codes can be asserted either via {@link org.springframework.http.HttpStatus}
 *       constants or via numeric codes.
 *   <li>Null arguments are not permitted; passing {@code null} will result in {@link
 *       java.lang.NullPointerException}.
 * </ul>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;
