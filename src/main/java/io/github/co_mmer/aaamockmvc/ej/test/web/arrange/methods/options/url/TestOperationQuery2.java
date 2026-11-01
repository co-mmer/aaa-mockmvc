package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url;

import java.util.Map;
import org.springframework.lang.NonNull;

interface TestOperationQuery2 {

  /**
   * Adds all given query parameters to the request URL in one call. If a key already exists, the
   * provided value replaces the previous one.
   *
   * @param keyValue map of query parameters to add; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange3OptionsUrl query(@NonNull Map<String, String> keyValue);
}
