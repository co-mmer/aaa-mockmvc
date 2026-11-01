package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.springframework.lang.Nullable;

public final class Conditions<E> {
  private final List<Predicate<E>> values;

  @SafeVarargs
  @Since("2.1.0")
  public Conditions(@Nullable Predicate<E>... values) {
    if (values == null) {
      throw new IllegalArgumentException("Match conditions must not be null.");
    }
    if (Arrays.stream(values).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Match conditions must not contain null.");
    }
    this.values = List.copyOf(Arrays.asList(values));
  }

  @Since("2.1.0")
  public boolean all(@Nullable E value) {
    return this.values.stream().allMatch(p -> p.test(value));
  }

  @Since("2.1.0")
  public boolean any(@Nullable E value) {
    return this.values.stream().anyMatch(p -> p.test(value));
  }
}
