package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestBodyDto;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

@Since("1.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestComponentBody {

  @Since("1.0.0")
  public static void apply(
      @NonNull MockHttpServletRequestBuilder builder, @NonNull TestRequestBodyDto requestBodyDto) {

    applyContent(builder, requestBodyDto);
    applyFile(builder, requestBodyDto);
  }

  private static void applyContent(
      MockHttpServletRequestBuilder builder, TestRequestBodyDto requestBodyDto) {

    if (requestBodyDto.isNotNullContent()) {
      builder.content(requestBodyDto.getContent());
      builder.contentType(requestBodyDto.getContentType());
    }
  }

  private static void applyFile(
      MockHttpServletRequestBuilder builder, TestRequestBodyDto requestBodyDto) {

    if (requestBodyDto.isNotEmptyFiles()) {
      var multipartBuilder = (MockMultipartHttpServletRequestBuilder) builder;
      requestBodyDto.getFiles().forEach(multipartBuilder::file);
    }
  }
}
