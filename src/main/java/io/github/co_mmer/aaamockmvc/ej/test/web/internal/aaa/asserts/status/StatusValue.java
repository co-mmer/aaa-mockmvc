package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record StatusValue(int value) {
  @NonNull
  @Since("2.1.0")
  public static StatusValue of(@Nullable HttpStatus value) {
    if (value == null) {
      throw new IllegalArgumentException("Expected HTTP status must not be null.");
    }
    return new StatusValue(value.value());
  }
}
