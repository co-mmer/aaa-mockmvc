package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.AAAType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Since("2.0.0")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestGenericMapper {

  private static <T> T parse(ObjectMapper om, String json, JavaType type)
      throws TestGenericMapperException {

    try {
      return StringUtils.isBlank(json) ? null : om.readValue(json, type);
    } catch (Exception e) {
      throw new TestGenericMapperException(e);
    }
  }

  @Since("2.1.0")
  @Nullable
  public static <T> T parse(ObjectMapper om, String content, AAAType<T> clazz)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructType(clazz.type());
    return parse(om, content, type);
  }

  @Since("2.1.0")
  @Nullable
  public static <E> Collection<E> parseCollection(
      ObjectMapper om, String content, AAAType<E> elementType) throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(Collection.class, elementType.type());
    return parse(om, content, type);
  }

  @Since("2.1.0")
  @Nullable
  public static <E> List<E> parseList(ObjectMapper om, String content, AAAType<E> elementType)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(List.class, elementType.type());
    return parse(om, content, type);
  }

  @Since("2.1.0")
  @Nullable
  public static <E> Set<E> parseSet(ObjectMapper om, String content, AAAType<E> elementType)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructCollectionType(Set.class, elementType.type());
    return parse(om, content, type);
  }

  @Since("2.1.0")
  @Nullable
  public static <K, V> Map<K, V> parseMap(
      ObjectMapper om, String content, AAAType<K> keyType, AAAType<V> valueType)
      throws TestGenericMapperException {
    var type = om.getTypeFactory().constructMapType(Map.class, keyType.type(), valueType.type());
    return parse(om, content, type);
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
