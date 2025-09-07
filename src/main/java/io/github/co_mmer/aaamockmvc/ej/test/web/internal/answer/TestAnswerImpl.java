package io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerRuntimeException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAnswerResult;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;

@Since("1.2.0")
public final class TestAnswerImpl implements TestAnswer {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAnswerImpl(TestAAAContext context) {
    this.context = context;
  }

  @Override
  public Integer asInteger() {
    return asObject(Integer.class);
  }

  @Override
  public Float asFloat() {
    return asObject(Float.class);
  }

  @Override
  public Double asDouble() {
    return asObject(Double.class);
  }

  @Override
  public Boolean asBoolean() {
    return asObject(Boolean.class);
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
    try {
      var content =
          TestGenericMapper.parse(
              this.context.getEnvironment().objectMapper(),
              this.context.getActResult().contentAsString(),
              resultType);

      this.context.setAnswerResult(new TestAnswerResult<>(content));
      return content;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerRuntimeException(e);
    }
  }

  @Override
  public <E> Collection<E> asCollection(@NonNull Class<E> elementClass) {
    try {
      var content =
          TestGenericMapper.parseCollection(
              this.context.getEnvironment().objectMapper(),
              this.context.getActResult().contentAsString(),
              elementClass);

      this.context.setAnswerResult(new TestAnswerResult<>(content));
      return content;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerRuntimeException(e);
    }
  }

  @Override
  public <E> List<E> asList(@NonNull Class<E> elementClass) {
    try {
      var content =
          TestGenericMapper.parseList(
              this.context.getEnvironment().objectMapper(),
              this.context.getActResult().contentAsString(),
              elementClass);

      this.context.setAnswerResult(new TestAnswerResult<>(content));
      return content;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerRuntimeException(e);
    }
  }

  @Override
  public <E> Set<E> asSet(@NonNull Class<E> elementClass) {
    try {
      var content =
          TestGenericMapper.parseSet(
              this.context.getEnvironment().objectMapper(),
              this.context.getActResult().contentAsString(),
              elementClass);

      this.context.setAnswerResult(new TestAnswerResult<>(content));
      return content;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerRuntimeException(e);
    }
  }

  @Override
  public <K, V> Map<K, V> asMap(@NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {
    try {
      var content =
          TestGenericMapper.parseMap(
              this.context.getEnvironment().objectMapper(),
              this.context.getActResult().contentAsString(),
              keyClass,
              valueClass);

      this.context.setAnswerResult(new TestAnswerResult<>(content));
      return content;
    } catch (TestGenericMapperException e) {
      throw new TestAnswerRuntimeException(e);
    }
  }
}
