package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.d;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestPath;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestPathDescription {

  public static String describe(RequestPath path) {
    Objects.requireNonNull(path, "path must not be null");

    return "Path: " + path.value();
  }
}
