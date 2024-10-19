package io.github.co_mmer.aaamockmvc.test.web.act.strategy;

import io.github.co_mmer.aaamockmvc.test.web.act.strategy.builder.TestRequestBuilder;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;

/**
 * Strategy for configuring a DELETE request.
 *
 * <p>This class implements the {@link TestRequestStrategy} for handling DELETE requests. It extends
 * {@link TestRequestBaseStrategy} and provides specific logic for initializing the request builder.
 *
 * @since 1.0.0
 */
public final class TestRequestDeleteStrategy extends TestRequestBaseStrategy {

  /**
   * Initializes the request builder for a DELETE request.
   *
   * <p>This method sets up a DELETE request using {@link TestRequestBuilder#delete} with the URI
   * obtained from the {@code requestDto}.
   *
   * @param requestDto the data transfer object containing the request details, including the URL
   * @throws NullPointerException if the {@code requestDto} is {@code null}
   * @since 1.0.0
   */
  @Override
  protected void initRequestBuilder(TestRequestDto requestDto) {
    this.requestBuilder = TestRequestBuilder.delete(requestDto.getUrl().getUri());
  }
}
