package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

import java.util.List;
import java.util.Objects;

public record MultipartBody(List<MultipartPart> parts) implements RequestBody {

  public MultipartBody {
    Objects.requireNonNull(parts, "parts must not be null");

    if (parts.stream().anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("parts must not contain null values");
    }

    parts = List.copyOf(parts);
  }
}
