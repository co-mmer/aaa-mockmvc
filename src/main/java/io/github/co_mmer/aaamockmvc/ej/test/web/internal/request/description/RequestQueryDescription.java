package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestQuery;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestQueryDescription {

  @Since("2.0.2")
  public static String describe(RequestQuery query) {
    var values = query.values();
    return CollectionUtils.isEmpty(values) ? StringUtils.EMPTY : "Query: " + values;
  }
}
