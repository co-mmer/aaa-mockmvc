package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.DomainValidation;
import java.util.ArrayList;
import java.util.List;

@Since("2.0.2")
public final class MultipartBody implements RequestBody {

  private final List<MultipartPart> parts = new ArrayList<>();

  @Since("2.0.2")
  public void add(MultipartPart part) {
    DomainValidation.requireNonNull(part, "Multipart part must not be null");
    this.parts.add(part);
  }

  @Since("2.0.2")
  public void add(String name, String filename, String contentType, byte[] content) {
    add(new MultipartPart(name, filename, contentType, content));
  }

  @Since("2.0.2")
  public List<MultipartPart> parts() {
    return List.copyOf(this.parts);
  }
}
