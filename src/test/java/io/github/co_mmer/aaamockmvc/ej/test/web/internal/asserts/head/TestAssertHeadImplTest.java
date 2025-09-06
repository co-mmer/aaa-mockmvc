package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_CONTENT_TYPE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S2699")
class TestAssertHeadImplTest extends TestAssertBase {

  private TestAssertHead testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.testAssert = new TestAssertHeadImpl(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestAssertHeadImpl(null));
    }
  }

  @Nested
  class containsKey {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_containsKey_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> testAssert.containsKey(null));
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsKey_key1_THEN_success() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      testAssert.containsKey(HEADER_KEY_AUTH);
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsKey_key2_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.containsKey(HEADER_KEY_CONTENT_TYPE));
    }
  }

  @Nested
  class doesNotContainKey {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_doesNotContainKey_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> testAssert.doesNotContainKey(null));
    }

    @Test
    void GIVEN_key1_value1_WHEN_doesNotContainKey_key2_THEN_success() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      testAssert.doesNotContainKey(HEADER_KEY_CONTENT_TYPE);
    }

    @Test
    void GIVEN_key1_value1_WHEN_doesNotContainKey_key1_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.doesNotContainKey(HEADER_KEY_AUTH));
    }
  }

  @Nested
  class containsEntry {

    @Test
    void GIVEN_key1_value1_WHEN_containsEntry_key1_value1_THEN_success() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      testAssert.containsEntry(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsEntry_key1_value2_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> testAssert.containsEntry(HEADER_KEY_AUTH, HEADER_VALUE_JSON));
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsEntry_key2_value1_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssert.containsEntry(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_TOKEN));
    }
  }

  @Nested
  class containsEntryExactly {

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_value1_value2_THEN_success() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);

      // Act & Assert
      testAssert.containsEntryExactly(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_value2_value1_THEN_success() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);

      // Act & Assert
      testAssert.containsEntryExactly(HEADER_KEY_AUTH, HEADER_VALUE_JSON, HEADER_VALUE_TOKEN);
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_value1_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssert.containsEntryExactly(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN));
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key2_value1_value2_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssert.containsEntryExactly(
                  HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON));
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.containsEntryExactly(HEADER_KEY_AUTH));
    }
  }
}
