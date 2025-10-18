package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockObjectMapper.throwOnWriteValueAsString;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

class TestGenericMapperTest {

  private MvcResult mockMvcResult;
  private MockHttpServletResponse mockHttpServletResponse;
  private ObjectMapper om;

  @BeforeEach
  void setUp() {
    this.mockMvcResult = mock(MvcResult.class);
    this.mockHttpServletResponse = mock(MockHttpServletResponse.class);
    when(this.mockMvcResult.getResponse()).thenReturn(this.mockHttpServletResponse);
    this.om = new ObjectMapper();
  }

  @Nested
  class parseWithString {

    @Test
    @SneakyThrows
    void GIVEN_A1_JSON_WHEN_parse_THEN_return_A1() {
      // Act
      var result = TestGenericMapper.parse(om, TEST_A1_JSON, TestObjectSimple.class);

      // Assert
      assertThat(result, is(A1));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    @SneakyThrows
    void GIVEN_blank_string_WHEN_parse_THEN_return_null(String content) {
      // Act
      var result = TestGenericMapper.parse(om, content, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parse_THEN_throw_Exception() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parse(om, invalid, TestObjectSimple.class));
    }
  }

  @Nested
  class parseListWithString {

    @Test
    @SneakyThrows
    void GIVEN_LIST_A1_A2_JSON_WHEN_parseList_THEN_return_List_A1_A2() {
      // Act
      var result = TestGenericMapper.parseList(om, TEST_LIST_A1_A2_JSON, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    @SneakyThrows
    void GIVEN_blank_string_WHEN_parseList_THEN_return_null(String content) {
      // Act
      var result = TestGenericMapper.parseList(om, content, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseList_THEN_throw_Exception() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseList(om, invalid, TestObjectSimple.class));
    }

    @Test
    void GIVEN_json_object_instead_of_array_WHEN_parseList_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseList(om, TEST_A1_JSON, TestObjectSimple.class));
    }

