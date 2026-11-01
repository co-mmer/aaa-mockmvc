package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool.TestAssertBooleanImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes.TestAssertByteImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz.TestAssertClassImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.MappingType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestAssertStringImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;

@Since("2.0.0")
public class TestAssertContentImpl implements TestAssertContent {

  private final TestAAAContext context;
  private final Content content;
  private final ObjectMapper mapper;

  @Since("2.1.0")
  public TestAssertContentImpl(@NonNull TestAAAContext context) {
    this.context = context;
    this.content = new Content(context.getActResult());
    this.mapper = context.getEnvironment().objectMapper();
  }

  @Override
  @NonNull
  public TestAssert1Boolean asBoolean() {
    var actual =
        this.content.asBoolean(
            json -> TestGenericMapper.parse(this.mapper, json, new MappingType<>(Boolean.class)));
    return new TestAssertBooleanImpl(this.context, actual);
  }

  @Override
  @NonNull
  public TestAssert1String asString() {
    var actual = this.content.asString();
    return new TestAssertStringImpl(this.context, actual);
  }

  @Override
  @NonNull
  public TestAssert1Byte asBytes() {
    var actual = this.content.asBytes();
    return new TestAssertByteImpl(this.context, actual);
  }

  @Override
  @NonNull
  public <C> TestAssert1Class<C> asClass(@NonNull Class<C> expectedClass) {
    var type = new MappingType<>(expectedClass);
    var actual =
        this.content.asClass(type, json -> TestGenericMapper.parse(this.mapper, json, type));
    return new TestAssertClassImpl<>(this.context, actual);
  }

  @Override
  @NonNull
  public <E> TestAssert1Collection<E> asCollection(@NonNull Class<E> elementClass) {
    var type = new MappingType<>(elementClass);
    var actual =
        this.content.asCollection(
            json -> TestGenericMapper.parseCollection(this.mapper, json, type));
    return new TestAssertCollectionImpl<>(this.context, actual);
  }

  @Override
  @NonNull
  public <E> TestAssert1Collection<E> asList(@NonNull Class<E> elementClass) {
    var type = new MappingType<>(elementClass);
    var actual = this.content.asList(json -> TestGenericMapper.parseList(this.mapper, json, type));
    return new TestAssertCollectionImpl<>(this.context, actual);
  }

  @Override
  @NonNull
  public <E> TestAssert1Collection<E> asSet(@NonNull Class<E> elementClass) {
    var type = new MappingType<>(elementClass);
    var actual = this.content.asSet(json -> TestGenericMapper.parseSet(this.mapper, json, type));
    return new TestAssertCollectionImpl<>(this.context, actual);
  }

  @Override
  @NonNull
  public <K, V> TestAssert1Map<K, V> asMap(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {
    var key = new MappingType<>(keyClass);
    var value = new MappingType<>(valueClass);
    var actual =
        this.content.asMap(json -> TestGenericMapper.parseMap(this.mapper, json, key, value));
    return new TestAssertMapImpl<>(this.context, actual);
  }
}
