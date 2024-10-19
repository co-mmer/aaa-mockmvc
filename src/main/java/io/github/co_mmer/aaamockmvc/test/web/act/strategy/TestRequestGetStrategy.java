package io.github.co_mmer.aaamockmvc.test.web.act.strategy;

import io.github.co_mmer.aaamockmvc.test.web.act.strategy.builder.TestRequestBuilder;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;

/**
 * Strategy for configuring a GET request.
 *
 * <p>This class implements the {@link TestRequestStrategy} for handling GET requests. It extends
 * {@link TestRequestBaseStrategy} and provides specific logic for initializing the request builder.
 *
 * @since 1.0.0
 */
public final class TestRequestGetStrategy extends TestRequestBaseStrategy {

  /**
   * Initializes the request builder for a GET request.
   *
   * <p>This method sets up a GET request using {@link TestRequestBuilder#get} with the URI obtained
   * from the {@code requestDto}.
   *
   * @param requestDto the data transfer object containing the request details, including the URL
   * @throws NullPointerException if the {@code requestDto} or its URL is {@code null}
   * @since 1.0.0
   */
  @Override
  protected void initRequestBuilder(TestRequestDto requestDto) {
    this.requestBuilder = TestRequestBuilder.get(requestDto.getUrl().getUri());
  }
}
