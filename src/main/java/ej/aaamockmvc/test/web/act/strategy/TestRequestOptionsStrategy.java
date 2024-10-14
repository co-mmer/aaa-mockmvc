package ej.aaamockmvc.test.web.act.strategy;

import ej.aaamockmvc.test.web.act.strategy.builder.TestRequestBuilder;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;

/**
 * Strategy for configuring an OPTIONS request.
 *
 * <p>This class implements the {@link TestRequestStrategy} for handling OPTIONS requests. It
 * extends {@link TestRequestBaseStrategy} and provides specific logic for initializing the request
 * builder.
 *
 * @since 1.0.0
 */
public final class TestRequestOptionsStrategy extends TestRequestBaseStrategy {

  /**
   * Initializes the request builder for an OPTIONS request.
   *
   * <p>This method sets up an OPTIONS request using {@link TestRequestBuilder#options} with the URI
   * obtained from the {@code requestDto}.
   *
   * @param requestDto the data transfer object containing the request details, including the URL
   * @throws NullPointerException if the {@code requestDto} is {@code null}
   * @since 1.0.0
   */
  @Override
  protected void initRequestBuilder(TestRequestDto requestDto) {
    this.requestBuilder = TestRequestBuilder.options(requestDto.getUrl().getUri());
  }
}
