package io.github.co_mmer.aaamockmvc.ej.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Configuration class that conditionally provides a {@link AAAMockMvc} bean if none is already
 * defined.
 *
 * <p>This configuration is useful in integration or test scenarios where either a {@link MockMvc}
 * or a {@link WebApplicationContext} is available in the Spring context. It constructs an {@code
 * AAAMockMvc} instance using the best available combination of dependencies.
 *
 * <p>The bean will only be created if no other {@code AAAMockMvc} bean is present in the context.
 *
 * @since 1.5.0
 */
@Configuration
@ConditionalOnMissingBean(AAAMockMvc.class)
public class AAAMockMvcConfig {

  /**
   * Creates an {@link AAAMockMvc} bean using available Spring test infrastructure components.
   *
   * <p>The method constructs an {@code AAAMockMvc} instance based on available dependencies in the
   * following priority:
   *
   * <ol>
   *   <li>If both {@link MockMvc} and {@link ObjectMapper} are available, use them.
   *   <li>If only {@link MockMvc} is available, use it.
   *   <li>If {@link WebApplicationContext} is available, optionally with {@link ObjectMapper}, use
   *       it to internally build {@code MockMvc}.
   * </ol>
   *
   * <p>If neither {@code MockMvc} nor {@code WebApplicationContext} is available, an {@link
   * IllegalStateException} is thrown to indicate misconfiguration or an unsupported test context.
   *
   * @param webApplicationContext a {@link WebApplicationContext}; must not be {@code null}
   * @param mockMvcProvider a provider for {@link MockMvc}; may be absent
   * @param objectMapperProvider a provider for {@link ObjectMapper}; optional
   * @return a fully configured {@code AAAMockMvc} instance
   * @throws IllegalStateException if neither {@code MockMvc} nor {@code WebApplicationContext} is
   *     available
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
