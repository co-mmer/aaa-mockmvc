package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestCookie;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestCookiesDescription {

  public static String describe(List<RequestCookie> cookies) {
    Objects.requireNonNull(cookies, "cookies must not be null");

    if (cookies.isEmpty()) {
      return "Cookies: <none>";
    }

    return "Cookies: " + cookies;
  }
}
