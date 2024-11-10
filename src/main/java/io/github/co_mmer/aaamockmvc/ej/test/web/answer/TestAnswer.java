package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.ResultActions;

/**
 * This interface defines methods for handling responses from HTTP requests in a testing context. It
 * provides various ways to retrieve the results of executed HTTP requests, including response
 * content as strings, byte arrays, and headers.
 *
 * @since 1.2.0
 */
public interface TestAnswer {

  /**
   * Retrieves the {@link ResultActions} from the executed HTTP request.
   *
   * @return the result actions of the request
   * @throws TestAnswerException if an error occurs while retrieving the result actions
   * @since 1.2.0
   */
  ResultActions answerAsResultActions() throws TestAnswerException;

  /**
   * Retrieves the response content as a string from the executed HTTP request.
   *
   * @return the response content as a string
   * @throws TestAnswerException if an error occurs while retrieving the response content
   * @since 1.2.0
   */
  String answerAsString() throws TestAnswerException;

  /**
   * Retrieves the response content as a byte array from the executed HTTP request.
   *
   * @return the response content as a byte array
   * @throws TestAnswerException if an error occurs while retrieving the response content
   * @since 1.2.0
   */
  byte[] answerAsByte() throws TestAnswerException;

  /**
   * Retrieves the response as an instance of the specified class type.
   *
   * @param resultType the target class to map the response content to (must not be {@code null})
   * @param <T> the type of the object to be returned
   * @return an instance of the specified type populated with the response data
   * @throws NullPointerException if the {@code resultType} is {@code null}
   * @throws TestAnswerException if an error occurs during the mapping process
   * @since 1.3.0
   */
  <T> T answerAsObject(@NonNull Class<T> resultType) throws TestAnswerException;

  /**
   * Retrieves the response as a {@link List} of the specified class type.
   *
   * @param elementType the target class type for each element in the list (must not be {@code
   *     null})
   * @param <T> the type of each element in the list
   * @return a {@link List} populated with elements of the specified type
   * @throws NullPointerException if the {@code targetClass} is {@code null}
   * @throws TestAnswerException if an error occurs during the mapping process
   * @since 1.3.0
   */
  <T> List<T> answerAsList(@NonNull Class<T> elementType) throws TestAnswerException;

  /**
   * Retrieves the HTTP response content as a {@code Set} of elements of type {@code T}.
   *
   * <p>This method maps the response content to a {@code Set} using the provided element type. It
   * utilizes an {@code ObjectMapper} to deserialize the content into a set of the specified type.
   *
   * @param elementType the expected class of the elements in the set (must not be {@code null})
   * @param <T> the type of the elements in the set
   * @return a {@code Set} containing the deserialized elements
   * @throws NullPointerException if {@code elementType} is {@code null}
   * @throws TestAnswerException if an error occurs during the response mapping process
   * @since 1.3.0
   */
  <T> Set<T> answerAsSet(@NonNull Class<T> elementType) throws TestAnswerException;

  /**
   * Retrieves the HTTP response content as a {@code Map} of key-value pairs of types {@code K} and
   * {@code V}.
   *
   * <p>This method maps the response content to a {@code Map} using the provided key and value
   * types. It utilizes an {@code ObjectMapper} to deserialize the content into a map with the
   * specified key and value types.
   *
   * @param keyType the expected class of the map keys (must not be {@code null})
   * @param valueType the expected class of the map values (must not be {@code null})
   * @param <K> the type of the keys in the map
   * @param <V> the type of the values in the map
   * @return a {@code Map} containing the deserialized key-value pairs
   * @throws NullPointerException if {@code keyType} or {@code valueType} are {@code null}
   * @throws TestAnswerException if an error occurs during the response mapping process
   * @since 1.3.0
   */
  <K, V> Map<K, V> answerAsMap(@NonNull Class<K> keyType, @NonNull Class<V> valueType)
      throws TestAnswerException;

  /**
   * Retrieves the value of a specified response header from the executed HTTP request.
   *
   * @param key the name of the response header to retrieve
   * @return the value of the response header, or {@code null} if the header is not present
   * @throws TestAnswerException if an error occurs while retrieving the header value
   * @since 1.2.0
   */
  @Nullable
  String answerHeader(String key) throws TestAnswerException;

  /**
   * Executes the HTTP request without returning any content.
   *
   * @throws TestAnswerException if an error occurs during the execution
   * @since 1.2.0
   */
  void answerVoid() throws TestAnswerException;
}
