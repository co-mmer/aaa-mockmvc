package ej.test.aaamockmvc.context;

import ej.test.aaamockmvc.request.model.TestRequestDto;

/**
 * Represents the context of a test request, encapsulating the request data and configuration.
 *
 * <p>This record is used to hold the information necessary for executing a test request, including
 * the request details encapsulated in a {@code TestRequestDto} and the associated configuration
 * from {@code TestRequestConfig}.
 *
 * @param request the details of the test request (must not be {@code null})
 * @param config the configuration for the test request (must not be {@code null})
 * @since 1.0.0
 */
public record TestRequestContext(TestRequestDto request, TestRequestConfig config) {}
