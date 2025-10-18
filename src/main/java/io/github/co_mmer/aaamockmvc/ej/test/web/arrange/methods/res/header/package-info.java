/**
 * Arrange-phase for configuring HTTP headers on POST, PUT, and PATCH requests.
 *
 * <p><b>Scope:</b> Provides a fluent API to define request headers ({@code Accept}, {@code
 * Authorization}, {@code Content-Type}, and arbitrary custom headers) for a pending POST/PUT/PATCH
 * request. Header names are treated case-insensitively, and multi-value headers are supported.
 * These steps mutate only the request specification; no network I/O is performed until {@code
 * act().perform()} is invoked.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All methods return the next step in the arrange chain, exposing only arrange-appropriate
 *       operations for the current state.
 *   <li>All validations throw {@link java.lang.NullPointerException} for {@code null} arguments.
 *   <li>Multi-value headers are appended rather than replaced, unless {@code set(...)} is used.
 *   <li>If a {@code Content-Type} header defines a charset, it is used to encode string payloads;
 *       otherwise UTF-8 is used by default.
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .post("/api/items")   // or .put("/api/items") or .patch("/api/items")
 *   .headers()
 *   .accept(MediaType.APPLICATION_JSON)
 *   .auth("token-123")
 *   .contentType(MediaType.APPLICATION_JSON)
 *   .add("X-Trace-Id", "1234");
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange1ResHead}.
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header;
