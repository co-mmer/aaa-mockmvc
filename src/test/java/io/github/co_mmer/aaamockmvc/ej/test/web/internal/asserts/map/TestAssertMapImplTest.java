package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_OBJECT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A2_A3;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestAssertMapImplTest extends TestAssertBase {

  private TestAssertMapImpl<Integer, TestObjectSimple> testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.testAssert = new TestAssertMapImpl<>(context);
  }

  @Nested
  class callConstructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> new TestAssertMapImpl<>(null));
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_A1_A2_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      useActResult(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      testAssert.isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_OBJECT})
    void GIVEN_empty_WHEN_isNotEmpty_THEN_failed(String value) {
      // Arrange
      useActResult(value);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::isNotEmpty);
    }
  }

  @Nested
  class isEmpty {

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_OBJECT})
    void GIVEN_blank_WHEN_isEmpty_THEN_success(String value) {
      // Arrange
      useActResult(value);

      // Act & Assert
      testAssert.isEmpty();
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEmpty_THEN_failed() {
      // Arrange
      useActResult(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, testAssert::isEmpty);
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_A1_A2_WHEN_isEqualTo_THEN_success() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      testAssert.isEqualTo(TEST_MAP_A1_A2);
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEqualTo_A1_A3_THEN_failed() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.isEqualTo(TEST_MAP_A2_A3));
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEqualTo_THEN_normalizeMapIsCalled() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useAssertResult(TEST_MAP_A1_A2);

      // Act
      testAssert.isEqualTo(TEST_MAP_A1_A2);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeMap(any()), times(2));
      mockTestArrangeNormalizer.close();
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_isEqualTo_THEN_throwException() {
      // Act & Assert
      assertThrows(NullPointerException.class, () -> testAssert.isEqualTo(null));
    }
  }

  @Nested
  class hasSize {

    @Test
    void GIVEN_A1_A2_WHEN_hasSize2_THEN_success() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      testAssert.hasSize(2);
    }

    @Test
    void GIVEN_A1_A2_WHEN_hasSize1_THEN_failed() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.hasSize(1));
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_headers_THEN_return_expected_class() {
      // Act
      var headers = testAssert.headers();

      // Assert
      assertThat(headers.getClass(), is(TestAssertHeadImpl.class));
    }
  }

  @Nested
  class combinationIsNotEmpty {

    @Test
    void isNotEmpty_isEqualTo() {
      // Arrange
      useActResult(TEST_MAP_A1_A2_JSON);
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      testAssert.isNotEmpty().isEqualTo(TEST_MAP_A1_A2);
    }
  }

  @Nested
  class combinationIsEmpty {

    @Test
    void isEmpty_headers() {
      // Arrange
      useActResult(EMPTY_OBJECT);

      // Act & Assert
      testAssert.isEmpty().headers();
    }
  }

  @Nested
  class combinationHasSize {

    @Test
    void hasSize_isEqualTo() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      testAssert.hasSize(2).isEqualTo(TEST_MAP_A1_A2);
    }

    @Test
    void hasSize_headers() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      testAssert.hasSize(2).headers();
    }
  }
}
