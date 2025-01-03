package io.github.co_mmer.aaamockmvc.ej.test.web.mapper;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1;
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
  void GIVEN_expected_class_WHEN_mapTo_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_A1_JSON);

    // Act
    var result = TestGenericMapper.mapTo(this.objectMapper, this.mockMvcResult, TestObject1.class);

    // Assert
    assertThat(result, is(A1));
  }

  @Test
  void GIVEN_unexpected_expectedClass_WHEN_mapTo_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_A1_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> TestGenericMapper.mapTo(this.objectMapper, this.mockMvcResult, String.class));
  }

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  void GIVEN_provideNullParameters_WHEN_mapToList_THEN_throw_NullPointerException(
      ObjectMapper mapper, MvcResult result, Class<?> clazz) {

    assertThrows(
        NullPointerException.class, () -> TestGenericMapper.mapToList(mapper, result, clazz));
  }

  @Test
  void GIVEN_expected_list_WHEN_mapToList_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

    // Act
    var result =
        TestGenericMapper.mapToList(this.objectMapper, this.mockMvcResult, TestObject1.class);

    // Assert
    assertThat(result, is(TEST_LIST_A1_A2));
  }

  @Test
  void GIVEN_unexpected_expectedClass_WHEN_mapToList_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> TestGenericMapper.mapToList(this.objectMapper, this.mockMvcResult, String.class));
  }

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  void GIVEN_provideNullParameters_WHEN_mapToSet_THEN_throw_NullPointerException(
      ObjectMapper mapper, MvcResult result, Class<?> clazz) {

    assertThrows(
        NullPointerException.class, () -> TestGenericMapper.mapToSet(mapper, result, clazz));
  }

  @Test
  void GIVEN_expected_set_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

    // Act
    var result =
        TestGenericMapper.mapToSet(this.objectMapper, this.mockMvcResult, TestObject1.class);

    // Assert
    assertThat(result, is(TEST_SET_A1_A2));
  }

  @Test
  void GIVEN_unexpected_expectedClass_WHEN_mapToSet_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> TestGenericMapper.mapToSet(this.objectMapper, this.mockMvcResult, String.class));
  }

  @ParameterizedTest()
  @MethodSource("provideNullParametersMapToMap")
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
  void GIVEN_expected_map_WHEN_mapToSet_THEN_return_expect_object() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

    // Act
    var result =
        TestGenericMapper.mapToMap(
            this.objectMapper, this.mockMvcResult, Boolean.class, TestObject1.class);

    // Assert
    assertThat(result, is(TEST_MAP_A1_A2));
  }

  @Test
  void GIVEN_unexpected_keyClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () ->
            TestGenericMapper.mapToMap(
                this.objectMapper, this.mockMvcResult, String.class, TestObject1.class));
  }

  @Test
  void GIVEN_unexpected_valueClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () ->
            TestGenericMapper.mapToMap(
                this.objectMapper, this.mockMvcResult, Boolean.class, String.class));
  }

  @Test
  void GIVEN_unexpected_keyClass_valueClass_WHEN_mapToMap_THEN_throw_exception() throws Exception {
    // Arrange
    when(this.mockHttpServletResponse.getContentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () ->
            TestGenericMapper.mapToMap(
                this.objectMapper, this.mockMvcResult, String.class, String.class));
  }

  @ParameterizedTest()
  @MethodSource("provideNullMapToString")
  void GIVEN_provideNullMapToString_WHEN_mapToString_THEN_throw_NullPointerException(
      ObjectMapper mapper, Object object) {

    assertThrows(NullPointerException.class, () -> TestGenericMapper.mapToString(mapper, object));
  }

  private static Stream<Arguments> provideNullMapToString() {
    return Stream.of(
        Arguments.of(null, A1),
        Arguments.of(new ObjectMapper(), null),
        Arguments.of(null, null));
  }

  @Test
  void GIVEN_unexpected_WHEN_mapToString_THEN_throw_exception() throws Exception {
    // Arrange
    var mockObjectMapper = MockObjectMapper.throwOnWriteValueAsString();

    // Assert
    assertThrows(
        TestGenericMapperException.class,

        // Act
        () -> TestGenericMapper.mapToString(mockObjectMapper, A1));
  }

  @Test
  void GIVEN_object_WHEN_mapToString_THEN_return_expect_object() throws Exception {
    // Act
    var result = TestGenericMapper.mapToString(this.objectMapper, A1);

    // Assert
    assertThat(result, is(TEST_A1_JSON));
  }
}
