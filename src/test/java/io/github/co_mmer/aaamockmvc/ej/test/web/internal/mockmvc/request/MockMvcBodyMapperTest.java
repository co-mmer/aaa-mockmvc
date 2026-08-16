package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.BinaryBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.FormBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.MultipartPart;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

class MockMvcBodyMapperTest {

  private static final TextBody ANY_TEXT_BODY = new TextBody("test body");
  private static final BinaryBody ANY_BINARY_BODY = new BinaryBody(new byte[]{1, 2, 3});

  private static final FormBody ANY_FORM_BODY =
      new FormBody(Map.of("parameter", List.of("first value", "second value")));

  private static final MultipartPart ANY_MULTIPART_PART =
      new MultipartPart("file", "test.txt", "text/plain", new byte[]{1, 2, 3});

  @Test
  void GIVEN_text_body_WHEN_applyText_THEN_call_content_with_body_value() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcBodyMapper.applyText(builder, ANY_TEXT_BODY);

    // Assert
    verify(builder).content(ANY_TEXT_BODY.value());
  }

  @Test
  void GIVEN_binary_body_WHEN_applyBinary_THEN_call_content_with_body_value() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);

    // Act
    MockMvcBodyMapper.applyBinary(builder, ANY_BINARY_BODY);

    // Assert
    verify(builder).content(ANY_BINARY_BODY.value());
  }

  @Test
  void GIVEN_form_body_WHEN_applyForm_THEN_call_param_with_body_values() {
    // Arrange
    var builder = mock(MockHttpServletRequestBuilder.class);
    var parameter = ANY_FORM_BODY.values().entrySet().iterator().next();

    // Act
    MockMvcBodyMapper.applyForm(builder, ANY_FORM_BODY);

    // Assert
    verify(builder).param(parameter.getKey(), parameter.getValue().toArray(new String[0]));
  }

  @Test
  void GIVEN_multipart_body_WHEN_applyMultipart_THEN_call_file_with_body_part() {
    // Arrange
    var builder = mock(MockMultipartHttpServletRequestBuilder.class);
    var body = new MultipartBody();
    body.add(ANY_MULTIPART_PART);
    var fileCaptor = ArgumentCaptor.forClass(MockMultipartFile.class);

    // Act
    MockMvcBodyMapper.applyMultipart(builder, body);

    // Assert
    verify(builder).file(fileCaptor.capture());
    assertThatMultipartFileMatchesPart(fileCaptor.getValue(), ANY_MULTIPART_PART);
  }

  @SneakyThrows
  private static void assertThatMultipartFileMatchesPart(
      MockMultipartFile file, MultipartPart part) {
    assertThat(file.getName(), is(part.name()));
    assertThat(file.getOriginalFilename(), is(part.filename()));
    assertThat(file.getContentType(), is(part.contentType()));
    assertThat(file.getBytes(), is(part.content()));
  }
}
