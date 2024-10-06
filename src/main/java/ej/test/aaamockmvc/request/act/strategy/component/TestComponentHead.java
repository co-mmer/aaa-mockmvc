package ej.test.aaamockmvc.request.act.strategy.component;

import static ej.test.aaamockmvc.request.model.TestRequestDtoUtils.isNotEmptyAccepts;
import static ej.test.aaamockmvc.request.model.TestRequestDtoUtils.isNotEmptyContentTypes;
import static ej.test.aaamockmvc.request.model.TestRequestDtoUtils.isNotEmptyKeyValue;

import ej.test.aaamockmvc.request.model.TestRequestHeadDto;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * Utility class for applying HTTP headers to request builders.
 *
 * <p>This class contains static methods for setting various HTTP headers such as Accept,
 * Content-Type, and custom key-value pairs from a {@link TestRequestHeadDto} onto a {@link
 * MockHttpServletRequestBuilder}.
 *
 * <p>It is designed to streamline the process of configuring request headers for different types of
 * HTTP requests, ensuring that all necessary headers are properly applied.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestComponentHead {

  /**
   * Applies the headers from the given {@code requestHeadDto} to the provided builder.
   *
   * <p>This method first applies the Accept headers, then the Content-Type headers, and finally any
   * custom key-value headers.
   *
   * @param builder the request builder to which the headers will be applied (must not be {@code
   *     null})
   * @param requestHeadDto the data transfer object containing the headers to be applied (must not
   *     be {@code null})
   * @throws NullPointerException if either {@code builder} or {@code requestHeadDto} is {@code
   *     null}
   * @since 1.0.0
   */
  public static void apply(
      @NonNull MockHttpServletRequestBuilder builder, @NonNull TestRequestHeadDto requestHeadDto) {

    applyAccepts(builder, requestHeadDto);
    applyContentType(builder, requestHeadDto);
    applyKeyValue(builder, requestHeadDto);
  }

  private static void applyAccepts(
      MockHttpServletRequestBuilder builder, TestRequestHeadDto requestHeadDto) {

    if (isNotEmptyAccepts(requestHeadDto)) {
      requestHeadDto.getAccepts().forEach(builder::accept);
    }
  }

  private static void applyContentType(
      MockHttpServletRequestBuilder builder, TestRequestHeadDto requestHeadDto) {

    if (isNotEmptyContentTypes(requestHeadDto)) {
      requestHeadDto.getContentTypes().forEach(builder::contentType);
    }
  }

  private static void applyKeyValue(
      MockHttpServletRequestBuilder builder, TestRequestHeadDto requestHeadDto) {

    if (isNotEmptyKeyValue(requestHeadDto)) {
      requestHeadDto.getKeyValue().forEach(builder::header);
    }
  }
}
