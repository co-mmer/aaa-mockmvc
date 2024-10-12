package ej.test.aaamockmvc.request.asserts.mapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ej.test.aaamockmvc.request.asserts.mapper.exception.TestAssertResultMapperException;
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
 * <p>This class uses the {@code TestAssertObjectMapper} to deserialize the JSON content of HTTP
 * responses into the expected data structures, supporting custom deserializers when provided.
 *
 * <p>All methods throw {@code TestAssertResultMapperException} if an error occurs during the
 * mapping process, which encapsulates the underlying exception.
 *
 * <p>This class is marked as {@code final} and cannot be instantiated directly. It uses a private
 * constructor for utility purposes.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAssertResultMapper {

  /**
   * Maps the JSON content of the {@code MvcResult} response to an object of the specified class.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception encapsulated in {@code TestAssertResultMapperException}.
   *
   * @param <T>           the type of the object to be mapped
   * @param result        the {@code MvcResult} containing the response (must not be {@code null})
   * @param expectedClass the expected class of the object (must not be {@code null})
   * @return an object of the specified type
   * @throws NullPointerException            if the {@code result} or {@code expectedClass} is
   *                                         {@code null}
   * @throws TestAssertResultMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  @SuppressWarnings("unchecked")
  public static <T> T mapTo(
      @NonNull MvcResult result,
      @NonNull Class<T> expectedClass,
      JsonDeserializer<T>... deserializers)
      throws TestAssertResultMapperException {

    var javaType =
        TestAssertObjectMapper.create(expectedClass, deserializers)
            .getTypeFactory()
            .constructType(expectedClass);

    return mapToGenericType(result, javaType, deserializers);
  }

  private static <T> T mapToGenericType(
      MvcResult result, JavaType javaType, JsonDeserializer<?>... deserializers)
      throws TestAssertResultMapperException {

    try {
      var jsonContent = result.getResponse().getContentAsString();
      return TestAssertObjectMapper.create(javaType.getRawClass(), deserializers)
          .readValue(jsonContent, javaType);
    } catch (Exception e) {
      throw new TestAssertResultMapperException(e);
    }
  }

  /**
   * Maps the JSON content of the {@code MvcResult} response to a {@code List} of the specified
   * class type.
   *
   * @param <T>           the type of the objects in the list
   * @param result        the {@code MvcResult} containing the response (must not be {@code null})
   * @param expectedClass the expected class of the objects in the list (must not be {@code null})
   * @param deserializers optional custom deserializers for the JSON mapping
   * @return a {@code List} of objects of the specified type
   * @throws NullPointerException            if the {@code result} or {@code expectedClass} is
   *                                         {@code null}
   * @throws TestAssertResultMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  @SuppressWarnings("unchecked")
  public static <T> List<T> mapToList(
      @NonNull MvcResult result,
      @NonNull Class<T> expectedClass,
      JsonDeserializer<T>... deserializers)
      throws TestAssertResultMapperException {

    var javaType =
        TestAssertObjectMapper.create(expectedClass, deserializers)
            .getTypeFactory()
            .constructParametricType(List.class, expectedClass);

    return mapToGenericType(result, javaType, deserializers);
  }

  /**
   * Maps the JSON content of the {@code MvcResult} response to a {@code Set} of the specified class
   * type.
   *
   * @param <T>           the type of the objects in the set
   * @param result        the {@code MvcResult} containing the response (must not be {@code null})
   * @param expectedClass the expected class of the objects in the set (must not be {@code null})
   * @param deserializers optional custom deserializers for the JSON mapping
   * @return a {@code Set} of objects of the specified type
   * @throws NullPointerException            if the {@code result} or {@code expectedClass} is
   *                                         {@code null}
   * @throws TestAssertResultMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  @SuppressWarnings("unchecked")
  public static <T> Set<T> mapToSet(
      @NonNull MvcResult result,
      @NonNull Class<T> expectedClass,
      JsonDeserializer<T>... deserializers)
      throws TestAssertResultMapperException {

    var javaType =
        TestAssertObjectMapper.create(expectedClass, deserializers)
            .getTypeFactory()
            .constructParametricType(Set.class, expectedClass);

    return mapToGenericType(result, javaType, deserializers);
  }

  /**
   * Maps the JSON content of the {@code MvcResult} response to a {@code Map} of key-value pairs,
   * with the specified key and value classes.
   *
   * @param <K>           the type of the keys in the map
   * @param <V>           the type of the values in the map
   * @param result        the {@code MvcResult} containing the response (must not be {@code null})
   * @param keyClass      the expected class of the map keys (must not be {@code null})
   * @param valueClass    the expected class of the map values (must not be {@code null})
   * @param deserializers optional custom deserializers for the value JSON mapping
   * @return a {@code Map} of key-value pairs of the specified types
   * @throws NullPointerException            if the {@code result}, {@code keyClass}, or
   *                                         {@code valueClass} is {@code null}
   * @throws TestAssertResultMapperException if an error occurs during mapping
   * @since 1.0.0
   */
  @SuppressWarnings("unchecked")
  public static <K, V> Map<K, V> mapToMap(
      @NonNull MvcResult result,
      @NonNull Class<K> keyClass,
      @NonNull Class<V> valueClass,
      JsonDeserializer<V>... deserializers)
      throws TestAssertResultMapperException {

    var javaType =
        TestAssertObjectMapper.create(valueClass, deserializers)
            .getTypeFactory()
            .constructParametricType(Map.class, keyClass, valueClass);

    return mapToGenericType(result, javaType, deserializers);
  }
}
