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
  public TestAnswerImpl(TestAAAContext context) {
    this.context = context;
  }

  @Override
  public Boolean asBoolean() {
    return parse("asBoolean()", AnswerType.result(Boolean.class));
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
    return parse("asObject()", AnswerType.result(resultType));
  }

  private <T> T parse(String stepName, AnswerType<T> answerType) {
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parse(mapper, content, answerType);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "answer()." + stepName,
              content,
              answerType.type().getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <E> Collection<E> asCollection(@NonNull Class<E> elementClass) {
    var answerType = AnswerType.collectionElement(elementClass);
    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseCollection(mapper, content, answerType);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "answer().asCollection()",
              content,
              answerType.type().getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <E> List<E> asList(@NonNull Class<E> elementClass) {
    var answerType = AnswerType.listElement(elementClass);

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseList(mapper, content, answerType);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "answer().asList()",
              content,
              answerType.type().getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <E> Set<E> asSet(@NonNull Class<E> elementClass) {
    var answerType = AnswerType.setElement(elementClass);

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseSet(mapper, content, answerType);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var reason =
          reasonContentMapOf(
              this.context.getStep(),
              "answer().asSet()",
              content,
              answerType.type().getSimpleName());
      throw new TestAnswerFailed(reason);
    }
  }

  @Override
  public <K, V> Map<K, V> asMap(@NonNull Class<K> keyClass, @NonNull Class<V> valueClass) {
    var answerKey = AnswerType.mapKey(keyClass);
    var answerValue = AnswerType.mapValue(valueClass);

    var content = this.context.getActResult().contentAsString();
    var mapper = this.context.getEnvironment().objectMapper();

    try {
      var actual = TestGenericMapper.parseMap(mapper, content, answerKey, answerValue);
      this.context.setAnswerResult(new TestAnswerResult<>(actual));
      return actual;
    } catch (TestGenericMapperException e) {
      var target =
          "Map<%s, %s>"
              .formatted(answerKey.type().getSimpleName(), answerValue.type().getSimpleName());
      var reason = reasonContentMapOf(this.context.getStep(), "answer().asMap()", content, target);
      throw new TestAnswerFailed(reason);
    }
  }
}
