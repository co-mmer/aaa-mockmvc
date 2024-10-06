package ej.test.aaamockmvc.request.act.strategy;

import static ej.test.aaamockmvc.request.model.TestRequestDtoUtils.isNotEmptyFiles;

import ej.test.aaamockmvc.request.act.strategy.builder.TestRequestBuilder;
import ej.test.aaamockmvc.request.act.strategy.component.TestComponentBody;
import ej.test.aaamockmvc.request.model.TestRequestDto;

/**
 * Strategy for configuring a PUT request.
 *
 * <p>This class implements the {@link TestRequestStrategy} for handling PUT requests. It extends
 * {@link TestRequestBaseStrategy} and provides the specific logic for initializing the request
 * builder and applying the request body.
 *
 * @since 1.0.0
 */
public final class TestRequestPutStrategy extends TestRequestBaseStrategy {

  /**
   * Initializes the request builder for a PUT request.
   *
   * <p>This method checks whether the request body contains files. If files are present, a
   * multipart PUT request is created using {@link TestRequestBuilder#putMultipart}. Otherwise, a
   * regular PUT request is created using {@link TestRequestBuilder#put}.
   *
   * @param requestDto the data transfer object containing the request details, including URL and
   *     body
   * @throws NullPointerException if the {@code requestDto} is {@code null}
   * @since 1.0.0
   */
  @Override
  protected void initRequestBuilder(TestRequestDto requestDto) {
    var uri = requestDto.getUrl().getUri();
    this.requestBuilder =
        isNotEmptyFiles(requestDto.getBody())
            ? TestRequestBuilder.putMultipart(uri)
            : TestRequestBuilder.put(uri);
  }

  /**
   * Applies the body content to the PUT request.
   *
   * <p>This method delegates the task of applying the request body to {@link
   * TestComponentBody#apply}, which handles the addition of content or files to the request,
   * depending on the request body.
   *
   * @param requestDto the data transfer object containing the request body
   * @throws NullPointerException if the {@code requestDto} or its body is {@code null}
   * @since 1.0.0
   */
  @Override
  protected void applyBody(TestRequestDto requestDto) {
    TestComponentBody.apply(this.requestBuilder, requestDto.getBody());
  }
}
