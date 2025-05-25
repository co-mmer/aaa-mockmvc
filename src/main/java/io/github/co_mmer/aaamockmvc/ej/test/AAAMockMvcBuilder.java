package io.github.co_mmer.aaamockmvc.ej.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Builder for creating instances of {@link AAAMockMvc} with flexible configuration.
 *
 * <p>This builder supports multiple construction paths depending on the availability of {@link
 * MockMvc}, {@link WebApplicationContext}, and {@link ObjectMapper} instances.
 *
 * <p>Throws {@link IllegalStateException} if neither {@link MockMvc} nor {@link
 * WebApplicationContext} is provided.
 *
 * @since 1.5.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class AAAMockMvcBuilder {

  private WebApplicationContext wac;
  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  /**
   * Creates a new builder instance.
   *
   * @return a new {@code AAAMockMvcBuilder}
   * @since 1.5.0
   */
  public static AAAMockMvcBuilder builder() {
    return new AAAMockMvcBuilder();
  }

  /**
   * Sets the {@link WebApplicationContext} to use when building {@link AAAMockMvc}.
   *
   * @param wac the web application context; may be {@code null}
   * @return this builder instance for chaining
   * @since 1.5.0
   */
  public AAAMockMvcBuilder withWebApplicationContext(WebApplicationContext wac) {
    this.wac = wac;
    return this;
  }

  /**
   * Sets the {@link MockMvc} instance to use when building {@link AAAMockMvc}.
   *
   * @param mockMvc the MockMvc instance; may be {@code null}
   * @return this builder instance for chaining
   * @since 1.5.0
   */
  public AAAMockMvcBuilder withMockMvc(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
    return this;
  }

  /**
   * Sets the {@link ObjectMapper} instance to use when building {@link AAAMockMvc}.
   *
   * @param objectMapper the ObjectMapper instance; may be {@code null}
   * @return this builder instance for chaining
   * @since 1.5.0
   */
  public AAAMockMvcBuilder withObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    return this;
  }

  /**
   * Builds and returns a fully configured {@link AAAMockMvc} instance.
   *
   * @return the constructed {@code AAAMockMvc} instance
   * @throws IllegalStateException if neither MockMvc nor WebApplicationContext is provided
   * @since 1.5.0
   */
  public AAAMockMvc build() {
    if (this.mockMvc != null && this.objectMapper != null) {
      return new AAAMockMvc(this.mockMvc, this.objectMapper);
    }
    if (this.mockMvc != null) {
      return new AAAMockMvc(mockMvc);
    }
    if (this.objectMapper != null && this.wac != null) {
      return new AAAMockMvc(wac, objectMapper);
    }
    if (this.wac != null) {
      return new AAAMockMvc(wac);
    }
    throw new IllegalStateException(
        "Neither WebApplicationContext nor MockMvc is available to create AAAMockMvc");
  }
}
