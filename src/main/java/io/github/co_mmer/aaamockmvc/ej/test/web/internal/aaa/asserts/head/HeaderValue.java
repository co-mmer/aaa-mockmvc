package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.unicode.UnicodeNormalizer.normalizeMapList;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class HeaderValue {
  private final Map<String, List<String>> values;
  private final Map<String, List<String>> comparison;

  @Since("2.1.0")
  public HeaderValue(@Nullable Map<String, List<String>> values) {
    if (values == null) {
      throw new IllegalArgumentException("Headers must not be null.");
    }
    var copy = new LinkedHashMap<String, List<String>>();
    values.forEach(
        (name, entries) -> {
          if (name == null || entries == null || entries.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Header names and values must not be null.");
          }
          copy.put(name, List.copyOf(entries));
        });
    this.values = Collections.unmodifiableMap(copy);
    this.comparison = normalizeMapList(this.values);
  }

  @NonNull
  @Since("2.1.0")
  public Map<String, List<String>> values() {
    return this.values;
  }

  @NonNull
  @Since("2.1.0")
  public Map<String, List<String>> comparison() {
    return this.comparison;
  }
}
