package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
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

class TestAssertStringImplTest extends TestAssertBase {

  private TestAssertStringImpl testAssertString;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssertString = new TestAssertStringImpl(this.actions);
  }

  @Nested
  class constructor {

    @ParameterizedTest()
    @MethodSource("provideNullParameters")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullParameters_WHEN_call_constructor_THEN_throw_NullPointerException(
        ResultActions actions) {

      assertThrows(NullPointerException.class, () -> new TestAssertStringImpl(actions));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(Arguments.of((ResultActions) null), Arguments.of(mock(ResultActions.class)));
    }
  }

  @Nested
  class assertStringNotEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertStringNotEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertString.assertStringNotEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertStringNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithResponse(EMPTY);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertString::assertStringNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertStringImpl(actions);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertStringNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringNotEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithByteException();
      var testAssertException = new TestAssertStringImpl(actions);

      // Act
      testAssertException.assertStringNotEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertStringEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertStringEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(EMPTY);

      // Act & Assert
      testAssertString.assertStringEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertStringEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertString::assertStringEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertContentException = new TestAssertStringImpl(actions);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertContentException::assertStringEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringEmpty_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithByteException();
      var testAssertException = new TestAssertStringImpl(actions);

      // Act
      testAssertException.assertStringEmpty();

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }
  }

  @Nested
  class assertStringEquals {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertStringEquals_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertString.assertStringEquals(TEST_A1_JSON);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertStringEquals_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertString.assertStringEquals(TEST_A2_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringEquals_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertContentException = new TestAssertStringImpl(actions);

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> testAssertContentException.assertStringEquals(TEST_A1_JSON));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringEquals_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithByteException();
      var testAssertException = new TestAssertStringImpl(actions);

      // Act
      testAssertException.assertStringEquals(TEST_A1_JSON);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_string_WHEN_assertStringEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_A1_JSON);

      // Act
      testAssertString.assertStringEquals(TEST_A1_JSON);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_assertStringEquals_THEN_throw_NullPointerException() {
      // Arrange
      var testAssert = new TestAssertStringImpl(actions);

      // Act & Assert
      assertThrows(NullPointerException.class, () -> testAssert.assertStringEquals(null));
    }
  }

  @Nested
  class assertStringLength {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertStringLength_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertString.assertStringLength(TEST_A1_JSON.length());
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertStringLength_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertString.assertStringLength(10));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringLength_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertContentException = new TestAssertStringImpl(actions);

      // Act & Assert
      assertThrows(
          AssertionFailedError.class, () -> testAssertContentException.assertStringLength(50));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertStringLength_THEN_Assertions_fail_is_called() {
      // Arrange
      var mockAssertions = Mockito.mockStatic(Assertions.class);

      useServerWithByteException();
      var testAssertException = new TestAssertStringImpl(actions);

      // Act
      testAssertException.assertStringLength(50);

      // Assert
      mockAssertions.verify(() -> Assertions.fail(any(Throwable.class)));
      mockAssertions.close();
    }

    @Test
    @SneakyThrows
    void GIVEN_string_WHEN_assertStringEquals_THEN_normalizeObject_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useServerWithResponse(TEST_A1_JSON);

      // Act
      testAssertString.assertStringEquals(TEST_A1_JSON);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_assertHead_THEN_return_expected_class() {
      // Arrange
      useHeader();

      // Act
      var assertHead = testAssertString.assertHead();

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
      testAssertString.assertStringNotEmpty().assertStringEquals(TEST_A1_JSON);
    }

    @Test
    @SneakyThrows
    void notEmpty_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertString.assertStringNotEmpty().assertHead();
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
      testAssertString.assertStringEmpty().assertHead();
    }
  }

  @Nested
  class combinationLength {

    @Test
    @SneakyThrows
    void length_equals() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertString.assertStringLength(TEST_A1_JSON.length()).assertStringEquals(TEST_A1_JSON);
    }

    @Test
    @SneakyThrows
    void length_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertString.assertStringLength(TEST_A1_JSON.length()).assertHead();
    }
  }

  @Nested
  class combinationEquals {

    @Test
    @SneakyThrows
    void equals_string_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssertString.assertStringEquals(TEST_A1_JSON).assertHead();
    }
  }
}
