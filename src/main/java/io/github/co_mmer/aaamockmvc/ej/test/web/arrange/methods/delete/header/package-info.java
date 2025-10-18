/**
 * Arrange-phase for configuring HTTP headers on DELETE requests.
 *
 * <p><b>Scope:</b> Provides a fluent API to define request headers ({@code Accept}, {@code
 * Authorization}, and arbitrary custom headers) for a pending DELETE request. Header names are
 * treated case-insensitively, and multi-value headers are supported. These steps mutate only the
 * request specification; no network I/O is performed until {@code act().perform()} is invoked.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All methods return the next step in the arrange chain, exposing only arrange-appropriate
 *       operations for the current state.
 *   <li>All validations throw {@link java.lang.NullPointerException} for {@code null} arguments.
 *   <li>Multi-value headers are appended rather than replaced, unless {@code set(...)} is used.
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .delete("/api/items")
 *   .headers()
 *   .accept(MediaType.APPLICATION_JSON)
 *   .auth("token-123")
 *   .add("X-Trace-Id", "1234");
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange1DeleteHeader}.
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header;
