package ej.aaamockmvc.test.web.request.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Record representing the context for an HTTP test request.
 *
 * <p>This record holds the {@link TestRequestDto} representing the details of the HTTP request and
 * the {@link TestRequestBean} which provides necessary components like {@link MockMvc} and {@link
 * ObjectMapper} for executing the request.
 *
 * @param request the {@link TestRequestDto} representing the HTTP request data (must not be {@code
 *     null})
 * @param bean the {@link TestRequestBean} containing {@link MockMvc} and {@link ObjectMapper}
 *     required for executing the request (must not be {@code null})
 * @since 1.0.0
 */
public record TestRequestContext(TestRequestDto request, TestRequestBean bean) {}
