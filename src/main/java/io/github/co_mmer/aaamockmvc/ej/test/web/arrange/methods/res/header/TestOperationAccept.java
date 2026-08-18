package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;

interface TestOperationAccept {

  /**
   * Sets the {@code Accept} header to the given media types (comma-separated).
   *
   * @param mediaTypes one or more media types; must not be {@code null} or empty
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange2ResHead accept(@NonNull MediaType... mediaTypes);
}
