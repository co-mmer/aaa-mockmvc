package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_AUTH;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_KEY_CONTENT_TYPE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.HEADER_VALUE_TOKEN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S2699")
class TestAssertHeadImplTest extends TestAssertBase {

  private TestAssertHead testAssert;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.testAssert = new TestAssertHeadImpl(context);
  }

  @Nested
  class containsKey {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_containsKey_THEN_throw_Exception() {
      assertThrows(IllegalArgumentException.class, () -> testAssert.containsKey(null));
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
      var ex =
          assertThrows(AssertionError.class, () -> testAssert.containsKey(HEADER_KEY_CONTENT_TYPE));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class doesNotContainKey {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_doesNotContainKey_THEN_throw_Exception() {
      assertThrows(IllegalArgumentException.class, () -> testAssert.doesNotContainKey(null));
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
      var ex =
          assertThrows(AssertionError.class, () -> testAssert.doesNotContainKey(HEADER_KEY_AUTH));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class containsEntry {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_containsEntry_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> testAssert.containsEntry(null, null));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_keyIsNull_WHEN_containsEntry_THEN_throwException() {
      assertThrows(
          IllegalArgumentException.class, () -> testAssert.containsEntry(null, HEADER_VALUE_TOKEN));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_valueIsNull_WHEN_containsEntry_THEN_throwException() {
      assertThrows(
          IllegalArgumentException.class, () -> testAssert.containsEntry(HEADER_KEY_AUTH, null));
    }

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
      var ex =
          assertThrows(
              AssertionError.class,
              () -> testAssert.containsEntry(HEADER_KEY_AUTH, HEADER_VALUE_JSON));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_key1_value1_WHEN_containsEntry_key2_value1_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class,
              () -> testAssert.containsEntry(HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_TOKEN));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  @SuppressWarnings("all")
  class containsEntryExactly {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_containsEntryExactly_THEN_throwException() {
      assertThrows(
          IllegalArgumentException.class, () -> testAssert.containsEntryExactly(null, null));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_keyIsNull_WHEN_containsEntryExactly_THEN_throwException() {
      assertThrows(
          IllegalArgumentException.class,
          () -> testAssert.containsEntryExactly(null, HEADER_VALUE_TOKEN));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_valueIsNull_WHEN_containsEntryExactly_THEN_throwException() {
      assertThrows(
          IllegalArgumentException.class,
          () -> testAssert.containsEntryExactly(HEADER_KEY_AUTH, null));
    }

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
      var ex =
          assertThrows(
              AssertionError.class,
              () -> testAssert.containsEntryExactly(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key2_value1_value2_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class,
              () ->
                  testAssert.containsEntryExactly(
                      HEADER_KEY_CONTENT_TYPE, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_key1_value1_value2_WHEN_containsEntryExactly_key1_THEN_failed() {
      // Arrange
      useActResult(HEADER_KEY_AUTH, HEADER_VALUE_TOKEN, HEADER_VALUE_JSON);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class, () -> testAssert.containsEntryExactly(HEADER_KEY_AUTH));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }
}
