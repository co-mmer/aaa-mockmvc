package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;

/**
 * Assertions for a deserialized content object of type {@code T}.
 *
 * <p><b>What it does:</b> Provides type-safe checks on the already mapped response body (e.g.,
 * nullability, equality, predicate-based matching). The body was deserialized once in the preceding
 * {@code content().asClass(T)} step and is cached for all subsequent assertions.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/user/42");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asClass(User.class)
 *  .isNotNull()
 *  .matchAll(user -> user.id() == 42, user -> user.status() == ACTIVE);
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and {@code
 * content().asClass(T)} successfully deserialized the response body.
 *
 * @param <T> the type of the asserted content object
 * @since 1.0.0
 */
public interface TestAssert1Class<T> extends OperationEquals<T>, OperationMatchAll<T>,
    OperationMatchAny<T>, OperationMatchNone<T> {

  /**
   * Asserts that the deserialized value is not {@code null}.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the value is {@code null}
   * @since 2.0.0
   */
  TestAssert2Class<T> isNotNull();

  /**
   * Asserts that the deserialized value is {@code null}.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the value is not {@code null}
   * @since 2.0.0
   */
  TestAssertLClass isNull();

}
