package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertionFailure.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Arrays;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Since("2.1.0")
public final class ByteAssertion {

  private final ByteValue actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public ByteAssertion(@NonNull ByteValue actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void isEmpty() {
    if (this.actual.length() != 0) {
      throw failure(
          this.step, "The response body must contain zero bytes.", "empty", this.actual.value());
    }
  }

  @Since("2.1.0")
  public void isNotEmpty() {
    if (this.actual.length() == 0) {
      throw failure(
          this.step,
          "The response body must contain at least one byte.",
          "not empty",
          this.actual.value());
    }
  }

  @Since("2.1.0")
  public void hasLength(int expected) {
    if (this.actual.length() != expected) {
      throw failure(
          this.step,
          "The response body must contain the expected number of bytes.",
          expected,
          this.actual.length());
    }
  }

  @Since("2.1.0")
  public void isEqualTo(@NonNull ByteValue expected) {
    if (!Arrays.equals(this.actual.value(), expected.value())) {
      throw failure(
          this.step,
          "The response bytes must match the expected bytes in both value and order.",
          expected.value(),
          this.actual.value());
    }
  }
}
