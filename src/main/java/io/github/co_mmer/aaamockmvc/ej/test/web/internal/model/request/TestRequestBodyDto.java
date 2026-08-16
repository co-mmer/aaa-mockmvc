package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.util.ArrayList;
import java.util.List;
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
}
