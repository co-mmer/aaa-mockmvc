package io.github.co_mmer.aaamockmvc.test.web.mapper;

import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapTo;
import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapToList;
import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapToMap;
import static io.github.co_mmer.aaamockmvc.test.web.mapper.TestGenericMapper.mapToSet;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_1_DTO;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_LIST_1_JSON;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_DTO;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_JSON;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_DTO;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_JSON;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECT_1_DTO;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECT_1_JSON;
import static io.github.co_mmer.aaamockmvc.testdata.testutil.TestValue.TEST_DESERIALIZE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.test.web.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.testdata.testutil.TestObjectDeserializer;
import io.github.co_mmer.aaamockmvc.testdata.testutil.TestObjectDto;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

class TestGenericMapperTest {

  private MvcResult mockMvcResult;
  private MockHttpServletResponse mockHttpServletResponse;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    this.mockMvcResult = mock(MvcResult.class);
    this.mockHttpServletResponse = mock(MockHttpServletResponse.class);
    when(this.mockMvcResult.getResponse()).thenReturn(this.mockHttpServletResponse);
    this.objectMapper = new ObjectMapper();
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_class_WHEN_mapTo_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECT_1_JSON);

    // Act
    var result = mapTo(this.objectMapper, this.mockMvcResult, TestObjectDto.class);

    // Assert
    assertThat(result, is(TEST_OBJECT_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_deserializers_WHEN_mapTo_THEN_return_deserialize_value() throws Exception {
    // Arrange
    var deserializer = new TestObjectDeserializer();
    var deserializers = new JsonDeserializer[] {deserializer};
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECT_1_JSON);

    // Act
    var result = mapTo(this.objectMapper, this.mockMvcResult, TestObjectDto.class, deserializers);

    // Assert
    assertThat(result.name(), is(TEST_DESERIALIZE));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_expectedClass_WHEN_mapTo_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECT_1_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> mapTo(this.objectMapper, this.mockMvcResult, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_list_WHEN_mapToList_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_LIST_1_JSON);

    // Act
    var result = mapToList(this.objectMapper, this.mockMvcResult, TestObjectDto.class);

    // Assert
    assertThat(result, is(TEST_OBJECTS_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_deserializers_WHEN_mapToList_THEN_return_deserialize_values() throws Exception {
    // Arrange
    var deserializer = new TestObjectDeserializer();
    var deserializers = new JsonDeserializer[] {deserializer};
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_LIST_1_JSON);

    // Act
    List<TestObjectDto> result =
        mapToList(this.objectMapper, this.mockMvcResult, TestObjectDto.class, deserializers);

    // Assert
    assertThat(result.get(0).name(), is(TEST_DESERIALIZE));
    assertThat(result.get(1).name(), is(TEST_DESERIALIZE));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_expectedClass_WHEN_mapToList_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_LIST_1_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> mapToList(this.objectMapper, this.mockMvcResult, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_set_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Act
    var result = mapToSet(this.objectMapper, this.mockMvcResult, TestObjectDto.class);

    // Assert
    assertThat(result, is(TEST_OBJECTS_SET_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_expectedClass_WHEN_mapToSet_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> mapToSet(this.objectMapper, this.mockMvcResult, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_map_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_MAP_1_JSON);

    // Act
    var result =
        mapToMap(this.objectMapper, this.mockMvcResult, Boolean.class, TestObjectDto.class);

    // Assert
    assertThat(result, is(TEST_OBJECTS_MAP_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_deserializers_WHEN_mapToSet_THEN_return_deserialize_values() throws Exception {
    // Arrange
    var deserializer = new TestObjectDeserializer();
    var deserializers = new JsonDeserializer[] {deserializer};
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_MAP_1_JSON);

    // Act
    Map<Boolean, TestObjectDto> result =
        mapToMap(
            this.objectMapper,
            this.mockMvcResult,
            Boolean.class,
            TestObjectDto.class,
            deserializers);

    // Assert
    assertThat(result.get(Boolean.TRUE).name(), is(TEST_DESERIALIZE));
    assertThat(result.get(Boolean.FALSE).name(), is(TEST_DESERIALIZE));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_keyClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> mapToMap(this.objectMapper, this.mockMvcResult, String.class, TestObjectDto.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_valueClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> mapToMap(this.objectMapper, this.mockMvcResult, Boolean.class, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_keyClass_valueClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> mapToMap(this.objectMapper, this.mockMvcResult, String.class, String.class));
  }
}
