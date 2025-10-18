package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.builder;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@Since("1.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestBuilderUtils {

  @Since("1.0.0")
  public static RequestPostProcessor setMethod(@NonNull HttpMethod method) {
    return request -> {
      request.setMethod(method.toString());
      return request;
    };
  }
}
