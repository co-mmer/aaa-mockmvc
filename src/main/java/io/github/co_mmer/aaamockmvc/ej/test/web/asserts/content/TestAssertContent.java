package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import lombok.NonNull;

/**
 * Entry point for content assertions.
 *
 * <p><b>What it does:</b> Selects the target representation of the HTTP response body (string,
 * bytes, class, collection, set, map) and returns a type-safe assertion step for further checks.
 * The body is deserialized exactly once (where applicable) using the configured mapper and cached
 * for subsequent assertions.
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
 *   .content()
 *   .asClass(User.class)
 *   .isNotNull()
 *   .isEqualTo(expected);
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and the response
 * contains a body.Preconditions: actPerform().perform() has been executed and the response contains
 * a body. If no body is present, the cached string representation is empty ({@code ""}) and the
 * byte representation has length {@code 0}
 *
 * @since 2.0.0
 */
public interface TestAssertContent {

  /**
   * Interprets the response body as a {@link String} using the response charset (or UTF-8 if not
   * specified).
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @since 2.0.0
   */
  TestAssert1String asString();

  /**
   * Uses the raw response body as {@code byte[]}.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @since 2.0.0
   */
  TestAssert1Byte asBytes();

  /**
   * Deserializes the response body into the given target class using the configured mapper.
   *
   * @param expectedClass the class to deserialize to; must not be {@code null}
   * @param <C> target type
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws TestAssertFailedError if the body cannot be deserialized to {@code expectedClass}
   * @since 2.0.0
   */
  <C> TestAssert1Class<C> asClass(@NonNull Class<C> expectedClass) throws TestAssertFailedError;

  /**
   * Deserializes the response body into a {@code Collection<E>} using the configured mapper and
   * exposes collection assertions.
   *
   * @param elementClass the element type; must not be {@code null}
   * @param <E> element type
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws TestAssertFailedError if the body cannot be deserialized to {@code Collection<E>}
   * @since 2.0.0
   */
  <E> TestAssert1Collection<E> asCollection(@NonNull Class<E> elementClass)
      throws TestAssertFailedError;

  /**
   * Deserializes the response body into a {@code List<E>} using the configured mapper and exposes
   * list assertions.
   *
   * @param elementClass the element type; must not be {@code null}
   * @param <E> element type
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws TestAssertFailedError if the body cannot be deserialized to {@code List<E>}
   * @since 2.0.0
   */
  <E> TestAssert1Collection<E> asList(@NonNull Class<E> elementClass) throws TestAssertFailedError;

  /**
   * Deserializes the response body into a {@code Set<E>} using the configured mapper and exposes
   * set assertions.
   *
   * @param elementClass the element type; must not be {@code null}
   * @param <E> element type
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws TestAssertFailedError if the body cannot be deserialized to {@code Set<E>}
   * @since 2.0.0
   */
  <E> TestAssert1Collection<E> asSet(@NonNull Class<E> elementClass) throws TestAssertFailedError;

  /**
   * Deserializes the response body into a {@code Map<K,V>} using the configured mapper and exposes
   * map assertions.
   *
   * @param keyClass the key type; must not be {@code null}
   * @param valueClass the value type; must not be {@code null}
   * @param <K> key type
   * @param <V> value type
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws TestAssertFailedError if the body cannot be deserialized to {@code Map<K,V>}
   * @since 2.0.0
   */
  <K, V> TestAssert1Map<K, V> asMap(@NonNull Class<K> keyClass, @NonNull Class<V> valueClass)
      throws TestAssertFailedError;
}
