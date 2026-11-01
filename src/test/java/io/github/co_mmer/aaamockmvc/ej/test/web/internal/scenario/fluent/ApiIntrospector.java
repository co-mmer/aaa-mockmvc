package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiIntrospector {

  public static Set<String> publicApiMethodNames(Class<?> type) {
    Set<String> names = new LinkedHashSet<>();
    for (Method m : type.getMethods()) {
      if (m.getDeclaringClass() == Object.class || m.isSynthetic()) {
        continue;
      }
      names.add(m.getName());
    }
    return names;
  }

  // ApiIntrospector.java
  public static Method findPublicMethodByNameAndReturn(
      Class<?> type, String methodName, Class<?> expectedReturn) {

    Method[] methods = type.getMethods(); // nur public
    Method candidate = null;

    for (Method m : methods) {
      if (!m.getName().equals(methodName)) {
        continue;
      }
      if (!Modifier.isPublic(m.getModifiers())) {
        continue;
      }

      if (m.getReturnType().equals(expectedReturn)) {
        // perfekte Übereinstimmung
        return m;
      } else if (candidate == null) {
        // merke irgendeinen Overload für bessere Fehlermeldungen
        candidate = m;
      }
    }
    // nichts mit passendem Return gefunden
    return null;
  }

  public static String header(Class<?> from, String start, Class<?> to) {
    return "fromClass: "
        + from.getSimpleName()
        + "  startMethod: "
        + start
        + "  toClass: "
        + to.getSimpleName();
  }

  public static String join(Set<String> s) {
    if (s.isEmpty()) {
      return "(keine)";
    }
    return s.stream().sorted().collect(Collectors.joining(", "));
  }
}
