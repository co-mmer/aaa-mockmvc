package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;

@Deprecated
@Since("1.0.0")
@Setter
@Getter
public final class TestRequestUrlDto {

  private HttpMethod method;
  private URI uri;
  private Map<String, String> query;

  @Since("1.0.0")
  public TestRequestUrlDto() {
    this.query = new HashMap<>();
  }

  @Since("2.0.0")
  public boolean isNotEmptyQuery() {
    return !CollectionUtils.isEmpty(this.getQuery());
  }
}
