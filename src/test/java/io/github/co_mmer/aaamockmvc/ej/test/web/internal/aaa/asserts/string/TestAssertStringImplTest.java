package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S2699")
class TestAssertStringImplTest extends TestAssertBase {

  private TestAssertStringImpl testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.testAssert = new TestAssertStringImpl(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestAssertStringImpl(null));
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_A1_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      testAssert.isNotEmpty();
    }

    @Test
    void GIVEN_empty_WHEN_isNotEmpty_THEN_failed() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, testAssert::isNotEmpty);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isEmpty {

    @Test
    void GIVEN_empty_WHEN_isEmpty_THEN_success() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      testAssert.isEmpty();
    }

    @Test
    void GIVEN_A1_WHEN_isEmpty_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, testAssert::isEmpty);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A1_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      testAssert.isEqualTo(TEST_A1_JSON);
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A2_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert.isEqualTo(TEST_A2_JSON));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_THEN_normalize_object_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useActResult(TEST_A1_JSON);

      // Act
      testAssert.isEqualTo(TEST_A1_JSON);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_isEqualTo_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> testAssert.isEqualTo(null));
    }
  }

  @Nested
  class hasLength {

    @Test
    void GIVEN_A1_WHEN_hasLength_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      testAssert.hasLength(TEST_A1_JSON.length());
    }

    @Test
    void GIVEN_A1_WHEN_hasLength10_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert.hasLength(10));
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
