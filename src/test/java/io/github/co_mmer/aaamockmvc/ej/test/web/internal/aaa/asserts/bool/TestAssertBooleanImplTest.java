package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S2699")
class TestAssertBooleanImplTest extends TestAssertBase {

  private TestAssertBooleanImpl testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.testAssert = new TestAssertBooleanImpl(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestAssertBooleanImpl(null));
    }
  }

  @Nested
  class isNotNull {

    @Test
    void GIVEN_True_WHEN_isNotNull_THEN_assert_true() {
      // Arrange
      useAssertResult(Boolean.TRUE);

      // Act & Assert
      testAssert.isNotNull();
    }

    @Test
    void GIVEN_null_WHEN_isNotNull_THEN_assert_false() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, testAssert::isNotNull);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isNull {

    @Test
    void GIVEN_null_WHEN_isNull_THEN_assert_true() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      testAssert.isNull();
    }

    @Test
    void GIVEN_True_WHEN_isNull_THEN_return_assert_false() {
      // Arrange
      useAssertResult(Boolean.TRUE);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, testAssert::isNull);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isTrue {

    @Test
    void GIVEN_true_WHEN_isTrue_THEN_assert_true() {
      // Arrange
      useAssertResult(Boolean.TRUE);

      // Act & Assert
      testAssert.isTrue();
    }

    @Test
    void GIVEN_False_WHEN_isTrue_THEN_return_assert_false() {
      // Arrange
      useAssertResult(Boolean.FALSE);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, testAssert::isTrue);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isFalse {

    @Test
    void GIVEN_False_WHEN_isFalse_THEN_assert_true() {
      // Arrange
      useAssertResult(Boolean.FALSE);

      // Act & Assert
      testAssert.isFalse();
    }

    @Test
    void GIVEN_True_WHEN_isFalse_THEN_return_assert_false() {
      // Arrange
      useAssertResult(Boolean.TRUE);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, testAssert::isFalse);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_False_WHEN_isEqualTo_THEN_assert_true() {
      // Arrange
      useAssertResult(Boolean.FALSE);

      // Act & Assert
      testAssert.isEqualTo(Boolean.FALSE);
    }

    @Test
    void GIVEN_True_WHEN_isEqualTo_THEN_return_assert_false() {
      // Arrange
      useAssertResult(Boolean.TRUE);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> testAssert.isEqualTo(Boolean.FALSE));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_isEqualTo_THEN_throw_NullPointerException() {
      // Act & Assert
      assertThrows(NullPointerException.class, () -> testAssert.isEqualTo(null));
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
