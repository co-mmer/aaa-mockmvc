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
  class assertMapNotEmpty {

    @Test
    void GIVEN_expected_WHEN_assertMapNotEmpty_THEN_assert_true() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert.assertMapNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_OBJECT})
    void GIVEN_empty_WHEN_assertMapNotEmpty_THEN_assert_false(String value) throws Exception {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertMapNotEmpty);
    }

    @Test
    void GIVEN_exception_WHEN_assertMapNotEmpty_THEN_assert_false() throws Exception {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertMapNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertMapNotEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertMapNotEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertMapEmpty {

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_OBJECT})
    void GIVEN_expected_WHEN_assertMapEmpty_THEN_assert_true(String value) throws Exception {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      testAssert.assertMapEmpty();
    }

    @Test
    void GIVEN_unexpected_WHEN_assertMapEmpty_THEN_return_assert_false() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertMapEmpty);
    }

    @Test
    void GIVEN_exception_WHEN_assertMapEmpty_THEN_assert_false() throws Exception {
      // Arrange
      useServerWithStringException();
      var testAssertMap = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertMap::assertMapEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertMapEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertMapEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertMapEquals {

    @Test
    void GIVEN_expected_map_WHEN_assertMapEquals_THEN_assert_is_true() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert.assertMapEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);
    }

    @Test
    void GIVEN_unexpected_map_WHEN_assertMapEquals_THEN_assert_is_false() throws Exception {
      // Arrange
      useServerWithResponse(TEST_SET_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssert.assertMapEquals(Boolean.class, TestObjectSimple.class, TEST_MAP_A1_A3));
    }

    @Test
    void GIVEN_exception_map_WHEN_assertMapEquals_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToMapThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssert.assertMapEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertMapEquals_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertMapEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    void GIVEN_map_WHEN_assertMapEquals_THEN_normalizeMap_is_called() throws Exception {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act
      testAssert.assertMapEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeMap(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_as_expectedMap_WHEN_assertMapEquals_THEN_throw_NullPointerException(
        Class<Integer> keyClass,
        Class<TestObjectSimple> valueClass,
        Map<Integer, TestObjectSimple> expectedMap) {

      // Arrange
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssertException.assertMapEquals(keyClass, valueClass, expectedMap));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(Integer.class, TestObjectSimple.class, null),
          Arguments.of(Integer.class, null, TEST_MAP_A1_A2),
          Arguments.of(null, TestObjectSimple.class, TEST_MAP_A1_A2));
    }
  }

  @Nested
  class assertMapSize {

    @Test
    void GIVEN_expected_WHEN_assertMapSize_THEN_assert_is_true() throws Exception {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A3_JSON);

      // Act & Assert
      testAssert.assertMapSize(2);
    }

    @Test
    void GIVEN_unexpected_WHEN_assertMapSize_THEN_assert_is_false() throws Exception {
      // Arrange
      useResultAssertionError();
      var testAssertMap = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertMap.assertMapSize(1));
    }

    @Test
    void GIVEN_exception_WHEN_assertMapSize_THEN_assert_is_false() throws Exception {
      // Arrange
      useResultException();
      var testAssertMap = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionFailedError.class, () -> testAssertMap.assertMapSize(1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertMapSize_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useResultException();
      var testAssertException = new TestAssertMapImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertMapSize(1);

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
    void not_empty__equals() {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert
          .assertMapNotEmpty()
          .assertMapEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);
    }
  }

  @Nested
  class combinationEmpty {

    @Test
    @SneakyThrows
    void empty__head() {
      // Arrange
      useServerWithResponse(TEST_MAP_EMPTY_JSON);

      // Act & Assert
      testAssert.assertMapEmpty().assertHead();
    }
  }

  @Nested
  class combinationSize {

    @Test
    @SneakyThrows
    void size__equals() {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert
          .assertMapSize(2)
          .assertMapEquals(Integer.class, TestObjectSimple.class, TEST_MAP_A1_A2);
    }

    @Test
    @SneakyThrows
    void size__head() {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert.assertMapSize(2).assertHead();
    }
  }
}
