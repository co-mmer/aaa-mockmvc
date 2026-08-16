package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestDescription {

  public static String describe(Request request) {
    return "Request: "
        + request.method()
        + System.lineSeparator()
        + RequestPathDescription.describe(request.path())
        + System.lineSeparator()
        + RequestQueryDescription.describe(request.query())
        + System.lineSeparator()
        + RequestHeadersDescription.describe(request.headers())
        + System.lineSeparator()
        + RequestBodyDescription.describe(request.body());
  }
}
