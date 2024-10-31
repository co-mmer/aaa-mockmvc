package io.github.co_mmer.aaamockmvc.ej.test.web.mapper;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_LIST_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECT_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECT_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_DESERIALIZE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1Deserializer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1Dto;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  @SuppressWarnings("unchecked")
  void GIVEN_provideNullParametersOnMapTo_WHEN_mapTo_THEN_throw_NullPointerException(
      ObjectMapper mapper, MvcResult result, Class<?> clazz) {

    assertThrows(NullPointerException.class, () -> TestGenericMapper.mapTo(mapper, result, clazz));
  }

  private static Stream<Arguments> provideNullParameters() {
    return Stream.of(
        Arguments.of(null, mock(MvcResult.class), String.class),
        Arguments.of(new ObjectMapper(), null, String.class),
        Arguments.of(new ObjectMapper(), mock(MvcResult.class), null),
        Arguments.of(null, null, String.class),
        Arguments.of(null, mock(MvcResult.class), null),
        Arguments.of(new ObjectMapper(), null, null),
        Arguments.of(null, null, null));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_class_WHEN_mapTo_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECT_1_JSON);

    // Act
    var result =
        TestGenericMapper.mapTo(this.objectMapper, this.mockMvcResult, TestObject1Dto.class);

    // Assert
    assertThat(result, is(TEST_OBJECT_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_deserializers_WHEN_mapTo_THEN_return_deserialize_value() throws Exception {
    // Arrange
    var deserializers = new JsonDeserializer[]{new TestObject1Deserializer()};
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECT_1_JSON);

    // Act
    var result =
        TestGenericMapper.mapTo(
            this.objectMapper, this.mockMvcResult, TestObject1Dto.class, deserializers);

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
        () -> TestGenericMapper.mapTo(this.objectMapper, this.mockMvcResult, String.class));
  }

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  @SuppressWarnings("unchecked")
  void GIVEN_provideNullParameters_WHEN_mapToList_THEN_throw_NullPointerException(
      ObjectMapper mapper, MvcResult result, Class<?> clazz) {

    assertThrows(
        NullPointerException.class, () -> TestGenericMapper.mapToList(mapper, result, clazz));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_list_WHEN_mapToList_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_LIST_1_JSON);

    // Act
    var result =
        TestGenericMapper.mapToList(this.objectMapper, this.mockMvcResult, TestObject1Dto.class);

    // Assert
    assertThat(result, is(TEST_OBJECTS_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_deserializers_WHEN_mapToList_THEN_return_deserialize_values() throws Exception {
    // Arrange
    var deserializers = new JsonDeserializer[]{new TestObject1Deserializer()};
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_LIST_1_JSON);

    // Act
    List<TestObject1Dto> result =
        TestGenericMapper.mapToList(
            this.objectMapper, this.mockMvcResult, TestObject1Dto.class, deserializers);

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
        () -> TestGenericMapper.mapToList(this.objectMapper, this.mockMvcResult, String.class));
  }

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  @SuppressWarnings("unchecked")
  void GIVEN_provideNullParameters_WHEN_mapToSet_THEN_throw_NullPointerException(
      ObjectMapper mapper, MvcResult result, Class<?> clazz) {

    assertThrows(
        NullPointerException.class, () -> TestGenericMapper.mapToSet(mapper, result, clazz));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_set_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Act
    var result =
        TestGenericMapper.mapToSet(this.objectMapper, this.mockMvcResult, TestObject1Dto.class);

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
        () -> TestGenericMapper.mapToSet(this.objectMapper, this.mockMvcResult, String.class));
  }

  @ParameterizedTest()
  @MethodSource("provideNullParametersMapToMap")
  @SuppressWarnings("unchecked")
  void GIVEN_provideNullParametersMapToMap_WHEN_mapToMap_THEN_throw_NullPointerException(
      ObjectMapper mapper, MvcResult result, Class<?> keyClass, Class<?> valueClass) {

    assertThrows(
        NullPointerException.class,
        () -> TestGenericMapper.mapToMap(mapper, result, keyClass, valueClass));
  }

  private static Stream<Arguments> provideNullParametersMapToMap() {
    return Stream.of(
        Arguments.of(null, mock(MvcResult.class), String.class, Integer.class),
        Arguments.of(new ObjectMapper(), null, String.class, Integer.class),
        Arguments.of(new ObjectMapper(), mock(MvcResult.class), null, Integer.class),
        Arguments.of(new ObjectMapper(), mock(MvcResult.class), String.class, null),
        Arguments.of(null, null, String.class, Integer.class),
        Arguments.of(null, mock(MvcResult.class), null, Integer.class),
        Arguments.of(new ObjectMapper(), null, null, Integer.class),
        Arguments.of(new ObjectMapper(), mock(MvcResult.class), null, null),
        Arguments.of(null, null, null, Integer.class),
        Arguments.of(null, mock(MvcResult.class), null, null),
        Arguments.of(new ObjectMapper(), null, null, null),
        Arguments.of(null, null, null, null));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_map_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_MAP_1_JSON);

    // Act
    var result =
        TestGenericMapper.mapToMap(
            this.objectMapper, this.mockMvcResult, Boolean.class, TestObject1Dto.class);

    // Assert
    assertThat(result, is(TEST_OBJECTS_MAP_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_deserializers_WHEN_mapToSet_THEN_return_deserialize_values() throws Exception {
    // Arrange
    var deserializers = new JsonDeserializer[]{new TestObject1Deserializer()};
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_MAP_1_JSON);

    // Act
    Map<Boolean, TestObject1Dto> result =
        TestGenericMapper.mapToMap(
            this.objectMapper,
            this.mockMvcResult,
            Boolean.class,
            TestObject1Dto.class,
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
        () ->
            TestGenericMapper.mapToMap(
                this.objectMapper, this.mockMvcResult, String.class, TestObject1Dto.class));
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
        () ->
            TestGenericMapper.mapToMap(
                this.objectMapper, this.mockMvcResult, Boolean.class, String.class));
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
        () ->
            TestGenericMapper.mapToMap(
                this.objectMapper, this.mockMvcResult, String.class, String.class));
  }
}
