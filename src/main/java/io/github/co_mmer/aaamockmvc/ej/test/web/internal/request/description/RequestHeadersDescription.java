package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestHeadersDescription {

  @Since("2.0.2")
  public static String describe(RequestHeaders headers) {
    var values = headers.values();
    return CollectionUtils.isEmpty(values) ? StringUtils.EMPTY : "Headers: " + values;
  }
}
