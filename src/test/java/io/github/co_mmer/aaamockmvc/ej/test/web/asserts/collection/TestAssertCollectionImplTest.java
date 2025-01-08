package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A4;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.CLOSE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.NEW;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_B1NEW_B2NEW_JSON;
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
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject2;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
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

class TestAssertCollectionImplTest extends TestAssertBase {

  private TestAssertCollectionImpl testAssertCollectionImpl;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssertCollectionImpl = new TestAssertCollectionImpl(this.actions, new ObjectMapper());
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
      testAssertCollectionImpl.assertCollectionNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {Strings.EMPTY, "[]"})
    @SneakyThrows
    void GIVEN_empty_WHEN_assertCollectionNotEmpty_THEN_assert_false(String value) {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertCollectionImpl::assertCollectionNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertCollectionNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionNotEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
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
      testAssertCollectionImpl.assertCollectionEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertCollectionImpl::assertCollectionEmpty);
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

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
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
      testAssertCollectionImpl.assertCollectionSize(2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionSize_THEN_assert_is_false() {
      // Arrange
      useResultAssertionError();

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertCollectionImpl.assertCollectionSize(1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionSize_THEN_assert_is_false() {
      // Arrange
      useResultException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class, () -> testAssertCollectionImpl.assertCollectionSize(1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionSize_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useResultException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionSize(1);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
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
      testAssertCollectionImpl.assertCollectionEquals(TestObject1.class, TEST_LIST_A1_A2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_list_WHEN_assertCollectionEquals_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionEquals(TestObject1.class, TEST_LIST_A1_A3));
    }

    @Test
    void GIVEN_exception_list_WHEN_assertCollectionEquals_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToListThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertCollectionImpl.assertCollectionEquals(TestObject1.class, TEST_LIST_A1_A2));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionEquals_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionEquals(TestObject1.class, TEST_LIST_A1_A2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_list_WHEN_assertCollectionEquals_THEN_normalizeCollection_is_called() {

      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act
      testAssertCollectionImpl.assertCollectionEquals(TestObject1.class, TEST_LIST_A1_A2);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeCollection(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class assertCollectionContainsAnyOrder {

    @Test
    @SneakyThrows
    void GIVEN_expected_list_WHEN_assertCollectionContainsAnyOrder_THEN_assert_is_true() {

      // Arrange
      useServerWithResponse(TEST_LIST_A1_A3_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionContainsAnyOrder(TestObject1.class, TEST_LIST_A3_A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_list_WHEN_assertCollectionContainsAnyOrder_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionContainsAnyOrder(
                  TestObject1.class, TEST_LIST_A1_A3));
    }

    @Test
    void GIVEN_exception_list_WHEN_assertCollectionContainsAnyOrder_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToListThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertCollectionImpl.assertCollectionContainsAnyOrder(
                  TestObject1.class, TEST_LIST_A1_A2));

      mockTestGenericMapper.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionContainsAnyOrder_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionContainsAnyOrder(TestObject1.class, TEST_LIST_A1_A2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_list_WHEN_assertCollectionContainsAnyOrder_THEN_normalizeCollection_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act
      testAssertCollectionImpl.assertCollectionContainsAnyOrder(TestObject1.class, TEST_LIST_A1_A2);

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
      testAssertCollectionImpl.assertCollectionContains(TestObject1.class, TEST_LIST_A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionContains_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertCollectionImpl.assertCollectionContains(TestObject1.class, TEST_LIST_A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionContains_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertCollectionImpl.assertCollectionContains(TestObject1.class, TEST_LIST_A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionContains_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionContains(TestObject1.class, TEST_LIST_A1_A2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionContains_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionContains(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionContains_varargs_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertCollectionImpl.assertCollectionContains(TestObject1.class, A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionContains_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertCollectionImpl.assertCollectionContains(TestObject1.class, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionContains_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionContains(TestObject1.class, A1);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
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
      testAssertCollectionImpl.assertCollectionNotContains(TestObject1.class, TEST_LIST_A3);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionNotContains_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionNotContains(
                  TestObject1.class, TEST_LIST_A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotContains_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionNotContains(
                  TestObject1.class, TEST_LIST_A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotContains_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionNotContains(TestObject1.class, TEST_LIST_A3);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionNotContains_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionNotContains(TestObject1.class, A3);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionNotContains_varargs_THEN_assert_is_false() {

      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertCollectionImpl.assertCollectionNotContains(TestObject1.class, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotContains_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertCollectionImpl.assertCollectionNotContains(TestObject1.class, A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionNotContains_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionNotContains(TestObject1.class, A3);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertCollectionMatchAll {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionMatchAll_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionMatchAll(
          TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionMatchAll_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAll(
                  TestObject1.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAll_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAll(
                  TestObject1.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAll_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionMatchAll(
          TestObject1.class, element -> element.name().equals(A));

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionMatchAll_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchNone(
                  TestObject2.class,
                  element -> element.name().equals(B),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_one_WHEN_assertCollectionMatchAll_varargs_THEN_assert_is_false() {

      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAll(
                  TestObject2.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_all_WHEN_assertCollectionMatchAll_varargs_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAll(
                  TestObject2.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(CLOSE)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAll_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAll(
                  TestObject2.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAll_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionMatchAll(
          TestObject2.class,
          element -> element.name().equals(A),
          element -> element.status().equals(NEW));

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertCollectionMatchAny {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertCollectionMatchAny_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionMatchAny(
          TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertCollectionMatchAny_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAny(
                  TestObject1.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAny_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAny(
                  TestObject1.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAny_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionMatchAny(
          TestObject1.class, element -> element.name().equals(A));

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_one_WHEN_assertCollectionMatchAny_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionMatchAny(
          TestObject2.class,
          element -> element.name().equals(A),
          element -> element.name().equals(B));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_all_WHEN_assertCollectionMatchAny_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionMatchAny(
          TestObject2.class,
          element -> element.name().equals(B),
          element -> element.status().equals(NEW));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_all_WHEN_assertCollectionMatchAny_varargs_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAny(
                  TestObject2.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(CLOSE)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAny_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchAny(
                  TestObject2.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchAny_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionMatchAny(
          TestObject2.class,
          element -> element.name().equals(A),
          element -> element.status().equals(NEW));

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertCollectionMatchNone {

    @Test
    @SneakyThrows
    void GIVEN_A1_A2_condition_B_WHEN_assertCollectionMatchNone_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionMatchNone(
          TestObject1.class, element -> element.name().equals(B));
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_A2_condition_A_WHEN_assertCollectionMatchNone_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchNone(
                  TestObject1.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchNone_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchNone(
                  TestObject1.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchNone_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionMatchNone(
          TestObject1.class, element -> element.name().equals(A));

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void
        GIVEN_B1NEW_B2NEW_condition_B_NEW_WHEN_assertCollectionMatchNone_varargs_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchNone(
                  TestObject2.class,
                  element -> element.name().equals(B),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void
        GIVEN_B1NEW_B2NEW_condition_B_CLOSE_WHEN_assertCollectionMatchNone_varargs_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchNone(
                  TestObject2.class,
                  element -> element.name().equals(B),
                  element -> element.status().equals(CLOSE)));
    }

    @Test
    @SneakyThrows
    void
        GIVEN_B1NEW_B2NEW_condition_A_CLOSE_WHEN_assertCollectionMatchNone_varargs_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl.assertCollectionMatchNone(
          TestObject2.class,
          element -> element.name().equals(A),
          element -> element.status().equals(CLOSE));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchNone_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertCollectionImpl.assertCollectionMatchNone(
                  TestObject2.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertCollectionMatchNone_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertCollectionMatchNone(
          TestObject2.class,
          element -> element.name().equals(A),
          element -> element.status().equals(NEW));

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class nextStep {

    @Test
    void assertHead() {
      // Arrange
      useHeader();

      // Act
      var assertHead = testAssertCollectionImpl.assertHead();

      // Assert
      assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
    }
  }

  @Nested
  class combinationNotEmpty {

    @Test
    @SneakyThrows
    void not_empty__contains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionContains(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void not_empty__contains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionContains(TestObject1.class, A1, A2);
    }

    @Test
    @SneakyThrows
    void not_empty__contains_any_order() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionContainsAnyOrder(TestObject1.class, List.of(A2, A1));
    }

    @Test
    @SneakyThrows
    void not_empty__not_contains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionNotContains(TestObject1.class, A3);
    }

    @Test
    @SneakyThrows
    void not_empty__not_contains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionNotContains(TestObject1.class, A3, A4);
    }

    @Test
    @SneakyThrows
    void not_empty__equals() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionEquals(TestObject1.class, TEST_LIST_A1_A2);
    }

    @Test
    @SneakyThrows
    void not_empty__match_all_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_empty__match_all_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionMatchAll(
              TestObject1.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void not_empty__match_any_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_empty__match_any_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void not_empty__match_none_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_empty__match_none_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotEmpty()
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }
  }

  @Nested
  class combinationSize {

    @Test
    @SneakyThrows
    void size__contains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionContains(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void size__contains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionContains(TestObject1.class, A1, A2);
    }

    @Test
    @SneakyThrows
    void size__contains_any_order() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionContainsAnyOrder(TestObject1.class, List.of(A2, A1));
    }

    @Test
    @SneakyThrows
    void size__not_contains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionNotContains(TestObject1.class, A3);
    }

    @Test
    @SneakyThrows
    void size__not_contains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionNotContains(TestObject1.class, A3, A4);
    }

    @Test
    @SneakyThrows
    void size__equals() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionEquals(TestObject1.class, TEST_LIST_A1_A2);
    }

    @Test
    @SneakyThrows
    void size__match_all_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void size__match_all_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionMatchAll(
              TestObject1.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void size__match_any_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void size__match_any_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void size__match_none_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void size__match_none_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionSize(2)
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }
  }

  @Nested
  class combinationContains {

    @Test
    @SneakyThrows
    void contains_1__match_any_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1)
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_1__match_any_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1)
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void contains_1__match_all_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1)
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_1__match_all_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1)
          .assertCollectionMatchAll(
              TestObject1.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void contains_1__match_none_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1)
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_1__match_none_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1)
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void contains_2__match_any_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1, A2)
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_2__match_any_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1, A2)
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void contains_2__match_all_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1, A2)
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_2__match_all_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1, A2)
          .assertCollectionMatchAll(
              TestObject1.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void contains_2__match_none_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1, A2)
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_2__match_none_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionContains(TestObject1.class, A1, A2)
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }
  }

  @Nested
  class combinationNotContains {

    @Test
    @SneakyThrows
    void not_contains_1__match_any_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3)
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_contains_1__match_any_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3)
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void not_contains_1__match_all_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3)
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_contains_1__match_all_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3)
          .assertCollectionMatchAll(
              TestObject1.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void not_contains_1__match_none_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3)
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_contains_1__match_none_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3)
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void not_contains_2__match_any_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3, A4)
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_contains_2__match_any_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3, A4)
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void not_contains_2__match_all_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3, A4)
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_contains_2__match_all_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3, A4)
          .assertCollectionMatchAll(
              TestObject1.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void not_contains_2__match_none_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3, A4)
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void not_contains_2__match_none_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionNotContains(TestObject1.class, A3, A4)
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }
  }

  @Nested
  class combinationMatchAll {

    @Test
    @SneakyThrows
    void matchAll_matchAny() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void matchAll_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_matchAny() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(
              TestObject2.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertCollectionMatchAny(TestObject2.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(
              TestObject2.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertCollectionMatchAny(
              TestObject2.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(TestObject2.class, element -> element.name().equals(B))
          .assertCollectionMatchNone(TestObject2.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(TestObject2.class, element -> element.name().equals(B))
          .assertCollectionMatchNone(
              TestObject2.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAll(
              TestObject2.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertHead();
    }
  }

  @Nested
  class combinationMatchAny {

    @Test
    @SneakyThrows
    void matchAny_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1)
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAny(TestObject1.class, element -> element.id() == 1)
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2)
          .assertCollectionMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2)
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAny(TestObject1.class, element -> element.name().equals(A))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchAny(
              TestObject1.class, element -> element.name().equals(A), element -> element.id() == 1)
          .assertHead();
    }
  }

  @Nested
  class combinationMatchNone {

    @Test
    @SneakyThrows
    void matchNone_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchNone(TestObject1.class, element -> element.name().equals(B))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchNone_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      testAssertCollectionImpl
          .assertCollectionMatchNone(
              TestObject1.class, element -> element.name().equals(B), element -> element.id() == 3)
          .assertHead();
    }
  }
}
