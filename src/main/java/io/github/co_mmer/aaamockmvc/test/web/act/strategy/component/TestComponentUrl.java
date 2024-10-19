package io.github.co_mmer.aaamockmvc.test.web.act.strategy.component;

import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDtoUtils;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestUrlDto;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * Utility class for applying URL parameters to request builders.
 *
 * <p>This class provides a static method to set URL query parameters from a {@link TestRequestDto}
 * onto a {@link MockHttpServletRequestBuilder}. It is designed to facilitate the inclusion of query
 * parameters in HTTP requests during testing.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestComponentUrl {

  /**
   * Applies the URL parameters from the given {@code requestDto} to the provided builder.
   *
   * <p>This method checks if the request DTO contains any URL parameters and applies them to the
   * request builder. Each parameter is added as a query parameter to the request.
   *
   * @param builder the request builder to which the URL parameters will be applied (must not be
   *     {@code null})
   * @param requestDto the data transfer object containing the URL parameters to be applied (must
   *     not be {@code null})
   * @throws NullPointerException if either {@code builder} or {@code requestDto} is {@code null}
   * @since 1.0.0
   */
  public static void apply(
      @NonNull MockHttpServletRequestBuilder builder, @NonNull TestRequestUrlDto requestDto) {

    if (TestRequestDtoUtils.isNotEmptyParam(requestDto)) {
      requestDto.getParam().forEach(builder::param);
    }
  }
}
