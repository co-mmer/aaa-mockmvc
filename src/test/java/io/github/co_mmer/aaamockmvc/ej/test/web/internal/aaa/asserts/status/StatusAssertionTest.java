package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class StatusAssertionTest {

  private static final StatusValue ACTUAL_STATUS_199 = new StatusValue(199);

  private static final StatusValue ACTUAL_STATUS_200 = new StatusValue(200);

  private static final StatusValue ACTUAL_STATUS_201 = new StatusValue(201);

  private static final StatusValue ACTUAL_STATUS_202 = new StatusValue(202);

  private static final StatusValue ACTUAL_STATUS_250 = new StatusValue(250);

  private static final StatusValue ACTUAL_STATUS_299 = new StatusValue(299);

  private static final StatusValue ACTUAL_STATUS_300 = new StatusValue(300);

  private static final StatusValue ACTUAL_STATUS_350 = new StatusValue(350);

  private static final StatusValue ACTUAL_STATUS_399 = new StatusValue(399);

  private static final StatusValue ACTUAL_STATUS_400 = new StatusValue(400);

  private static final StatusValue ACTUAL_STATUS_401 = new StatusValue(401);

  private static final StatusValue ACTUAL_STATUS_403 = new StatusValue(403);

  private static final StatusValue ACTUAL_STATUS_404 = new StatusValue(404);

  private static final StatusValue ACTUAL_STATUS_450 = new StatusValue(450);

  private static final StatusValue ACTUAL_STATUS_499 = new StatusValue(499);

  private static final StatusValue ACTUAL_STATUS_500 = new StatusValue(500);

  private static final StatusValue ACTUAL_STATUS_550 = new StatusValue(550);

  private static final StatusValue ACTUAL_STATUS_599 = new StatusValue(599);

  private static final StatusValue ACTUAL_STATUS_600 = new StatusValue(600);

  private static final StatusValue EXPECTED_OK = new StatusValue(200);
  private static final StatusValue EXPECTED_UNAUTHORIZED = new StatusValue(401);
  private static final StatusValue EXPECTED_ZERO = new StatusValue(0);
  private static final StatusRange EXPECTED_RANGE = new StatusRange(200, 299);
  private static final StatusRange EXPECTED_SINGLE_STATUS_RANGE = new StatusRange(200, 200);
  private static final String RANGE_EXPECTATION = "[200, 299]";
  private static final String SINGLE_STATUS_RANGE_EXPECTATION = "[200, 200]";
  private static final String STEP_NAME = "Load status";

  private static final String STATUS_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must equal the expected status code.";

  private static final String RANGE_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must be within the expected range, including both bounds.";

  private static final String OK_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must be 200 (OK).";

  private static final String CREATED_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must be 201 (Created).";

  private static final String ACCEPTED_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must be 202 (Accepted).";

  private static final String NOT_FOUND_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must be 404 (Not Found).";

  private static final String FORBIDDEN_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must be 403 (Forbidden).";

  private static final String UNAUTHORIZED_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must be 401 (Unauthorized).";

  private static final String SUCCESSFUL_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must indicate success within the expected inclusive range.";

  private static final String REDIRECT_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must indicate a redirection within the expected inclusive range.";

  private static final String CLIENT_ERROR_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must indicate a client error within the expected inclusive range.";

  private static final String SERVER_ERROR_ASSERTION_MESSAGE =
      "Assertion: The HTTP response status must indicate a server error within the expected inclusive range.";

  private static final String SUCCESSFUL_RANGE_EXPECTATION = "[200, 299]";

  private static final String REDIRECT_RANGE_EXPECTATION = "[300, 399]";

  private static final String CLIENT_ERROR_RANGE_EXPECTATION = "[400, 499]";

  private static final String SERVER_ERROR_RANGE_EXPECTATION = "[500, 599]";

  private void assertFailure(
      AssertionFailedError exception, Object expected, Object actual, String assertionMessage) {
    assertThat(exception.getExpected().getValue(), is(expected));
    assertThat(exception.getActual().getValue(), is(actual));
    assertThat(exception.getMessage(), containsString(assertionMessage));
  }

  @Nested
  class is {

    @Test
    void GIVEN_matching_status_WHEN_is_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.is(EXPECTED_OK));
    }

    @Test
    void GIVEN_different_status_WHEN_is_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.is(EXPECTED_UNAUTHORIZED));

      // Assert
      assertFailure(exception, 401, 200, STATUS_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_zero_expected_WHEN_is_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, () -> assertion.is(EXPECTED_ZERO));

      // Assert
      assertFailure(exception, 0, 200, STATUS_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isInRange {

    @Test
    void GIVEN_lower_bound_WHEN_isInRange_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isInRange(EXPECTED_RANGE));
    }

    @Test
    void GIVEN_upper_bound_WHEN_isInRange_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_299, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isInRange(EXPECTED_RANGE));
    }

    @Test
    void GIVEN_inside_range_WHEN_isInRange_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_250, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isInRange(EXPECTED_RANGE));
    }

    @Test
    void GIVEN_below_range_WHEN_isInRange_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_199, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isInRange(EXPECTED_RANGE));

      // Assert
      assertFailure(exception, RANGE_EXPECTATION, 199, RANGE_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_above_range_WHEN_isInRange_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_300, null);

      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> assertion.isInRange(EXPECTED_RANGE));

      // Assert
      assertFailure(exception, RANGE_EXPECTATION, 300, RANGE_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_single_status_matches_WHEN_isInRange_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act & Assert
      assertDoesNotThrow(() -> assertion.isInRange(EXPECTED_SINGLE_STATUS_RANGE));
    }

    @Test
    void GIVEN_single_status_differs_WHEN_isInRange_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_201, null);

      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> assertion.isInRange(EXPECTED_SINGLE_STATUS_RANGE));

      // Assert
      assertFailure(exception, SINGLE_STATUS_RANGE_EXPECTATION, 201, RANGE_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isOk {

    @Test
    void GIVEN_matching_status_WHEN_isOk_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isOk);
    }

    @Test
    void GIVEN_different_status_WHEN_isOk_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_401, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isOk);

      // Assert
      assertFailure(exception, 200, 401, OK_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isCreated {

    @Test
    void GIVEN_matching_status_WHEN_isCreated_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_201, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isCreated);
    }

    @Test
    void GIVEN_different_status_WHEN_isCreated_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isCreated);

      // Assert
      assertFailure(exception, 201, 200, CREATED_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isAccepted {

    @Test
    void GIVEN_matching_status_WHEN_isAccepted_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_202, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isAccepted);
    }

    @Test
    void GIVEN_different_status_WHEN_isAccepted_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isAccepted);

      // Assert
      assertFailure(exception, 202, 200, ACCEPTED_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isNotFound {

    @Test
    void GIVEN_matching_status_WHEN_isNotFound_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_404, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isNotFound);
    }

    @Test
    void GIVEN_different_status_WHEN_isNotFound_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isNotFound);

      // Assert
      assertFailure(exception, 404, 200, NOT_FOUND_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isForbidden {

    @Test
    void GIVEN_matching_status_WHEN_isForbidden_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_403, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isForbidden);
    }

    @Test
    void GIVEN_different_status_WHEN_isForbidden_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isForbidden);

      // Assert
      assertFailure(exception, 403, 200, FORBIDDEN_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class isUnauthorized {

    @Test
    void GIVEN_matching_status_WHEN_isUnauthorized_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_401, null);

      // Act & Assert
      assertDoesNotThrow(assertion::isUnauthorized);
    }

    @Test
    void GIVEN_different_status_WHEN_isUnauthorized_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::isUnauthorized);

      // Assert
      assertFailure(exception, 401, 200, UNAUTHORIZED_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class is2xxSuccessful {

    @Test
    void GIVEN_lower_bound_WHEN_is2xxSuccessful_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_200, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is2xxSuccessful);
    }

    @Test
    void GIVEN_upper_bound_WHEN_is2xxSuccessful_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_299, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is2xxSuccessful);
    }

    @Test
    void GIVEN_inside_range_WHEN_is2xxSuccessful_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_250, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is2xxSuccessful);
    }

    @Test
    void GIVEN_below_range_WHEN_is2xxSuccessful_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_199, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is2xxSuccessful);

      // Assert
      assertFailure(exception, SUCCESSFUL_RANGE_EXPECTATION, 199, SUCCESSFUL_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_above_range_WHEN_is2xxSuccessful_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_300, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is2xxSuccessful);

      // Assert
      assertFailure(exception, SUCCESSFUL_RANGE_EXPECTATION, 300, SUCCESSFUL_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class is3xxRedirect {

    @Test
    void GIVEN_lower_bound_WHEN_is3xxRedirect_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_300, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is3xxRedirect);
    }

    @Test
    void GIVEN_upper_bound_WHEN_is3xxRedirect_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_399, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is3xxRedirect);
    }

    @Test
    void GIVEN_inside_range_WHEN_is3xxRedirect_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_350, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is3xxRedirect);
    }

    @Test
    void GIVEN_below_range_WHEN_is3xxRedirect_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_299, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is3xxRedirect);

      // Assert
      assertFailure(exception, REDIRECT_RANGE_EXPECTATION, 299, REDIRECT_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_above_range_WHEN_is3xxRedirect_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_400, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is3xxRedirect);

      // Assert
      assertFailure(exception, REDIRECT_RANGE_EXPECTATION, 400, REDIRECT_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class is4xxClientError {

    @Test
    void GIVEN_lower_bound_WHEN_is4xxClientError_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_400, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is4xxClientError);
    }

    @Test
    void GIVEN_upper_bound_WHEN_is4xxClientError_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_499, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is4xxClientError);
    }

    @Test
    void GIVEN_inside_range_WHEN_is4xxClientError_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_450, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is4xxClientError);
    }

    @Test
    void GIVEN_below_range_WHEN_is4xxClientError_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_399, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is4xxClientError);

      // Assert
      assertFailure(exception, CLIENT_ERROR_RANGE_EXPECTATION, 399, CLIENT_ERROR_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_above_range_WHEN_is4xxClientError_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_500, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is4xxClientError);

      // Assert
      assertFailure(exception, CLIENT_ERROR_RANGE_EXPECTATION, 500, CLIENT_ERROR_ASSERTION_MESSAGE);
    }
  }

  @Nested
  class is5xxServerError {

    @Test
    void GIVEN_lower_bound_WHEN_is5xxServerError_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_500, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is5xxServerError);
    }

    @Test
    void GIVEN_upper_bound_WHEN_is5xxServerError_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_599, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is5xxServerError);
    }

    @Test
    void GIVEN_inside_range_WHEN_is5xxServerError_THEN_success() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_550, null);

      // Act & Assert
      assertDoesNotThrow(assertion::is5xxServerError);
    }

    @Test
    void GIVEN_below_range_WHEN_is5xxServerError_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_499, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is5xxServerError);

      // Assert
      assertFailure(exception, SERVER_ERROR_RANGE_EXPECTATION, 499, SERVER_ERROR_ASSERTION_MESSAGE);
    }

    @Test
    void GIVEN_above_range_WHEN_is5xxServerError_THEN_assertion_failed() {
      // Arrange
      var assertion = new StatusAssertion(ACTUAL_STATUS_600, null);

      // Act
      var exception = assertThrows(AssertionFailedError.class, assertion::is5xxServerError);

      // Assert
      assertFailure(exception, SERVER_ERROR_RANGE_EXPECTATION, 600, SERVER_ERROR_ASSERTION_MESSAGE);
    }
  }

  @Test
  void GIVEN_step_WHEN_assertion_fails_THEN_step_is_included() {
    // Arrange
    var assertion = new StatusAssertion(ACTUAL_STATUS_401, new TestStepDto(STEP_NAME));

    // Act
    var exception = assertThrows(AssertionFailedError.class, assertion::isOk);

    // Assert
    assertThat(exception.getMessage(), containsString(STEP_NAME));
    assertFailure(exception, 200, 401, OK_ASSERTION_MESSAGE);
  }
}
