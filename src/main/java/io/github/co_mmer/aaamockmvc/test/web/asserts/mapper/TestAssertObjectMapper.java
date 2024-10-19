package io.github.co_mmer.aaamockmvc.test.web.asserts.mapper;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Utility class for creating and configuring an {@code ObjectMapper} with custom deserializers.
 *
 * <p>This class provides methods to add specific deserializers to an existing {@code ObjectMapper},
 * allowing for the deserialization of complex objects in a controlled manner.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAssertObjectMapper {

  /**
   * Creates and configures an {@code ObjectMapper} with optional custom deserializers.
   *
   * <p>If provided, the deserializers are registered for the specified expected class.
   *
   * @param objectMapper the existing {@code ObjectMapper} to configure (must not be {@code null})
   * @param expectedClass the class type for which the deserializers are intended (must not be
   *     {@code null})
   * @param deserializers an array of custom {@code JsonDeserializer} instances to register (can be
   *     {@code null})
   * @param <T> the type of the expected class
   * @return the configured {@code ObjectMapper} instance
   * @throws NullPointerException if either {@code objectMapper} or {@code expectedClass} is {@code
   *     null}
   * @since 1.0.0
   */
  public static <T> ObjectMapper create(
      @NonNull ObjectMapper objectMapper,
      @NonNull Class<T> expectedClass,
      JsonDeserializer<?>[] deserializers) {

    if (deserializers != null && deserializers.length > 0) {
      addDeserializer(expectedClass, deserializers, objectMapper);
    }
    return objectMapper;
  }

  @SuppressWarnings("unchecked")
  private static <T> void addDeserializer(
      Class<T> expectedClass, JsonDeserializer<?>[] deserializers, ObjectMapper objectMapper) {

    var module = new SimpleModule();
    Arrays.stream(deserializers)
        .forEach(
            deserializer ->
                module.addDeserializer(expectedClass, (JsonDeserializer<T>) deserializer));
    objectMapper.registerModule(module);
  }
}
