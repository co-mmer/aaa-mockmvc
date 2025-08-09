package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY_OBJECT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A3_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_EMPTY_JSON;
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
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.Map;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertMapImplTest extends TestAssertBase {

  private TestAssertMapImpl testAssert;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssert = new TestAssertMapImpl(this.actions, new ObjectMapper());
  }

  @Nested
  class callConstructor {

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
  }

  @Nested
  class assertContentNotEmpty {

    @Test
    void GIVEN_expected_WHEN_assertContentNotEmpty_THEN_assert_true() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert.assertContentNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_OBJECT})
    void GIVEN_empty_WHEN_assertContentNotEmpty_THEN_assert_false(String value) throws Exception {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertContentNotEmpty);
    }

    @Test
    void GIVEN_exception_WHEN_assertContentNotEmpty_THEN_assert_false() throws Exception {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertContentNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentNotEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentNotEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertContentEmpty {

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_OBJECT})
    void GIVEN_expected_WHEN_assertContentEmpty_THEN_assert_true(String value) throws Exception {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      testAssert.assertContentEmpty();
    }

    @Test
    void GIVEN_unexpected_WHEN_assertContentEmpty_THEN_return_assert_false() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertContentEmpty);
    }

    @Test
    void GIVEN_exception_WHEN_assertContentEmpty_THEN_assert_false() throws Exception {
      // Arrange
      useServerWithStringException();
      var testAssertMap = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertMap::assertContentEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertContentEquals {

    @Test
    void GIVEN_expected_map_WHEN_assertContentEquals_THEN_assert_is_true() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert.assertContentEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);
    }

    @Test
    void GIVEN_unexpected_map_WHEN_assertContentEquals_THEN_assert_is_false() throws Exception {
      // Arrange
      useServerWithResponse(TEST_SET_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssert.assertContentEquals(
                  Boolean.class, TestObjectSimple.class, TEST_MAP_A1_A3));
    }

    @Test
    void GIVEN_exception_map_WHEN_assertContentEquals_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToMapThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssert.assertContentEquals(
                  Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEquals_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentEquals(
          Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    void GIVEN_map_WHEN_assertContentEquals_THEN_normalizeMap_is_called() throws Exception {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act
      testAssert.assertContentEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeMap(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_as_expectedMap_WHEN_assertContentEquals_THEN_throw_NullPointerException(
        Class<Integer> keyClass,
        Class<TestObjectSimple> valueClass,
        Map<Integer, TestObjectSimple> expectedMap) {

      // Arrange
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssertException.assertContentEquals(keyClass, valueClass, expectedMap));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(Integer.class, TestObjectSimple.class, null),
          Arguments.of(Integer.class, null, TEST_MAP_A1_A2),
          Arguments.of(null, TestObjectSimple.class, TEST_MAP_A1_A2));
    }
  }

  @Nested
  class assertContentSize {

    @Test
    void GIVEN_expected_WHEN_assertContentSize_THEN_assert_is_true() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A3_JSON);

      // Act & Assert
      testAssert.assertContentSize(2);
    }

    @Test
    void GIVEN_unexpected_WHEN_assertContentSize_THEN_assert_is_false() throws Exception {
      // Arrange
      useResultAssertionError();
      var testAssertMap = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertMap.assertContentSize(1));
    }

    @Test
    void GIVEN_exception_WHEN_assertContentSize_THEN_assert_is_false() throws Exception {
      // Arrange
      useResultException();
      var testAssertMap = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionFailedError.class, () -> testAssertMap.assertContentSize(1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentSize_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useResultException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentSize(1);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_assertHead_THEN_return_expected_class() {
      // Arrange
      useHeader();

      // Act
      var assertHead = testAssert.assertHead();

      // Assert
      assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
    }
  }

  @Nested
  class combinationNotEmpty {

    @Test
    @SneakyThrows
    void notEmpty_equals() {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert
          .assertContentNotEmpty()
          .assertContentEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);
    }
  }

  @Nested
  class combinationEmpty {

    @Test
    @SneakyThrows
    void empty_head() {
      // Arrange
      useServerWithResponse(TEST_MAP_EMPTY_JSON);

      // Act & Assert
      testAssert.assertContentEmpty().assertHead();
    }
  }

  @Nested
  class combinationSize {

    @Test
    @SneakyThrows
    void size_equals() {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert
          .assertContentSize(2)
          .assertContentEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);
    }

    @Test
    @SneakyThrows
    void size_head() {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert.assertContentSize(2).assertHead();
    }
  }
}
