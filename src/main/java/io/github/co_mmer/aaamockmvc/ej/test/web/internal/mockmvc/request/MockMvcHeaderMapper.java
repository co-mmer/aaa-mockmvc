package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcHeaderMapper {

  public static void apply(
      MockHttpServletRequestBuilder builder, Map<String, List<String>> headers) {

    if (headers == null || headers.isEmpty()) {
      return;
    }

    headers.forEach(
        (name, values) -> {
          if (values == null || values.isEmpty()) {
            return;
          }

          values.forEach(value -> builder.header(name, value));
        });
  }
}
