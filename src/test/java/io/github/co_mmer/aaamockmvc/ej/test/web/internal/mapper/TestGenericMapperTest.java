package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.MappingType;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.io.UnsupportedEncodingException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

class TestGenericMapperTest {

  private static final MappingType<TestObjectSimple> SIMPLE_TYPE =
      new MappingType<>(TestObjectSimple.class);
  private static final MappingType<String> STRING_TYPE = new MappingType<>(String.class);
  private static final MappingType<Integer> INTEGER_TYPE = new MappingType<>(Integer.class);
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
      var result =
          TestGenericMapper.parse(TestGenericMapperTest.this.om, TEST_A1_JSON, SIMPLE_TYPE);

      // Assert
      assertThat(result, is(A1));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    void GIVEN_blank_string_WHEN_parse_THEN_TestGenericMapperException_is_thrown(String content) {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parse(TestGenericMapperTest.this.om, content, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_null_string_WHEN_parse_THEN_IllegalArgumentException_is_thrown() {
      // Act & Assert
      assertThrows(
          IllegalArgumentException.class,
          () -> TestGenericMapper.parse(TestGenericMapperTest.this.om, null, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parse_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parse(TestGenericMapperTest.this.om, invalid, SIMPLE_TYPE));
    }
  }

  @Nested
  class parseListWithString {

    @Test
    @SneakyThrows
    void GIVEN_LIST_A1_A2_JSON_WHEN_parseList_THEN_return_List_A1_A2() {
      // Act
      var result =
          TestGenericMapper.parseList(
              TestGenericMapperTest.this.om, TEST_LIST_A1_A2_JSON, SIMPLE_TYPE);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    void GIVEN_blank_string_WHEN_parseList_THEN_TestGenericMapperException_is_thrown(
        String content) {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseList(TestGenericMapperTest.this.om, content, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_null_string_WHEN_parseList_THEN_IllegalArgumentException_is_thrown() {
      // Act & Assert
      assertThrows(
          IllegalArgumentException.class,
          () -> TestGenericMapper.parseList(TestGenericMapperTest.this.om, null, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseList_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseList(TestGenericMapperTest.this.om, invalid, SIMPLE_TYPE));
    }

    @Test
    void
        GIVEN_json_object_instead_of_array_WHEN_parseList_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseList(
                  TestGenericMapperTest.this.om, TEST_A1_JSON, SIMPLE_TYPE));
    }

    @Test
    void
        GIVEN_list_json_WHEN_parseList_with_wrong_element_class_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseList(
                  TestGenericMapperTest.this.om, TEST_LIST_A1_A2_JSON, INTEGER_TYPE));
    }
  }

  @Nested
  class parseSetWithString {

    @Test
    @SneakyThrows
    void GIVEN_SET_A1_A2_JSON_WHEN_parseSet_THEN_return_Set_A1_A2() {
      // Act
      var result =
          TestGenericMapper.parseSet(
              TestGenericMapperTest.this.om, TEST_SET_A1_A2_JSON, SIMPLE_TYPE);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    void GIVEN_blank_string_WHEN_parseSet_THEN_TestGenericMapperException_is_thrown(
        String content) {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseSet(TestGenericMapperTest.this.om, content, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_null_string_WHEN_parseSet_THEN_IllegalArgumentException_is_thrown() {
      // Act & Assert
      assertThrows(
          IllegalArgumentException.class,
          () -> TestGenericMapper.parseSet(TestGenericMapperTest.this.om, null, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseSet_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () -> TestGenericMapper.parseSet(TestGenericMapperTest.this.om, invalid, SIMPLE_TYPE));
    }

    @Test
    void
        GIVEN_json_object_instead_of_array_WHEN_parseSet_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseSet(TestGenericMapperTest.this.om, TEST_A1_JSON, SIMPLE_TYPE));
    }

    @Test
    void
        GIVEN_set_json_WHEN_parseSet_with_wrong_element_class_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseSet(
                  TestGenericMapperTest.this.om, TEST_SET_A1_A2_JSON, STRING_TYPE));
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
              TestGenericMapperTest.this.om, TEST_MAP_A1_A2_JSON, INTEGER_TYPE, SIMPLE_TYPE);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    void GIVEN_blank_string_WHEN_parseMap_THEN_TestGenericMapperException_is_thrown(
        String content) {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseMap(
                  TestGenericMapperTest.this.om, content, INTEGER_TYPE, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_null_string_WHEN_parseMap_THEN_IllegalArgumentException_is_thrown() {
      // Act & Assert
      assertThrows(
          IllegalArgumentException.class,
          () ->
              TestGenericMapper.parseMap(
                  TestGenericMapperTest.this.om, null, INTEGER_TYPE, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseMap_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseMap(
                  TestGenericMapperTest.this.om, invalid, INTEGER_TYPE, SIMPLE_TYPE));
    }

    @Test
    void
        GIVEN_array_json_instead_of_object_WHEN_parseMap_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseMap(
                  TestGenericMapperTest.this.om, TEST_LIST_A1_A2_JSON, INTEGER_TYPE, SIMPLE_TYPE));
    }

    @Test
    @SneakyThrows
    void GIVEN_numeric_keys_and_String_keyClass_WHEN_parseMap_THEN_return_map_with_string_keys() {
      // Act
      var result =
          TestGenericMapper.parseMap(
              TestGenericMapperTest.this.om, TEST_MAP_A1_A2_JSON, STRING_TYPE, SIMPLE_TYPE);

      // Assert
      assertThat(result.keySet(), containsInAnyOrder("1", "2"));
      assertThat(result.get("1"), is(A1));
      assertThat(result.get("2"), is(A2));
    }

    @Test
    void GIVEN_wrong_valueClass_WHEN_parseMap_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseMap(
                  TestGenericMapperTest.this.om, TEST_MAP_A1_A2_JSON, INTEGER_TYPE, STRING_TYPE));
    }

    @Test
    void GIVEN_wrong_key_and_value_class_WHEN_parseMap_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseMap(
                  TestGenericMapperTest.this.om, TEST_MAP_A1_A2_JSON, STRING_TYPE, STRING_TYPE));
    }
  }

  @Nested
  class parseCollectionWithString {

    @Test
    @SneakyThrows
    void GIVEN_LIST_A1_A2_JSON_WHEN_parseCollection_THEN_return_Collection_A1_A2() {
      // Act
      var result =
          TestGenericMapper.parseCollection(
              TestGenericMapperTest.this.om, TEST_LIST_A1_A2_JSON, SIMPLE_TYPE);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    void GIVEN_blank_string_WHEN_parseCollection_THEN_TestGenericMapperException_is_thrown(
        String content) {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om, content, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_null_string_WHEN_parseCollection_THEN_IllegalArgumentException_is_thrown() {
      // Act & Assert
      assertThrows(
          IllegalArgumentException.class,
          () ->
              TestGenericMapper.parseCollection(TestGenericMapperTest.this.om, null, SIMPLE_TYPE));
    }

    @Test
    void GIVEN_invalid_json_WHEN_parseCollection_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      var invalid = "{not-valid-json";

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om, invalid, SIMPLE_TYPE));
    }

    @Test
    void
        GIVEN_json_object_instead_of_array_WHEN_parseCollection_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om, TEST_A1_JSON, SIMPLE_TYPE));
    }

    @Test
    void
        GIVEN_list_json_WHEN_parseCollection_with_wrong_element_class_THEN_TestGenericMapperException_is_thrown() {
      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om, TEST_LIST_A1_A2_JSON, STRING_TYPE));
    }
  }

  @Nested
  class parseCollectionWithMvcResult {

    @Test
    @SneakyThrows
    void GIVEN_LIST_A1_A2_JSON_WHEN_parseCollection_THEN_return_Collection_A1_A2() {
      // Arrange
      when(TestGenericMapperTest.this.mockHttpServletResponse.getContentAsString())
          .thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var result =
          TestGenericMapper.parseCollection(
              TestGenericMapperTest.this.om,
              TestGenericMapperTest.this.mockMvcResult.getResponse().getContentAsString(),
              SIMPLE_TYPE);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "\n", "\t", "   "})
    @SneakyThrows
    void GIVEN_blank_WHEN_parseCollection_THEN_TestGenericMapperException_is_thrown(
        String response) {
      // Arrange
      when(TestGenericMapperTest.this.mockHttpServletResponse.getContentAsString())
          .thenReturn(response);

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om,
                  TestGenericMapperTest.this.mockMvcResult.getResponse().getContentAsString(),
                  SIMPLE_TYPE));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_body_WHEN_parseCollection_THEN_IllegalArgumentException_is_thrown() {
      // Arrange
      when(TestGenericMapperTest.this.mockHttpServletResponse.getContentAsString())
          .thenReturn(null);

      // Act & Assert
      assertThrows(
          IllegalArgumentException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om,
                  TestGenericMapperTest.this.mockMvcResult.getResponse().getContentAsString(),
                  SIMPLE_TYPE));
    }

    @Test
    @SneakyThrows
    void GIVEN_invalid_json_WHEN_parseCollection_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      when(TestGenericMapperTest.this.mockHttpServletResponse.getContentAsString())
          .thenReturn("{not-valid-json");

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om,
                  TestGenericMapperTest.this.mockMvcResult.getResponse().getContentAsString(),
                  SIMPLE_TYPE));
    }

    @Test
    @SneakyThrows
    void
        GIVEN_json_object_instead_of_array_WHEN_parseCollection_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      when(TestGenericMapperTest.this.mockHttpServletResponse.getContentAsString())
          .thenReturn(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om,
                  TestGenericMapperTest.this.mockMvcResult.getResponse().getContentAsString(),
                  SIMPLE_TYPE));
    }

    @Test
    @SneakyThrows
    void
        GIVEN_list_json_WHEN_parseCollection_with_wrong_element_class_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      when(TestGenericMapperTest.this.mockHttpServletResponse.getContentAsString())
          .thenReturn(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          TestGenericMapperException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om,
                  TestGenericMapperTest.this.mockMvcResult.getResponse().getContentAsString(),
                  STRING_TYPE));
    }

    @Test
    @SneakyThrows
    void
        GIVEN_getContentAsString_throws_WHEN_parseCollection_THEN_UnsupportedEncodingException_is_thrown() {
      // Arrange
      when(TestGenericMapperTest.this.mockHttpServletResponse.getContentAsString())
          .thenThrow(new UnsupportedEncodingException("Unsupported encoding"));

      // Act & Assert
      assertThrows(
          UnsupportedEncodingException.class,
          () ->
              TestGenericMapper.parseCollection(
                  TestGenericMapperTest.this.om,
                  TestGenericMapperTest.this.mockMvcResult.getResponse().getContentAsString(),
                  SIMPLE_TYPE));
    }
  }

  @Nested
  class toJson {

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_toJson_THEN_TestGenericMapperException_is_thrown() {
      // Arrange
      var mockObjectMapper = mock(ObjectMapper.class);
      when(mockObjectMapper.writeValueAsString(A1)).thenThrow(mock(JsonProcessingException.class));

      // Act && Assert
      assertThrows(
          TestGenericMapperException.class, () -> TestGenericMapper.toJson(mockObjectMapper, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(TestGenericMapperTest.this.om, A1);

      // Assert
      assertThat(result, is(TEST_A1_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_list_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(TestGenericMapperTest.this.om, TEST_LIST_A1_A2);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_set_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(TestGenericMapperTest.this.om, TEST_SET_A1_A2);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_map_WHEN_toJson_THEN_return_expected_json() {
      // Act
      var result = TestGenericMapper.toJson(TestGenericMapperTest.this.om, TEST_MAP_A1_A2);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_toJson_THEN_return_string_null() {
      // Act
      var result = TestGenericMapper.toJson(TestGenericMapperTest.this.om, null);

      // Assert
      assertThat(result, is("null"));
    }
  }
}
