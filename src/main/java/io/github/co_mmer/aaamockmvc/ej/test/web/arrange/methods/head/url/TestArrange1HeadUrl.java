package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange1HeadHeader;
import java.util.Map;
import lombok.NonNull;

/**
 * HEAD arrange step for defining the request URL and query parameters.
 *
 * <p><b>What it does:</b> Adds query parameters to the configured HEAD URL and transitions to
 * further arrange steps (e.g., headers). No network I/O is performed; this only mutates the request
 * specification for the subsequent {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .head("/api/items")
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
public interface TestArrange1HeadUrl {

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
  TestArrange2HeadUrl query(String key, String value);

  /**
   * Adds all given query parameters to the request URL in one call. If a key already exists, the
   * provided value replaces the previous one.
   *
   * @param keyValue map of query parameters to add; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange3HeadUrl query(@NonNull Map<String, String> keyValue);

  /**
   * Switches to the headers arrange step for the same request specification.
   *
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1HeadHeader headers();
}
