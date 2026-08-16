package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.CollectionUtils;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcQueryMapper {

  @Since("2.0.2")
  public static void apply(
      MockHttpServletRequestBuilder builder, Map<String, List<String>> queryParameters) {

    if (!CollectionUtils.isEmpty(queryParameters)) {
      queryParameters.forEach(
          (name, values) -> {
            if (values == null || values.isEmpty()) {
              builder.queryParam(name);
              return; // todo kann das wirklich auftreten, sollte RequestHeaders verhindert werden
            }

            builder.queryParam(name, values.toArray(new String[0]));
          });
    }
  }
}
