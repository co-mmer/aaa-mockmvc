package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_ACCEPT_NULL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_CONTENT_TYPE_NULL;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestHeadDto.TEST_REQUEST_HEAD_KEY_VALUE_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

class TestRequestHeadDtoTest {

  @ParameterizedTest
  @MethodSource("useCaseAccepts")
  void GIVEN_useCaseAccepts_WHEN_isNotEmptyAccepts_THEN_return_expected_boolean(
      TestRequestHeadDto headDto, boolean expectedBoolean) {

    // Act
    var result = headDto.isNotEmptyAccepts();

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseAccepts() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_HEAD_ACCEPT_NULL, false),
        Arguments.of(TEST_REQUEST_HEAD_ACCEPT_EMPTY, false),
        Arguments.of(TEST_REQUEST_HEAD_ACCEPT_1, true));
  }

  @ParameterizedTest
  @MethodSource("useCaseContentTypes")
  void GIVEN_useCaseContentTypes_WHEN_isNotNullContentType_THEN_return_expected_boolean(
      TestRequestHeadDto headDto, boolean expectedBoolean) {

    // Act
    var result = headDto.isNotNullContentType();

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseContentTypes() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_HEAD_CONTENT_TYPE_NULL, false),
        Arguments.of(TEST_REQUEST_HEAD_CONTENT_TYPE, true));
  }

  @ParameterizedTest
  @MethodSource("useCaseKeyValue")
  void GIVEN_useCaseKeyValue_WHEN_isNotEmptyKeyValue_THEN_return_expected_boolean(
      TestRequestHeadDto headDto, boolean expectedBoolean) {

    // Act
    var result = headDto.isNotEmptyKeyValue();

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseKeyValue() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_HEAD_KEY_VALUE_NULL, false),
        Arguments.of(TEST_REQUEST_HEAD_KEY_VALUE_EMPTY, false),
        Arguments.of(TEST_REQUEST_HEAD_KEY_VALUE_1, true));
  }

  @Nested
  class asMessage {

    @Test
    void GIVEN_defaults_WHEN_asMessage_THEN_placeholders_and_empty_map() {
      // Arrange
      var dto = new TestRequestHeadDto();

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Headers: accepts=<none> | content-type=<none> | key-value={}"));
    }

    @Test
    void GIVEN_only_accepts_WHEN_asMessage_THEN_render_accepts_and_placeholders() {
      // Arrange
      var dto = new TestRequestHeadDto();
      dto.getAccepts().add(MediaType.APPLICATION_JSON);
      dto.getAccepts().add(MediaType.TEXT_PLAIN);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, startsWith("Headers: accepts=application/json, text/plain"));
      assertThat(msg, containsString(" | content-type=<none> | key-value={}"));
    }

    @Test
    void GIVEN_only_content_type_WHEN_asMessage_THEN_render_content_type_and_placeholders() {
      // Arrange
      var dto = new TestRequestHeadDto();
      dto.setContentType(MediaType.APPLICATION_XML);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Headers: accepts=<none> | content-type=application/xml | key-value={}"));
    }

    @Test
    void GIVEN_accepts_and_content_type_WHEN_asMessage_THEN_render_both() {
      // Arrange
      var dto = new TestRequestHeadDto();
      dto.getAccepts().add(MediaType.APPLICATION_JSON);
      dto.setContentType(MediaType.APPLICATION_JSON);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(
          msg,
          is("Headers: accepts=application/json | content-type=application/json | key-value={}"));
    }

    @Test
    void GIVEN_key_value_single_entry_WHEN_asMessage_THEN_render_key_value_map() {
      // Arrange
      var dto = new TestRequestHeadDto();
      dto.getKeyValue().put("X-Foo", List.of("a", "b"));

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, startsWith("Headers: accepts=<none> | content-type=<none> | key-value={"));
      assertThat(msg, containsString("X-Foo=[a, b]"));
      assertThat(msg, endsWith("}"));
    }

    @Test
    void GIVEN_full_headers_WHEN_asMessage_THEN_render_all_sections() {
      // Arrange
      var dto = new TestRequestHeadDto();
      dto.getAccepts().add(MediaType.APPLICATION_JSON);
      dto.getAccepts().add(MediaType.TEXT_PLAIN);
      dto.setContentType(MediaType.APPLICATION_JSON);
      dto.getKeyValue().put("Authorization", List.of("Bearer token"));
      dto.getKeyValue().put("X-Trace-Id", List.of("abc123"));

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(
          msg,
          startsWith(
              "Headers: accepts=application/json, text/plain | content-type=application/json | key-value={"));
      assertThat(msg, containsString("Authorization=[Bearer token]"));
      assertThat(msg, containsString("X-Trace-Id=[abc123]"));
      assertThat(msg, endsWith("}"));
    }

    @Test
    void GIVEN_null_value_in_key_value_WHEN_asMessage_THEN_render_null_literal() {
      // Arrange
      var dto = new TestRequestHeadDto();
      dto.getKeyValue().put("X-Null", null);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(
          msg, is("Headers: accepts=<none> | content-type=<none> | key-value={X-Null=null}"));
    }
  }
}
