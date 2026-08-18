package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

@Since("2.1.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcBodyMapper {

  @Since("2.1.0")
  public static void applyText(MockHttpServletRequestBuilder builder, TextBody body) {
    builder.content(body.value());
  }

  @Since("2.1.0")
  public static void applyMultipart(
      MockMultipartHttpServletRequestBuilder builder, MultipartBody body) {
    for (var part : body.parts()) {
      builder.file(
          new MockMultipartFile(part.name(), part.filename(), part.contentType(), part.content()));
    }
  }
}
