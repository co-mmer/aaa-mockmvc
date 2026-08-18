package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcHeaderMapper {

  @Since("2.0.2")
  public static void apply(
      MockHttpServletRequestBuilder builder, Map<String, List<String>> headers) {

    headers.forEach((name, values) -> values.forEach(value -> builder.header(name, value)));
  }
}
