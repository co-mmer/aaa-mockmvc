package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Since("2.1.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcQueryMapper {

  @Since("2.1.0")
  public static void apply(
      MockHttpServletRequestBuilder builder, Map<String, List<String>> queryParameters) {

    queryParameters.forEach(
        (name, values) -> builder.queryParam(name, values.toArray(new String[0])));
  }
}
