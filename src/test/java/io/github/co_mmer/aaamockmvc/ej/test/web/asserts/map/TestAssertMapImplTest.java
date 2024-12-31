package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A3_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectA;
import java.util.stream.Stream;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertMapImplTest extends TestAssertBase {

  private TestAssertMapImpl testAssert;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssert = new TestAssertMapImpl(this.actions, new ObjectMapper());
  }

  @ParameterizedTest()
  @MethodSource("provideNullParameters")
  @SuppressWarnings("ConstantConditions")
  void GIVEN_provideNullParameters_WHEN_call_constructor_THEN_throw_NullPointerException(
      ResultActions actions, ObjectMapper objectMapper) {

    assertThrows(NullPointerException.class, () -> new TestAssertMapImpl(actions, objectMapper));
  }

  private static Stream<Arguments> provideNullParameters() {
    return Stream.of(
        Arguments.of(null, new ObjectMapper()),
        Arguments.of(mock(ResultActions.class), null),
        Arguments.of(null, null));
  }

  @Test
  void GIVEN_expected_WHEN_assertMapNotEmpty_THEN_assert_true() throws Exception {
    // Arrange
    useServerWithResponse(TEST_MAP_A1_A2_JSON);

    // Act & Assert
    this.testAssert.assertMapNotEmpty();
  }

  @ParameterizedTest
  @ValueSource(strings = {Strings.EMPTY, "{}"})
  void GIVEN_empty_WHEN_assertMapNotEmpty_THEN_assert_false(String value) throws Exception {
    // Arrange
    useServerWithResponse(value);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertMapNotEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertMapNotEmpty_THEN_assert_false() throws Exception {
    // Arrange
    useServerWithStringException();
    var testAssertMap = new TestAssertMapImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, testAssertMap::assertMapNotEmpty);
  }

  @ParameterizedTest
  @ValueSource(strings = {Strings.EMPTY, "{}"})
  void GIVEN_expected_WHEN_assertMapEmpty_THEN_assert_true(String value) throws Exception {
    // Arrange
    useServerWithResponse(value);

    // Act & Assert
    this.testAssert.assertMapEmpty();
  }

  @Test
  void GIVEN_unexpected_WHEN_assertMapEmpty_THEN_return_assert_false() throws Exception {
    // Arrange
    useServerWithResponse(TEST_MAP_A1_A2_JSON);

    // Act & Assert
    assertThrows(AssertionError.class, this.testAssert::assertMapEmpty);
  }

  @Test
  void GIVEN_exception_WHEN_assertMapEmpty_THEN_assert_false() throws Exception {
    // Arrange
    useServerWithStringException();
    var testAssertMap = new TestAssertMapImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, testAssertMap::assertMapEmpty);
  }

  @Test
  void GIVEN_expected_map_WHEN_assertMapEquals_THEN_assert_is_true() throws Exception {
    // Arrange
    useServerWithResponse(TEST_MAP_A1_A2_JSON);

    // Act & Assert
    this.testAssert.assertMapEquals(Boolean.class, TestObjectA.class, TEST_MAP_A1_A2);
  }

  @Test
  void GIVEN_unexpected_map_WHEN_assertMapEquals_THEN_assert_is_false() throws Exception {
    // Arrange
    useServerWithResponse(TEST_SET_A1_A2_JSON);

    // Act & Assert
    assertThrows(
        AssertionError.class,
        () ->
            this.testAssert.assertMapEquals(
                Boolean.class, TestObjectA.class, TEST_MAP_A1_A3));
  }

  @Test
  void GIVEN_exception_map_WHEN_assertMapEquals_THEN_assert_is_false() {
    // Arrange
    var mockTestGenericMapper = MockTestGenericMapper.mapToMapThrowException();

    // Act & Assert
    assertThrows(
        AssertionFailedError.class,
        () -> this.testAssert.assertMapEquals(Boolean.class, TestObjectA.class, TEST_MAP_A1_A2));

    mockTestGenericMapper.close();
  }

  @Test
  void GIVEN_map_WHEN_assertMapEquals_THEN_normalizeMap_is_called() throws Exception {
    // Arrange
    var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
    useServerWithResponse(TEST_MAP_A1_A2_JSON);

    // Act
    this.testAssert.assertMapEquals(Boolean.class, TestObjectA.class, TEST_MAP_A1_A2);

    // Assert
    mockTestArrangeNormalizer.verify(() -> normalizeMap(any()), times(2));
    mockTestArrangeNormalizer.close();
  }

  @Test
  void GIVEN_expected_WHEN_assertMapSize_THEN_assert_is_true() throws Exception {
    // Arrange
    useServerWithResponse(TEST_MAP_A1_A3_JSON);

    // Act & Assert
    this.testAssert.assertMapSize(2);
  }

  @Test
  void GIVEN_unexpected_WHEN_assertMapSize_THEN_assert_is_false() throws Exception {
    // Arrange
    useResultAssertionError();
    var testAssertMap = new TestAssertMapImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionError.class, () -> testAssertMap.assertMapSize(1));
  }

  @Test
  void GIVEN_exception_WHEN_assertMapSize_THEN_assert_is_false() throws Exception {
    // Arrange
    useResultException();
    var testAssertMap = new TestAssertMapImpl(this.actions, new ObjectMapper());

    // Act & Assert
    assertThrows(AssertionFailedError.class, () -> testAssertMap.assertMapSize(1));
  }

  @Test
  void WHEN_assertHead_THEN_return_expected_class() {
    // Arrange
    useHeader();

    // Act
    var assertHead = this.testAssert.assertHead();

    // Assert
    assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
  }
}
