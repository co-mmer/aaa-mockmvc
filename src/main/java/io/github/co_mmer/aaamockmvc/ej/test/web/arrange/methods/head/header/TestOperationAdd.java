package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header;

interface TestOperationAdd {

  /**
   * Adds a header value. If the header already exists, the value is <em>appended</em> (multi-value
   * header); it does not replace existing values.
   *
   * @param key   the header name; must not be {@code null}
   * @param value the header value (converted via {@code String.valueOf(value)}); must not be
   *              {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   * the current state.
   * @since 2.0.0
   */
  TestArrange5HeadHeader add(String key, Object value);

}
