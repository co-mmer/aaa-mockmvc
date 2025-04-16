package io.github.co_mmer.aaamockmvc.ej.test.web.mapper;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockObjectMapper.throwOnWriteValueAsString;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
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

  @Nested
  class mapTo {

    @Test
    @SneakyThrows
    void GIVEN_expected_class_WHEN_mapTo_THEN_return_expect_object() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var result = TestGenericMapper.mapTo(objectMapper, mockMvcResult, TestObjectSimple.class);

      // Assert
      assertThat(result, is(A1));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_empty_string_WHEN_mapTo_THEN_return_expect_null(String response) {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(response);

      // Act
      var result = TestGenericMapper.mapTo(objectMapper, mockMvcResult, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_expectedClass_WHEN_mapTo_THEN_throw_exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_A1_JSON);

      // Assert
      assertThrows(
          TestGenericMapperException.class,

          // Act
          () -> TestGenericMapper.mapTo(objectMapper, mockMvcResult, String.class));
    }
  }

  @Nested
  class mapToList {

    @Test
    @SneakyThrows
    void GIVEN_expected_list_WHEN_mapToList_THEN_return_expect_object() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var result = TestGenericMapper.mapToList(objectMapper, mockMvcResult, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_response_null_WHEN_mapTo_THEN_return_expect_null(String response) {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(response);

      // Act
      var result = TestGenericMapper.mapToList(objectMapper, mockMvcResult, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_expectedClass_WHEN_mapToList_THEN_throw_exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Assert
      assertThrows(
          TestGenericMapperException.class,

          // Act
          () -> TestGenericMapper.mapToList(objectMapper, mockMvcResult, String.class));
    }
  }

  @Nested
  class mapToSet {

    @Test
    @SneakyThrows
    void GIVEN_expected_set_WHEN_mapToSet_THEN_return_expect_object() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Act
      var result = TestGenericMapper.mapToSet(objectMapper, mockMvcResult, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_SET_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_response_null_WHEN_mapTo_THEN_return_expect_null(String response) {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(response);

      // Act
      var result = TestGenericMapper.mapToSet(objectMapper, mockMvcResult, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_expectedClass_WHEN_mapToSet_THEN_throw_exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Assert
      assertThrows(
          TestGenericMapperException.class,

          // Act
          () -> TestGenericMapper.mapToSet(objectMapper, mockMvcResult, String.class));
    }
  }

  @Nested
  class mapToMap {

    @Test
    @SneakyThrows
    void GIVEN_expected_map_WHEN_mapToSet_THEN_return_expect_object() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      var result =
          TestGenericMapper.mapToMap(
              objectMapper, mockMvcResult, Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(TEST_MAP_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_response_null_WHEN_mapToMap_THEN_return_expect_null(String response) {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(response);

      // Act
      var result =
          TestGenericMapper.mapToMap(
              objectMapper, mockMvcResult, Integer.class, TestObjectSimple.class);

      // Assert
      assertThat(result, is(nullValue()));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_keyClass_WHEN_mapToMap_THEN_throw_exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Assert
      assertThrows(
          TestGenericMapperException.class,

          // Act
          () ->
              TestGenericMapper.mapToMap(
                  objectMapper, mockMvcResult, String.class, TestObjectSimple.class));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_valueClass_WHEN_mapToMap_THEN_throw_exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Assert
      assertThrows(
          TestGenericMapperException.class,

          // Act
          () ->
              TestGenericMapper.mapToMap(objectMapper, mockMvcResult, Boolean.class, String.class));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_keyClass_valueClass_WHEN_mapToMap_THEN_throw_exception() {
      // Arrange
      when(mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Assert
      assertThrows(
          TestGenericMapperException.class,

          // Act
          () ->
              TestGenericMapper.mapToMap(objectMapper, mockMvcResult, String.class, String.class));
    }
  }

  @Nested
  class mapToString {

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_mapToString_THEN_throw_exception() {
      // Arrange
      var mockObjectMapper = throwOnWriteValueAsString();

      // Assert
      assertThrows(
          TestGenericMapperException.class,

          // Act
          () -> TestGenericMapper.mapToString(mockObjectMapper, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_mapToString_THEN_return_expect_object() {
      // Act
      var result = TestGenericMapper.mapToString(objectMapper, A1);

      // Assert
      assertThat(result, is(TEST_A1_JSON));
    }
  }
}
