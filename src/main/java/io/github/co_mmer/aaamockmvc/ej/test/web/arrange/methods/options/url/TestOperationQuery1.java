package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url;

interface TestOperationQuery1 {

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
  TestArrange2OptionsUrl query(String key, String value);
}
