package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
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

  @Since("2.0.0")
  public String asMessage() {
    var m = buildMessageMethode();
    var u = uri != null ? uri.toString() : "<no uri>";
    var q = isNotEmptyQuery() ? buildMessageQuery() : StringUtils.EMPTY;
    return "Request: " + m + " " + u + q;
  }

  private String buildMessageQuery() {
    return " | query: "
        + query.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining("&"));
  }

  private String buildMessageMethode() {
    return method != null ? method.name() : "<no method>";
  }
}
