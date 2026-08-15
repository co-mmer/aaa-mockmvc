package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

import java.util.Objects;

public record TextBody(String value) implements RequestBody {

  public TextBody {
    Objects.requireNonNull(value, "value must not be null");
  }
}
