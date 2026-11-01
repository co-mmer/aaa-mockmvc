package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.unicode.UnicodeNormalizer.normalizeMap;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Since("2.1.0")
public final class MapValue<K, V> {

  private final Map<K, V> values;
  private final Map<String, String> comparison;

  @Since("2.1.0")
  public MapValue(@Nullable Map<K, V> values) {
    if (values == null) {
      throw new IllegalArgumentException("Map must not be null.");
    }
    this.values = Collections.unmodifiableMap(new LinkedHashMap<>(values));
    this.comparison = Collections.unmodifiableMap(normalizeMap(this.values));
  }

  @NonNull
  @Since("2.1.0")
  public Map<K, V> values() {
    return this.values;
  }

  @NonNull
  @Since("2.1.0")
  public Map<String, String> comparison() {
    return this.comparison;
  }
}
