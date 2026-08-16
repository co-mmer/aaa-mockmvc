package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request;

import java.util.Arrays;
import java.util.Objects;

public record BinaryBody(byte[] value) implements RequestBody {

  public BinaryBody {
    Objects.requireNonNull(value, "value must not be null");

    value = Arrays.copyOf(value, value.length);
  }

  @Override
  public byte[] value() {
    return Arrays.copyOf(value, value.length);
  }
}
