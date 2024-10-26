package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head;

import lombok.NonNull;

/**
 * This interface defines a set of assertions for validating HTTP response headers in a testing
 * context. It provides methods to check for the presence of specific headers, validate their
 * absence, and assert that their values match expected values.
 *
 * <p>These assertions are particularly useful for verifying the correctness of HTTP responses in a
 * Spring web application context, especially when working with the {@code MockMvc} framework.
 *
 * @since 1.0.0
 */
public interface TestAssertHead {

  /**
   * Asserts that the HTTP response contains the specified header.
   *
   * <p>This method checks if the response includes the header with the given key.
   *
   * @param expectedKey the key of the header to check (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code expectedKey} is {@code null}
   * @since 1.0.0
   */
  TestAssertHead assertHeadContains(@NonNull String expectedKey);

  /**
   * Asserts that the HTTP response does not contain the specified header.
   *
   * <p>This method checks if the response does not include the header with the given key.
   *
   * @param notExpectedKey the key of the header that should not be present (must not be {@code
   *     null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code notExpectedKey} is {@code null}
   * @since 1.0.0
   */
  TestAssertHead assertHeadNotContains(@NonNull String notExpectedKey);

  /**
   * Asserts that the specified header in the HTTP response matches the expected value.
   *
   * <p>This method compares the value of the response header with the expected value.
   *
   * @param expectedKey the key of the header to check (must not be {@code null})
   * @param expectedValue the expected value of the header
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code expectedKey} is {@code null}
   * @since 1.0.0
   */
  TestAssertHead assertHeadEquals(@NonNull String expectedKey, String expectedValue);
}
