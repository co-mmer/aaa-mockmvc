package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange1DeleteHeader;

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
public interface TestArrange2DeleteUrl {

  /**
   * Adds a single query parameter to the request URL. Calling this method multiple times appends
   * additional parameters; if the same key is provided again, the last value wins (replaces the
   * previous one).
   *
   * @param key the query parameter name; must not be {@code null}
   * @param value the query parameter value; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange2DeleteUrl query(String key, String value);

  /**
   * Switches to the headers arrange step for the same request specification.
   *
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1DeleteHeader headers();
}
