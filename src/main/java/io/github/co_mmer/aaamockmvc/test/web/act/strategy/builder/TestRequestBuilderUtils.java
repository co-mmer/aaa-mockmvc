package io.github.co_mmer.aaamockmvc.test.web.act.strategy.builder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

/**
 * This class provides utility methods for configuring HTTP request builders.
 *
 * <p>It contains static methods that assist in setting properties and behavior for HTTP requests,
 * facilitating a more fluent and readable request-building process.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestBuilderUtils {

  /**
   * Sets the HTTP method for the given request.
   *
   * @param method the HTTP method to set (must not be {@code null})
   * @return a {@link RequestPostProcessor} that modifies the request with the specified method
   * @throws NullPointerException if the {@code method} is {@code null}
   * @since 1.0.0
   */
  public static RequestPostProcessor setMethod(@NonNull HttpMethod method) {
    return request -> {
      request.setMethod(method.toString());
      return request;
    };
  }
}
