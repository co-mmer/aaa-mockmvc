package ej.test.aaamockmvc.request.asserts.mapper;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Utility class for creating and configuring an {@code ObjectMapper} for JSON serialization and
 * deserialization.
 *
 * <p>This class allows the addition of custom deserializers for specific classes, enabling more
 * flexible handling of JSON data structures.
 *
 * <p>All methods are static and the class cannot be instantiated, as indicated by the private
 * constructor.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAssertObjectMapper {

  /**
   * Creates a new instance of {@code ObjectMapper} configured for the specified class type.
   *
   * <p>If any custom deserializers are provided, they will be added to the {@code ObjectMapper}
   * to handle specific JSON formats for the expected class type.
   *
   * @param <T>           the type of the expected class
   * @param expectedClass the expected class for the JSON mapping (must not be {@code null})
   * @param deserializers optional array of custom deserializers for the expected class
   * @return a configured instance of {@code ObjectMapper}
   * @throws NullPointerException if the {@code expectedClass} is {@code null}
   * @since 1.0.0
   */
  public static <T> ObjectMapper create(
      @NonNull Class<T> expectedClass, JsonDeserializer<?>[] deserializers) {

    var objectMapper = new ObjectMapper();
    if (deserializers != null && deserializers.length > 0) {
      addDeserializer(expectedClass, deserializers, objectMapper);
    }
    return objectMapper;
  }

  @SuppressWarnings("unchecked")
  private static <T> void addDeserializer(Class<T> expectedClass,
      JsonDeserializer<?>[] deserializers, ObjectMapper objectMapper) {

    var module = new SimpleModule();
    Arrays.stream(deserializers)
        .forEach(
            deserializer ->
                module.addDeserializer(expectedClass, (JsonDeserializer<T>) deserializer));
    objectMapper.registerModule(module);
  }
}
