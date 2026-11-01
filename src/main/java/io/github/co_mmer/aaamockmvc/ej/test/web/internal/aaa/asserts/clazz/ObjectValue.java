package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.unicode.UnicodeNormalizer.normalizeObject;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class ObjectValue<T> {
  private final T value;
  private final String comparison;

  @Since("2.1.0")
  public ObjectValue(@Nullable T value) {
    this.value = value;
    this.comparison = value == null ? null : normalizeObject(value);
  }

  @NonNull
  @Since("2.1.0")
  public static <T> ObjectValue<T> expected(@Nullable T value) {
    if (value == null) {
      throw new IllegalArgumentException("Expected object must not be null.");
    }
    return new ObjectValue<>(value);
  }

  @Nullable
  @Since("2.1.0")
  public T value() {
    return this.value;
  }

  @Nullable
  @Since("2.1.0")
  public String comparison() {
    return this.comparison;
  }
}
