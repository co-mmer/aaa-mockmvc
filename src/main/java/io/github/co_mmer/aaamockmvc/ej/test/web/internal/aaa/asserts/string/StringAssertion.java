package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssertDetail.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class StringAssertion {
  private final StringValue actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public StringAssertion(@NonNull StringValue actual) {
    this(actual, null);
  }

  @Since("2.1.0")
  public StringAssertion(@NonNull StringValue actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void isEmpty() {
    if (this.actual.value().length() != 0) {
      throw failure(
          this.step,
          "The response text must contain zero characters.",
          "empty",
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isNotEmpty() {
    if (this.actual.value().length() == 0) {
      throw failure(
          this.step,
          "The response text must contain at least one character.",
          "not empty",
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void hasLength(int expected) {
    if (this.actual.value().length() != expected) {
      throw failure(
          this.step,
          "The response text must have the expected length.",
          expected,
          this.actual.value().length());
    }
  }

  @Since("2.1.0")
  public void isEqualTo(@NonNull StringValue expected) {
    if (!(this.actual.comparison().equals(expected.comparison()))) {
      throw failure(
          this.step,
          "The response text must equal the expected text.",
          expected.value(),
          this.actual.value());
    }
  }
}
