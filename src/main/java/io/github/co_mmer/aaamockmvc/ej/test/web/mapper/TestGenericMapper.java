package io.github.co_mmer.aaamockmvc.ej.test.web.mapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
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
 * <p>This class uses the {@code TestGenericMapperConfigurer} to deserialize the JSON content of
 * HTTP responses into the expected data structures, supporting custom deserializers when provided.
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
      var jsonContent = result.getResponse().getContentAsString();
      return objectMapper.readValue(jsonContent, javaType);
    } catch (Exception e) {
      throw new TestGenericMapperException(e);
    }
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

    var javaType = objectMapper.getTypeFactory().constructParametricType(List.class, elementType);
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

    var javaType = objectMapper.getTypeFactory().constructParametricType(Set.class, elementType);
    return mapToGenericType(objectMapper, result, javaType);
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
}
