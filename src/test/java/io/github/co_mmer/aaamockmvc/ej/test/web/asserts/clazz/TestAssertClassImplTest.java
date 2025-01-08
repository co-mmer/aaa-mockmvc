package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeObject;
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
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject1;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject2;
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
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.web.servlet.ResultActions;

class TestAssertClassImplTest extends TestAssertBase {

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
      useServerWithResponse(Strings.EMPTY);

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
      useServerWithResponse(Strings.EMPTY);

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
  }

  @Nested
  class assertClassEquals {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassEquals_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassEquals(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertClassEquals_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> testAssertClass.assertClassEquals(TestObject1.class, A2));
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
          () -> testAssertException.assertClassEquals(TestObject1.class, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_object_WHEN_assertClassEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_A1_JSON);

      // Act
      testAssertClass.assertClassEquals(TestObject1.class, A1);

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
      testAssertClass.assertClassMatchAll(TestObject1.class, element -> element.name().equals(A));
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
                  TestObject1.class, element -> element.name().equals(B)));
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
                  TestObject1.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassMatchAll_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchAll(
          TestObject1.class, element -> element.name().equals(A), element -> element.id() == 1);
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
                  TestObject1.class,
                  element -> element.name().equals(A),
                  element -> element.id() == 2));
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
                  TestObject1.class,
                  element -> element.name().equals(A),
                  element -> element.id() == 2));
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
      testAssertClass.assertClassMatchAny(TestObject1.class, element -> element.id() == 1);
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
                  TestObject1.class, element -> element.name().equals(B)));
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
                  TestObject1.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassMatchAny_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchAny(
          TestObject1.class, element -> element.name().equals(B), element -> element.id() == 1);
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
                  TestObject1.class,
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
                  TestObject1.class,
                  element -> element.name().equals(A),
                  element -> element.id() == 2));
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
      testAssertClass.assertClassMatchNone(TestObject1.class, element -> element.id() == 2);
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
                  TestObject1.class, element -> element.name().equals(A)));
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
                  TestObject1.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertClassMatchNone_vararg_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass.assertClassMatchNone(
          TestObject1.class, element -> element.name().equals(B), element -> element.id() == 2);
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
                  TestObject1.class,
                  element -> element.name().equals(A),
                  element -> element.id() == 3));
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
                  TestObject1.class,
                  element -> element.name().equals(A),
                  element -> element.id() == 1));
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
                  TestObject1.class,
                  element -> element.name().equals(A),
                  element -> element.id() == 1));
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
      testAssertClass.assertClassNotEmpty().assertClassEquals(TestObject1.class, A1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchAll() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchAll(TestObject1.class, element -> element.name().equals(A));
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
              TestObject1.class, element -> element.name().equals(A), element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchAny() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchAny(TestObject1.class, element -> element.name().equals(A));
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
              TestObject1.class, element -> element.name().equals(A), element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    void notEmpty_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassNotEmpty()
          .assertClassMatchNone(TestObject1.class, element -> element.name().equals(B));
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
              TestObject1.class, element -> element.name().equals(B), element -> element.id() == 2);
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
      useServerWithResponse(Strings.EMPTY);

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
      testAssertClass.assertClassEquals(TestObject1.class, A1).assertHead();
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
          .assertClassMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertClassMatchAny(TestObject1.class, element -> element.id() == 1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertClassMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void matchAll_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertClassMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObject1.class, element -> element.name().equals(A))
          .assertClassMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg__matchAny() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(
              TestObject2.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertClassMatchAny(TestObject2.class, element -> element.id() == 1);
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
              TestObject2.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertClassMatchAny(
              TestObject2.class, element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObject2.class, element -> element.name().equals(B))
          .assertClassMatchNone(TestObject2.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_B1NEW_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObject2.class, element -> element.name().equals(B))
          .assertClassMatchNone(
              TestObject2.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAll_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAll(TestObject1.class, element -> element.name().equals(A))
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
              TestObject1.class, element -> element.name().equals(A), element -> element.id() == 1)
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
          .assertClassMatchAny(TestObject1.class, element -> element.id() == 1)
          .assertClassMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(TestObject1.class, element -> element.id() == 1)
          .assertClassMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2)
          .assertClassMatchNone(TestObject1.class, element -> element.id() == 3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(
              TestObject1.class, element -> element.id() == 1, element -> element.id() == 2)
          .assertClassMatchNone(
              TestObject1.class, element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    @SneakyThrows
    void matchAny_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchAny(TestObject1.class, element -> element.name().equals(A))
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
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertClass
          .assertClassMatchNone(TestObject1.class, element -> element.name().equals(B))
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
              TestObject1.class, element -> element.name().equals(B), element -> element.id() == 2)
          .assertHead();
    }
  }
}
