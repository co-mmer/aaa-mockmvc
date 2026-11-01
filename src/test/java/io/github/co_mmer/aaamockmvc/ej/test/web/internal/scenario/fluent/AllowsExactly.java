package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.findPublicMethodByNameAndReturn;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.header;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.join;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.publicApiMethodNames;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.FluentAssert.Matcher;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public final class AllowsExactly implements Matcher<FluentTransition> {

  private final Set<String> expected;

  private AllowsExactly(Set<String> expected) {
    this.expected = expected;
  }

  public static AllowsExactly of(String first, String... more) {
    Set<String> exp = new LinkedHashSet<>();
    exp.add(first);
    exp.addAll(Arrays.asList(more));
    return new AllowsExactly(exp);
  }

  @Override
  public Optional<String> match(FluentTransition t) {
    Method start = findPublicMethodByNameAndReturn(t.fromClass(), t.startMethod(), t.toClass());
    if (start == null) {
      return Optional.of(
          header(t.fromClass(), t.startMethod(), t.toClass()) + " -> startMethod nicht gefunden");
    }
    Class<?> actualTo = start.getReturnType();
    if (!actualTo.equals(t.toClass())) {
      return Optional.of(
          header(t.fromClass(), t.startMethod(), t.toClass())
              + "\n   erwarteter Zieltyp: "
              + t.toClass().getSimpleName()
              + "\n   tatsächlicher     : "
              + actualTo.getSimpleName());
    }

    Set<String> actual = publicApiMethodNames(t.toClass());
    if (!expected.equals(actual)) {
      return Optional.of(
          header(t.fromClass(), t.startMethod(), t.toClass())
              + "\n   erwartete Allowed : "
              + join(expected)
              + "\n   tatsächliche      : "
              + join(actual));
    }
    return Optional.empty();
  }
}
