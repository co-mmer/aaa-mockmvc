package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertionFailure.failure;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Since("2.1.0")
public final class HeaderAssertion {

  private final HeaderValue actual;
  private final TestStepDto step;

  @Since("2.1.0")
  public HeaderAssertion(@NonNull HeaderValue actual, @Nullable TestStepDto step) {
    this.actual = actual;
    this.step = step;
  }

  @Since("2.1.0")
  public void containsKey(@NonNull HeaderName expected) {
    if (!this.actual.comparison().containsKey(expected.comparison())) {
      throw failure(
          this.step,
          "The response must contain header '%s'.".formatted(expected.value()),
          expected.value(),
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void doesNotContainKey(@NonNull HeaderName expected) {
    if (this.actual.comparison().containsKey(expected.comparison())) {
      throw failure(
          this.step,
          "Header '%s' must be absent from the response.".formatted(expected.value()),
          expected.value(),
          this.actual.values());
    }
  }

  @Since("2.1.0")
  public void containsEntry(@NonNull HeaderName name, @NonNull HeaderEntryValue expected) {
    var values = this.actual.comparison().get(name.comparison());
    if (values == null || !values.contains(expected.comparison())) {
      throw failure(
          this.step,
          "Header '%s' must include the expected value; additional values are allowed."
              .formatted(name.value()),
          expected.value(),
          values);
    }
  }

  @Since("2.1.0")
  public void containsEntryExactly(@NonNull HeaderName name, @NonNull HeaderEntryValues expected) {
    if (expected.values().isEmpty()) {
      throw new InvalidAssertionException(
          "containsEntryExactly() requires at least one expected header value.");
    }
    var values = this.actual.comparison().get(name.comparison());
    if (values == null || !occurrences(values).equals(occurrences(expected.comparison()))) {
      throw failure(
          this.step,
          "Header '%s' must contain exactly the expected values with the same occurrence counts; value order is ignored."
              .formatted(name.value()),
          expected.values(),
          values);
    }
  }

  @NonNull
  private static Map<String, Integer> occurrences(@NonNull Collection<String> values) {
    Map<String, Integer> counts = new HashMap<>();
    values.forEach(v -> counts.merge(v, 1, Integer::sum));
    return counts;
  }
}
