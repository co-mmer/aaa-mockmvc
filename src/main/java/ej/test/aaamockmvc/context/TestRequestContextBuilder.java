package ej.test.aaamockmvc.context;

import ej.test.aaamockmvc.request.model.TestRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Builder class for constructing instances of {@link TestRequestContext}.
 *
 * <p>This class provides a fluent API for setting up the configuration and request details
 * necessary to create a {@code TestRequestContext}. It allows chaining method calls for ease of
 * use.
 *
 * <p>This class is marked as {@code final} and cannot be subclassed. The constructor is private,
 * enforcing the use of the builder pattern for creating instances.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestContextBuilder {

  private TestRequestConfig config;
  private TestRequestDto request;

  /**
   * Sets the {@link TestRequestConfig} for the context being built.
   *
   * @param config the {@link TestRequestConfig} representing the configuration for the test request
   * @return the current {@code TestRequestContextBuilder} instance for further configuration
   * @since 1.0.0
   */
  public TestRequestContextBuilder withTestRequestConfig(TestRequestConfig config) {
    this.config = config;
    return this;
  }

  /**
   * Sets the {@link TestRequestDto} for the context being built.
   *
   * @param request the {@link TestRequestDto} representing the HTTP request
   * @return the current {@code TestRequestContextBuilder} instance for further configuration
   * @since 1.0.0
   */
  public TestRequestContextBuilder withTestRequest(TestRequestDto request) {
    this.request = request;
    return this;
  }

  /**
   * Builds and returns a new {@link TestRequestContext} using the configured {@link TestRequestDto}
   * and {@link TestRequestContext}.
   *
   * @return a new {@code TestRequestContext} instance
   * @since 1.0.0
   */
  public TestRequestContext build() {
    return new TestRequestContext(this.request, this.config);
  }

  /**
   * Returns a new instance of {@code TestRequestContextBuilder}.
   *
   * <p>This method is the starting point for building a {@link TestRequestContext}.
   *
   * @return a new {@code TestRequestContextBuilder} instance
   * @since 1.0.0
   */
  public static TestRequestContextBuilder getInstance() {
    return new TestRequestContextBuilder();
  }
}
