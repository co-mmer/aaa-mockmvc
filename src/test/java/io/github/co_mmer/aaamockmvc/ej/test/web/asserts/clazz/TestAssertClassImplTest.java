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
  class assertClassNotEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassNotEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassNotEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithResponse(EMPTY);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertClass::assertClassNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertClassNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassNotEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassNotEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertClassEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(EMPTY);

      // Act & Assert
      testAssertClass.assertClassEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertClass::assertClassEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertClassEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertClassEquals {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassEquals_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassEquals(TestObjectSimple.class, A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassEquals_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertClass.assertClassEquals(TestObjectSimple.class, A2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassEquals_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssertException.assertClassEquals(TestObjectSimple.class, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassEquals_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassEquals(TestObjectSimple.class, A1);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertClassEquals_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, TestObjectSimple expectedResponse) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertClassEquals(expectedClass, expectedResponse));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_assertClassEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_A1_JSON);

      // Act
      testAssertClass.assertClassEquals(TestObjectSimple.class, A1);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class assertClassMatchAll {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassMatchAll_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);
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
              testAssertClass.assertClassMatchAll(
                  TestObjectSimple.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAll_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertClassMatchAll(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAll_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertClassMatchAll_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple> condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertClassMatchAll(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassMatchAll_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchAll(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassMatchAll_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertClassMatchAll(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAll_vararg_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertClassMatchAll(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAll_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassMatchAll(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParametersVararg")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertClassMatchAll_vararg_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple>[] condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertClassMatchAll(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParametersVararg() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, new Predicate[] {PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2}));
    }
  }

  @Nested
  class assertClassMatchAny {

    @Test
    @SneakyThrows
    void GIVEN_A1_match_id1_WHEN_assertClassMatchAny_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassMatchAny_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertClassMatchAny(
                  TestObjectSimple.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAny_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertClassMatchAny(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAny_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassMatchAny(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertClassMatchAny_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple> condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertClassMatchAny(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassMatchAny_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchAny(
          TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassMatchAny_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertClassMatchAny(
                  TestObjectSimple.class,
                  element -> element.name().equals(B),
                  element -> element.id() == 3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAny_vararg_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertClassMatchAny(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchAny_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassMatchAny(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParametersVararg")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertClassMatchAny_vararg_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple>[] condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertClassMatchAny(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParametersVararg() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, new Predicate[] {PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2}));
    }
  }

  @Nested
  class assertClassMatchNone {

    @Test
    @SneakyThrows
    void GIVEN_A1_match_id2_WHEN_assertClassMatchNone_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchNone(TestObjectSimple.class, PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassMatchNone_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertClassMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchNone_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertClassMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchNone_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassMatchNone(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParameters")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertClassMatchNone_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple> condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertClassMatchNone(expectedClass, condition));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null),
          Arguments.of(TestObjectSimple.class, null),
          Arguments.of(null, PREDICATE_NAME_EQUALS_A));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassMatchNone_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchNone(
          TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_A3_WHEN_assertClassMatchNone_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertClassMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, element -> element.id() == 3));
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_match_A1_WHEN_assertClassMatchNone_vararg_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.assertClassMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchNone_vararg_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () ->
              testAssertException.assertClassMatchNone(
                  TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertClassMatchNone_vararg_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithStringException();
      var testAssertException = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act
      testAssertException.assertClassMatchNone(
          TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @ParameterizedTest
    @MethodSource("provideNullParametersVararg")
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertClassMatchNone_vararg_THEN_throw_NullPointerException(
        Class<TestObjectSimple> expectedClass, Predicate<TestObjectSimple>[] condition) {

      // Arrange
      var testAssert = new TestAssertClassImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(
          NullPointerException.class,
          () -> testAssert.assertClassMatchNone(expectedClass, condition));
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
      testAssertClass.assertClassNotEmpty().assertClassEquals(TestObjectSimple.class, A1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchAll() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notEmpty_matchAll_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchAll(
              TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchAny() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchAny(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notEmpty_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchAny(
              TestObjectSimple.class, PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchNone(TestObjectSimple.class, element -> element.name().equals(B));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notEmpty_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchNone(
              TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void notEmpty_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassNotEmpty().assertHead();
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
      testAssertClass.assertClassEmpty().assertHead();
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
      testAssertClass.assertClassEquals(TestObjectSimple.class, A1).assertHead();
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
          .assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertClassMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertClassMatchAny(
              TestObjectSimple.class, PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2);
    }

    @Test
    @SneakyThrows
    void matchAll_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertClassMatchNone(TestObjectSimple.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertClassMatchNone(
              TestObjectSimple.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg__matchAny() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(
              TestObjectMatch.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertClassMatchAny(TestObjectMatch.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(
              TestObjectMatch.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertClassMatchAny(
              TestObjectMatch.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObjectMatch.class, element -> element.name().equals(B))
          .assertClassMatchNone(TestObjectMatch.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObjectMatch.class, element -> element.name().equals(B))
          .assertClassMatchNone(
              TestObjectMatch.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(
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
          .assertClassMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1)
          .assertClassMatchNone(TestObjectSimple.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1)
          .assertClassMatchNone(
              TestObjectSimple.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2)
          .assertClassMatchNone(TestObjectSimple.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(TestObjectSimple.class, PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2)
          .assertClassMatchNone(
              TestObjectSimple.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(TestObjectSimple.class, PREDICATE_NAME_EQUALS_A)
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(
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
          .assertClassMatchNone(TestObjectSimple.class, element -> element.name().equals(B))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchNone_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchNone(
              TestObjectSimple.class, element -> element.name().equals(B), PREDICATE_ID_EQUALS_2)
          .assertHead();
    }
  }
}
