package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertReason.reasonContentMapOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerFailed;
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
  public TestAnswerImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public Boolean asBoolean() {
    return parse("asBoolean()", Boolean.class);
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
    return parse("asObject()", resultType);
  }

  private <T> T parse(String stepName, Class<T> resultType) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, resultType);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "answer()." + stepName, content, resultType.getSimpleName());
      throw new TestAnswerFailed(reason);
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
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "answer().asCollection()",
              content,
              elementClass.getSimpleName());
      throw new TestAnswerFailed(reason);
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
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "answer().asList()", content, elementClass.getSimpleName());
      throw new TestAnswerFailed(reason);
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
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "answer().asSet()", content, elementClass.getSimpleName());
      throw new TestAnswerFailed(reason);
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
      var target = "Map<%s, %s>".formatted(keyClass.getSimpleName(), valueClass.getSimpleName());
      var reason = reasonContentMapOf(this.context.getStep(), "answer().asMap()", content, target);
      throw new TestAnswerFailed(reason);
    }
  }
}
