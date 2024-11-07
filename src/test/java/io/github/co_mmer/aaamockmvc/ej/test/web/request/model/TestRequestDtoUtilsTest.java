package io.github.co_mmer.aaamockmvc.ej.test.web.request.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestBodyDto;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestRequestDtoUtilsTest {

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_isNotEmptyAccepts_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestDtoUtils.isNotEmptyAccepts(null));
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
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_NULL, false),
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_EMPTY, false),
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_1, true));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_isNotEmptyContentTypes_THEN_throw_NullPointerException() {
    assertThrows(
        NullPointerException.class, () -> TestRequestDtoUtils.isNotEmptyContentTypes(null));
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
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_NULL, false),
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_EMPTY, false),
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_1, true));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_isNotNullContent_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestDtoUtils.isNotNullContent(null));
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
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_NULL, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_CONTENT_NULL, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_CONTENT, true));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_isNotEmptyFiles_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestDtoUtils.isNotEmptyFiles(null));
  }

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
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_NULL, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_EMPTY, false),
        Arguments.of(TestDataRequestBodyDto.TEST_REQUEST_BODY_FILE_1, true));
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void GIVEN_null_WHEN_isNotEmptyKeyValues_THEN_throw_NullPointerException() {
    assertThrows(NullPointerException.class, () -> TestRequestDtoUtils.isNotEmptyKeyValue(null));
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
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_NULL, false),
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_EMPTY, false),
        Arguments.of(TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_1, true));
  }
}
