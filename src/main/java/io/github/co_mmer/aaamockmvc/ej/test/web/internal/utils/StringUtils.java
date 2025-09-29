package io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("1.4.1")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

  @Since("1.4.1")
  public static boolean isBlank(String string) {
    return string == null || string.trim().isEmpty();
  }

  @Since("1.4.1")
  public static final String EMPTY = "";

  @Since("1.4.1")
  public static final String EMPTY_ARRAY = "[]";

  @Since("1.4.1")
  public static final String EMPTY_OBJECT = "{}";

  @Since("1.4.1")
  public static final String UNSET = "<unset>";

  public static boolean isNotBlank(String string) {
    return !isBlank(string);
  }
}
