package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header;

import java.util.List;
import java.util.Map;
import org.springframework.lang.NonNull;

interface TestOperationSet {

  /**
   * Replaces the current header map with the given one (full replace). Keys are treated
   * case-insensitively; value lists are preserved as given (order kept).
   *
   * @param keyValue the complete header map to set; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange6HeadHeader set(@NonNull Map<String, List<Object>> keyValue);
}
