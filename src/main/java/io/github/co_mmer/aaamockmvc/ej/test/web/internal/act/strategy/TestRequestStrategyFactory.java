package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpMethod;

@Since("1.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestStrategyFactory {

  @Since("1.0.0")
  public static TestRequestStrategy resolve(@NonNull HttpMethod method) {
    return switch (method.name()) {
      case "GET" -> new TestRequestGetStrategy();
      case "POST" -> new TestRequestPostStrategy();
      case "PUT" -> new TestRequestPutStrategy();
      case "PATCH" -> new TestRequestPatchStrategy();
      case "DELETE" -> new TestRequestDeleteStrategy();
      case "HEAD" -> new TestRequestHeadStrategy();
      case "OPTIONS" -> new TestRequestOptionsStrategy();
      default -> throw new IllegalArgumentException("Unsupported method: " + method);
    };
  }
}
