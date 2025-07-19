package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestHeadDto;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Since("1.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestComponentHead {

  @Since("1.0.0")
  public static void apply(
      @NonNull MockHttpServletRequestBuilder builder, @NonNull TestRequestHeadDto requestHeadDto) {

    applyAccepts(builder, requestHeadDto);
    applyContentType(builder, requestHeadDto);
    applyKeyValue(builder, requestHeadDto);
  }

  private static void applyAccepts(
      MockHttpServletRequestBuilder builder, TestRequestHeadDto requestHeadDto) {

    if (requestHeadDto.isNotEmptyAccepts()) {
      builder.accept(requestHeadDto.getAccepts().toArray(MediaType[]::new));
    }
  }

  private static void applyContentType(
      MockHttpServletRequestBuilder builder, TestRequestHeadDto requestHeadDto) {

    if (requestHeadDto.isNotNullContentType()) {
      builder.contentType(requestHeadDto.getContentType());
    }
  }

  private static void applyKeyValue(
      MockHttpServletRequestBuilder builder, TestRequestHeadDto requestHeadDto) {

    if (requestHeadDto.isNotEmptyKeyValue()) {
      requestHeadDto.getKeyValue().forEach(builder::header);
    }
  }
}
