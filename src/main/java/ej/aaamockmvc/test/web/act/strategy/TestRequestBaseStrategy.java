package ej.aaamockmvc.test.web.act.strategy;

import ej.aaamockmvc.test.web.act.strategy.component.TestComponentHead;
import ej.aaamockmvc.test.web.act.strategy.component.TestComponentUrl;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import lombok.NonNull;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * Abstract base class for handling HTTP request strategies.
 *
 * <p>This class implements the {@link TestRequestStrategy} interface and provides common
 * functionality for initializing and applying the request configuration (URL, headers, and body)
 * across different HTTP methods (GET, POST, PUT, DELETE, etc.). Subclasses must implement the
 * method for initializing the specific request builder for each HTTP method.
 *
 * <p>The request-building process involves setting the URL, headers, and optionally the body (for
 * POST, PUT, PATCH, etc.). The subclasses handle the specific logic for each type of HTTP request.
 *
 * @since 1.0.0
 */
public abstract class TestRequestBaseStrategy implements TestRequestStrategy {

  protected MockHttpServletRequestBuilder requestBuilder;

  /**
   * Applies the necessary configurations to the request based on the {@code requestDto}.
   *
   * <p>This method initializes the request builder, applies the URL and headers using {@link
   * TestComponentUrl} and {@link TestComponentHead}, and then invokes {@link
   * #applyBody(TestRequestDto)} to set the body content, if needed.
   *
   * @param requestDto the data transfer object containing details of the request (URL, headers,
   *     body) (must not be {@code null})
   * @return the fully configured {@link MockHttpServletRequestBuilder} for executing the request
   * @throws NullPointerException if {@code requestDto} or required fields are {@code null}
   * @since 1.0.0
   */
  @Override
  public final MockHttpServletRequestBuilder apply(@NonNull TestRequestDto requestDto) {
    initRequestBuilder(requestDto);
    TestComponentUrl.apply(this.requestBuilder, requestDto.getUrl());
    TestComponentHead.apply(this.requestBuilder, requestDto.getHead());

    applyBody(requestDto);
    return this.requestBuilder;
  }

  /**
   * Initializes the request builder for the specific HTTP method.
   *
   * <p>This abstract method must be implemented by subclasses to initialize the {@code
   * requestBuilder} for the corresponding HTTP method (e.g., GET, POST, DELETE).
   *
   * @param requestDto the data transfer object containing the request details
   * @since 1.0.0
   */
  protected abstract void initRequestBuilder(TestRequestDto requestDto);

  /**
   * Optionally applies the body content to the request.
   *
   * <p>This method can be overridden by subclasses that need to handle request bodies (e.g., POST,
   * PUT, PATCH). By default, it does nothing, as some methods like GET and DELETE do not require
   * bodies.
   *
   * @param requestDto the data transfer object containing the body content
   * @since 1.0.0
   */
  protected void applyBody(TestRequestDto requestDto) {
    // No implementation by default, meant to be overridden by subclasses as needed.
  }
}
