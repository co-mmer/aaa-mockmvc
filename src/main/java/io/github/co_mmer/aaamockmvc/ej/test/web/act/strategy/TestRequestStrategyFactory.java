package io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Factory class responsible for resolving and returning the appropriate {@link TestRequestStrategy}
 * based on the provided {@link TestRequestType}.
 *
 * <p>This factory uses a switch expression to map each request type (such as GET, POST, PUT, etc.)
 * to its corresponding strategy implementation. The factory ensures that the correct strategy is
 * returned depending on the type of HTTP request.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestStrategyFactory {

  /**
   * Resolves the appropriate {@link TestRequestStrategy} based on the given {@link
   * TestRequestType}.
   *
   * <p>This method uses a switch expression to select and instantiate the correct strategy class
   * for the provided request type. Each request type has its own corresponding strategy class,
   * which defines how the request is handled.
   *
   * @param type the type of the HTTP request (must not be {@code null})
   * @return the corresponding {@code TestRequestStrategy} for the given {@code TestRequestType}
   * @throws NullPointerException if the {@code type} is {@code null}
   * @since 1.0.0
   */
  public static TestRequestStrategy resolve(@NonNull TestRequestType type) {
    return switch (type) {
      case GET -> new TestRequestGetStrategy();
      case POST -> new TestRequestPostStrategy();
      case PUT -> new TestRequestPutStrategy();
      case PATCH -> new TestRequestPatchStrategy();
      case DELETE -> new TestRequestDeleteStrategy();
      case HEAD -> new TestRequestHeadStrategy();
      case OPTIONS -> new TestRequestOptionsStrategy();
    };
  }
}
