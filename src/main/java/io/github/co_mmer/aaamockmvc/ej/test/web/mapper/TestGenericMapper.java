package io.github.co_mmer.aaamockmvc.ej.test.web.mapper;

import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.isBlank;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Utility class responsible for mapping the content of {@code MvcResult} responses to various
 * generic types such as objects, lists, sets, or maps.
 *
 * <p>All methods throw {@code TestGenericMapperException} if an error occurs during the mapping
 * process, which encapsulates the underlying exception.
 *
 * <p>This class is marked as {@code final} and cannot be instantiated directly. It uses a private
 * constructor for utility purposes.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestGenericMapper {

  /**
   * Maps the JSON content of the {@code MvcResult} response to an object of the specified class.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception encapsulated in {@code TestGenericMapperException}.
   *
   * @param objectMapper the {@code ObjectMapper} used for mapping (must not be {@code null})
   * @param <T> the type of the object to be mapped
   * @param result the {@code MvcResult} containing the response (must not be {@code null})
   * @param resultType the expected class of the object (must not be {@code null})
   * @return an object of the specified type
   * @throws NullPointerException if the {@code result}, {@code objectMapper}, or {@code resultType}
   *     is {@code null}
   * @throws TestGenericMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  public static <T> T mapTo(
      @NonNull ObjectMapper objectMapper, @NonNull MvcResult result, @NonNull Class<T> resultType)
      throws TestGenericMapperException {

    var javaType = objectMapper.getTypeFactory().constructType(resultType);
    return mapToGenericType(objectMapper, result, javaType);
  }

  private static <T> T mapToGenericType(
      ObjectMapper objectMapper, MvcResult result, JavaType javaType)
      throws TestGenericMapperException {

    try {
      var content = result.getResponse().getContentAsString();
      return isBlank(content) ? null : objectMapper.readValue(content, javaType);
    } catch (Exception e) {
      throw new TestGenericMapperException(e);
    }
  }

  /**
   * Maps the JSON content of the {@code MvcResult} response to a {@code Collection} of the
   * specified class type.
   *
   * @param objectMapper the {@code ObjectMapper} used for mapping (must not be {@code null})
   * @param <T> the type of the objects in the collection
   * @param result the {@code MvcResult} containing the response (must not be {@code null})
   * @param elementType the expected class of the objects in the collection (must not be {@code
   *     null})
   * @return a {@code Collection} of objects of the specified type
   * @throws NullPointerException if the {@code result}, {@code objectMapper}, or {@code
   *     elementType} is {@code null}
   * @throws TestGenericMapperException if an error occurs during mapping
   * @since 1.4.1
   */
  public static <T> Collection<T> mapToCollection(
      @NonNull ObjectMapper objectMapper, @NonNull MvcResult result, @NonNull Class<T> elementType)
      throws TestGenericMapperException {

    var javaType = constructParametricType(objectMapper, Collection.class, elementType);
    return mapToGenericType(objectMapper, result, javaType);
  }

  /**
   * Maps the JSON content of the {@code MvcResult} response to a {@code List} of the specified
   * class type.
   *
   * @param objectMapper the {@code ObjectMapper} used for mapping (must not be {@code null})
   * @param <T> the type of the objects in the list
   * @param result the {@code MvcResult} containing the response (must not be {@code null})
   * @param elementType the expected class of the objects in the list (must not be {@code null})
   * @return a {@code List} of objects of the specified type
   * @throws NullPointerException if the {@code result}, {@code objectMapper}, or {@code
   *     elementType} is {@code null}
   * @throws TestGenericMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  public static <T> List<T> mapToList(
      @NonNull ObjectMapper objectMapper, @NonNull MvcResult result, @NonNull Class<T> elementType)
      throws TestGenericMapperException {

    var javaType = constructParametricType(objectMapper, List.class, elementType);
    return mapToGenericType(objectMapper, result, javaType);
  }

  /**
   * Maps the JSON content of the {@code MvcResult} response to a {@code Set} of the specified class
   * type.
   *
   * @param objectMapper the {@code ObjectMapper} used for mapping (must not be {@code null})
   * @param <T> the type of the objects in the set
   * @param result the {@code MvcResult} containing the response (must not be {@code null})
   * @param elementType the expected class of the objects in the set (must not be {@code null})
   * @return a {@code Set} of objects of the specified type
   * @throws NullPointerException if the {@code result}, {@code objectMapper}, or {@code
   *     elementType} is {@code null}
   * @throws TestGenericMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  public static <T> Set<T> mapToSet(
      @NonNull ObjectMapper objectMapper, @NonNull MvcResult result, @NonNull Class<T> elementType)
      throws TestGenericMapperException {

    var javaType = constructParametricType(objectMapper, Set.class, elementType);
    return mapToGenericType(objectMapper, result, javaType);
  }

  private static <T> JavaType constructParametricType(
      ObjectMapper objectMapper, Class<?> clazz, Class<T> elementType) {
    return objectMapper.getTypeFactory().constructParametricType(clazz, elementType);
  }

  /**
   * Maps the JSON content of the {@code MvcResult} response to a {@code Map} of key-value pairs,
   * with the specified key and value classes.
   *
   * @param objectMapper the {@code ObjectMapper} used for mapping (must not be {@code null})
   * @param <K> the type of the keys in the map
   * @param <V> the type of the values in the map
   * @param result the {@code MvcResult} containing the response (must not be {@code null})
   * @param keyType the expected class of the map keys (must not be {@code null})
   * @param valueType the expected class of the map values (must not be {@code null})
   * @return a {@code Map} of key-value pairs of the specified types
   * @throws NullPointerException if the {@code result}, {@code objectMapper}, {@code keyType}, or
   *     {@code valueType} is {@code null}
   * @throws TestGenericMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  public static <K, V> Map<K, V> mapToMap(
      @NonNull ObjectMapper objectMapper,
      @NonNull MvcResult result,
      @NonNull Class<K> keyType,
      @NonNull Class<V> valueType)
      throws TestGenericMapperException {

    var javaType =
        objectMapper.getTypeFactory().constructParametricType(Map.class, keyType, valueType);
    return mapToGenericType(objectMapper, result, javaType);
  }

  /**
   * Maps the specified content to a JSON string representation using the provided {@code
   * ObjectMapper}.
   *
   * @param objectMapper the {@code ObjectMapper} used for mapping (must not be {@code null})
   * @param content the content to be serialized to JSON (must not be {@code null})
   * @param <T> the type of the content being serialized
   * @return a JSON string representation of the specified content
   * @throws NullPointerException if the {@code objectMapper} or {@code content} is {@code null}
   * @throws TestGenericMapperException if an error occurs during serialization
   * @since 1.3.0
   */
  public static <T> String mapToString(@NonNull ObjectMapper objectMapper, @NonNull T content)
      throws TestGenericMapperException {

    try {
      return objectMapper.writeValueAsString(content);
    } catch (Exception e) {
      throw new TestGenericMapperException(e);
    }
  }
}
