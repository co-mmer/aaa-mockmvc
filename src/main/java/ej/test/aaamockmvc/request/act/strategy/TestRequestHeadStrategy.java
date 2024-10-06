package ej.test.aaamockmvc.request.act.strategy;

import ej.test.aaamockmvc.request.act.strategy.builder.TestRequestBuilder;
import ej.test.aaamockmvc.request.model.TestRequestDto;

/**
 * Strategy for configuring a HEAD request.
 *
 * <p>This class implements the {@link TestRequestStrategy} for handling HEAD requests. It extends
 * {@link TestRequestBaseStrategy} and provides specific logic for initializing the request builder.
 *
 * @since 1.0.0
 */
public final class TestRequestHeadStrategy extends TestRequestBaseStrategy {

  /**
   * Initializes the request builder for a HEAD request.
   *
   * <p>This method sets up a HEAD request using {@link TestRequestBuilder#head} with the URI
   * obtained from the {@code requestDto}.
   *
   * @param requestDto the data transfer object containing the request details, including the URL
   * @throws NullPointerException if the {@code requestDto} is {@code null}
   * @since 1.0.0
   */
  @Override
  protected void initRequestBuilder(TestRequestDto requestDto) {
    this.requestBuilder = TestRequestBuilder.head(requestDto.getUrl().getUri());
  }
}
