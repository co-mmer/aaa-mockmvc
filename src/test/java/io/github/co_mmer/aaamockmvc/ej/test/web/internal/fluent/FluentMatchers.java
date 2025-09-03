package io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent;

/** Öffentliche DSL-Fassade – hält die API stabil und künftige Erweiterungen offen. */
public final class FluentMatchers {

  private FluentMatchers() {}

  /** Fabrik für eine einzelne FluentTransition. */
  public static FluentTransition transition(
      Class<?> fromClass, String startMethod, Class<?> toClass) {
    return new FluentTransition(fromClass, startMethod, toClass);
  }

  /** Matcher: exakt diese erlaubten Methoden. */
  public static AllowsExactly allowsExactly(String first, String... more) {
    return AllowsExactly.of(first, more);
  }
}
