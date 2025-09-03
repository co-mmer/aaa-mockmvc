package io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent;

public record FluentTransition(Class<?> fromClass, String startMethod, Class<?> toClass) {

  public FluentTransition {
    if (fromClass == null) {
      throw new IllegalArgumentException("fromClass must not be null");
    }
    if (startMethod == null || startMethod.isBlank()) {
      throw new IllegalArgumentException("startMethod must not be blank");
    }
    if (toClass == null) {
      throw new IllegalArgumentException("toClass must not be null");
    }
  }
}
