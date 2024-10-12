package ej.test.aaamockmvc.request.asserts.mapper;

import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_1_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECT_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECT_1_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ej.test.aaamockmvc.request.asserts.mapper.exception.TestAssertResultMapperException;
import ej.test.aaamockmvc.testdata.testutil.TestObjectDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;

class TestAssertResultMapperTest {

  private MvcResult mockMvcResult;
  private MockHttpServletResponse mockHttpServletResponse;

  @BeforeEach
  void setUp() {
    this.mockMvcResult = mock(MvcResult.class);
    this.mockHttpServletResponse = mock(MockHttpServletResponse.class);
    when(this.mockMvcResult.getResponse()).thenReturn(this.mockHttpServletResponse);
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_class_WHEN_mapTo_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECT_1_JSON);

    // Act
    var result = TestAssertResultMapper.mapTo(this.mockMvcResult, TestObjectDto.class);

    // Assert
    assertThat(result, is(TEST_OBJECT_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_expectedClass_WHEN_mapTo_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECT_1_JSON);

    // Assert
    assertThrows(
        TestAssertResultMapperException.class,

        // Act
        () -> TestAssertResultMapper.mapTo(this.mockMvcResult, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_list_WHEN_mapToList_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_1_JSON);

    // Act
    var result = TestAssertResultMapper.mapToList(this.mockMvcResult, TestObjectDto.class);

    // Assert
    assertThat(result, is(TEST_OBJECTS_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_expectedClass_WHEN_mapToList_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_1_JSON);

    // Assert
    assertThrows(
        TestAssertResultMapperException.class,

        // Act
        () -> TestAssertResultMapper.mapToList(this.mockMvcResult, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_set_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Act
    var result = TestAssertResultMapper.mapToSet(this.mockMvcResult, TestObjectDto.class);

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
        TestAssertResultMapperException.class,

        // Act
        () -> TestAssertResultMapper.mapToSet(this.mockMvcResult, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_map_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_MAP_1_JSON);

    // Act
    var result =
        TestAssertResultMapper.mapToMap(this.mockMvcResult, Boolean.class, TestObjectDto.class);

    // Assert
    assertThat(result, is(TEST_OBJECTS_MAP_1_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_keyClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Assert
    assertThrows(
        TestAssertResultMapperException.class,

        // Act
        () ->
            TestAssertResultMapper.mapToMap(this.mockMvcResult, String.class, TestObjectDto.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_valueClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Assert
    assertThrows(
        TestAssertResultMapperException.class,

        // Act
        () -> TestAssertResultMapper.mapToMap(this.mockMvcResult, Boolean.class, String.class));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_keyClass_valueClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_OBJECTS_SET_1_JSON);

    // Assert
    assertThrows(
        TestAssertResultMapperException.class,

        // Act
        () -> TestAssertResultMapper.mapToMap(this.mockMvcResult, String.class, String.class));
  }
}