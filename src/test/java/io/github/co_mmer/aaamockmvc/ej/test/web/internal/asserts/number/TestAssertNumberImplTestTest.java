package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.number;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_EVEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_NEGATIVE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_ODD;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_ONE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_POSITIVE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_TWO;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_INTEGER_ZERO;
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
class TestAssertNumberImplTestTest extends TestAssertBase {

  private TestAssertNumberImpl impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.impl = new TestAssertNumberImpl(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestAssertNumberImpl(null));
    }
  }

  @Nested
  class isNull {

    @Test
    void GIVEN_null_WHEN_isNull_THEN_success() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      impl.isNull();
    }

    @Test
    void GIVEN_noNull_WHEN_isNull_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNull);
    }
  }

  @Nested
  class isNotNull {

    @Test
    void GIVEN_noNull_WHEN_isNotNull_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      impl.isNotNull();
    }

    @Test
    void GIVEN_null_WHEN_isNotNull_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNotNull);
    }
  }

  @Nested
  class isPositive {

    @Test
    void GIVEN_positive_WHEN_isPositive_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_POSITIVE);

      // Act & Assert
      impl.isPositive();
    }

    @Test
    void GIVEN_negative_WHEN_isPositive_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_NEGATIVE);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isPositive);
    }

    @Test
    void GIVEN_null_WHEN_isPositive_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isPositive);
    }
  }

  @Nested
  class isNonPositive {

    @Test
    void GIVEN_negative_WHEN_isNonPositive_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_NEGATIVE);

      // Act & Assert
      impl.isNonPositive();
    }

    @Test
    void GIVEN_positive_WHEN_isNonPositive_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_POSITIVE);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNonPositive);
    }

    @Test
    void GIVEN_null_WHEN_isNonPositive_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNonPositive);
    }
  }

  @Nested
  class isNegative {

    @Test
    void GIVEN_negative_WHEN_isNegative_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_NEGATIVE);

      // Act & Assert
      impl.isNegative();
    }

    @Test
    void GIVEN_positive_WHEN_isNegative_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_POSITIVE);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNegative);
    }

    @Test
    void GIVEN_null_WHEN_isNegative_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNegative);
    }
  }

  @Nested
  class isNoNegative {

    @Test
    void GIVEN_positive_WHEN_isNoNegative_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_POSITIVE);

      // Act & Assert
      impl.isNoNegative();
    }

    @Test
    void GIVEN_negative_WHEN_isNoNegative_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_NEGATIVE);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNoNegative);
    }

    @Test
    void GIVEN_null_WHEN_isNoNegative_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNoNegative);
    }
  }

  @Nested
  class isZero {

    @Test
    void GIVEN_0_WHEN_isZero_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_ZERO);

      // Act & Assert
      impl.isZero();
    }

    @Test
    void GIVEN_1_WHEN_isZero_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.isZero());
    }
  }

  @Nested
  class isNotEqualTo {

    @Test
    void GIVEN_1_WHEN_isNotEqualTo_2_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      impl.isNotEqualTo(TEST_INTEGER_TWO);
    }

    @Test
    void GIVEN_1_WHEN_isNotEqualTo_1_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.isNotEqualTo(TEST_INTEGER_ONE));
    }

    @Test
    void GIVEN_null_WHEN_isNotEqualTo_1_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.isNotEqualTo(TEST_INTEGER_ONE));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_1_WHEN_isNotEqualTo_null_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      assertThrows(NullPointerException.class, () -> impl.isNotEqualTo(null));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_1_WHEN_isEqualTo_1_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      impl.isEqualTo(TEST_INTEGER_ONE);
    }

    @Test
    void GIVEN_1_WHEN_isEqualTo_2_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.isEqualTo(TEST_INTEGER_TWO));
    }

    @Test
    void GIVEN_null_WHEN_isEqualTo_1_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.isEqualTo(TEST_INTEGER_ONE));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_1_WHEN_isEqualTo_null_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_ONE);

      // Act & Assert
      assertThrows(NullPointerException.class, () -> impl.isEqualTo(null));
    }
  }

  @Nested
  class isEven {

    @Test
    void GIVEN_even_WHEN_isEven_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_EVEN);

      // Act & Assert
      impl.isEven();
    }

    @Test
    void GIVEN_odd_WHEN_isEven_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_ODD);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isEven);
    }

    @Test
    void GIVEN_null_WHEN_isEven_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isEven);
    }
  }

  @Nested
  class isOdd {

    @Test
    void GIVEN_odd_WHEN_isOdd_THEN_success() {
      // Arrange
      useAssertResult(TEST_INTEGER_ODD);

      // Act & Assert
      impl.isOdd();
    }

    @Test
    void GIVEN_even_WHEN_isOdd_THEN_failed() {
      // Arrange
      useAssertResult(TEST_INTEGER_EVEN);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isOdd);
    }

    @Test
    void GIVEN_null_WHEN_isEven_THEN_failed() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, impl::isOdd);
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_headers_THEN_return_expected_class() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
