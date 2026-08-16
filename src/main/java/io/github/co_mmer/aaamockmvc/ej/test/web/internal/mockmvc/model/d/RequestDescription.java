package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.d;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestDescription {

  public static String describe(TestArrangeResult request) {
    return "TestArrangeResult: "
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
