package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
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

class TestAssertByteImplTest extends TestAssertBase {

  private static final String EXPECTED_CONTENT = "expected content";
  private static final String ACTUAL_CONTENT = "actual content";
  private TestAssertByteImpl testAssert;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.testAssert = new TestAssertByteImpl(this.actions);
  }

  @Nested
  class constructor {

    @ParameterizedTest()
    @MethodSource("provideNullParameters")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullParameters_WHEN_call_constructor_THEN_throw_NullPointerException(
        ResultActions actions, ObjectMapper objectMapper) {

      assertThrows(
          NullPointerException.class, () -> new TestAssertContentImpl(actions, objectMapper));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, new ObjectMapper()),
          Arguments.of(mock(ResultActions.class), null),
          Arguments.of(null, null));
    }
  }

  @Nested
  class assertByteNotEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertByteNotEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(ACTUAL_CONTENT);

      // Act & Assert
      testAssert.assertByteNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {Strings.EMPTY, "[]"})
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertByteNotEmpty_THEN_assert_false(String value) {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertByteNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertByteNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertByte = new TestAssertByteImpl(actions);

      // Act & Assert
      assertThrows(AssertionFailedError.class, testAssertByte::assertByteNotEmpty);
    }
  }

  @Nested
  class assertByteEmpty {

    @ParameterizedTest
    @ValueSource(strings = {Strings.EMPTY, "[]"})
    @SneakyThrows
    void GIVEN_expected_WHEN_assertByteEmpty_THEN_assert_true(String value) {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      testAssert.assertByteEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertByteIsEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(ACTUAL_CONTENT);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::assertByteEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertByteEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertByte = new TestAssertByteImpl(actions);

      // Act & Assert
      assertThrows(AssertionFailedError.class, testAssertByte::assertByteEmpty);
    }
  }

  @Nested
  class assertByteLength {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertByteLength_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssert.assertByteLength(TEST_A1_JSON.length());
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertByteLength_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.assertByteLength(1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertByteEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertByte = new TestAssertByteImpl(actions);

      // Act & Assert
      assertThrows(AssertionFailedError.class, () -> testAssertByte.assertByteLength(1));
    }
  }

  @Nested
  class assertByteEquals {

    @Test
    @SneakyThrows
    void GIVEN_expected_byte_WHEN_assertByteEquals_THEN_assert_true() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssert.assertByteEquals(EXPECTED_CONTENT.getBytes());
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_byte_WHEN_assertByteEquals_THEN_assert_false() {
      // Arrange
      useServerWithResponse(ACTUAL_CONTENT);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.assertByteEquals(new byte[1]));
    }

    @Test
    void GIVEN_exception_byte_WHEN_assertByteEquals_THEN_assert_false() {
      // Arrange
      useServerWithByteException();
      var testAssertByte = new TestAssertByteImpl(actions);

      // Act & Assert
      assertThrows(AssertionFailedError.class, () -> testAssertByte.assertByteEquals(new byte[1]));
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
    void notEmpty__equals() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssert.assertByteNotEmpty().assertByteEquals(EXPECTED_CONTENT.getBytes());
    }

    @Test
    @SneakyThrows
    void notEmpty__head() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssert.assertByteNotEmpty().assertHead();
    }
  }

  @Nested
  class combinationEmpty {

    @Test
    @SneakyThrows
    void empty__head() {
      // Arrange
      useServerWithResponse(Strings.EMPTY);

      // Act & Assert
      testAssert.assertByteEmpty().assertHead();
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
      testAssert.assertByteLength(TEST_A1_JSON.length()).assertByteEquals(TEST_A1_JSON.getBytes());
    }

    @Test
    @SneakyThrows
    void length_head() {
      // Arrange
      useServerWithResponse(TEST_A1_JSON);

      // Act & Assert
      testAssert.assertByteLength(TEST_A1_JSON.length()).assertHead();
    }
  }

  @Nested
  class combinationEquals {

    @Test
    @SneakyThrows
    void equals__head() {
      // Arrange
      useServerWithResponse(EXPECTED_CONTENT);

      // Act & Assert
      testAssert.assertByteEquals(EXPECTED_CONTENT.getBytes()).assertHead();
    }
  }
}
