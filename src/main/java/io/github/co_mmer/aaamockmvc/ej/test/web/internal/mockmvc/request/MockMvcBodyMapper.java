package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.BinaryBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.FormBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TextBody;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcBodyMapper {

  public static void applyText(MockHttpServletRequestBuilder builder, TextBody body) {
    builder.content(body.value());
  }

  public static void applyBinary(MockHttpServletRequestBuilder builder, BinaryBody body) {
    builder.content(body.value());
  }

  public static void applyForm(MockHttpServletRequestBuilder builder, FormBody body) {
    body.values().forEach((name, values) -> builder.param(name, values.toArray(new String[0])));
  }

  public static void applyMultipart(
      MockMultipartHttpServletRequestBuilder builder, MultipartBody body) {
    for (var part : body.parts()) {
      builder.file(
          new MockMultipartFile(part.name(), part.filename(), part.contentType(), part.content()));
    }
  }
}
