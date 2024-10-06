package ej.test.aaamockmvc.context;

import ej.test.aaamockmvc.request.model.TestRequestDto;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This record encapsulates the context required for executing a test request, including the {@code
 * MockMvc} instance and the {@code TestRequestDto} object that holds the request details.
 *
 * <p>The {@code TestRequestContext} provides access to the configured {@code MockMvc} for
 * simulating HTTP requests and the {@code TestRequestDto} for storing the details of the current
 * request.
 *
 * @param mvc the {@code MockMvc} instance used to perform the request (must not be {@code null})
 * @param request the {@code TestRequestDto} object containing the request details (must not be
 *     {@code null})
 * @since 1.0.0
 */
public record TestRequestContext(MockMvc mvc, TestRequestDto request) {}
