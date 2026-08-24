package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.1.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcHttpMapper {

  @Since("2.1.0")
  public static org.springframework.http.HttpMethod mapTo(HttpMethod method) {
    return switch (method) {
      case GET -> org.springframework.http.HttpMethod.GET;
      case HEAD -> org.springframework.http.HttpMethod.HEAD;
      case POST -> org.springframework.http.HttpMethod.POST;
      case PUT -> org.springframework.http.HttpMethod.PUT;
      case PATCH -> org.springframework.http.HttpMethod.PATCH;
      case DELETE -> org.springframework.http.HttpMethod.DELETE;
      case OPTIONS -> org.springframework.http.HttpMethod.OPTIONS;
      case TRACE -> org.springframework.http.HttpMethod.TRACE;
    };
  }
}
