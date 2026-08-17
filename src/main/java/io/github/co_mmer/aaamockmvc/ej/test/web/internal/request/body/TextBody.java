package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Objects;

@Since("2.0.2")
public record TextBody(String value) implements RequestBody {

  public TextBody {
    Objects.requireNonNull(value, "value must not be null");
  }
}
