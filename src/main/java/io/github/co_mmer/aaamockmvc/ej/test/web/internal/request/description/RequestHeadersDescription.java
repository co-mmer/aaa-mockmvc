package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestHeaders;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestHeadersDescription {

  private static final String NONE = "<none>";

  public static String describe(RequestHeaders headers) {

    var values = headers.values();

    if (values.isEmpty()) {
      return "Headers: " + NONE;
    }

    return "Headers: " + values;
  }
}
