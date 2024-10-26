package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head;

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
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestHeadDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestArrangeHeadUtilsTest {

  @Nested
  class setAccepts {

    @SuppressWarnings("all")
    @Test
    void GIVEN_null_null_WHEN_setAccepts_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> TestArrangeHeadUtils.setAccepts(null, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void GIVEN_requestHeadDto_null_WHEN_setAccepts_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> TestArrangeHeadUtils.setAccepts(null, APPLICATION_JSON));
    }

    @SuppressWarnings("all")
    @Test
    void GIVEN_accepts_null_WHEN_setAccepts_THEN_throw_NullPointerException() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> TestArrangeHeadUtils.setAccepts(testRequestHeadDto, null));
    }

    @Test
    void GIVEN_type_WHEN_setAccepts_THEN_getAccepts_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      TestArrangeHeadUtils.setAccepts(testRequestHeadDto, APPLICATION_JSON);

      // Assert
      assertThat(testRequestHeadDto.getAccepts(), contains(APPLICATION_JSON));
    }

    @Test
    void GIVEN_types_WHEN_setAccepts_THEN_getAccepts_returned_expected_values() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      TestArrangeHeadUtils.setAccepts(testRequestHeadDto, APPLICATION_JSON, APPLICATION_XML);

      // Assert
      assertThat(testRequestHeadDto.getAccepts(), contains(APPLICATION_JSON, APPLICATION_XML));
    }
  }

  @Nested
  class setContentTypes {

    @SuppressWarnings("all")
    @Test
    void GIVEN_null_null_WHEN_setContentTypes_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> TestArrangeHeadUtils.setContentTypes(null, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void GIVEN_requestHeadDto_null_WHEN_setContentTypes_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> TestArrangeHeadUtils.setContentTypes(null, APPLICATION_JSON));
    }

    @SuppressWarnings("all")
    @Test
    void GIVEN_types_null_WHEN_setContentTypes_THEN_throw_NullPointerException() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> TestArrangeHeadUtils.setContentTypes(testRequestHeadDto, null));
    }

    @Test
    void GIVEN_type_WHEN_setContentTypes_THEN_getContentTypes_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      TestArrangeHeadUtils.setContentTypes(testRequestHeadDto, APPLICATION_JSON);

      // Assert
      assertThat(testRequestHeadDto.getContentTypes(), contains(APPLICATION_JSON));
    }

    @Test
    void GIVEN_types_WHEN_setContentTypes_THEN_getContentTypes_returned_expected_values() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      TestArrangeHeadUtils.setContentTypes(testRequestHeadDto, APPLICATION_JSON, APPLICATION_XML);

      // Assert
      assertThat(testRequestHeadDto.getContentTypes(), contains(APPLICATION_JSON, APPLICATION_XML));
    }
  }

  @Nested
  class addKeyValue {

    @SuppressWarnings("all")
    @Test
    void GIVEN_null_WHEN_addKeyValue_THEN_throw_NullPointerException() {
      //  Assert
      assertThrows(
          NullPointerException.class,

          // Act
          () -> TestArrangeHeadUtils.addKeyValue(null, TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1));
    }

    @Test
    void GIVEN_key_value_1_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      TestArrangeHeadUtils.addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue().size(), is(1));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_1), is(TEST_HEADER_VALUE_1));
    }

    @Test
    void GIVEN_key_value_2_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      TestArrangeHeadUtils.addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_1, TEST_HEADER_VALUE_1);
      TestArrangeHeadUtils.addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_2, TEST_HEADER_VALUE_2);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue().size(), is(2));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_1), is(TEST_HEADER_VALUE_1));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_2), is(TEST_HEADER_VALUE_2));
    }

    @Test
    void GIVEN_key_value_map_WHEN_addKeyValue_THEN_getKeyValue_returned_expected_value() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();

      // Act
      TestArrangeHeadUtils.addKeyValue(testRequestHeadDto, TEST_HEADER_MAP_1_2);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue(), is(TEST_HEADER_MAP_1_2));
    }

    @Test
    void
        GIVEN_addKeyValue_key_0_WHEN_addKeyValue_with_map_key_1_2_THEN_getKeyValue_returned_expected_values() {
      // Arrange
      var testRequestHeadDto = new TestRequestHeadDto();
      TestArrangeHeadUtils.addKeyValue(testRequestHeadDto, TEST_HEADER_KEY_0, TEST_HEADER_VALUE_0);

      // Act
      TestArrangeHeadUtils.addKeyValue(testRequestHeadDto, TEST_HEADER_MAP_1_2);

      // Assert
      assertThat(testRequestHeadDto.getKeyValue().size(), is(3));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_0), is(TEST_HEADER_VALUE_0));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_1), is(TEST_HEADER_VALUE_1));
      assertThat(testRequestHeadDto.getKeyValue().get(TEST_HEADER_KEY_2), is(TEST_HEADER_VALUE_2));
    }
  }
}
