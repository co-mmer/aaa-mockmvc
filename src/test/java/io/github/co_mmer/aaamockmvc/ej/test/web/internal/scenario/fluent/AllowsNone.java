package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.findPublicMethodByNameAndReturn;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.header;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.join;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.ApiIntrospector.publicApiMethodNames;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.FluentAssert.Matcher;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

/** Matcher: es gibt KEINE erlaubten Methoden. */
public final class AllowsNone implements Matcher<FluentTransition> {

  private static final AllowsNone INSTANCE = new AllowsNone();

  private AllowsNone() {}

  public static AllowsNone instance() {
    return INSTANCE;
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
    if (!actual.isEmpty()) {
      return Optional.of(
          header(t.fromClass(), t.startMethod(), t.toClass())
              + "\n   erwartete Allowed : — keine —"
              + "\n   tatsächliche      : "
              + join(actual));
    }
    return Optional.empty();
  }
}
