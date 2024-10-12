package ej.test.aaamockmvc.request.base;

import ej.test.aaamockmvc.context.TestRequestConfig;
import ej.test.aaamockmvc.context.TestRequestContext;
import ej.test.aaamockmvc.context.TestRequestContextBuilder;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import lombok.NonNull;

/**
 * Base class for handling HTTP request methods in a test context.
 *
 * <p>This class is designed to be extended by specific request method classes (e.g., GET, POST) and
 * provides common functionalities for creating request contexts.
 *
 * @since 1.0.0
 */
public abstract class TestRequestBase {

  private final TestRequestConfig config;

  /**
   * Constructs a {@code TestRequestBase} instance with the specified configuration.
   *
   * @param config the configuration for the test request (must not be {@code null})
   * @throws NullPointerException if {@code config} is {@code null}
   * @since 1.0.0
   */
  protected TestRequestBase(@NonNull TestRequestConfig config) {
    this.config = config;
  }

  /**
   * Creates a {@code TestRequestContext} for the specified request type.
   *
   * @param type the type of the test request (must not be {@code null})
   * @return a {@code TestRequestContext} for the specified request type
   * @throws NullPointerException if {@code type} is {@code null}
   * @since 1.0.0
   */
  protected TestRequestContext createContext(@NonNull TestRequestType type) {
    return TestRequestContextBuilder.getInstance()
        .withTestRequestConfig(this.config)
        .withTestRequest(new TestRequestDto(type))
        .build();
  }
}
