package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_ARRAY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_OBJECT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("java:S2699")
class TestAssertByteImplTest extends TestAssertBase {

  private TestAssertByteImpl testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.testAssert = new TestAssertByteImpl(context);
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
      var ex = assertThrows(AssertionError.class, testAssert::isNotEmpty);
      assertThat(ex.getMessage(), not(EMPTY));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
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
      var ex = assertThrows(AssertionError.class, testAssert::isEmpty);
      assertThat(ex.getMessage(), not(EMPTY));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
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
      var ex = assertThrows(AssertionError.class, () -> testAssert.hasLength(8));
      assertThat(ex.getMessage(), not(EMPTY));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
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
    @SuppressWarnings("java:S5778")
    void GIVEN_unexpected_byte_WHEN_isEqualTo_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON.getBytes());

      // Act & Assert
      var ex =
          assertThrows(AssertionError.class, () -> testAssert.isEqualTo(TEST_A2_JSON.getBytes()));
      assertThat(ex.getMessage(), not(EMPTY));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
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
