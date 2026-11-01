package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record BooleanValue(Boolean value) {
  @Since("2.1.0")
  public BooleanValue(@Nullable Boolean value) {
    this.value = value;
  }

  @Nullable
  @Override
  @Since("2.1.0")
  public Boolean value() {
    return this.value;
  }

  @NonNull
  @Since("2.1.0")
  public static BooleanValue expected(@Nullable Boolean value) {
    if (value == null) {
      throw new IllegalArgumentException("Expected boolean must not be null.");
    }
    return new BooleanValue(value);
  }
}
