package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A3_JSON;
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
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertCollectionImplTest extends TestAssertBase {

  private TestAssertCollectionImpl testAssert;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssert = new TestAssertCollectionImpl(this.actions, new ObjectMapper());
  }

  @Nested
  class callConstructor {

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
  }

  @Nested
  class assertCollectionNotEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionNotEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssert.assertCollectionNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {Strings.EMPTY, "[]"})
    @SneakyThrows
    void GIVEN_empty_WHEN_assertCollectionNotEmpty_THEN_assert_false(String value) {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertCollectionNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertCollection::assertCollectionNotEmpty);
    }
  }

  @Nested
  class assertCollectionEmpty {

    @ParameterizedTest
    @ValueSource(strings = {Strings.EMPTY, "[]"})
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionEmpty_THEN_assert_true(String value) {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      testAssert.assertCollectionEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertCollectionEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertCollection::assertCollectionEmpty);
    }
  }

  @Nested
  class assertCollectionSize {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionSize_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_MAP_A1_A3_JSON);

      // Act & Assert
      testAssert.assertCollectionSize(2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionSize_THEN_assert_is_false() {
      // Arrange
      useResultAssertionError();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertCollection.assertCollectionSize(1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionSize_THEN_assert_is_false() {
      // Arrange
      useResultException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionFailedError.class, () -> testAssertCollection.assertCollectionSize(1));
    }
  }

  @Nested
  class assertCollectionEquals {

    @Test
    @SneakyThrows
    void GIVEN_expected_list_WHEN_assertCollectionEquals_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssert.assertCollectionEquals(TestObjectA.class, TEST_LIST_A1_A2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_list_WHEN_assertCollectionEquals_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssert.assertCollectionEquals(TestObjectA.class, TEST_LIST_A1_A3));
    }

    @Test
    void GIVEN_exception_list_WHEN_assertCollectionEquals_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToListThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssert.assertCollectionEquals(TestObjectA.class, TEST_LIST_A1_A2));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_list_WHEN_assertCollectionEquals_THEN_normalizeCollection_is_called() {

      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act
      testAssert.assertCollectionEquals(TestObjectA.class, TEST_LIST_A1_A2);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeCollection(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class assertCollectionEqualsIgnoreOrder {

    @Test
    @SneakyThrows
    void GIVEN_expected_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_assert_is_true() {

      // Arrange
      useServerWithResponse(TEST_LIST_A1_A3_JSON);

      // Act & Assert
      testAssert.assertCollectionEqualsIgnoreOrder(TestObjectA.class, TEST_LIST_A3_A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssert.assertCollectionEqualsIgnoreOrder(
                  TestObjectA.class, TEST_LIST_A1_A3));
    }

    @Test
    void GIVEN_exception_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToListThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssert.assertCollectionEqualsIgnoreOrder(
                  TestObjectA.class, TEST_LIST_A1_A2));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_list_WHEN_assertCollectionEqualsIgnoreOrder_THEN_normalizeCollection_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act
      testAssert.assertCollectionEqualsIgnoreOrder(TestObjectA.class, TEST_LIST_A1_A2);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeCollection(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class assertCollectionContains {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionContains_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssert.assertCollectionContains(TestObjectA.class, TEST_LIST_A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionContains_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssert.assertCollectionContains(TestObjectA.class, TEST_LIST_A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionContains_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssertCollection.assertCollectionContains(TestObjectA.class, TEST_LIST_A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionContains_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssert.assertCollectionContains(TestObjectA.class, TEST_A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionContains_varargs_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssert.assertCollectionContains(TestObjectA.class, TEST_A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionContains_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssertCollection.assertCollectionContains(TestObjectA.class, TEST_A1));
    }
  }

  @Nested
  class assertCollectionNotContains {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionNotContains_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssert.assertCollectionNotContains(TestObjectA.class, TEST_LIST_A3);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionNotContains_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssert.assertCollectionNotContains(TestObjectA.class, TEST_LIST_A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotContains_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssertCollection.assertCollectionNotContains(TestObjectA.class, TEST_LIST_A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionNotContains_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssert.assertCollectionNotContains(TestObjectA.class, TEST_A3);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionNotContains_varargs_THEN_assert_is_false() {

      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssert.assertCollectionNotContains(TestObjectA.class, TEST_A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotContains_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class,
          () -> testAssertCollection.assertCollectionNotContains(TestObjectA.class, TEST_A3));
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
}
