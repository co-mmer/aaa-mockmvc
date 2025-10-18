package io.github.co_mmer.aaamockmvc.ej.test.web.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.AAAMockMvc;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@Since("1.5.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class AAAMockMvcBuilder {

  private WebApplicationContext wac;
  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Since("1.5.0")
  public static AAAMockMvcBuilder builder() {
    return new AAAMockMvcBuilder();
  }

  @Since("1.5.0")
  public AAAMockMvcBuilder withWebApplicationContext(WebApplicationContext wac) {
    this.wac = wac;
    return this;
  }

  @Since("1.5.0")
  public AAAMockMvcBuilder withMockMvc(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
    return this;
  }

  @Since("1.5.0")
  public AAAMockMvcBuilder withObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    return this;
  }

  @Since("1.5.0")
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
