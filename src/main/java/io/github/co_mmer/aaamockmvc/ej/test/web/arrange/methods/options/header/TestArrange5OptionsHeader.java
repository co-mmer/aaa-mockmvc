package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header;

/**
 * OPTIONS arrange step for defining HTTP headers.
 *
 * <p><b>What it does:</b> Configures request headers for the pending OPTIONS request. Header names
 * are treated case-insensitively and multi-value headers are supported. This step only mutates the
 * request specification; no network I/O is performed until {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .options("/api/items")
 *   .headers()
 *   .accept(MediaType.APPLICATION_JSON)
 *   .auth("token-123")
 *   .add("X-Trace-Id", "1234");
 * }</pre>
 *
 * <p><b>Preconditions:</b> This is part of the arrange phase; {@code actPerform().perform()} has
 * not yet been executed.
 *
 * @since 1.0.0
 */
public interface TestArrange5OptionsHeader {

  /**
   * Adds a header value. If the header already exists, the value is <em>appended</em> (multi-value
   * header); it does not replace existing values.
   *
   * @param key the header name; must not be {@code null}
   * @param value the header value (converted via {@code String.valueOf(value)}); must not be {@code
   *     null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange5OptionsHeader add(String key, Object value);
}
