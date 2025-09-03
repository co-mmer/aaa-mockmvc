package io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent;

import java.lang.reflect.Method;
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

  public static Method findPublicMethodByName(Class<?> type, String name) {
    for (Method m : type.getMethods()) {
      if (m.getDeclaringClass() == Object.class || m.isSynthetic()) {
        continue;
      }
      if (m.getName().equals(name)) {
        return m;
      }
    }
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
