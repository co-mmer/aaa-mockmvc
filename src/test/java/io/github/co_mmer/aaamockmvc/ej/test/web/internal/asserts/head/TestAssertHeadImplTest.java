package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_KEY_2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.TEST_HEAD_VALUE_2;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
    void GIVEN_null_WHEN_call_constructor_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> new TestAssertHeadImpl(null));
    }
  }

  @Nested
  class containsKey {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_containsKey_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> testAssert.containsKey(null));
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsKey_key1_THEN_success() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

      // Act & Assert
      testAssert.containsKey(TEST_HEAD_KEY_1);
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsKey_key2_THEN_failed() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.containsKey(TEST_HEAD_KEY_2));
    }
  }

  @Nested
  class doesNotContainKey {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_doesNotContainKey_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> testAssert.doesNotContainKey(null));
    }

    @Test
    void GIVEN_key1_value1_WHEN_doesNotContainKey_key2_THEN_success() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

      // Act & Assert
      testAssert.doesNotContainKey(TEST_HEAD_KEY_2);
    }

    @Test
    void GIVEN_key1_value1_WHEN_doesNotContainKey_key1_THEN_failed() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.doesNotContainKey(TEST_HEAD_KEY_1));
    }
  }

  @Nested
  class containsEntry {

    @Test
    void GIVEN_key1_value1_WHEN_containsEntry_key1_value1_THEN_success() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

      // Act & Assert
      testAssert.containsEntry(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsEntry_key1_value2_THEN_failed() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> testAssert.containsEntry(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_2));
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsEntry_key2_value1_THEN_failed() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> testAssert.containsEntry(TEST_HEAD_KEY_2, TEST_HEAD_VALUE_1));
    }
  }

  @Nested
  class containsEntryExactly {

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_value1_value2_THEN_success() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1, TEST_HEAD_VALUE_2);

      // Act & Assert
      testAssert.containsEntryExactly(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1, TEST_HEAD_VALUE_2);
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_value2_value1_THEN_success() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1, TEST_HEAD_VALUE_2);

      // Act & Assert
      testAssert.containsEntryExactly(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_2, TEST_HEAD_VALUE_1);
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_value1_THEN_failed() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1, TEST_HEAD_VALUE_2);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssert.containsEntryExactly(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1));
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key2_value1_value2_THEN_failed() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1, TEST_HEAD_VALUE_2);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssert.containsEntryExactly(
                  TEST_HEAD_KEY_2, TEST_HEAD_VALUE_1, TEST_HEAD_VALUE_2));
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_THEN_failed() {
      // Arrange
      useActResult(TEST_HEAD_KEY_1, TEST_HEAD_VALUE_1, TEST_HEAD_VALUE_2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssert.containsEntryExactly(TEST_HEAD_KEY_1));
    }
  }
}
