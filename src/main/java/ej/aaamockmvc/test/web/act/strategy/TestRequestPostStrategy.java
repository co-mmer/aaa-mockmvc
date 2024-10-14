package ej.aaamockmvc.test.web.act.strategy;

import static ej.aaamockmvc.test.web.request.model.TestRequestDtoUtils.isNotEmptyFiles;

import ej.aaamockmvc.test.web.act.strategy.builder.TestRequestBuilder;
import ej.aaamockmvc.test.web.act.strategy.component.TestComponentBody;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;

/**
 * Strategy for configuring a POST request.
 *
 * <p>This class implements the {@link TestRequestStrategy} for handling POST requests. It extends
 * {@link TestRequestBaseStrategy} and provides the specific logic for initializing the request
 * builder and applying the request body.
 *
 * @since 1.0.0
 */
public final class TestRequestPostStrategy extends TestRequestBaseStrategy {

  /**
   * Initializes the request builder for a POST request.
   *
   * <p>This method checks whether the request body contains files. If files are present, a
   * multipart POST request is created using {@link TestRequestBuilder#postMultipart}. Otherwise, a
   * regular POST request is created using {@link TestRequestBuilder#post}.
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
            ? TestRequestBuilder.postMultipart(uri)
            : TestRequestBuilder.post(uri);
  }

  /**
   * Applies the body content to the POST request.
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
