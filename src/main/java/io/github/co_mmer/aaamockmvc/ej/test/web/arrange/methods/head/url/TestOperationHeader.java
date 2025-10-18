package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange1HeadHeader;

interface TestOperationHeader {

  /**
   * Switches to the headers arrange step for the same request specification.
   *
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange1HeadHeader headers();
}
