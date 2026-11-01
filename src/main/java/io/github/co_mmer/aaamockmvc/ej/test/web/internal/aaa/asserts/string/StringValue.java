package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.unicode.UnicodeNormalizer.normalizeObject;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class StringValue {
  private final String value;
  private final String comparison;

  @Since("2.1.0")
  public StringValue(@Nullable String value) {
    if (value == null) {
      throw new IllegalArgumentException("String must not be null.");
    }
    this.value = value;
    this.comparison = normalizeObject(value);
  }

  @NonNull
  @Since("2.1.0")
  public String value() {
    return this.value;
  }

  @NonNull
  @Since("2.1.0")
  public String comparison() {
    return this.comparison;
  }
}
