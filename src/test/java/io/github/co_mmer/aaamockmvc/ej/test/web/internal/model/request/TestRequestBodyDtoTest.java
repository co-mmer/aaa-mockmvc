package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestBodyDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

class TestRequestBodyDtoTest {

  @ParameterizedTest
  @MethodSource("useCaseContent")
  void GIVEN_useCaseContent_WHEN_isNotNullContent_THEN_return_expected_boolean(
      TestRequestBodyDto bodyDto, boolean expectedBoolean) {

    // Act
    var result = bodyDto.isNotNullContent();

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseContent() {
    return Stream.of(
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_NULL, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_CONTENT_NULL, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_CONTENT, true));
  }

  @ParameterizedTest
  @MethodSource("useCaseFiles")
  void GIVEN_useCaseFiles_WHEN_isNotEmptyFiles_THEN_return_expected_boolean(
      TestRequestBodyDto bodyDto, boolean expectedBoolean) {

    // Act
    var result = bodyDto.isNotEmptyFiles();

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseFiles() {
    return Stream.of(
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_NULL, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_EMPTY, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_1, true));
  }

  @Nested
  class asMessage {

    @Test
    void GIVEN_no_content_no_contentType_no_files_WHEN_asMessage_THEN_defaults() {
      // Arrange
      var dto = new TestRequestBodyDto();

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Body: 0 bytes | content-type=<none>"));
    }

    @Test
    void GIVEN_text_content_and_no_contentType_WHEN_asMessage_THEN_bytes_ct_none_and_preview() {
      // Arrange
      var dto = new TestRequestBodyDto();
      dto.setContent("hello");

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Body: 5 bytes | content-type=<none> | preview: hello"));
    }

    @Test
    void GIVEN_blank_content_AND_contentType_WHEN_asMessage_THEN_bytes_and_ct_without_preview() {
      // Arrange
      var dto = new TestRequestBodyDto();
      dto.setContent("   ");
      dto.setContentType(MediaType.TEXT_PLAIN);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Body: 3 bytes | content-type=text/plain"));
    }

    @Test
    void GIVEN_content_AND_contentType_json_WHEN_asMessage_THEN_show_preview() {
      // Arrange
      var dto = new TestRequestBodyDto();
      dto.setContent("{\"a\":1}");
      dto.setContentType(MediaType.APPLICATION_JSON);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Body: 7 bytes | content-type=application/json | preview: {\"a\":1}"));
    }

    @Test
    void GIVEN_single_file_WHEN_asMessage_THEN_multipart_1_file_and_filename_listed() {
      // Arrange
      var dto = new TestRequestBodyDto();
      dto.getFiles().add(new MockMultipartFile("file", "a.txt", "text/plain", "x".getBytes()));

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(
          msg, is("Body: multipart 1 file(s) | files=a.txt | 0 bytes | content-type=<none>"));
    }

    @Test
    void GIVEN_multiple_files_WHEN_asMessage_THEN_multipart_count_and_filenames_comma_separated() {
      // Arrange
      var dto = new TestRequestBodyDto();
      dto.getFiles().add(new MockMultipartFile("f1", "a.txt", "text/plain", "x".getBytes()));
      dto.getFiles().add(new MockMultipartFile("f2", "b.csv", "text/csv", "y".getBytes()));

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(
          msg,
          is("Body: multipart 2 file(s) | files=a.txt, b.csv | 0 bytes | content-type=<none>"));
    }

    @Test
    void
        GIVEN_files_present_AND_content_also_set_WHEN_asMessage_THEN_both_files_and_content_included() {
      // Arrange
      var dto = new TestRequestBodyDto();
      dto.setContent("hello");
      dto.setContentType(MediaType.TEXT_PLAIN);
      dto.getFiles().add(new MockMultipartFile("f1", "a.txt", "text/plain", "x".getBytes()));

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(
          msg,
          is(
              "Body: multipart 1 file(s) | files=a.txt | 5 bytes | content-type=text/plain | preview: hello"));
    }

    @Test
    void GIVEN_content_null_but_contentType_set_WHEN_asMessage_THEN_zero_bytes_and_ct() {
      // Arrange
      var dto = new TestRequestBodyDto();
      dto.setContent(null);
      dto.setContentType(MediaType.APPLICATION_XML);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Body: 0 bytes | content-type=application/xml"));
    }
  }
}
