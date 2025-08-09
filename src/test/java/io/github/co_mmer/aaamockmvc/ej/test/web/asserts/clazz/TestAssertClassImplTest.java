package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.NEW;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_B1NEW_JSON;
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
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectMatch;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertClassImplTest extends TestAssertBase {

  private static final Predicate<TestObjectSimple> PREDICATE_NAME_EQUALS_A =
      element -> element.name().equals(A);
  private static final Predicate<TestObjectSimple> PREDICATE_ID_EQUALS_2 =
      element -> element.id() == 2;
  private static final Predicate<TestObjectSimple> PREDICATE_ID_EQUALS_1 =
      element -> element.id() == 1;

  private TestAssertClassImpl testAssertClass;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssertClass = new TestAssertClassImpl(this.actions, new ObjectMapper());
  }

  @Nested
  class constructor {

    @ParameterizedTest()
    @MethodSource("provideNullParameters")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullParameters_WHEN_call_constructor_THEN_throw_NullPointerException(
        ResultActions actions, ObjectMapper objectMapper) {

      assertThrows(
          NullPointerException.class, () -> new TestAssertClassImpl(actions, objectMapper));
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
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentNotEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentNotEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithResponse(EMPTY);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertClass::assertContentNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertContentNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentNotEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentNotEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertContentEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(EMPTY);

      // Act & Assert
      testAssertClass.assertContentEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertClass::assertContentEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertContentEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

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
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentEquals_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentEquals(TestObjectSimple.class, A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentEquals_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertClass.assertContentEquals(TestObjectSimple.class, A2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEquals_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssertException.assertContentEquals(TestObjectSimple.class, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentEquals_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentEquals(TestObjectSimple.class, A1);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertContentEquals_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, TestObjectSimple expectedResponse) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertContentEquals(expectedClass, expectedResponse));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_assertContentEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_A1_JSON);

      // Act
      testAssertClass.assertContentEquals(TestObjectSimple.class, A1);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class assertContentMatchAll {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentMatchAll_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassMatchAll_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertContentMatchAll(
                  TestObjectSimple.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAll_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertContentMatchAll(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAll_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertContentMatchAll_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple> condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertContentMatchAll(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentMatchAll_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentMatchAll(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentMatchAll_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertContentMatchAll(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAll_vararg_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertContentMatchAll(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAll_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentMatchAll(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParametersVararg")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertContentMatchAll_vararg_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple>[] condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertContentMatchAll(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParametersVararg() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, new Predicate[] {PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2}));
    }
  }

  @Nested
  class assertContentMatchAny {

    @Test
    @SneakyThrows
    void GIVEN_A1_match_id1_WHEN_assertContentMatchAny_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentMatchAny_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertContentMatchAny(
                  TestObjectSimple.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAny_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertContentMatchAny(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAny_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentMatchAny(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertContentMatchAny_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple> condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertContentMatchAny(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentMatchAny_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentMatchAny(
          TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentMatchAny_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertContentMatchAny(
                  TestObjectSimple.class,
                  element -> element.name().equals(B),
                  element -> element.id() == 3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAny_vararg_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertContentMatchAny(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAny_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentMatchAny(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParametersVararg")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertContentMatchAny_vararg_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple>[] condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertContentMatchAny(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParametersVararg() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, new Predicate[] {PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2}));
    }
  }

  @Nested
  class assertContentMatchNone {

    @Test
    @SneakyThrows
    void GIVEN_A1_match_id2_WHEN_assertContentMatchNone_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentMatchNone(TestObjectSimple.class, PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentMatchNone_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertContentMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchNone_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertContentMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchNone_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentMatchNone(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertContentMatchNone_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple> condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertContentMatchNone(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentMatchNone_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentMatchNone(
          TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_A3_WHEN_assertContentMatchNone_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertContentMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, element -> element.id() == 3));
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_match_A1_WHEN_assertContentMatchNone_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertContentMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchNone_vararg_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertContentMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchNone_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertContentMatchNone(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParametersVararg")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertContentMatchNone_vararg_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple>[] condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertContentMatchNone(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParametersVararg() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, new Predicate[] {PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1}));
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_assertHead_THEN_return_expected_class() {
      // Arrange
      useHeader();

      // Act
      var assertHead = testAssertClass.assertHead();

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
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentNotEmpty().assertContentEquals(TestObjectSimple.class, A1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchAll() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentNotEmpty()
          .assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notEmpty_matchAll_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentNotEmpty()
          .assertContentMatchAll(
              TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchAny() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentNotEmpty()
          .assertContentMatchAny(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notEmpty_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentNotEmpty()
          .assertContentMatchAny(
              TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentNotEmpty()
          .assertContentMatchNone(TestObjectSimple.class, element -> element.name().equals(B));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notEmpty_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentNotEmpty()
          .assertContentMatchNone(
              TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void notEmpty_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentNotEmpty().assertHead();
    }
  }

  @Nested
  class combinationEmpty {

    @Test
    @SneakyThrows
    void empty_head() {
      // Arrange
      useServerWithResponse(EMPTY);

      // Act & Assert
      testAssertClass.assertContentEmpty().assertHead();
    }
  }

  @Nested
  class combinationEquals {

    @Test
    @SneakyThrows
    void equals_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertContentEquals(TestObjectSimple.class, A1).assertHead();
    }
  }

  @Nested
  class combinationMatchAll {

    @Test
    @SneakyThrows
    void matchAll_matchAny() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertContentMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertContentMatchAny(
              TestObjectSimple.class, PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void matchAll_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertContentMatchNone(
              TestObjectSimple.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg__matchAny() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(
              TestObjectMatch.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertContentMatchAny(TestObjectMatch.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(
              TestObjectMatch.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertContentMatchAny(
              TestObjectMatch.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(TestObjectMatch.class, element -> element.name().equals(B))
          .assertContentMatchNone(TestObjectMatch.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(TestObjectMatch.class, element -> element.name().equals(B))
          .assertContentMatchNone(
              TestObjectMatch.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAll(
              TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1)
          .assertHead();
    }
  }

  @Nested
  class combinationMatchAny {

    @Test
    @SneakyThrows
    void matchAny_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1)
          .assertContentMatchNone(
              TestObjectSimple.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAny(
              TestObjectSimple.class, PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAny(
              TestObjectSimple.class, PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2)
          .assertContentMatchNone(
              TestObjectSimple.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAny(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchAny(
              TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1)
          .assertHead();
    }
  }

  @Nested
  class combinationMatchNone {

    @Test
    @SneakyThrows
    void matchNone_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchNone(TestObjectSimple.class, element -> element.name().equals(B))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchNone_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertContentMatchNone(
              TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_2)
          .assertHead();
    }
  }
}
