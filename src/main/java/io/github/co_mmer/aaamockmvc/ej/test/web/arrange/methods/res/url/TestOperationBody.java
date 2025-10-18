package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody;

interface TestOperationBody {

  /**
   * Switches to the body arrange step for the same request specification.
   *
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1ResBody body();
}
