package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool;

/**
 * Entry point for raw <b>Boolean</b> content assertions.
 *
 * <p><b>What it does:</b> Exposes assertions for a response body that has been deserialized to a
 * {@link Boolean}. Use this when an endpoint returns a plain boolean payload (e.g., feature flags,
 * success toggles).
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/feature/enabled");
 *
 * act()
 *   .perform();
 *
 * asserts()
 *   .content()
 *   .asBoolean()
 *   .isTrue();
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code act().perform()} has been executed; assertions operate on the
 * stored snapshot.
 *
 * @since 2.0.0
 */
public interface TestAssert1Boolean {

  /**
   * Asserts that the actual value is {@code null}.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the value is not {@code null}
   * @since 2.0.0
   */
  TestAssert4Boolean isNull();

  /**
   * Asserts that the actual value is not {@code null}.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the value is {@code null}
   * @since 2.0.0
   */
  TestAssert3Boolean isNotNull();

  /**
   * Asserts that the actual value is {@code Boolean.TRUE}.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the value is not {@code true}
   * @since 2.0.0
   */
  TestAssert4Boolean isTrue();

  /**
   * Asserts that the actual value is {@code Boolean.FALSE}.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   *     based on the current state
   * @throws AssertionError if the value is not {@code false}
   * @since 2.0.0
   */
  TestAssert4Boolean isFalse();
}
