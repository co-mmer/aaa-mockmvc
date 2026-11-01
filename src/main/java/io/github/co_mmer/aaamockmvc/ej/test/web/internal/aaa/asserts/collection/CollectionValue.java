package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.unicode.UnicodeNormalizer.normalizeCollection;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class CollectionValue<E> {
  private final Collection<E> values;
  private final Collection<String> comparison;

  @Since("2.1.0")
  public CollectionValue(@Nullable Collection<E> values) {
    if (values == null) {
      throw new IllegalArgumentException("Collection must not be null.");
    }
    if (values.stream().anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Collection must not contain null elements.");
    }
    this.values =
        values instanceof Set<?>
            ? Collections.unmodifiableSet(new LinkedHashSet<>(values))
            : List.copyOf(values);
    var normalized = normalizeCollection(this.values);
    this.comparison =
        values instanceof Set<?>
            ? Collections.unmodifiableSet(new LinkedHashSet<>(normalized))
            : normalized;
  }

  @SafeVarargs
  @NonNull
  @Since("2.1.0")
  public static <E> CollectionValue<E> of(@Nullable E... values) {
    if (values == null) {
      throw new IllegalArgumentException("Collection must not be null.");
    }
    return new CollectionValue<>(Arrays.asList(values));
  }

  @NonNull
  @Since("2.1.0")
  public Collection<E> values() {
    return this.values;
  }

  @NonNull
  @Since("2.1.0")
  public Collection<String> comparison() {
    return this.comparison;
  }
}