    @Test
    void GIVEN_list_json_WHEN_parseList_with_wrong_element_class_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseList(om, TEST_LIST_A1_A2_JSON, String.class));
    }
  }

  @Nested
  class parseSetWithString {

    @Test
    @SneakyThrows
    void GIVEN_SET_A1_A2_JSON_WHEN_parseSet_THEN_return_Set_A1_A2() {
      // Act
      var result = TestGenericMapper.parseSet(om, TEST_SET_A1_A2_JSON, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    @SneakyThrows
    void GIVEN_blank_string_WHEN_parseSet_THEN_return_null(String content) {
      // Act
      var result = TestGenericMapper.parseSet(om, content, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseSet_THEN_throw_Exception() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseSet(om, invalid, TestObjectSimple.class));
    }

    @Test
    void GIVEN_json_object_instead_of_array_WHEN_parseSet_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseSet(om, TEST_A1_JSON, TestObjectSimple.class));
    }

    @Test
    void GIVEN_set_json_WHEN_parseSet_with_wrong_element_class_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseSet(om, TEST_SET_A1_A2_JSON, String.class));
    }
  }

  @Nested
  class parseMapWithString {

    @Test
    @SneakyThrows
    void GIVEN_expected_map_WHEN_parseMap_THEN_return_expected_object() {
      // Act
      var result =
          TestGenericMapper.parseMap(
              om, TEST_MAP_A1_A2_JSON, Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    @SneakyThrows
    void GIVEN_blank_string_WHEN_parseMap_THEN_return_null(String content) {
      // Act
      var result = TestGenericMapper.parseMap(om, content, Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseMap_THEN_throw_Exception() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseMap(om, invalid, Integer.class, TestObjectSimple.class));
    }

    @Test
    void GIVEN_array_json_instead_of_object_WHEN_parseMap_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseMap(
                  om, TEST_LIST_A1_A2_JSON, Integer.class, TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    void GIVEN_numeric_keys_and_String_keyClass_WHEN_parseMap_THEN_return_map_with_string_keys() {
      // Act
      var result =
          TestGenericMapper.parseMap(om, TEST_MAP_A1_A2_JSON, String.class, TestObjectSimple.class);

      // Assert
      assertThat(result.keySet(), containsInAnyOrder("1", "2"));
      assertThat(result.get("1"), is(A1));
      assertThat(result.get("2"), is(A2));
    }

    @Test
    void GIVEN_wrong_valueClass_WHEN_parseMap_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseMap(om, TEST_MAP_A1_A2_JSON, Integer.class, String.class));
    }

    @Test
    void GIVEN_wrong_key_and_value_class_WHEN_parseMap_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseMap(om, TEST_MAP_A1_A2_JSON, String.class, String.class));
    }
  }

  @Nested
  class parseCollectionWithString {

    @Test
    @SneakyThrows
    void GIVEN_LIST_A1_A2_JSON_WHEN_parseCollection_THEN_return_Collection_A1_A2() {
      // Act
      var result =
          TestGenericMapper.parseCollection(om, TEST_LIST_A1_A2_JSON, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    @SneakyThrows
    void GIVEN_blank_string_WHEN_parseCollection_THEN_return_null(String content) {
      // Act
      var result = TestGenericMapper.parseCollection(om, content, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseCollection_THEN_throw_exception() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseCollection(om, invalid, TestObjectSimple.class));
    }

    @Test
    void GIVEN_json_object_instead_of_array_WHEN_parseCollection_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseCollection(om, TEST_A1_JSON, TestObjectSimple.class));
    }

    @Test
    void GIVEN_list_json_WHEN_parseCollection_with_wrong_element_class_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseCollection(om, TEST_LIST_A1_A2_JSON, String.class));
    }
  }

  @Nested
  class parseCollectionWithMvcResult {

    @Test
    @SneakyThrows
    void GIVEN_LIST_A1_A2_JSON_WHEN_parseCollection_THEN_return_Collection_A1_A2() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var result =
          TestGenericMapper.parseCollection(
              om, mockMvcResult.getResponse().getContentAsString(), TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    @SneakyThrows
    void GIVEN_blank_WHEN_parseCollection_THEN_return_null(String response) {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(response);

      // Act
      var result =
          TestGenericMapper.parseCollection(
              om, mockMvcResult.getResponse().getContentAsString(), TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_invalid_json_WHEN_parseCollection_THEN_throw_Exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn("{not-valid-json");

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  om, mockMvcResult.getResponse().getContentAsString(), TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    void GIVEN_json_object_instead_of_array_WHEN_parseCollection_THEN_throw_Exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  om, mockMvcResult.getResponse().getContentAsString(), TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    void GIVEN_list_json_WHEN_parseCollection_with_wrong_element_class_THEN_throw_Exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  om, mockMvcResult.getResponse().getContentAsString(), String.class));
    }

    @Test
    @SneakyThrows
    void GIVEN_getContentAsString_throws_WHEN_parseCollection_THEN_throw_Exception() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseCollection(om, TEST_LIST_A1_A2_JSON, String.class));
    }
  }

  @Nested
  class toJson {

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_toJson_THEN_throw_Exception() {
      // Arrange
      var mockObjectMapper = throwOnWriteValueAsString();

      // Act && Assert
      assertThrows(
          TestGenericMapperException.class, () -> TestGenericMapper.toJson(mockObjectMapper, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(om, A1);

      // Assert
      assertThat(result, is(TEST_A1_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_list_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(om, TEST_LIST_A1_A2);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_set_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(om, TEST_SET_A1_A2);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_map_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(om, TEST_MAP_A1_A2);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_toJson_THEN_return_string_null() {
      // Act
      var result = TestGenericMapper.toJson(om, null);

      // Assert
      assertThat(result, is("null"));
    }
  }
}
