package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertReason.reasonContentMapOf;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerFailed;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.lang.NonNull;

@Since("1.2.0")
public final class TestAnswerImpl implements TestAnswer {

  private final TestAAAContext context;

  @Since("2.0.0")
  public TestAnswerImpl(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Override
  public Boolean asBoolean() {
    return parse("asBoolean()", AnswerTarget.result(Boolean.class));
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
    return parse("asObject()", AnswerTarget.result(resultType));
  }

  private <T> T parse(String stepName, AnswerTarget<T> target) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, target.type());
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "answer()." + stepName,
              content,
              target.type().getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <E> Collection<E> asCollection(@NonNull Class<E> elementClass) {
    var target = AnswerTarget.collectionElement(elementClass).type();
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseCollection(mapper, content, target);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "answer().asCollection()", content, target.getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <E> List<E> asList(@NonNull Class<E> elementClass) {
    var target = AnswerTarget.listElement(elementClass).type();

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseList(mapper, content, target);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "answer().asList()", content, target.getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <E> Set<E> asSet(@NonNull Class<E> elementClass) {
    var target = AnswerTarget.setElement(elementClass).type();

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseSet(mapper, content, target);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(), "answer().asSet()", content, target.getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <K, V> Map<K, V> asMap(@NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {
    var targetKey = AnswerTarget.mapKey(keyClass).type();
    var targetValue = AnswerTarget.mapValue(valueClass).type();

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseMap(mapper, content, targetKey, targetValue);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var target = "Map<%s, %s>".formatted(targetKey.getSimpleName(), targetValue.getSimpleName());
      var reason = reasonContentMapOf(this.context.getStep(), "answer().asMap()", content, target);
      throw new TestAnswerFailed(reason);
    }
  }
}
