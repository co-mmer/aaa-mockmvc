package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url;

import java.util.Map;
import lombok.NonNull;

interface TestOperationQuery2 {

  /**
   * Adds all given query parameters to the request URL in one call. If a key already exists, the
   * provided value replaces the previous one.
   *
   * @param keyValue map of query parameters to add; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   * the current state.
   * @since 2.0.0
   */
  TestArrange3DeleteUrl query(@NonNull Map<String, String> keyValue);

}
