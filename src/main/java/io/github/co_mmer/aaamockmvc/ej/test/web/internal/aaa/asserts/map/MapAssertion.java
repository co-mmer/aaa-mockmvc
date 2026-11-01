package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssertDetail.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class MapAssertion<K, V> {
  private final MapValue<K, V> actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public MapAssertion(@NonNull MapValue<K, V> actual) {
    this(actual, null);
  }

  @Since("2.1.0")
  public MapAssertion(@NonNull MapValue<K, V> actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void isEmpty() {
    if (!this.actual.values().isEmpty()) {
      throw failure(
          this.step, "The response map must contain no entries.", "empty", this.actual.values());
    }
  }

  @Since("2.1.0")
  public void isNotEmpty() {
    if (this.actual.values().isEmpty()) {
      throw failure(
          this.step,
          "The response map must contain at least one entry.",
          "not empty",
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void hasSize(int expected) {
    if (this.actual.values().size() != expected) {
      throw failure(
          this.step,
          "The response map must contain the expected number of entries.",
          expected,
          this.actual.values().size());
    }
  }

  @Since("2.1.0")
  public void isEqualTo(@NonNull MapValue<K, V> expected) {
    if (!this.actual.comparison().equals(expected.comparison())) {
      throw failure(
          this.step,
          "The response map must contain exactly the expected key-value pairs; entry order is ignored.",
          expected.values(),
          this.actual.values());
    }
  }
}
