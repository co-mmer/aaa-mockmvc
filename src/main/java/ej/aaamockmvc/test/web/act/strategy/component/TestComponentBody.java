package ej.aaamockmvc.test.web.act.strategy.component;

import static ej.aaamockmvc.test.web.request.model.TestRequestDtoUtils.isNotEmptyFiles;
import static ej.aaamockmvc.test.web.request.model.TestRequestDtoUtils.isNotNullContent;

import ej.aaamockmvc.test.web.request.model.TestRequestBodyDto;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

/**
 * Utility class for applying request body configurations to HTTP request builders.
 *
 * <p>This class contains static methods for setting the content and files of a request body for
 * different types of HTTP requests. It applies the specified content and file attachments from the
 * {@link TestRequestBodyDto} to a {@link MockHttpServletRequestBuilder} or {@link
 * MockMultipartHttpServletRequestBuilder}.
 *
 * <p>It's designed to handle both textual content (like JSON or XML) and file uploads in a
 * structured way, ensuring that the appropriate request builder methods are used based on the
 * presence of files or content.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestComponentBody {

  /**
   * Applies the content and files from the {@link TestRequestBodyDto} to the given {@link
   * MockHttpServletRequestBuilder}.
   *
   * <p>This method checks whether the request body contains textual content and/or file
   * attachments, and applies them accordingly to the request builder.
   *
   * @param builder the request builder to which the content and files are applied (must not be
   *     {@code null})
   * @param requestBodyDto the DTO containing the content and file data (must not be {@code null})
   * @throws NullPointerException if either the {@code builder} or {@code requestBodyDto} is {@code
   *     null}
   * @since 1.0.0
   */
  public static void apply(
      @NonNull MockHttpServletRequestBuilder builder, @NonNull TestRequestBodyDto requestBodyDto) {

    applyContent(builder, requestBodyDto);
    applyFile(builder, requestBodyDto);
  }

  private static void applyContent(
      MockHttpServletRequestBuilder builder, TestRequestBodyDto requestBodyDto) {

    if (isNotNullContent(requestBodyDto)) {
      builder.content(requestBodyDto.getContent());
      builder.contentType(requestBodyDto.getContentType());
    }
  }

  private static void applyFile(
      MockHttpServletRequestBuilder builder, TestRequestBodyDto requestBodyDto) {

    if (isNotEmptyFiles(requestBodyDto)) {
      var multipartBuilder = (MockMultipartHttpServletRequestBuilder) builder;
      requestBodyDto.getFiles().forEach(multipartBuilder::file);
    }
  }
}
