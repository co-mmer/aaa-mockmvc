package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.CollectionUtils;

@Deprecated
@Since("1.0.0")
@Getter
@Setter
public final class TestRequestBodyDto {

  private String content;
  private MediaType contentType;
  private List<MockMultipartFile> files;

  @Since("1.0.0")
  public TestRequestBodyDto() {
    this.files = new ArrayList<>();
  }

  @Since("2.0.0")
  public boolean isNotNullContent() {
    return this.getContent() != null;
  }

  @Since("2.0.0")
  public boolean isNotEmptyFiles() {
    return !CollectionUtils.isEmpty(this.getFiles());
  }

  @Since("2.0.0")
  public String asMessage() {
    var parts = new ArrayList<String>();

    if (isNotEmptyFiles()) {
      parts.add("multipart " + files.size() + " file(s)");
      parts.add("files=" + buildMessageFileNames());
    }

    parts.add(getMessageBytes() + " bytes");
    parts.add("content-type=" + buildMessageContentType());

    var preview = buildMessagePreviewPart();
    if (!preview.isEmpty()) {
      parts.add(preview);
    }

    return "Body: " + String.join(" | ", parts);
  }

  private String buildMessageFileNames() {
    return this.files.stream()
        .map(f -> Optional.of(f.getOriginalFilename()).orElse("<no-name>"))
        .collect(Collectors.joining(", "));
  }

  private String buildMessagePreviewPart() {
    return (isNotNullContent() && !this.content.isBlank())
        ? "preview: " + this.content
        : StringUtils.EMPTY;
  }

  private int getMessageBytes() {
    return isNotNullContent() ? this.content.length() : 0;
  }

  private String buildMessageContentType() {
    return this.contentType != null ? this.contentType.toString() : "<none>";
  }
}
