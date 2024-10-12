package ej.test.aaamockmvc.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

/**
 * A configuration record that holds the {@code MockMvc} instance and {@code ObjectMapper} for
 * testing HTTP requests in a Spring application context.
 *
 * <p>This record encapsulates the essential components required for performing and validating HTTP
 * requests and responses during unit tests.
 *
 * @param mvc the {@code MockMvc} instance used for performing HTTP requests (must not be {@code
 *     null})
 * @param objectMapper the {@code ObjectMapper} used for serializing and deserializing JSON content
 *     (must not be {@code null})
 * @since 1.0.0
 */
public record TestRequestConfig(MockMvc mvc, ObjectMapper objectMapper) {}
