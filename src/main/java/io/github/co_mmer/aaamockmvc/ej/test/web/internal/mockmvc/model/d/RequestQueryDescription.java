package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.d;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestQuery;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestQueryDescription {

  private static final String NONE = "<none>";

  public static String describe(RequestQuery query) {
    Objects.requireNonNull(query, "query must not be null");

    var values = query.values();

    if (values.isEmpty()) {
      return "Query: " + NONE;
    }

    return "Query: " + values;
  }
}
