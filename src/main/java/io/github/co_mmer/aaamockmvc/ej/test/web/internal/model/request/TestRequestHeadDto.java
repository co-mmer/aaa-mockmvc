package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

@Deprecated
@Since("1.0.0")
@Setter
@Getter
public final class TestRequestHeadDto {

  private List<MediaType> accepts;
  private MediaType contentType;
  private Map<String, List<Object>> keyValue;

  @Since("1.0.0")
  public TestRequestHeadDto() {
    this.accepts = new ArrayList<>();
    this.keyValue = new HashMap<>();
  }

  @Since("2.0.0")
  public boolean isNotEmptyAccepts() {
    return !CollectionUtils.isEmpty(this.getAccepts());
  }

  @Since("2.0.0")
  public boolean isNotNullContentType() {
    return this.getContentType() != null;
  }

  @Since("2.0.0")
  public boolean isNotEmptyKeyValue() {
    return !CollectionUtils.isEmpty(this.getKeyValue());
  }
}
