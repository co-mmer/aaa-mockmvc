package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.bool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S2699")
class TestAssertBooleanImplTest extends TestAssertBase {

  private TestAssertBooleanImpl testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
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
      assertThrows(AssertionError.class, testAssert::isNotNull);
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
      assertThrows(AssertionError.class, testAssert::isNull);
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
      assertThrows(AssertionError.class, testAssert::isTrue);
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
      assertThrows(AssertionError.class, testAssert::isFalse);
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
