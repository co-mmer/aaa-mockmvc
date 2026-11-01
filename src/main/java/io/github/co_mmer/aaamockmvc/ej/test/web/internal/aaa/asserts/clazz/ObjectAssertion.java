package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertionFailure.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.Conditions;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Objects;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class ObjectAssertion<T> {

  private final ObjectValue<T> actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public ObjectAssertion(@NonNull ObjectValue<T> actual) {
    this(actual, null);
  }

  @Since("2.1.0")
  public ObjectAssertion(@NonNull ObjectValue<T> actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void isNull() {
    if (this.actual.value() != null) {
      throw failure(this.step, "The response object must be null.", null, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isNotNull() {
    if (this.actual.value() == null) {
      throw failure(this.step, "The response object must not be null.", "not null", null);
    }
  }

  @Since("2.1.0")
  public void isEqualTo(@NonNull ObjectValue<T> expected) {
    if (this.actual.value() != null
        && this.actual.value().getClass() != expected.value().getClass()) {
      throw failure(
          this.step,
          "The response object must have the expected type: expected %s, actual %s."
              .formatted(
                  expected.value().getClass().getName(), this.actual.value().getClass().getName()),
          expected.value(),
          this.actual.value());
    }
    if (this.actual.value() == null
        || !Objects.equals(this.actual.comparison(), expected.comparison())) {
      throw failure(
          this.step,
          "The response object must equal the expected object.",
          expected.value(),
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void matchAll(@NonNull Conditions<T> conditions) {
    if (!conditions.all(this.actual.value())) {
      throw failure(
          this.step,
          "The response object must satisfy every supplied condition.",
          "matches all conditions",
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void matchAny(@NonNull Conditions<T> conditions) {
    if (!conditions.any(this.actual.value())) {
      throw failure(
          this.step,
          "The response object must satisfy at least one supplied condition.",
          "matches any conditions",
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void matchNone(@NonNull Conditions<T> conditions) {
    if (conditions.any(this.actual.value())) {
      throw failure(
          this.step,
          "The response object must not satisfy any supplied condition.",
          "matches no conditions",
          this.actual.value());
    }
  }
}
