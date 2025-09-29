package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeBodySetter.addFile;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeBodySetter.addFiles;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeBodySetter.setContent;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestBody.TEST_BODY_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_3_4;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestFiles.TEST_FILE_4;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestBodyDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestArrangeBodySetterTest {

  private TestRequestBodyDto bodyDto;

  @BeforeEach
  void setUp() {
    this.bodyDto = new TestRequestBodyDto();
  }

  @Nested
  class addFile {

    @Test
    void WHEN_addFile_THEN_getFiles_returned_expected_value() {
      // Act
      addFile(bodyDto, TEST_FILE_1);

      // Assert
      assertThat(bodyDto.getFiles().size(), is(1));
      assertThat(bodyDto.getFiles().get(0), is(TEST_FILE_1));
    }

    @Test
    void GIVEN_file_WHEN_addFile_THEN_getFiles_returned_expected_value() {
      // Arrange
      bodyDto.setFiles(List.of(TEST_FILE_1));

      // Act
      addFile(bodyDto, TEST_FILE_2);

      // Assert
      assertThat(bodyDto.getFiles().size(), is(2));
      assertThat(bodyDto.getFiles(), contains(TEST_FILE_1, TEST_FILE_2));
    }

    @Test
    void GIVEN_files_WHEN_addFile_THEN_getFiles_returned_expected_value() {
      // Arrange
      bodyDto.setFiles(List.of(TEST_FILE_1, TEST_FILE_2));

      // Act
      addFile(bodyDto, TEST_FILE_3);

      // Assert
      assertThat(bodyDto.getFiles().size(), is(3));
      assertThat(bodyDto.getFiles(), contains(TEST_FILE_1, TEST_FILE_2, TEST_FILE_3));
    }
  }

  @Nested
  class addFiles {

    @Test
    void WHEN_addFiles_THEN_getFiles_returned_expected_value() {
      // Act
      addFiles(bodyDto, TEST_FILE_1_2);

      // Assert
      assertThat(bodyDto.getFiles().size(), is(2));
      assertThat(bodyDto.getFiles(), contains(TEST_FILE_1, TEST_FILE_2));
    }

    @Test
    void GIVEN_files_WHEN_addFiles_THEN_getFiles_returned_expected_value() {
      // Arrange
      bodyDto.setFiles(TEST_FILE_1_2);

      // Act
      addFiles(bodyDto, TEST_FILE_3_4);

      // Assert
      assertThat(bodyDto.getFiles().size(), is(4));
      assertThat(bodyDto.getFiles(), contains(TEST_FILE_1, TEST_FILE_2, TEST_FILE_3, TEST_FILE_4));
    }
  }

  @Nested
  class setContent {

    @Test
    void GIVEN_content_type_WHEN_setContent_THEN_getContent_returned_expected_value() {
      // Act
      setContent(bodyDto, TEST_BODY_JSON, APPLICATION_JSON);

      // Assert
      assertThat(bodyDto.getContent(), is(TEST_BODY_JSON));
      assertThat(bodyDto.getContentType(), is(APPLICATION_JSON));
    }
  }
}
