package ej.test.aaamockmvc.request.arrange.utils;

import static ej.test.aaamockmvc.testdata.testutil.TestBody.TEST_BODY_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_1;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_1_2;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_2;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_3;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_3_4;
import static ej.test.aaamockmvc.testdata.testutil.TestFiles.TEST_FILE_4;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import ej.test.aaamockmvc.request.model.TestRequestBodyDto;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestArrangeRequestBodyTest {

  @Test
  void WHEN_addFile_THEN_getFiles_returned_expected_value() {
    // Arrange
    var testRequestBodyDto = new TestRequestBodyDto();

    // Act
    TestArrangeRequestBody.addFile(testRequestBodyDto, TEST_FILE_1);

    // Assert
    assertThat(testRequestBodyDto.getFiles().size(), is(1));
    assertThat(testRequestBodyDto.getFiles().get(0), is(TEST_FILE_1));
  }

  @Test
  void GIVEN_file_WHEN_addFile_THEN_getFiles_returned_expected_value() {
    // Arrange
    var testRequestBodyDto = new TestRequestBodyDto();
    testRequestBodyDto.setFiles(List.of(TEST_FILE_1));

    // Act
    TestArrangeRequestBody.addFile(testRequestBodyDto, TEST_FILE_2);

    // Assert
    assertThat(testRequestBodyDto.getFiles().size(), is(2));
    assertThat(testRequestBodyDto.getFiles(), contains(TEST_FILE_1, TEST_FILE_2));
  }

  @Test
  void GIVEN_files_WHEN_addFile_THEN_getFiles_returned_expected_value() {
    // Arrange
    var testRequestBodyDto = new TestRequestBodyDto();
    testRequestBodyDto.setFiles(List.of(TEST_FILE_1, TEST_FILE_2));

    // Act
    TestArrangeRequestBody.addFile(testRequestBodyDto, TEST_FILE_3);

    // Assert
    assertThat(testRequestBodyDto.getFiles().size(), is(3));
    assertThat(testRequestBodyDto.getFiles(), contains(TEST_FILE_1, TEST_FILE_2, TEST_FILE_3));
  }

  @Test
  void WHEN_addFiles_THEN_getFiles_returned_expected_value() {
    // Arrange
    var testRequestBodyDto = new TestRequestBodyDto();

    // Act
    TestArrangeRequestBody.addFiles(testRequestBodyDto, TEST_FILE_1_2);

    // Assert
    assertThat(testRequestBodyDto.getFiles().size(), is(2));
    assertThat(testRequestBodyDto.getFiles(), contains(TEST_FILE_1, TEST_FILE_2));
  }

  @Test
  void GIVEN_files_WHEN_addFiles_THEN_getFiles_returned_expected_value() {
    // Arrange
    var testRequestBodyDto = new TestRequestBodyDto();
    testRequestBodyDto.setFiles(TEST_FILE_1_2);

    // Act
    TestArrangeRequestBody.addFiles(testRequestBodyDto, TEST_FILE_3_4);

    // Assert
    assertThat(testRequestBodyDto.getFiles().size(), is(4));
    assertThat(
        testRequestBodyDto.getFiles(),
        contains(TEST_FILE_1, TEST_FILE_2, TEST_FILE_3, TEST_FILE_4));
  }

  @Test
  void GIVEN_content_type_WHEN_setContent_THEN_getContent_returned_expected_value() {
    // Arrange
    var testRequestBodyDto = new TestRequestBodyDto();

    // Act
    TestArrangeRequestBody.setContent(testRequestBodyDto, TEST_BODY_JSON, APPLICATION_JSON);

    // Assert
    assertThat(testRequestBodyDto.getContent(), is(TEST_BODY_JSON));
    assertThat(testRequestBodyDto.getContentType(), is(APPLICATION_JSON));
  }
}
