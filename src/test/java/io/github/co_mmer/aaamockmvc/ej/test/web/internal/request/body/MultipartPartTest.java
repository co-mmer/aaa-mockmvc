package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MultipartPartTest {

  private static final String ANY_NAME = "file";
  private static final String ANY_FILENAME = "document.txt";
  private static final String ANY_CONTENT_TYPE = "text/plain";
  private static final byte[] ANY_CONTENT = {1, 2, 3};

  @Nested
  class CreatePart {

    @Test
    void GIVEN_valid_values_WHEN_create_THEN_store_values() {
      // Act
      var part = new MultipartPart(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, ANY_CONTENT);

      // Assert
      assertThat(part.name(), is(ANY_NAME));
      assertThat(part.filename(), is(ANY_FILENAME));
      assertThat(part.contentType(), is(ANY_CONTENT_TYPE));
      assertThat(part.content(), is(ANY_CONTENT));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void GIVEN_optional_filename_WHEN_create_THEN_store_filename(String filename) {
      // Act
      var part = new MultipartPart(ANY_NAME, filename, ANY_CONTENT_TYPE, ANY_CONTENT);

      // Assert
      assertThat(part.filename(), is(filename));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void GIVEN_optional_content_type_WHEN_create_THEN_store_content_type(String contentType) {
      // Act
      var part = new MultipartPart(ANY_NAME, ANY_FILENAME, contentType, ANY_CONTENT);

      // Assert
      assertThat(part.contentType(), is(contentType));
    }

    @Test
    void GIVEN_empty_content_WHEN_create_THEN_store_empty_content() {
      // Act
      var part = new MultipartPart(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, new byte[0]);

      // Assert
      assertThat(part.content(), is(new byte[0]));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void GIVEN_blank_name_WHEN_create_THEN_return_meaningful_message(String name) {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> new MultipartPart(name, ANY_FILENAME, ANY_CONTENT_TYPE, ANY_CONTENT));

      // Assert
      assertThat(ex.getMessage(), is("Multipart part name must not be blank"));
    }

    @Test
    void GIVEN_null_content_WHEN_create_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> new MultipartPart(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, null));

      // Assert
      assertThat(ex.getMessage(), is("Multipart content must not be null"));
    }
  }

  @Nested
  class ProtectContent {

    @Test
    void GIVEN_source_content_WHEN_modify_source_THEN_keep_part_unchanged() {
      // Arrange
      var source = new byte[] {1, 2, 3};
      var part = new MultipartPart(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, source);

      // Act
      source[0] = 99;

      // Assert
      assertThat(part.content(), is(new byte[] {1, 2, 3}));
    }

    @Test
    void GIVEN_returned_content_WHEN_modify_externally_THEN_keep_part_unchanged() {
      // Arrange
      var part = new MultipartPart(ANY_NAME, ANY_FILENAME, ANY_CONTENT_TYPE, ANY_CONTENT);
      var returnedContent = part.content();

      // Act
      returnedContent[0] = 99;

      // Assert
      assertThat(part.content(), is(new byte[] {1, 2, 3}));
    }
  }
}
