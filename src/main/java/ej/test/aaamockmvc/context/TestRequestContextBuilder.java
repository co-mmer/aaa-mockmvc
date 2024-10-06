package ej.test.aaamockmvc.context;

import ej.test.aaamockmvc.request.model.TestRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Builder class for constructing instances of {@link TestRequestContext}.
 *
 * <p>This builder allows the configuration of a {@link MockMvc} instance and a {@link
 * TestRequestDto} to create a fully configured {@code TestRequestContext}. It follows the builder
 * pattern to make the object creation flexible and readable.
 *
 * <p>Use {@link #getInstance()} to obtain a new builder instance.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestContextBuilder {

  private MockMvc mvc;
  private TestRequestDto request;

  /**
   * Sets the {@link MockMvc} instance for the context being built.
   *
   * @param mvc the {@link MockMvc} instance to be associated with the request context
   * @return the current {@code TestRequestContextBuilder} instance for further configuration
   * @since 1.0.0
   */
  public TestRequestContextBuilder withMockMvc(MockMvc mvc) {
    this.mvc = mvc;
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
   * Builds and returns a new {@link TestRequestContext} using the configured {@link MockMvc} and
   * {@link TestRequestDto}.
   *
   * @return a new {@code TestRequestContext} instance
   * @since 1.0.0
   */
  public TestRequestContext build() {
    return new TestRequestContext(this.mvc, this.request);
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
