package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header;

interface TestOperationAuth {

  /**
   * Sets the {@code Authorization} header.
   *
   * @param token the bearer token; must not be {@code null} or blank
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   * the current state.
   * @since 2.0.0
   */
  TestArrange3OptionsHeader auth(String token);
}
