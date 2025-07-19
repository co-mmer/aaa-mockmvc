package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import java.net.URI;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

class TestRequestDtoTest {

  @Test
  void WHEN_call_constructor_THEN_return_url_head_body_not_null() {
    // Act
    var result = new TestRequestDto();

    // Assert
    assertThat(result.getUrl(), is(notNullValue()));
    assertThat(result.getHead(), is(notNullValue()));
    assertThat(result.getBody(), is(notNullValue()));
  }

  @Nested
  class asMessage {

    @Test
    void GIVEN_defaults_WHEN_asMessage_THEN_three_lines_with_placeholders() {
      // Arrange
      var arrange = new TestArrangeResult();

      // Act
      var msg = arrange.asMessage();

      // Assert
      var expected =
          String.join(
              "\n",
              "Request: <no method> <no uri>",
              "Headers: accepts=<none> | content-type=<none> | key-value={}",
              "Body: 0 bytes | content-type=<none>");
      assertThat(msg, is(expected));
    }

    @Test
    @SneakyThrows
    void GIVEN_url_only_WHEN_asMessage_THEN_url_line_filled_others_default() {
      // Arrange
      var arrange = new TestArrangeResult();
      arrange.getUrl().setMethod(HttpMethod.GET);
      arrange.getUrl().setUri(new URI("http://localhost/api"));

      // Act
      var msg = arrange.asMessage();

      // Assert
      var expected =
          String.join(
              "\n",
              "Request: GET http://localhost/api",
              "Headers: accepts=<none> | content-type=<none> | key-value={}",
              "Body: 0 bytes | content-type=<none>");
      assertThat(msg, is(expected));
    }

    @Test
    void GIVEN_head_only_WHEN_asMessage_THEN_head_line_filled_others_default() {
      // Arrange
      var arrange = new TestArrangeResult();
      arrange.getHead().getAccepts().add(MediaType.APPLICATION_JSON);
      arrange.getHead().setContentType(MediaType.TEXT_PLAIN);
      arrange.getHead().getKeyValue().put("X-Foo", List.of("bar"));

      // Act
      var msg = arrange.asMessage();

      // Assert
      assertThat(msg, startsWith("Request: <no method> <no uri>\n"));
      assertThat(
          msg,
          containsString(
              "Headers: accepts=application/json | content-type=text/plain | key-value={"));
      assertThat(msg, containsString("X-Foo=[bar]"));
      assertThat(msg, endsWith("\nBody: 0 bytes | content-type=<none>"));
    }

    @Test
    void GIVEN_body_content_only_WHEN_asMessage_THEN_body_line_filled_others_default() {
      // Arrange
      var arrange = new TestArrangeResult();
      arrange.getBody().setContent("{\"a\":1}");
      arrange.getBody().setContentType(MediaType.APPLICATION_JSON);

      // Act
      var msg = arrange.asMessage();

      // Assert
      var expected =
          String.join(
              "\n",
              "Request: <no method> <no uri>",
              "Headers: accepts=<none> | content-type=<none> | key-value={}",
              "Body: 7 bytes | content-type=application/json | preview: {\"a\":1}");
      assertThat(msg, is(expected));
    }

    @Test
    void GIVEN_body_files_only_WHEN_asMessage_THEN_body_line_shows_multipart() {
      // Arrange
      var arrange = new TestArrangeResult();
      arrange
          .getBody()
          .getFiles()
          .add(new MockMultipartFile("f1", "a.txt", "text/plain", "x".getBytes()));

      // Act
      var msg = arrange.asMessage();

      // Assert
      var expected =
          String.join(
              "\n",
              "Request: <no method> <no uri>",
              "Headers: accepts=<none> | content-type=<none> | key-value={}",
              "Body: multipart 1 file(s) | files=a.txt | 0 bytes | content-type=<none>");
      assertThat(msg, is(expected));
    }

    @Test
    void GIVEN_all_sections_filled_WHEN_asMessage_THEN_every_line_combined() throws Exception {
      // Arrange
      var arrange = new TestArrangeResult();
      arrange.getUrl().setMethod(HttpMethod.POST);
      arrange.getUrl().setUri(new URI("https://example.com/path"));
      arrange.getUrl().getQuery().put("q", "42");

      arrange.getHead().getAccepts().add(MediaType.APPLICATION_JSON);
      arrange.getHead().getAccepts().add(MediaType.TEXT_PLAIN);
      arrange.getHead().setContentType(MediaType.APPLICATION_JSON);
      arrange.getHead().getKeyValue().put("Authorization", List.of("Bearer token"));

      arrange.getBody().setContent("hello");
      arrange.getBody().setContentType(MediaType.TEXT_PLAIN);
      arrange
          .getBody()
          .getFiles()
          .add(new MockMultipartFile("f1", "a.txt", "text/plain", "x".getBytes()));
      arrange
          .getBody()
          .getFiles()
          .add(new MockMultipartFile("f2", "b.csv", "text/csv", "y".getBytes()));

      // Act
      var msg = arrange.asMessage();

      // Assert
      assertThat(msg, startsWith("Request: POST https://example.com/path | query: q=42\n"));
      assertThat(
          msg,
          containsString(
              "Headers: accepts=application/json, text/plain | content-type=application/json | key-value={"));
      assertThat(msg, containsString("Authorization=[Bearer token]"));
      assertThat(
          msg,
          containsString(
              "\nBody: multipart 2 file(s) | files=a.txt, b.csv | 5 bytes | content-type=text/plain | preview: hello"));
    }
  }
}
