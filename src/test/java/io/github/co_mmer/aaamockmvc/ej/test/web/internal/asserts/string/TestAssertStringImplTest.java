package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestAssertStringImplTest extends TestAssertBase {

  private TestAssertStringImpl testAssertString;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.testAssertString = new TestAssertStringImpl(context);
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
      testAssertString.isNotEmpty();
    }

    @Test
    void GIVEN_empty_WHEN_isNotEmpty_THEN_failed() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertString::isNotEmpty);
    }
  }

  @Nested
  class isEmpty {

    @Test
    void GIVEN_empty_WHEN_isEmpty_THEN_success() {
      // Arrange
      useActResult(EMPTY);

      // Act & Assert
      testAssertString.isEmpty();
    }

    @Test
    void GIVEN_A1_WHEN_isEmpty_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertString::isEmpty);
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A1_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      testAssertString.isEqualTo(TEST_A1_JSON);
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A2_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertString.isEqualTo(TEST_A2_JSON));
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_THEN_normalize_object_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useActResult(TEST_A1_JSON);

      // Act
      testAssertString.isEqualTo(TEST_A1_JSON);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_isEqualTo_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> testAssertString.isEqualTo(null));
    }
  }

  @Nested
  class hasLength {

    @Test
    void GIVEN_A1_WHEN_hasLength_THEN_success() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      testAssertString.hasLength(TEST_A1_JSON.length());
    }

    @Test
    void GIVEN_A1_WHEN_hasLength10_THEN_failed() {
      // Arrange
      useActResult(TEST_A1_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertString.hasLength(10));
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_headers_THEN_return_expected_class() {
      // Act
      var headers = testAssertString.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
