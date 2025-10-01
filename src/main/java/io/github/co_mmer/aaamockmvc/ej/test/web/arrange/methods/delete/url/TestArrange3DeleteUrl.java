package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url;

/**
 * DELETE arrange step for defining the request URL and query parameters.
 *
 * <p><b>What it does:</b> Adds query parameters to the configured DELETE URL and transitions to
 * further arrange steps (e.g., headers). No network I/O is performed; this only mutates the request
 * specification for the subsequent {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .delete("/api/items")
 *   .query("page", "1")
 *   .headers()
 *   .add("X-Trace-Id", "1234");
 * }</pre>
 *
 * <p><b>Preconditions:</b> This is part of the arrange phase; {@code actPerform().perform()} has
 * not yet been executed.
 *
 * @since 1.0.0
 */
public interface TestArrange3DeleteUrl extends TestOperationHeader {}
