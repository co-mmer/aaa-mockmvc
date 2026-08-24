package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertReason.reasonContentMapOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool.TestAssert1Boolean;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertType;
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
    var assertType = AssertType.resultClass(Boolean.class);
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, assertType);
      this.context.setAssertOperand(AssertOperand.bool(actual));
      return new TestAssertBooleanImpl(this.context);
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(this.context.getStep(), "content().asBoolean()", content, "Boolean");
      throw new AssertionFailedError(reason);
    }
  }

  @Override
  public TestAssert1String asString() {
    var assertOperand = AssertOperand.string(this.context.getActResult().contentAsString());
    this.context.setAssertOperand(assertOperand);
    return new TestAssertStringImpl(this.context);
  }

  @Override
  public TestAssert1Byte asBytes() {
    var assertOperand = AssertOperand.bytes(this.context.getActResult().contentAsBytes());
    this.context.setAssertOperand(assertOperand);
    return new TestAssertByteImpl(this.context);
  }

  @Override
  public <C> TestAssert1Class<C> asClass(@NonNull Class<C> expectedClass) {
    var assertType = AssertType.resultClass(expectedClass);
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, assertType);
      this.context.setAssertOperand(AssertOperand.clazz(actual));
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
    var assertType = AssertType.collectionElementClass(elementClass);
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseCollection(mapper, content, assertType);
      this.context.setAssertOperand(AssertOperand.collection(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "content().asCollection()",
              content,
              assertType.type().getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asList(@NonNull Class<E> elementClass) {
    var assertType = AssertType.listElementClass(elementClass);
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseList(mapper, content, assertType);
      this.context.setAssertOperand(AssertOperand.list(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "content().asList()",
              content,
              assertType.type().getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <E> TestAssert1Collection<E> asSet(@NonNull Class<E> elementClass) {
    var assertType = AssertType.setElementClass(elementClass);
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseSet(mapper, content, assertType);
      this.context.setAssertOperand(AssertOperand.set(actual));
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "content().asSet()",
              content,
              assertType.type().getSimpleName());
      throw new AssertionFailedError(reason);
    }
    return new TestAssertCollectionImpl<>(this.context);
  }

  @Override
  public <K, V> TestAssert1Map<K, V> asMap(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {

    var assertKey = AssertType.mapKeyClass(keyClass);
    var assertValue = AssertType.mapValueClass(valueClass);

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();
    try {
      var actual = TestGenericMapper.parseMap(mapper, content, assertKey, assertValue);
      this.context.setAssertOperand(AssertOperand.map(actual));
      return new TestAssertMapImpl<>(this.context);
    } catch (TestGenericMapperException e) {
      var target =
          "Map<%s, %s>"
              .formatted(assertKey.type().getSimpleName(), assertValue.type().getSimpleName());
      var reason = reasonContentMapOf(this.context.getStep(), "content().asMap()", content, target);
      throw new AssertionFailedError(reason);
    }
  }
}
