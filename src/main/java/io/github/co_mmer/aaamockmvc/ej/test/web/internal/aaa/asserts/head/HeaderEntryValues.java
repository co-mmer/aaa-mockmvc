package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.unicode.UnicodeNormalizer.normalizeCollection;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Since("2.1.0")
public final class HeaderEntryValues {

  private final List<String> values;
  private final List<String> comparison;

  @Since("2.1.0")
  public HeaderEntryValues(@Nullable String... values) {
    if (values == null) {
      throw new IllegalArgumentException("Header values must not be null.");
    }
    for (int i = 0; i < values.length; i++) {
      if (values[i] == null) {
        throw new IllegalArgumentException(
            "Header value at position %d must not be null.".formatted(i + 1));
      }
    }
    this.values = List.of(values);
    this.comparison = normalizeCollection(this.values);
  }

  @NonNull
  @Since("2.1.0")
  public List<String> values() {
    return this.values;
  }

  @NonNull
  @Since("2.1.0")
  public List<String> comparison() {
    return this.comparison;
  }
}
