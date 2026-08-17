package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.NonNull;

@Since("2.0.2")
public final class MultipartBody implements RequestBody {

  private final List<MultipartPart> parts = new ArrayList<>();

  @Since("2.0.2")
  public void add(@NonNull MultipartPart part) {
    parts.add(part);
  }

  @Since("2.0.2")
  public void add(
      @NonNull String name, String filename, String contentType, @NonNull byte[] content) {
    validateName(name);
    validateFilename(filename);
    validateContentType(contentType);

    parts.add(
        new MultipartPart(name, filename, contentType, Arrays.copyOf(content, content.length)));
  }

  @Since("2.0.2")
  public List<MultipartPart> parts() {
    return List.copyOf(parts);
  }

  private static void validateName(String name) {
    if (name.isBlank()) {
      throw new IllegalArgumentException("Multipart part name must not be blank");
    }
  }

  private static void validateFilename(String filename) {
    if (filename != null && filename.isBlank()) {
      throw new IllegalArgumentException("Multipart filename must not be blank");
    }
  }

  private static void validateContentType(String contentType) {
    if (contentType != null && contentType.isBlank()) {
      throw new IllegalArgumentException("Multipart content type must not be blank");
    }
  }
}
