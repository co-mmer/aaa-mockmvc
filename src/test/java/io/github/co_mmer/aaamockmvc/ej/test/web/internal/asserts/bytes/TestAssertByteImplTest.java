package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.bytes;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_ARRAY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_OBJECT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestAssertByteImplTest extends TestAssertBase {

  private TestAssertByteImpl testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.testAssert = new TestAssertByteImpl(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestAssertByteImpl(null));
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_byte_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act & Assert
      testAssert.isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_ARRAY, EMPTY_OBJECT})
    void GIVEN_blank_WHEN_isNotEmpty_THEN_failed(String content) {
      // Arrange
      useActResult(content.getBytes());

      // Act & Assert
      var exception = assertThrows(AssertionError.class, testAssert::isNotEmpty);
      assertThat(exception.getMessage(), not(EMPTY));
    }
  }

  @Nested
  class isEmpty {

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_ARRAY, EMPTY_OBJECT})
    void GIVEN_blank_WHEN_isEmpty_THEN_success(String content) {
      // Arrange
      useActResult(content.getBytes());

      // Act & Assert
      testAssert.isEmpty();
    }

    @Test
    void GIVEN_byte_WHEN_isEmpty_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act & Assert
      var exception = assertThrows(AssertionError.class, testAssert::isEmpty);
      assertThat(exception.getMessage(), not(EMPTY));
    }
  }

  @Nested
  class hasLength {

    @Test
    void GIVEN_length1_WHEN_hasLength1_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act & Assert
      testAssert.hasLength(TEST_A1_JSON.length());
    }

    @Test
    void GIVEN_length1_WHEN_hasLength8_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act & Assert
      var exception = assertThrows(AssertionError.class, () -> testAssert.hasLength(8));
      assertThat(exception.getMessage(), not(EMPTY));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_expected_WHEN_isEqualTo_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act & Assert
      testAssert.isEqualTo(TEST_A1_JSON.getBytes());
    }

    @Test
    void GIVEN_unexpected_byte_WHEN_isEqualTo_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act & Assert
      var exception =
          assertThrows(AssertionError.class, () -> testAssert.isEqualTo(TEST_A2_JSON.getBytes()));
      assertThat(exception.getMessage(), not(EMPTY));
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_headers_THEN_return_expected_class() {
      // Act
      var headers = testAssert.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
