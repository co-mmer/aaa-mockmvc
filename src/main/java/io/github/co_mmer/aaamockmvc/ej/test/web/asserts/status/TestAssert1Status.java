package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

/**
 * This interface defines a set of assertions for validating the status of HTTP responses in a
 * testing context. It allows various checks on the response status, including specific status code
 * assertions and range validations.
 *
 * @since 1.1.0
 */
public interface TestAssert1Status {

  /**
   * Asserts that the HTTP response status matches the given {@code HttpStatus}.
   *
   * @param status the expected {@code HttpStatus} of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatus(@NonNull HttpStatus status);

  /**
   * Asserts that the HTTP response status matches the given status code.
   *
   * @param status the expected status code of the response
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatus(int status);

  /**
   * Asserts that the HTTP response status is 200 OK.
   *
   * <p>This method checks if the response status is HTTP 200 (OK). If the status does not match, an
   * assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsOk();

  /**
   * Asserts that the HTTP response status is 201 Created.
   *
   * <p>This method checks if the response status is HTTP 201 (Created). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsCreated();

  /**
   * Asserts that the HTTP response status is 202 Accepted.
   *
   * <p>This method checks if the response status is HTTP 202 (Accepted). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsAccepted();

  /**
   * Asserts that the HTTP response status is 404 Not Found.
   *
   * <p>This method checks if the response status is HTTP 404 (Not Found). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsNotFound();

  /**
   * Asserts that the HTTP response status indicates a client error (4xx).
   *
   * <p>This method checks if the response status code is within the 400-499 range. If the status
   * does not match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsClientError();

  /**
   * Asserts that the HTTP response status indicates a server error (5xx).
   *
   * <p>This method checks if the response status code is within the 500-599 range. If the status
   * does not match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsServerError();

  /**
   * Asserts that the HTTP response status indicates a redirection (3xx).
   *
   * <p>This method checks if the response status code is within the 300-399 range. If the status
   * does not match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsRedirect();

  /**
   * Asserts that the HTTP response status is 403 Forbidden.
   *
   * <p>This method checks if the response status is HTTP 403 (Forbidden). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsAccessForbidden();

  /**
   * Asserts that the HTTP response status is 401 Unauthorized.
   *
   * <p>This method checks if the response status is HTTP 401 (Unauthorized). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusIsAccessUnauthorized();

  /**
   * Asserts that the HTTP response status code is within a specified range.
   *
   * <p>This method checks if the status code of the response falls between the specified minimum
   * and maximum values (inclusive). If the actual status code does not fall within the range, an
   * assertion failure is triggered.
   *
   * @param minStatusCode the minimum expected status code (must be less than or equal to
   *     maxStatusCode)
   * @param maxStatusCode the maximum expected status code (must be greater than or equal to
   *     minStatusCode)
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  TestAssert2Status assertStatusInRange(int minStatusCode, int maxStatusCode);
}
