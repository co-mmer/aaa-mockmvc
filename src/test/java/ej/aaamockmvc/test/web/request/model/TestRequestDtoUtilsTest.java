package ej.aaamockmvc.test.web.request.model;

import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_CONTENT;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_CONTENT_NULL;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_EMPTY;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_NULL;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_1;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_EMPTY;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_NULL;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_EMPTY;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_NULL;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_1;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_EMPTY;
import static ej.aaamockmvc.test.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestRequestDtoUtilsTest {

  @ParameterizedTest
  @MethodSource("useCaseContainsFiles")
  void GIVEN_useCaseContainsFiles_WHEN_containsFiles_THEN_return_expected_boolean(
      TestRequestBodyDto bodyDto, boolean expectedBoolean) {

    // Act
    var result = TestRequestDtoUtils.isNotEmptyFiles(bodyDto);

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseContainsFiles() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_BODY_FILE_NULL, false),
        Arguments.of(TEST_REQUEST_BODY_FILE_EMPTY, false),
        Arguments.of(TEST_REQUEST_BODY_FILE_1, true));
  }

  @ParameterizedTest
  @MethodSource("useCaseContainsContent")
  void GIVEN_useCaseContainsContent_WHEN_containsContent_THEN_return_expected_boolean(
      TestRequestBodyDto bodyDto, boolean expectedBoolean) {

    // Act
    var result = TestRequestDtoUtils.isNotNullContent(bodyDto);

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseContainsContent() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_BODY_FILE_NULL, false),
        Arguments.of(TEST_REQUEST_BODY_CONTENT_NULL, false),
        Arguments.of(TEST_REQUEST_BODY_CONTENT, true));
  }

  @ParameterizedTest
  @MethodSource("useCaseContainsHeadAccepts")
  void GIVEN_useCaseContainsHeadAccepts_WHEN_containsHeadKeyValue_THEN_return_expected_boolean(
      TestRequestHeadDto headDto, boolean expectedBoolean) {

    // Act
    var result = TestRequestDtoUtils.isNotEmptyAccepts(headDto);

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseContainsHeadAccepts() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_HEAD_ACCEPT_NULL, false),
        Arguments.of(TEST_REQUEST_HEAD_ACCEPT_EMPTY, false),
        Arguments.of(TEST_REQUEST_HEAD_ACCEPT_1, true));
  }

  @ParameterizedTest
  @MethodSource("useCaseContainsHeadContentTypes")
  void
      GIVEN_useCaseContainsHeadContentTypes_WHEN_containsHeadContentTypes_THEN_return_expected_boolean(
          TestRequestHeadDto headDto, boolean expectedBoolean) {

    // Act
    var result = TestRequestDtoUtils.isNotEmptyContentTypes(headDto);

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseContainsHeadContentTypes() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_HEAD_CONTENT_TYPE_NULL, false),
        Arguments.of(TEST_REQUEST_HEAD_CONTENT_TYPE_EMPTY, false),
        Arguments.of(TEST_REQUEST_HEAD_CONTENT_TYPE_1, true));
  }

  @ParameterizedTest
  @MethodSource("useCaseContainsHeadKeyValue")
  void GIVEN_useCaseContainsHeadKeyValue_WHEN_containsHeadKeyValue_THEN_return_expected_boolean(
      TestRequestHeadDto headDto, boolean expectedBoolean) {

    // Act
    var result = TestRequestDtoUtils.isNotEmptyKeyValue(headDto);

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseContainsHeadKeyValue() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_HEAD_KEY_VALUE_NULL, false),
        Arguments.of(TEST_REQUEST_HEAD_KEY_VALUE_EMPTY, false),
        Arguments.of(TEST_REQUEST_HEAD_KEY_VALUE_1, true));
  }
}
