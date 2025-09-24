package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

/**
 * Assertions for the response body as a {@link String}.
 *
 * <p><b>What it does:</b> Provides string-specific checks such as emptiness, length, and equality
 * on the cached response body produced by {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/text");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asString()
 *  .isNotEmpty()
 *  .isEqualTo("aaa");
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed. If no body is present,
 * the cached string representation is empty ({@code ""}).
 *
 * @since 1.0.0
 */
public interface TestAssert1String extends OperationEquals {

  /**
   * Asserts that the body string is not empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the string is empty
   * @since 2.0.0
   */
  TestAssert2String isNotEmpty();

  /**
   * Asserts that the body string is empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the string is not empty
   * @since 2.0.0
   */
  TestAssertLString isEmpty();

  /**
   * Asserts that the body string has the given length (in characters).
   *
   * @param expectedLength the expected number of characters
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the length differs from {@code expectedLength}
   * @since 2.0.0
   */
  TestAssert2String hasLength(int expectedLength);
}
