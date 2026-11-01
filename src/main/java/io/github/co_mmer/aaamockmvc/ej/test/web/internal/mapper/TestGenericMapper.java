package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.AAAType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Since("2.0.0")
public final class TestGenericMapper {

  private TestGenericMapper() {}

  @Since("2.1.0")
  @FunctionalInterface
  public interface Reader<T> {

    T read(String content) throws TestGenericMapperException;
  }

  @Since("2.1.0")
  public static <T> T parse(ObjectMapper mapper, String content, AAAType<T> type)
      throws TestGenericMapperException {
    return read(mapper, content, mapper.getTypeFactory().constructType(type.type()));
  }

  @Since("2.1.0")
  public static <E> Collection<E> parseCollection(
      ObjectMapper mapper, String content, AAAType<E> type) throws TestGenericMapperException {
    return read(
        mapper,
        content,
        mapper.getTypeFactory().constructCollectionType(Collection.class, type.type()));
  }

  @Since("2.1.0")
  public static <E> List<E> parseList(ObjectMapper mapper, String content, AAAType<E> type)
      throws TestGenericMapperException {
    return read(
        mapper, content, mapper.getTypeFactory().constructCollectionType(List.class, type.type()));
  }

  @Since("2.1.0")
  public static <E> Set<E> parseSet(ObjectMapper mapper, String content, AAAType<E> type)
      throws TestGenericMapperException {
    return read(
        mapper, content, mapper.getTypeFactory().constructCollectionType(Set.class, type.type()));
  }

  @Since("2.1.0")
  public static <K, V> Map<K, V> parseMap(
      ObjectMapper mapper, String content, AAAType<K> key, AAAType<V> value)
      throws TestGenericMapperException {
    return read(
        mapper,
        content,
        mapper.getTypeFactory().constructMapType(Map.class, key.type(), value.type()));
  }

  private static <T> T read(ObjectMapper mapper, String content, JavaType type)
      throws TestGenericMapperException {
    try {
      return mapper.readValue(content, type);
    } catch (JsonProcessingException cause) {
      throw new TestGenericMapperException(cause);
    }
  }

  @Since("2.0.0")
  public static String toJson(ObjectMapper mapper, Object value) throws TestGenericMapperException {
    try {
      return mapper.writeValueAsString(value);
    } catch (JsonProcessingException cause) {
      throw new TestGenericMapperException(cause);
    }
  }
}
