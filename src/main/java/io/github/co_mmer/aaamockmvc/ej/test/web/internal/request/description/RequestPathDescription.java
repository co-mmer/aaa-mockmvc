package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestPath;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestPathDescription {

  private static final String PATH_PREFIX = "Path: ";
  private static final String MISSING_PATH_MESSAGE = "Request path must be set";

  @Since("2.0.2")
  public static String describe(RequestPath path) {
    if (path == null || path.value() == null) {
      throw new IllegalArgumentException(MISSING_PATH_MESSAGE);
    }

    return PATH_PREFIX + path.value();
  }
}
