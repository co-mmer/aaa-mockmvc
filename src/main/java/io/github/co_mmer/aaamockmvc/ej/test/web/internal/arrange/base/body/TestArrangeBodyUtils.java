package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestBodyDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@Since("1.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeBodyUtils {

  @Since("1.0.0")
  public static void addFile(
      @NonNull TestRequestBodyDto destination, @NonNull MockMultipartFile file) {

    var items = new ArrayList<>(destination.getFiles());
    items.add(file);
    destination.setFiles(items);
  }

  @Since("1.0.0")
  public static void addFiles(
      @NonNull TestRequestBodyDto destination, @NonNull List<MockMultipartFile> files) {

    var items = new ArrayList<>(destination.getFiles());
    items.addAll(files);
    destination.setFiles(items);
  }

  @Since("1.0.0")
  public static void setContent(
      @NonNull TestRequestBodyDto destination, @NonNull String content, @NonNull MediaType type) {

    destination.setContent(content);
    destination.setContentType(type);
  }
}
