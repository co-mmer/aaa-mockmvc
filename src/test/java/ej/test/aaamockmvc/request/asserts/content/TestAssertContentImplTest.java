package ej.test.aaamockmvc.request.asserts.content;

import static ej.test.aaamockmvc.testdata.testutil.TestMockHttpServletResponse.mockGetContentAsSByteException;
import static ej.test.aaamockmvc.testdata.testutil.TestMockHttpServletResponse.mockGetContentAsStringException;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_2_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_LIST_1_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_1_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_2_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_MAP_2_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_SET_1_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECTS_SET_2_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECT_1_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECT_1_JSON;
import static ej.test.aaamockmvc.testdata.testutil.TestObject.TEST_OBJECT_2_DTO;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static ej.test.aaamockmvc.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.test.aaamockmvc.request.asserts.head.TestAssertHeadImpl;
import ej.test.aaamockmvc.request.asserts.mapper.TestAssertResultMapper;
import ej.test.aaamockmvc.request.asserts.mapper.exception.TestAssertResultMapperException;
import ej.test.aaamockmvc.testdata.testutil.TestObjectDto;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertContentImplTest {

  private static final String EXPECTED_CONTENT = "expected content";
  private static final String ACTUAL_CONTENT = "actual content";
  private ResultActions actions;
  private MockHttpServletResponse response;
  private TestAssertContent testAssert;

  @BeforeEach
  void setUp() {
    this.actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(this.response);
    when(this.actions.andReturn()).thenReturn(mvcResult);

    this.testAssert = new TestAssertContentImpl(this.actions, new ObjectMapper());
  }

  @Test
  void GIVEN_expected_WHEN_assertContentStringIsNotEmpty_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(EXPECTED_CONTENT);

    // Act & Assert
    this.testAssert.assertContentStringIsNotEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertContentStringIsNotEmpty_THEN_assert_false() throws Exception {
    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertContentStringIsNotEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertContentStringIsNotEmpty_THEN_assert_false() throws Exception {
    // Arrange
    var mockMvcResult = mockGetContentAsStringException();
    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, testAssertContent::assertContentStringIsNotEmpty);
  }

  @Test
  void GIVEN_expected_WHEN_assertContentStringIsEmpty_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    this.testAssert.assertContentStringIsEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertContentStringIsEmpty_THEN_return_assert_false()
      throws Exception {

    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertContentStringIsEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertContentStringIsEmpty_THEN_assert_false() throws Exception {
    // Arrange
    var mockMvcResult = mockGetContentAsStringException();
    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, testAssertContent::assertContentStringIsEmpty);
  }

  @Test
  void GIVEN_expected_WHEN_assertContentEquals_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(EXPECTED_CONTENT);

    // Act & Assert
    this.testAssert.assertContentEquals(EXPECTED_CONTENT);
  }

  @Test
  void GIVEN_unexpected_WHEN_assertContentEquals_THEN_assert_false() throws Exception {
    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert.assertContentEquals(EXPECTED_CONTENT));
  }

  @Test
  void GIVEN_exception_WHEN_assertContentEquals_THEN_assert_false() throws Exception {
    // Arrange
    var mockMvcResult = mockGetContentAsStringException();
    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(
        AssertionFailedError.class, () -> testAssertContent.assertContentEquals(EXPECTED_CONTENT));
  }

  @Test
  void GIVEN_expected_WHEN_assertContentByteIsNotEmpty_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    this.testAssert.assertContentByteIsNotEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertContentByteIsNotEmpty_THEN_assert_false() throws Exception {
    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertContentByteIsNotEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertContentByteIsNotEmpty_THEN_assert_false() {
    // Arrange
    var mockMvcResult = mockGetContentAsSByteException();

    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionFailedError.class, testAssertContent::assertContentByteIsNotEmpty);
  }

  @Test
  void GIVEN_expected_WHEN_assertContentByteIsEmpty_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(Strings.EMPTY);

    // Act & Assert
    this.testAssert.assertContentByteIsEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertContentByteIsEmpty_THEN_return_assert_false() throws Exception {
    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertContentByteIsEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertContentByteIsEmpty_THEN_assert_false() {
    // Arrange
    var mockMvcResult = mockGetContentAsSByteException();

    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionFailedError.class, testAssertContent::assertContentByteIsEmpty);
  }

  @Test
  void GIVEN_expected_byte_WHEN_assertContentEquals_THEN_assert_true() throws Exception {
    // Arrange
    this.response.getWriter().write(EXPECTED_CONTENT);

    // Act & Assert
    this.testAssert.assertContentEquals(EXPECTED_CONTENT.getBytes());
  }

  @Test
  void GIVEN_unexpected_byte_WHEN_assertContentEquals_THEN_assert_false() throws Exception {
    // Arrange
    this.response.getWriter().write(ACTUAL_CONTENT);

    // Act & Assert
    assertThrows(AssertionError.class, () -> this.testAssert.assertContentEquals(new byte[1]));
  }

  @Test
  void GIVEN_exception_byte_WHEN_assertContentEquals_THEN_assert_false() {
    // Arrange
    var mockMvcResult = mockGetContentAsSByteException();
    when(this.actions.andReturn()).thenReturn(mockMvcResult);
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(
        AssertionFailedError.class, () -> testAssertContent.assertContentEquals(new byte[1]));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_object_WHEN_assertContentEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECT_1_JSON);

    // Act & Assert
    this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECT_1_DTO);
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_object_WHEN_assertContentEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECT_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () -> this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECT_2_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_exception_object_WHEN_assertContentEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestAssertResultMapper = mockStatic(TestAssertResultMapper.class);
    mockTestAssertResultMapper
        .when(() -> TestAssertResultMapper.mapTo(any(), any(), any()))
        .thenThrow(new TestAssertResultMapperException(new Throwable("error")));

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () -> this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECT_1_DTO));

    mockTestAssertResultMapper.close();
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_list_WHEN_assertContentEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECTS_LIST_1_JSON);

    // Act & Assert
    this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECTS_1_DTO);
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_list_WHEN_assertContentEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECTS_LIST_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () -> this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECTS_2_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_exception_list_WHEN_assertContentEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestAssertResultMapper = mockStatic(TestAssertResultMapper.class);
    mockTestAssertResultMapper
        .when(() -> TestAssertResultMapper.mapToList(any(), any(), any()))
        .thenThrow(new TestAssertResultMapperException(new Throwable("error")));

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () -> this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECTS_1_DTO));

    mockTestAssertResultMapper.close();
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_set_WHEN_assertContentEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECTS_SET_1_JSON);

    // Act & Assert
    this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECTS_SET_1_DTO);
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_set_WHEN_assertContentEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECTS_SET_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () -> this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECTS_SET_2_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_exception_set_WHEN_assertContentEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestAssertResultMapper = mockStatic(TestAssertResultMapper.class);
    mockTestAssertResultMapper
        .when(() -> TestAssertResultMapper.mapToSet(any(), any(), any()))
        .thenThrow(new TestAssertResultMapperException(new Throwable("error")));

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () -> this.testAssert.assertContentEquals(TestObjectDto.class, TEST_OBJECTS_SET_1_DTO));

    mockTestAssertResultMapper.close();
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_expected_map_WHEN_assertContentEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECTS_MAP_1_JSON);

    // Act & Assert
    this.testAssert.assertContentEquals(Boolean.class, TestObjectDto.class, TEST_OBJECTS_MAP_1_DTO);
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_unexpected_map_WHEN_assertContentEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECTS_SET_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () ->
            this.testAssert.assertContentEquals(
                Boolean.class, TestObjectDto.class, TEST_OBJECTS_MAP_2_DTO));
  }

  @Test
  @SuppressWarnings("unchecked")
  void GIVEN_exception_map_WHEN_assertContentEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestAssertResultMapper = mockStatic(TestAssertResultMapper.class);
    mockTestAssertResultMapper
        .when(() -> TestAssertResultMapper.mapToMap(any(), any(), any(), any()))
        .thenThrow(new TestAssertResultMapperException(new Throwable("error")));

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () ->
            this.testAssert.assertContentEquals(
                Boolean.class, TestObjectDto.class, TEST_OBJECTS_MAP_1_DTO));

    mockTestAssertResultMapper.close();
  }

  @Test
  void GIVEN_expected_WHEN_assertContentSize_THEN_assert_is_true() throws Exception {
    // Arrange
    this.response.getWriter().write(TEST_OBJECTS_MAP_2_JSON);

    // Act & Assert
    this.testAssert.assertContentSize(2);
  }

  @Test
  void GIVEN_unexpected_WHEN_assertContentSize_THEN_assert_is_false() throws Exception {
    // Arrange
    when(this.actions.andExpect(any())).thenThrow(new AssertionError());
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, () -> testAssertContent.assertContentSize(1));
  }

  @Test
  void GIVEN_exception_WHEN_assertContentSize_THEN_assert_is_false() throws Exception {
    // Arrange
    when(this.actions.andExpect(any())).thenThrow(new Exception());
    var testAssertContent = new TestAssertContentImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionFailedError.class, () -> testAssertContent.assertContentSize(1));
  }

  @Test
  void WHEN_assertHead_THEN_return_expected_class() {
    // Arrange
    this.response.setHeader(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

    // Act
    var assertHead = this.testAssert.assertHead();

    // Assert
    assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
  }
}
