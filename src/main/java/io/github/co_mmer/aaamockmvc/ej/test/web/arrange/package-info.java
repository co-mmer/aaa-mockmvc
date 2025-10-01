/**
 * Arrange-phase entry point for defining HTTP requests.
 *
 * <p><b>Scope:</b> Provides the DSL entry point
 * {@link io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange} for starting the
 * specification of an HTTP request. All standard HTTP methods are supported: GET, DELETE, HEAD,
 * OPTIONS, POST, PUT, and PATCH. Each method begins a fluent chain where URL, query parameters,
 * headers, and (where applicable) request bodies can be defined. This phase mutates only the
 * request specification; no network I/O is performed until {@code act().perform()} is invoked.
 *
 * <p><b>General rules:</b></p>
 * <ul>
 *   <li>All methods return the next step in the arrange chain, exposing only arrange-appropriate
 *       operations for the current state.</li>
 *   <li>All validations throw {@link java.lang.NullPointerException} for {@code null} arguments.</li>
 *   <li>URL patterns may contain URI template variables (e.g. {@code "/users/{id}"}), which are
 *       substituted in order.</li>
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b></p>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/users/{id}", 42)
 *   .query("verbose", "true")
 *   .headers()
 *   .accept(MediaType.APPLICATION_JSON);
 *
 * act()
 *   .perform();
 *
 * asserts()
 *   .status()
 *   .isOk();
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is
 * {@link io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.arrange;
