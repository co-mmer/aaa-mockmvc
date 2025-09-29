package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerFailed;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;

/**
 * Read access (“answer” phase) to the captured HTTP response payload.
 *
 * <p><b>What it does:</b> Returns the response body either as a cached string/byte array or
 * deserialized into objects/collections/maps using the configured mapper. No additional I/O is
 * performed; values are derived from the immutable snapshot produced by {@code act().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange().get("/api/users");
 * act().perform();
 * List<User> users = answer().asList(User.class);
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code act().perform()} has been executed. If no body is present, {@link
 * #asString()} returns {@code ""} and {@link #asByte()} returns an empty array.
 *
 * @since 1.2.0
 */
public interface TestAnswer {

  /**
   * Parses the response body as an {@link Number} using the configured mapper. Intended for scalar
   * numeric payloads (e.g., {@code 42}).
   *
   * @return the parsed Number value
   * @throws TestAnswerFailed if deserialization fails or the body is not a numeric scalar
   *     compatible with {@code Number}
   * @since 2.0.0
   */
  Number asNumber();

  /**
   * Parses the response body as an {@link BigDecimal} using the configured mapper. Intended for
   * scalar numeric payloads (e.g., {@code 42}).
   *
   * @return the parsed BigDecimal value
   * @throws TestAnswerFailed if deserialization fails or the body is not a numeric scalar
   *     compatible with {@code BigDecimal}
   * @since 2.0.0
   */
  BigDecimal asBigDecimal();

  /**
   * Parses the response body as an {@link BigInteger} using the configured mapper. Intended for
   * scalar numeric payloads (e.g., {@code 42}).
   *
   * @return the parsed BigInteger value
   * @throws TestAnswerFailed if deserialization fails or the body is not a numeric scalar
   *     compatible with {@code BigInteger}
   * @since 2.0.0
   */
  BigInteger asBigInteger();

  /**
   * Parses the response body as an {@link Integer} using the configured mapper. Intended for scalar
   * numeric payloads (e.g., {@code 42}).
   *
   * @return the parsed integer value
   * @throws TestAnswerFailed if deserialization fails or the body is not a numeric scalar
   *     compatible with {@code Integer}
   * @since 2.0.0
   */
  Integer asInteger();

  /**
   * Parses the response body as an {@link Long} using the configured mapper. Intended for scalar
   * numeric payloads (e.g., {@code 42}).
   *
   * @return the parsed long value
   * @throws TestAnswerFailed if deserialization fails or the body is not a numeric scalar
   *     compatible with {@code Long}
   * @since 2.0.0
   */
  Long asLong();

  /**
   * Parses the response body as a {@link Float} using the configured mapper. Intended for scalar
   * numeric payloads (e.g., {@code 12.34}).
   *
   * @return the parsed float value
   * @throws TestAnswerFailed if deserialization fails or the body is not a numeric scalar
   *     compatible with {@code Float}
   * @since 2.0.0
   */
  Float asFloat();

  /**
   * Parses the response body as a {@link Double} using the configured mapper. Intended for scalar
   * numeric payloads (e.g., {@code 12.34}).
   *
   * @return the parsed double value
   * @throws TestAnswerFailed if deserialization fails or the body is not a numeric scalar
   *     compatible with {@code Double}
   * @since 2.0.0
   */
  Double asDouble();

  /**
   * Parses the response body as a {@link Boolean} using the configured mapper. Intended for boolean
   * literals (e.g., {@code true} / {@code false}).
   *
   * @return the parsed boolean value
   * @throws TestAnswerFailed if deserialization fails or the body is not compatible with {@code
   *     Boolean}
   * @since 2.0.0
   */
  Boolean asBoolean();

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
   * @throws TestAnswerFailed if deserialization fails or the body is not compatible with {@code
   *     resultType}
   * @since 2.0.0
   */
  <T> T asObject(@NonNull Class<T> resultType) throws TestAnswerFailed;

  /**
   * Deserializes the response body into a {@code Collection<T>} using the configured mapper.
   *
   * @param elementType the element type; must not be {@code null}
   * @param <E> element type
   * @return the deserialized collection
   * @throws TestAnswerFailed if deserialization fails or the body is not a JSON array compatible
   *     with {@code Collection<T>}
   * @since 2.0.0
   */
  <E> Collection<E> asCollection(@NonNull Class<E> elementType);

  /**
   * Deserializes the response body into a {@code List<T>} using the configured mapper.
   *
   * @param elementType the element type; must not be {@code null}
   * @param <E> element type
   * @return the deserialized list
   * @throws TestAnswerFailed if deserialization fails or the body is not a JSON array compatible
   *     with {@code List<T>}
   * @since 2.0.0
   */
  <E> List<E> asList(@NonNull Class<E> elementType) throws TestAnswerFailed;

  /**
   * Deserializes the response body into a {@code Set<T>} using the configured mapper.
   *
   * @param elementType the element type; must not be {@code null}
   * @param <T> element type
   * @return the deserialized set
   * @throws TestAnswerFailed if deserialization fails or the body is not a JSON array compatible
   *     with {@code Set<T>}
   * @since 2.0.0
   */
  <T> Set<T> asSet(@NonNull Class<T> elementType) throws TestAnswerFailed;

  /**
   * Deserializes the response body into a {@code Map<K,V>} using the configured mapper.
   *
   * @param keyType the key type; must not be {@code null}
   * @param valueType the value type; must not be {@code null}
   * @param <K> key type
   * @param <V> value type
   * @return the deserialized map
   * @throws TestAnswerFailed if deserialization fails or the body is not a JSON object compatible
   *     with {@code Map<K,V>}
   * @since 2.0.0
   */
  <K, V> Map<K, V> asMap(@NonNull Class<K> keyType, @NonNull Class<V> valueType)
      throws TestAnswerFailed;
}
