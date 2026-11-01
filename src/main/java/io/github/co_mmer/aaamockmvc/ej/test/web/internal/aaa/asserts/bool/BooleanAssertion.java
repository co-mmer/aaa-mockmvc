package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertionFailure.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Objects;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class BooleanAssertion {

  private final BooleanValue actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public BooleanAssertion(@NonNull BooleanValue actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void isNull() {
    if (this.actual.value() != null) {
      throw failure(
          this.step, "The response Boolean value must be null.", null, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isNotNull() {
    if (this.actual.value() == null) {
      throw failure(this.step, "The response Boolean value must not be null.", "not null", null);
    }
  }

  @Since("2.1.0")
  public void isEqualTo(@NonNull BooleanValue expected) {
    if (!(Objects.equals(this.actual.value(), expected.value()))) {
      throw failure(
          this.step,
          "The response Boolean value must equal the expected value.",
          expected.value(),
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isTrue() {
    if (!Boolean.TRUE.equals(this.actual.value())) {
      throw failure(
          this.step, "The response Boolean value must be true.", true, this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isFalse() {
    if (!Boolean.FALSE.equals(this.actual.value())) {
      throw failure(
          this.step, "The response Boolean value must be false.", false, this.actual.value());
    }
  }
}
