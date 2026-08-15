package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcQueryParameterMapper {

  public static void apply(
      MockHttpServletRequestBuilder builder, Map<String, List<String>> queryParameters) {

    if (queryParameters == null || queryParameters.isEmpty()) {
      return;
    }

    queryParameters.forEach(
        (name, values) -> {
          if (values == null || values.isEmpty()) {
            builder.queryParam(name);
            return;
          }

          builder.queryParam(name, values.toArray(new String[0]));
        });
  }
}
