package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.mapper;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestActResult;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TestActResultMapper {

  @Since("2.0.0")
  public static TestActResult mapTo(ResultActions actions) {
    var response = actions.andReturn().getResponse();
    var status = response.getStatus();
    var headers = toHeaders(response);
    var content = response.getContentAsByteArray();
    var charset = resolveCharset(response);
    return new TestActResult(status, headers, content, charset);
  }

  private static HttpHeaders toHeaders(MockHttpServletResponse response) {
    var headers = new HttpHeaders();
    for (var name : response.getHeaderNames()) {
      headers.put(name, new ArrayList<>(response.getHeaders(name)));
    }
    return headers;
  }

  @SuppressWarnings("all")
  private static Charset resolveCharset(MockHttpServletResponse response) {
    try {
      var encoding = response.getCharacterEncoding();
      return (encoding != null) ? Charset.forName(encoding) : StandardCharsets.UTF_8;
    } catch (Exception ignored) {
      return StandardCharsets.UTF_8;
    }
  }
}
