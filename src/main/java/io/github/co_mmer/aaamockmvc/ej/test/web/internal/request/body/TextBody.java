package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.DomainValidation;

@Since("2.0.2")
public record TextBody(String value) implements RequestBody {

  public TextBody {
    DomainValidation.requireNonNull(value, "value content must not be null");
  }
}
