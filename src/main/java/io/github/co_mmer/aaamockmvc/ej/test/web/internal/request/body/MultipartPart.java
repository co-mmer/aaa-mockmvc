package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.DomainValidation;
import java.util.Arrays;
import org.springframework.lang.Nullable;

@Since("2.0.2")
@SuppressWarnings({"java:S6206", "ClassCanBeRecord"})
public final class MultipartPart {

  private final String name;
  private final String filename;
  private final String contentType;
  private final byte[] content;

  public MultipartPart(
      String name, @Nullable String filename, @Nullable String contentType, byte[] content) {

    DomainValidation.requireNonBlank(name, "Multipart part name must not be blank");
    DomainValidation.requireNonNull(content, "Multipart content must not be null");

    this.name = name;
    this.filename = filename;
    this.contentType = contentType;
    this.content = Arrays.copyOf(content, content.length);
  }

  public String name() {
    return this.name;
  }

  public String filename() {
    return this.filename;
  }

  public String contentType() {
    return this.contentType;
  }

  public byte[] content() {
    return Arrays.copyOf(this.content, this.content.length);
  }
}
