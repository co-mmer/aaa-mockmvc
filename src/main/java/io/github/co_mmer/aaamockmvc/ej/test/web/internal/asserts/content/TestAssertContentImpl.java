package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.content;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.bytes.TestAssertByteImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.clazz.TestAssertClassImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestAssertStringImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAssertResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor
public class TestAssertContentImpl implements TestAssertContent {

  private final TestAAAContext context;

  @Override
  public TestAssert1String asString() {
    var assertResult = new TestAssertResult<>(this.context.getActResult().contentAsString());
    this.context.setAssertResult(assertResult);
    return new TestAssertStringImpl(this.context);
  }

  @Override
  public TestAssert1Byte asBytes() {
    var assertResult = new TestAssertResult<>(this.context.getActResult().contentAsBytes());
    this.context.setAssertResult(assertResult);
    return new TestAssertByteImpl(this.context);
  }

  @Override
  public <C> TestAssert1Class<C> asClass(@NonNull Class<C> expectedClass)
      throws TestAssertFailedError {

    var content = this.context.getActResult().contentAsString();
    var objectMapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(objectMapper, content, expectedClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      throw new TestAssertFailedError("asClass", content, expectedClass.getSimpleName(), e);
    }
    return new TestAssertClassImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asCollection(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult().contentAsString();
    var objectMapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseCollection(objectMapper, content, elementClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      throw new TestAssertFailedError("asCollection", content, elementClass.getSimpleName(), e);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asList(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult().contentAsString();
    var objectMapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseList(objectMapper, content, elementClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      throw new TestAssertFailedError("asList", content, elementClass.getSimpleName(), e);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asSet(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult().contentAsString();
    var objectMapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseSet(objectMapper, content, elementClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      throw new TestAssertFailedError("asSet", content, elementClass.getSimpleName(), e);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <K, V> TestAssert1Map<K, V> asMap(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {

    var content = this.context.getActResult().contentAsString();
    var objectMapper = this.context.getEnvironment().objectMapper();
    try {
      var actual = TestGenericMapper.parseMap(objectMapper, content, keyClass, valueClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
      return new TestAssertMapImpl<>(this.context);
    } catch (TestGenericMapperException e) {
      throw new TestAssertFailedError(
          "asMap", content, keyClass.getSimpleName(), valueClass.getSimpleName(), e);
    }
  }
}
