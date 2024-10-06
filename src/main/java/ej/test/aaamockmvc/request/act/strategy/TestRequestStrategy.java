package ej.test.aaamockmvc.request.act.strategy;

import ej.test.aaamockmvc.request.model.TestRequestDto;
import lombok.NonNull;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * Strategy interface for applying HTTP request configuration.
 *
 * <p>This interface defines a strategy for configuring a {@link MockHttpServletRequestBuilder}
 * based on the provided {@link TestRequestDto}. Each implementation of this interface corresponds
 * to a specific HTTP method (e.g., GET, POST, PUT) and applies the appropriate settings to the
 * request builder.
 *
 * @since 1.0.0
 */
public interface TestRequestStrategy {

  /**
   * Applies the request configuration to a {@link MockHttpServletRequestBuilder}.
   *
   * <p>This method takes a {@link TestRequestDto} containing request data such as URL, headers,
   * parameters, and body, and uses it to configure a {@code MockHttpServletRequestBuilder} for
   * executing the request. Each implementation of this strategy will handle a specific HTTP method,
   * applying the relevant configuration to the request.
   *
   * @param request the request data containing URL, headers, parameters, and body (must not be
   *     {@code null})
   * @return a configured {@code MockHttpServletRequestBuilder} for the specified request
   * @throws NullPointerException if the {@code request} is {@code null}
   * @since 1.0.0
   */
  MockHttpServletRequestBuilder apply(@NonNull TestRequestDto request);
}
