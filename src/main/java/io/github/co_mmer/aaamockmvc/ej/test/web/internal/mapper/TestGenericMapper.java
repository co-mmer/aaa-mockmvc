package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.MvcResult;

@Since("2.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestGenericMapper {

  private static <T> T parse(ObjectMapper om, String content, JavaType type)
      throws TestGenericMapperException {
    try {
      return StringUtils.isBlank(content) ? null : om.readValue(content, type);
    } catch (Exception e) {
      throw new TestGenericMapperException(e);
    }
  }

  private static String getContent(MvcResult result) throws TestGenericMapperException {
    try {
      return result.getResponse().getContentAsString();
    } catch (Exception e) {
      throw new TestGenericMapperException(e);
    }
  }

  @Since("2.0.0")
  public static <T> T parse(ObjectMapper om, String content, Class<T> clazz)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructType(clazz);
    return parse(om, content, type);
  }

  @Since("2.0.0")
  public static <T> T parse(ObjectMapper om, MvcResult result, Class<T> clazz)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructType(clazz);
    return parse(om, getContent(result), type);
  }

  @Since("2.0.0")
  public static <E> Collection<E> parseCollection(
      ObjectMapper om, String content, Class<E> elementClass) throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(Collection.class, elementClass);
    return parse(om, content, type);
  }

  @Since("2.0.0")
  public static <E> List<E> parseList(ObjectMapper om, String content, Class<E> elementClass)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(List.class, elementClass);
    return parse(om, content, type);
  }

  @Since("2.0.0")
  public static <E> List<E> parseList(ObjectMapper om, MvcResult result, Class<E> elementClass)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(List.class, elementClass);
    return parse(om, getContent(result), type);
  }

  @Since("2.0.0")
  public static <E> Set<E> parseSet(ObjectMapper om, String content, Class<E> elementClass)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(Set.class, elementClass);
    return parse(om, content, type);
  }

  @Since("2.0.0")
  public static <E> Set<E> parseSet(ObjectMapper om, MvcResult result, Class<E> elementClass)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(Set.class, elementClass);
    return parse(om, getContent(result), type);
  }

  @Since("2.0.0")
  public static <K, V> Map<K, V> parseMap(
      ObjectMapper om, String content, Class<K> keyClass, Class<V> valueClass)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
    return parse(om, content, type);
  }

  @Since("2.0.0")
  public static <K, V> Map<K, V> parseMap(
      ObjectMapper om, MvcResult result, Class<K> keyClass, Class<V> valueClass)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
    return parse(om, getContent(result), type);
  }

  @Since("2.0.0")
  public static String toJson(ObjectMapper om, Object value) throws TestGenericMapperException {
    try {
      return om.writeValueAsString(value);
    } catch (Exception e) {
      throw new TestGenericMapperException(e);
    }
  }
}
