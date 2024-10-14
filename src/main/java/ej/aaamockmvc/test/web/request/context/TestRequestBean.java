package ej.aaamockmvc.test.web.request.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

/**
 * A record that encapsulates the essential components needed for performing test requests in a
 * Spring MVC context.
 *
 * <p>This class holds instances of {@link MockMvc} and {@link ObjectMapper}, which are crucial for
 * executing HTTP requests and serializing/deserializing JSON content, respectively.
 *
 * @param mvc the {@link MockMvc} instance used for performing HTTP requests in tests (must not be
 *     {@code null})
 * @param objectMapper the {@link ObjectMapper} instance used for JSON serialization and
 *     deserialization (must not be {@code null})
 * @since 1.0.0
 */
public record TestRequestBean(MockMvc mvc, ObjectMapper objectMapper) {}
