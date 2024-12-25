package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestMockHttpServletResponse.mockGetContentAsStringException;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_1_3_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_1_3_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_3_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_1_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_MAP_2_DTO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_OBJECTS_MAP_2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1Dto;
import java.util.stream.Stream;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertCollectionImplTest {

  private ResultActions actions;
  private MockHttpServletResponse response;
  private TestAssertCollectionImpl testAssert;

  @BeforeEach
  void setUp() {
    this.actions = mock(ResultActions.class);
    var mvcResult = mock(MvcResult.class);
    this.response = new MockHttpServletResponse();

    when(mvcResult.getResponse()).thenReturn(this.response);
    when(this.actions.andReturn()).thenReturn(mvcResult);

    this.testAssert = new TestAssertCollectionImpl(this.actions, new ObjectMapper());
  }

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_provideNullParameters_WHEN_call_constructor_THEN_throw_NullPointerException(
      ResultActions actions, ObjectMapper objectMapper) {

    assertThrows(
        NullPointerException.class, () -> new TestAssertCollectionImpl(actions, objectMapper));
  }

  private static Stream<Arguments> provideNullParameters() {
    return Stream.of(
        Arguments.of(null, new ObjectMapper()),
        Arguments.of(mock(ResultActions.class), null),
        Arguments.of(null, null));
  }

  @Test
  void GIVEN_expected_WHEN_assertCollectionNotEmpty_THEN_assert_true() throws Exception {
    // Arrange
    mockResponse(TEST_LIST_1_JSON);

    // Act & Assert
    this.testAssert.assertCollectionNotEmpty();
  }

  private void mockResponse(String json) throws Exception {
    this.response.getWriter().write(json);
  }

  @ParameterizedTest
  @ValueSource(strings = {Strings.EMPTY, "[]"})
  void GIVEN_empty_WHEN_assertCollectionNotEmpty_THEN_assert_false(String value) throws Exception {
    // Arrange
    mockResponse(value);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertCollectionNotEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertCollectionNotEmpty_THEN_assert_false() throws Exception {
    // Arrange
    mockActionReturn(mockGetContentAsStringException());
    var testAssertCollection = new TestAssertCollectionImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, testAssertCollection::assertCollectionNotEmpty);
  }

  private void mockActionReturn(MvcResult mvcResult) {
    when(this.actions.andReturn()).thenReturn(mvcResult);
  }

  @ParameterizedTest
  @ValueSource(strings = {Strings.EMPTY, "[]"})
  void GIVEN_expected_WHEN_assertCollectionEmpty_THEN_assert_true(String value) throws Exception {
    // Arrange
    mockResponse(value);

    // Act & Assert
    this.testAssert.assertCollectionEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertCollectionEmpty_THEN_return_assert_false() throws Exception {
    // Arrange
    mockResponse(TEST_LIST_1_JSON);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertCollectionEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertCollectionEmpty_THEN_assert_false() throws Exception {
    // Arrange
    mockActionReturn(mockGetContentAsStringException());
    var testAssertCollection = new TestAssertCollectionImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, testAssertCollection::assertCollectionEmpty);
  }

  @Test
  void GIVEN_expected_list_WHEN_assertCollectionEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    mockResponse(TEST_LIST_1_JSON);

    // Act & Assert
    this.testAssert.assertCollectionEquals(TestObject1Dto.class, TEST_LIST_1_DTO);
  }

  @Test
  void GIVEN_unexpected_list_WHEN_assertCollectionEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    mockResponse(TEST_LIST_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () -> this.testAssert.assertCollectionEquals(TestObject1Dto.class, TEST_LIST_1_3_DTO));
  }

  @Test
  void GIVEN_exception_list_WHEN_assertCollectionEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestGenericMapper = MockTestGenericMapper.mapToListThrowException();

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () -> this.testAssert.assertCollectionEquals(TestObject1Dto.class, TEST_LIST_1_DTO));

    mockTestGenericMapper.close();
  }

  @Test
  void GIVEN_list_WHEN_assertCollectionEquals_THEN_normalizeCollection_is_called()
      throws Exception {

    // Arrange
    var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
    mockResponse(TEST_LIST_1_JSON);

    // Act
    this.testAssert.assertCollectionEquals(TestObject1Dto.class, TEST_LIST_1_DTO);

    // Assert
    mockTestArrangeNormalizer.verify(() -> normalizeCollection(any()), times(2));
    mockTestArrangeNormalizer.close();
  }

  @Test
  void GIVEN_expected_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_assert_is_true()
      throws Exception {

    // Arrange
    mockResponse(TEST_LIST_1_3_JSON);

    // Act & Assert
    this.testAssert.assertCollectionEqualsIgnoreOrder(TestObject1Dto.class, TEST_LIST_3_1_DTO);
  }

  @Test
  void GIVEN_unexpected_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_assert_is_false()
      throws Exception {

    // Arrange
    mockResponse(TEST_LIST_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () ->
            this.testAssert.assertCollectionEqualsIgnoreOrder(
                TestObject1Dto.class, TEST_LIST_1_3_DTO));
  }

  @Test
  void GIVEN_exception_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_assert_is_false() {
    // Arrange
    var mockTestGenericMapper = MockTestGenericMapper.mapToListThrowException();

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () ->
            this.testAssert.assertCollectionEqualsIgnoreOrder(
                TestObject1Dto.class, TEST_LIST_1_DTO));

    mockTestGenericMapper.close();
  }

  @Test
  void GIVEN_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_normalizeCollection_is_called()
      throws Exception {

    // Arrange
    var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
    mockResponse(TEST_LIST_1_JSON);

    // Act
    this.testAssert.assertCollectionEqualsIgnoreOrder(TestObject1Dto.class, TEST_LIST_1_DTO);

    // Assert
    mockTestArrangeNormalizer.verify(() -> normalizeCollection(any()), times(2));
    mockTestArrangeNormalizer.close();
  }

  //
  @Test
  void GIVEN_expected_map_WHEN_assertCollectionEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    mockResponse(TEST_MAP_1_JSON);

    // Act & Assert
    this.testAssert.assertCollectionEquals(Boolean.class, TestObject1Dto.class, TEST_MAP_1_DTO);
  }

  @Test
  void GIVEN_unexpected_map_WHEN_assertCollectionEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    mockResponse(TEST_SET_1_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () ->
            this.testAssert.assertCollectionEquals(
                Boolean.class, TestObject1Dto.class, TEST_OBJECTS_MAP_2_DTO));
  }

  @Test
  void GIVEN_exception_map_WHEN_assertCollectionEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestGenericMapper = MockTestGenericMapper.mapToMapThrowException();

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () ->
            this.testAssert.assertCollectionEquals(
                Boolean.class, TestObject1Dto.class, TEST_MAP_1_DTO));

    mockTestGenericMapper.close();
  }

  @Test
  void GIVEN_map_WHEN_assertCollectionEquals_THEN_normalizeMap_is_called() throws Exception {
    // Arrange
    var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
    mockResponse(TEST_MAP_1_JSON);

    // Act
    this.testAssert.assertCollectionEquals(Boolean.class, TestObject1Dto.class, TEST_MAP_1_DTO);

    // Assert
    mockTestArrangeNormalizer.verify(() -> normalizeMap(any()), times(2));
    mockTestArrangeNormalizer.close();
  }

  @Test
  void GIVEN_expected_WHEN_assertCollectionSize_THEN_assert_is_true() throws Exception {
    // Arrange
    mockResponse(TEST_OBJECTS_MAP_2_JSON);

    // Act & Assert
    this.testAssert.assertCollectionSize(2);
  }

  @Test
  void GIVEN_unexpected_WHEN_assertCollectionSize_THEN_assert_is_false() throws Exception {
    // Arrange
    mockActionThrow(new AssertionError());
    var testAssertCollection = new TestAssertCollectionImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, () -> testAssertCollection.assertCollectionSize(1));
  }

  private void mockActionThrow(Throwable throwable) throws Exception {
    when(this.actions.andExpect(any())).thenThrow(throwable);
  }

  @Test
  void GIVEN_exception_WHEN_assertCollectionSize_THEN_assert_is_false() throws Exception {
    // Arrange
    mockActionThrow(new Exception());
    var testAssertCollection = new TestAssertCollectionImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionFailedError.class, () -> testAssertCollection.assertCollectionSize(1));
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
