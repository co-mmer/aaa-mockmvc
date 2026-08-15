package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssertReason.reasonContentMapOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.bool.TestAssertBooleanImpl;
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
import org.opentest4j.AssertionFailedError;

@Since("2.0.0")
@RequiredArgsConstructor
public class TestAssertContentImpl implements TestAssertContent {

  private final TestAAAContext context;

  @Override
  public TestAssert1Boolean asBoolean() {
    var content = this.context.getActResult2().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, Boolean.class);
      this.context.setAssertResult(new TestAssertResult<>(actual));
      return new TestAssertBooleanImpl(this.context);
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(this.context.getStep(), "content().asBoolean()", content, "Boolean");
      throw new AssertionFailedError(reason);
    }
  }

  @Override
  public TestAssert1String asString() {
    var assertResult = new TestAssertResult<>(this.context.getActResult2().contentAsString());
    this.context.setAssertResult(assertResult);
    return new TestAssertStringImpl(this.context);
  }

  @Override
  public TestAssert1Byte asBytes() {
    var assertResult = new TestAssertResult<>(this.context.getActResult2().contentAsBytes());
    this.context.setAssertResult(assertResult);
    return new TestAssertByteImpl(this.context);
  }

  @Override
  public <C> TestAssert1Class<C> asClass(@NonNull Class<C> expectedClass)
      throws AssertionFailedError {

    var content = this.context.getActResult2().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, expectedClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "content().asClass()",
              content,
              expectedClass.getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertClassImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asCollection(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult2().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseCollection(mapper, content, elementClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "content().asCollection()",
              content,
              elementClass.getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asList(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult2().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseList(mapper, content, elementClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "content().asList()", content, elementClass.getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asSet(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult2().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseSet(mapper, content, elementClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "content().asSet()", content, elementClass.getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <K, V> TestAssert1Map<K, V> asMap(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {

    var content = this.context.getActResult2().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();
    try {
      var actual = TestGenericMapper.parseMap(mapper, content, keyClass, valueClass);
      this.context.setAssertResult(new TestAssertResult<>(actual));
      return new TestAssertMapImpl<>(this.context);
    } catch (TestGenericMapperException e) {
      var target = "Map<%s, %s>".formatted(keyClass.getSimpleName(), valueClass.getSimpleName());
      var reason = reasonContentMapOf(this.context.getStep(), "content().asMap()", content, target);
      throw new AssertionFailedError(reason);
    }
  }
}
