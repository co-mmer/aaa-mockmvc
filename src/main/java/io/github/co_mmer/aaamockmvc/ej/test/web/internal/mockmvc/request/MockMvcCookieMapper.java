package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestCookie;
import jakarta.servlet.http.Cookie;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockMvcCookieMapper {

  public static void apply(MockHttpServletRequestBuilder builder, List<RequestCookie> cookies) {
    if (cookies == null || cookies.isEmpty()) {
      return;
    }

    Cookie[] mappedCookies = cookies.stream().map(MockMvcCookieMapper::map).toArray(Cookie[]::new);

    builder.cookie(mappedCookies);
  }

  private static Cookie map(RequestCookie source) {
    Objects.requireNonNull(source, "cookie must not be null");

    return new Cookie(source.name(), source.value());
  }
}
