package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertReason.reasonContentMapOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool.TestAssertBooleanImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes.TestAssertByteImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz.TestAssertClassImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestAssertStringImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import lombok.RequiredArgsConstructor;
import org.opentest4j.AssertionFailedError;
import org.springframework.lang.NonNull;

@Since("2.0.0")
@RequiredArgsConstructor
public class TestAssertContentImpl implements TestAssertContent {

  private final TestAAAContext context;

  @Override
  public TestAssert1Boolean asBoolean() {
    var content = this.context.getActResult().contentAsString();
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
      throws AssertionFailedError {

    var target = TestAssertType.expectedClass(expectedClass).value();
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, target);
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
    var target = TestAssertType.collectionElementClass(elementClass).value();
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseCollection(mapper, content, target);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "content().asCollection()", content, target.getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asList(@NonNull Class<E> elementClass) {
    var target = TestAssertType.listElementClass(elementClass).value();
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseList(mapper, content, target);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "content().asList()", content, target.getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asSet(@NonNull Class<E> elementClass) {
    var target = TestAssertType.setElementClass(elementClass).value();
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseSet(mapper, content, target);
      this.context.setAssertResult(new TestAssertResult<>(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "content().asSet()", content, target.getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <K, V> TestAssert1Map<K, V> asMap(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {

    var targetKey = TestAssertType.mapKeyClass(keyClass).value();
    var targetValue = TestAssertType.mapValueClass(valueClass).value();

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();
    try {
      var actual = TestGenericMapper.parseMap(mapper, content, targetKey, targetValue);
      this.context.setAssertResult(new TestAssertResult<>(actual));
      return new TestAssertMapImpl<>(this.context);
    } catch (TestGenericMapperException e) {
      var target = "Map<%s, %s>".formatted(targetKey.getSimpleName(), targetValue.getSimpleName());
      var reason = reasonContentMapOf(this.context.getStep(), "content().asMap()", content, target);
      throw new AssertionFailedError(reason);
    }
  }
}
