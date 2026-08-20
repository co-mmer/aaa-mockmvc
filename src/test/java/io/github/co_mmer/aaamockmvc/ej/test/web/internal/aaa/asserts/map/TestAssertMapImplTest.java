package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_OBJECT;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.MAP_WITH_NORMALIZED_KEY_COLLISION;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A2_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("java:S2699")
class TestAssertMapImplTest extends TestAssertBase {

  private TestAssertMapImpl<Integer, TestObjectSimple> impl;
  private TestAssertMapImpl<String, String> implWithString;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.impl = new TestAssertMapImpl<>(context);
    this.implWithString = new TestAssertMapImpl<>(context);
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_A1_A2_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      useActResult(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      impl.isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_OBJECT})
    void GIVEN_empty_WHEN_isNotEmpty_THEN_failed(String value) {
      // Arrange
      useActResult(value);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, impl::isNotEmpty);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
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
      impl.isEmpty();
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEmpty_THEN_failed() {
      // Arrange
      useActResult(TEST_MAP_A1_A2_JSON);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, impl::isEmpty);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class hasSize {

    @Test
    void GIVEN_A1_A2_WHEN_hasSize2_THEN_success() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      impl.hasSize(2);
    }

    @Test
    void GIVEN_A1_A2_WHEN_hasSize1_THEN_failed() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> impl.hasSize(1));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_isEqualTo_THEN_throw_Exception() {
      assertThrows(IllegalArgumentException.class, () -> impl.isEqualTo(null));
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEqualTo_THEN_success() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      impl.isEqualTo(TEST_MAP_A1_A2);
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEqualTo_A1_A3_THEN_failed() {
      // Arrange
      useAssertResult(TEST_MAP_A1_A2);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> impl.isEqualTo(TEST_MAP_A2_A3));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_collidingKeys_WHEN_isEqualTo_THEN_throw_Exception() {
      // Assert
      useAssertResult(MAP_WITH_NORMALIZED_KEY_COLLISION);

      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> implWithString.isEqualTo(MAP_WITH_NORMALIZED_KEY_COLLISION));

      // Assert
      assertThat(ex.getMessage(), containsString("Key collision after NFC normalization:"));
      assertThat(ex.getMessage(), containsString("\"Café\" [U+0043 U+0061 U+0066 U+00E9]"));
      assertThat(ex.getMessage(), containsString("and"));
      assertThat(ex.getMessage(), containsString("\"Café\" [U+0043 U+0061 U+0066 U+0065 U+0301]"));
    }

    @Test
    void WHEN_isEqualTo_THEN_normalizedValue_is_called() {
      // Arrange
      var realUnexpectedValue = AssertValue.expectedMap(TEST_MAP_A1_A2);
      var spyUnexpectedValue = spy(realUnexpectedValue);

      var mockAssertValue = mockStatic(AssertValue.class);
      mockAssertValue
          .when(() -> AssertValue.expectedMap(TEST_MAP_A1_A2))
          .thenReturn(spyUnexpectedValue);

      useAssertResult(TEST_MAP_A1_A2);

      // Act
      impl.isEqualTo(TEST_MAP_A1_A2);

      // Assert
      verify(spyUnexpectedValue).normalizedValue();
      mockAssertValue.close();
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
