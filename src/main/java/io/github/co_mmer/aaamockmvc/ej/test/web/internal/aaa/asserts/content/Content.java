package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool.BooleanValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes.ByteValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz.ObjectValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection.CollectionValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map.MapValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.MappingType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.StringValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper.Reader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.opentest4j.AssertionFailedError;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class Content {

  private final String value;
  private final byte[] bytes;

  @Since("2.1.0")
  public Content(@Nullable TestActResult response) {
    if (response == null) {
      throw new IllegalStateException("Execute perform() before entering content assertions.");
    }
    this.value = response.contentAsString();
    this.bytes = response.contentAsBytes().clone();
  }

  @NonNull
  @Since("2.1.0")
  public StringValue asString() {
    requireBody("asString()");
    return new StringValue(this.value);
  }

  @NonNull
  @Since("2.1.0")
  public ByteValue asBytes() {
    requireBody("asBytes()");
    return new ByteValue(this.bytes);
  }

  @NonNull
  @Since("2.1.0")
  public BooleanValue asBoolean(@NonNull Reader<Boolean> mapper) {
    requireBody("asBoolean()");
    return new BooleanValue(read(new MappingType<>(Boolean.class), mapper));
  }

  @NonNull
  @Since("2.1.0")
  public <T> ObjectValue<T> asClass(@NonNull MappingType<T> type, @NonNull Reader<T> mapper) {
    requireBody("asClass(...)");
    return new ObjectValue<>(read(type, mapper));
  }

  @Nullable
  private <T> T read(@NonNull MappingType<T> type, @NonNull Reader<T> mapper) {
    try {
      return mapper.read(this.value);
    } catch (TestGenericMapperException cause) {
      throw new AssertionFailedError(
          "The response body could not be read as " + type.type().getSimpleName() + ".", cause);
    }
  }

  @NonNull
  @Since("2.1.0")
  public <E> CollectionValue<E> asCollection(@NonNull Reader<Collection<E>> mapper) {
    requireBody("asCollection(...)");
    return collection(mapper);
  }

  @NonNull
  @Since("2.1.0")
  public <E> CollectionValue<E> asList(@NonNull Reader<List<E>> mapper) {
    requireBody("asList(...)");
    return collection(mapper);
  }

  @NonNull
  @Since("2.1.0")
  public <E> CollectionValue<E> asSet(@NonNull Reader<Set<E>> mapper) {
    requireBody("asSet(...)");
    return collection(mapper);
  }

  @NonNull
  private <E> CollectionValue<E> collection(@NonNull Reader<? extends Collection<E>> mapper) {
    Collection<E> elements;
    try {
      elements = mapper.read(this.value);
    } catch (TestGenericMapperException cause) {
      throw new AssertionFailedError("The response body could not be read as a collection.", cause);
    }
    if (elements == null) {
      throw new AssertionFailedError("Expected a response collection, but found JSON null.");
    }
    if (elements.stream().anyMatch(Objects::isNull)) {
      throw new AssertionFailedError("The response collection must not contain null elements.");
    }
    return new CollectionValue<>(elements);
  }

  @NonNull
  @Since("2.1.0")
  public <K, V> MapValue<K, V> asMap(@NonNull Reader<Map<K, V>> mapper) {
    requireBody("asMap(...)");
    Map<K, V> values;
    try {
      values = mapper.read(this.value);
    } catch (TestGenericMapperException cause) {
      throw new AssertionFailedError("The response body could not be read as a map.", cause);
    }
    if (values == null) {
      throw new AssertionFailedError("Expected a response map, but found JSON null.");
    }
    return new MapValue<>(values);
  }

  private void requireBody(@NonNull String operation) {
    if (this.bytes.length == 0) {
      throw new AssertionFailedError(
          operation + " requires a non-empty response body, but the response body was empty.");
    }
  }
}
