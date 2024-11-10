package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head;

import static io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head.TestArrangeHeadUtils.addKeyValue;
import static io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head.TestArrangeHeadUtils.setAccepts;
import static io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head.TestArrangeHeadUtils.setContentTypes;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_0;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_MAP_1_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_0;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestHeader.TEST_HEADER_VALUE_2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestHeadDto;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

class TestArrangeHeadUtilsTest {

  @Nested
  class setAccepts {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_types_null_WHEN_setAccepts_THEN_throw_IllegalArgumentException() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      var exception =
          assertThrows(
              IllegalArgumentException.class, () -> setAccepts(testRequestHeadDto, null, null));

      // Assert
      assertThat(exception.getMessage(), is("Accepts must not contain null values"));
    }

    @ParameterizedTest()
    @MethodSource("provideNull")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNull_WHEN_setAccepts_THEN_throw_NullPointerException(
        TestRequestHeadDto testRequestHeadDto, MediaType mediaType) {

      assertThrows(NullPointerException.class, () -> setAccepts(testRequestHeadDto, mediaType));
    }

    private static Stream<Arguments> provideNull() {
      return Stream.of(Arguments.of(null, APPLICATION_JSON), Arguments.of(null, null));
    }

    @Test
    void GIVEN_type_WHEN_setAccepts_THEN_getAccepts_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      setAccepts(testRequestHeadDto, APPLICATION_JSON);

      // Assert
      assertThat(testRequestHeadDto.getAccepts(), contains(APPLICATION_JSON));
    }

    @Test
    void GIVEN_types_WHEN_setAccepts_THEN_getAccepts_returned_expected_values() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      setAccepts(testRequestHeadDto, APPLICATION_JSON, APPLICATION_XML);

      // Assert
      assertThat(testRequestHeadDto.getAccepts(), contains(APPLICATION_JSON, APPLICATION_XML));
    }
  }

  @Nested
  class setContentTypes {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_types_null_WHEN_setContentTypes_THEN_throw_IllegalArgumentException() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      var exception =
          assertThrows(
              IllegalArgumentException.class,
              () -> setContentTypes(testRequestHeadDto, null, null));

      // Assert
      assertThat(exception.getMessage(), is("ContentTypes must not contain null values"));
    }

    @ParameterizedTest()
    @MethodSource("provideNull")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNull_WHEN_setContentTypes_THEN_throw_NullPointerException(
        TestRequestHeadDto testRequestHeadDto, MediaType mediaType) {

      assertThrows(
          NullPointerException.class, () -> setContentTypes(testRequestHeadDto, mediaType));
    }

    private static Stream<Arguments> provideNull() {
      return Stream.of(Arguments.of(null, APPLICATION_JSON), Arguments.of(null, null));
    }

    @Test
    void GIVEN_type_WHEN_setContentTypes_THEN_getContentTypes_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      setContentTypes(testRequestHeadDto, APPLICATION_JSON);

      // Assert
      assertThat(testRequestHeadDto.getContentTypes(), contains(APPLICATION_JSON));
    }

    @Test
    void GIVEN_types_WHEN_setContentTypes_THEN_getContentTypes_returned_expected_values() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      setContentTypes(testRequestHeadDto, APPLICATION_JSON, APPLICATION_XML);

      // Assert
      assertThat(testRequestHeadDto.getContentTypes(), contains(APPLICATION_JSON, APPLICATION_XML));
    }
  }

  @Nested
  class addKeyValue {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_addKeyValue_THEN_throw_NullPointerException() {
      assertThrows(
          NullPointerException.class,
          () -> addKeyValue(null, TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1));
    }

    @Test
    void GIVEN_key_value_1_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue().size(), is(1));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_1), is(TEST_HEADER_VALUE_1));
    }

    @Test
    void GIVEN_key_value_2_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);
      addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue().size(), is(2));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_1), is(TEST_HEADER_VALUE_1));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_2), is(TEST_HEADER_VALUE_2));
    }

    @ParameterizedTest()
    @MethodSource("provideNull")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNull_WHEN_addKeyValue_THEN_throw_NullPointerException(
        TestRequestHeadDto testRequestHeadDto, Map<String, Object> keyValue) {

      assertThrows(NullPointerException.class, () -> addKeyValue(testRequestHeadDto, keyValue));
    }

    private static Stream<Arguments> provideNull() {
      return Stream.of(
          Arguments.of(null, TEST_HEADER_MAP_1_2),
          Arguments.of(mock(TestRequestHeadDto.class), null),
          Arguments.of(null, null));
    }

    @Test
    void GIVEN_key_value_map_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      addKeyValue(testRequestHeadDto, TEST_HEADER_MAP_1_2);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue(), is(TEST_HEADER_MAP_1_2));
    }

    @Test
    void
        GIVEN_addKeyValue_key_0_WHEN_addKeyValue_with_map_key_1_2_THEN_getKeyValue_returned_expected_values() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();
      addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_0, TEST_HEADER_VALUE_0);

      // Act
      addKeyValue(testRequestHeadDto, TEST_HEADER_MAP_1_2);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue().size(), is(3));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_0), is(TEST_HEADER_VALUE_0));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_1), is(TEST_HEADER_VALUE_1));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_2), is(TEST_HEADER_VALUE_2));
    }
  }
}
