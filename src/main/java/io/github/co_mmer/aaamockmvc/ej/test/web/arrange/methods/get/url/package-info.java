/**
 * Arrange-phase for configuring the request URL and query parameters on GET requests.
 *
 * <p><b>Scope:</b> Provides a fluent API to add query parameters to a GET request URL and
 * transition to further arrange steps (e.g., headers). Query parameters can be added individually
 * or in bulk; when the same key is specified multiple times, the last value wins (replaces the
 * previous one). These steps mutate only the request specification; no network I/O is performed
 * until {@code act().perform()} is invoked.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All methods return the next step in the arrange chain, exposing only arrange-appropriate
 *       operations for the current state.
 *   <li>All validations throw {@link java.lang.NullPointerException} for {@code null} arguments.
 *   <li>For duplicate query keys, the most recently provided value replaces the previous one.
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/items")
 *   .query("page", "1")
 *   .headers()
 *   .add("X-Trace-Id", "1234");
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange1GetUrl}.
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url;
