package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

/**
 * Assertions for the HTTP response status code.
 *
 * <p><b>What it does:</b> Provides exact and range-based checks for the status captured during
 * {@code actPerform().perform()}. Use this to verify success, redirects, client/server errors, or
 * specific codes like 200/201/404.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/users/42");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .status()
 *  .isOk();
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed; assertions operate on
 * the stored snapshot.
 *
 * @since 1.1.0
 */
public interface TestAssert1Status {

  /**
   * Asserts that the status equals the given {@link org.springframework.http.HttpStatus}.
   *
   * @param status the expected status; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the actual status differs from {@code status}
   * @since 2.0.0
   */
  TestAssert2Status is(@NonNull HttpStatus status);

  /**
   * Asserts that the status equals the given numeric code.
   *
   * @param status the expected HTTP status code (e.g., 200, 404)
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the actual status differs from {@code status}
   * @since 2.0.0
   */
  TestAssert2Status is(int status);

  /**
   * Asserts that the status is 200 OK.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 200
   * @since 2.0.0
   */
  TestAssert2Status isOk();

  /**
   * Asserts that the status is 201 Created.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 201
   * @since 2.0.0
   */
  TestAssert2Status isCreated();

  /**
   * Asserts that the status is 202 Accepted.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 202
   * @since 2.0.0
   */
  TestAssert2Status isAccepted();

  /**
   * Asserts that the status is 404 Not Found.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 404
   * @since 2.0.0
   */
  TestAssert2Status isNotFound();

  /**
   * Asserts that the status is in the 2xx Successful range.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 2xx
   * @since 2.0.0
   */
  TestAssert2Status is2xxSuccessful();

  /**
   * Asserts that the status is in the 3xx Redirection range.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 3xx
   * @since 2.0.0
   */
  TestAssert2Status is3xxRedirect();

  /**
   * Asserts that the status is in the 4xx Client Error range.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 4xx
   * @since 2.0.0
   */
  TestAssert2Status is4xxClientError();

  /**
   * Asserts that the status is in the 5xx Server Error range.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 5xx
   * @since 2.0.0
   */
  TestAssert2Status is5xxServerError();

  /**
   * Asserts that the status is 403 Forbidden.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 403
   * @since 2.0.0
   */
  TestAssert2Status isForbidden();

  /**
   * Asserts that the status is 401 Unauthorized.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the status is not 401
   * @since 2.0.0
   */
  TestAssert2Status isUnauthorized();

  /**
   * Asserts that the status code is within the given inclusive range.
   *
   * @param minStatusCode the lower bound (inclusive), e.g., 200
   * @param maxStatusCode the upper bound (inclusive), e.g., 299
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws IllegalArgumentException if {@code minStatusCode > maxStatusCode}
   * @throws AssertionError if the status is outside {@code [minStatusCode, maxStatusCode]}
   * @since 2.0.0
   */
  TestAssert2Status isInRange(int minStatusCode, int maxStatusCode);
}
