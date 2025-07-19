package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerRuntimeException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;

/**
 * Read access (“answer” phase) to the captured HTTP response payload.
 *
 * <p><b>What it does:</b> Returns the response body either as a cached string/byte array or
 * deserialized into objects/collections/maps using the configured mapper. No additional I/O is
 * performed; values are derived from the immutable snapshot produced by {@code
 * actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/users");
 *
 * actPerform()
 *  .perform();
 *
 * List<User> users = answer().asList(User.class);
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed. If no body is present,
 * {@link #asString()} returns {@code ""} and {@link #asByte()} returns an empty array.
 *
 * @since 1.2.0
 */
public interface TestAnswer {

  /**
   * Returns the response body as a {@link String} (using the response charset or UTF-8).
   *
   * @return the cached string representation of the response body; {@code ""} if no body is present
   * @since 2.0.0
   */
  String asString();

  /**
   * Returns the raw response body as {@code byte[]}.
   *
   * @return the cached byte representation of the response body; an empty array if no body is
   *     present
   * @since 2.0.0
   */
  byte[] asByte();

  /**
   * Deserializes the response body into the given target class using the configured mapper.
   *
   * @param resultType the target type to deserialize to; must not be {@code null}
   * @param <T> target type
   * @return the deserialized object
   * @throws TestAnswerRuntimeException if deserialization fails or the body is not compatible with
   *     {@code resultType}
   * @since 2.0.0
   */
  <T> T asObject(@NonNull Class<T> resultType) throws TestAnswerRuntimeException;

  /**
   * Deserializes the response body into a {@code List<T>} using the configured mapper.
   *
   * @param elementType the element type; must not be {@code null}
   * @param <T> element type
   * @return the deserialized list (never {@code null})
   * @throws TestAnswerRuntimeException if deserialization fails or the body is not a JSON array
   *     compatible with {@code List<T>}
   * @since 2.0.0
   */
  <T> List<T> asList(@NonNull Class<T> elementType) throws TestAnswerRuntimeException;

  /**
   * Deserializes the response body into a {@code Set<T>} using the configured mapper.
   *
   * @param elementType the element type; must not be {@code null}
   * @param <T> element type
   * @return the deserialized set (never {@code null})
   * @throws TestAnswerRuntimeException if deserialization fails or the body is not a JSON array
   *     compatible with {@code Set<T>}
   * @since 2.0.0
   */
  <T> Set<T> asSet(@NonNull Class<T> elementType) throws TestAnswerRuntimeException;

  /**
   * Deserializes the response body into a {@code Map<K,V>} using the configured mapper.
   *
   * @param keyType the key type; must not be {@code null}
   * @param valueType the value type; must not be {@code null}
   * @param <K> key type
   * @param <V> value type
   * @return the deserialized map (never {@code null})
   * @throws TestAnswerRuntimeException if deserialization fails or the body is not a JSON object
   *     compatible with {@code Map<K,V>}
   * @since 2.0.0
   */
  <K, V> Map<K, V> asMap(@NonNull Class<K> keyType, @NonNull Class<V> valueType)
      throws TestAnswerRuntimeException;
}
