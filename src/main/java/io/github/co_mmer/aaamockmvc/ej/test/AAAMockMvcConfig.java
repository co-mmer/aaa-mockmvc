package io.github.co_mmer.aaamockmvc.ej.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.AAAMockMvcBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring configuration that provides an {@link AAAMockMvc} bean for AAA-style tests.
 *
 * <p><b>What it does:</b> Registers a single {@code AAAMockMvc} instance when none is already
 * present in the arrange (see {@link ConditionalOnMissingBean}). It wires the current {@link
 * WebApplicationContext} and, if available via {@link ObjectProvider}, an existing {@link MockMvc}
 * and {@link ObjectMapper}. Missing optional dependencies are handled by the builder.
 *
 * <p><b>Typical usage:</b>
 *
 * <pre>{@code
 * @SpringBootTest
 * @Import(AAAMockMvcConfig.class) // or extend AAAMockMvcTestSupport which already imports it
 * class UserApiTest { ... }
 * }</pre>
 *
 * <p><b>Customization:</b> Define your own {@code @Bean AAAMockMvc} to override this configuration.
 *
 * @since 1.5.0
 */
@Configuration
@ConditionalOnMissingBean(AAAMockMvc.class)
public class AAAMockMvcConfig {

  /**
   * Builds the {@link AAAMockMvc} DSL entry point backed by the given Spring test infrastructure.
   *
   * <p>The builder is supplied with:
   *
   * <ul>
   *   <li>the active {@link WebApplicationContext} (required),
   *   <li>an optional {@link MockMvc} (taken from the arrange if available),
   *   <li>an optional {@link ObjectMapper} (taken from the arrange if available).
   * </ul>
   *
   * <p>If optional components are absent, the builder applies sensible defaults.
   *
   * @param webApplicationContext the current web application arrange (required)
   * @param mockMvcProvider provider for an existing {@link MockMvc} (optional)
   * @param objectMapperProvider provider for an existing {@link ObjectMapper} (optional)
   * @return a configured {@link AAAMockMvc} bean ready for Arrange–Act–Assert tests
   * @since 1.5.0
   */
  @Bean
  public AAAMockMvc aaaMockMvc(
      WebApplicationContext webApplicationContext,
      ObjectProvider<MockMvc> mockMvcProvider,
      ObjectProvider<ObjectMapper> objectMapperProvider) {

    return AAAMockMvcBuilder.builder()
        .withWebApplicationContext(webApplicationContext)
        .withMockMvc(mockMvcProvider.getIfAvailable())
        .withObjectMapper(objectMapperProvider.getIfAvailable())
        .build();
  }
}
