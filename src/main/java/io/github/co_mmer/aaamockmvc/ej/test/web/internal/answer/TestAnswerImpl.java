package io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAnswerResult;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;

@Since("1.2.0")
public final class TestAnswerImpl implements TestAnswer {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAnswerImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public Number asNumber() {
    return parse("asNumber", Number.class);
  }

  @Override
  public BigDecimal asBigDecimal() {
    return parse("asBigDecimal", BigDecimal.class);
  }

  @Override
  public BigInteger asBigInteger() {
    return parse("asBigInteger", BigInteger.class);
  }

  @Override
  public Integer asInteger() {
    return parse("asInteger", Integer.class);
  }

  @Override
  public Long asLong() {
    return parse("asLong", Long.class);
  }

  @Override
  public Float asFloat() {
    return parse("asFloat", Float.class);
  }

  @Override
  public Double asDouble() {
    return parse("asDouble", Double.class);
  }

  @Override
  public Boolean asBoolean() {
    return parse("asBoolean", Boolean.class);
  }

  @Override
  public String asString() {
    var content = this.context.getActResult().contentAsString();
    this.context.setAnswerResult(new TestAnswerResult<>(content));
    return content;
  }

  @Override
  public byte[] asByte() {
    var content = this.context.getActResult().contentAsBytes();
    this.context.setAnswerResult(new TestAnswerResult<>(content));
    return content;
  }

  @Override
  public <T> T asObject(@NonNull Class<T> resultType) {
    return parse("asObject", resultType);
  }

  private <T> T parse(String stepName, Class<T> resultType) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, resultType);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException(stepName, content, resultType.getSimpleName(), e);
    }
  }

  @Override
  public <E> Collection<E> asCollection(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseCollection(mapper, content, elementClass);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException("asCollection", content, elementClass.getSimpleName(), e);
    }
  }

  @Override
  public <E> List<E> asList(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseList(mapper, content, elementClass);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException("asList", content, elementClass.getSimpleName(), e);
    }
  }

  @Override
  public <E> Set<E> asSet(@NonNull Class<E> elementClass) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseSet(mapper, content, elementClass);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException("asSet", content, elementClass.getSimpleName(), e);
    }
  }

  @Override
  public <K, V> Map<K, V> asMap(@NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseMap(mapper, content, keyClass, valueClass);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerException(
          "asMap", content, keyClass.getSimpleName(), valueClass.getSimpleName(), e);
    }
  }
}
