package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.AAAType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.Nullable;

public record MappingType<T>(Class<T> type) implements AAAType<T> {

  @Since("2.1.0")
  public MappingType(@Nullable Class<T> type) {
    if (type == null) {
      throw new IllegalArgumentException("Target type must not be null.");
    }
    this.type = type;
  }
}
