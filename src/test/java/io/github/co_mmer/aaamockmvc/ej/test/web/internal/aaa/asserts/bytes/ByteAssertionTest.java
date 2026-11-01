package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class ByteAssertionTest {

  private static final String EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response body must contain zero bytes.";
  private static final String NOT_EMPTY_ASSERTION_MESSAGE =
      "Assertion: The response body must contain at least one byte.";
  private static final String LENGTH_ASSERTION_MESSAGE =
      "Assertion: The response body must contain the expected number of bytes.";
  private static final String EQUALITY_ASSERTION_MESSAGE =
      "Assertion: The response bytes must match the expected bytes in both value and order.";
  private static final String STEP_NAME = "Load bytes";

  @Nested
  class isEmpty {

    @Test
    void GIVEN_empty_WHEN_isEmpty_THEN_success() {
      // Arrange
      var actual = new byte[0];
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isEmpty);
    }

    @Test
    void GIVEN_non_empty_WHEN_isEmpty_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "empty"));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(EMPTY_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_non_empty_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotEmpty);
    }

    @Test
    void GIVEN_empty_WHEN_isNotEmpty_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[0];
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNotEmpty);

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) "not empty"));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(NOT_EMPTY_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class hasLength {

    @Test
    void GIVEN_matching_length_WHEN_hasLength_THEN_success() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasLength(2));
    }

    @Test
    void GIVEN_empty_matching_length_WHEN_hasLength_THEN_success() {
      // Arrange
      var actual = new byte[0];
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.hasLength(0));
    }

    @Test
    void GIVEN_different_length_WHEN_hasLength_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasLength(1));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) 1));
      assertThat(exception.getActual().getValue(), is((Object) actual.length));
      assertThat(exception.getMessage(), containsString(LENGTH_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_empty_different_length_WHEN_hasLength_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[0];
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasLength(1));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) 1));
      assertThat(exception.getActual().getValue(), is((Object) actual.length));
      assertThat(exception.getMessage(), containsString(LENGTH_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_non_empty_expected_zero_WHEN_hasLength_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1};
      var assertion = new ByteAssertion(new ByteValue(actual), null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.hasLength(0));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) 0));
      assertThat(exception.getActual().getValue(), is((Object) actual.length));
      assertThat(exception.getMessage(), containsString(LENGTH_ASSERTION_MESSAGE));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_same_bytes_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[] {1, 2});

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(expected));
    }

    @Test
    void GIVEN_both_empty_WHEN_isEqualTo_THEN_success() {
      // Arrange
      var actual = new byte[0];
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[0]);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isEqualTo(expected));
    }

    @Test
    void GIVEN_different_values_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[] {1, 3});

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) expected.value()));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_different_order_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[] {2, 1});

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) expected.value()));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_different_length_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[] {1});

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) expected.value()));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_different_repetitions_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1, 1, 2};
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[] {1, 2, 2});

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) expected.value()));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_empty_actual_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[0];
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[] {1});

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) expected.value()));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }

    @Test
    void GIVEN_empty_expected_WHEN_isEqualTo_THEN_assertion_failed() {
      // Arrange
      var actual = new byte[] {1};
      var assertion = new ByteAssertion(new ByteValue(actual), null);
      var expected = new ByteValue(new byte[0]);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.isEqualTo(expected));

      // Assert
      assertThat(exception.getExpected().getValue(), is((Object) expected.value()));
      assertThat(exception.getActual().getValue(), is((Object) actual));
      assertThat(exception.getMessage(), containsString(EQUALITY_ASSERTION_MESSAGE));
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new ByteAssertion(new ByteValue(new byte[] {1}), new TestStepDto(STEP_NAME));

    // Act
    var exception = assertThrows(AssertionFailedError.class, assertion::isEmpty);

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertThat(exception.getMessage(), containsString(EMPTY_ASSERTION_MESSAGE));
  }
}
