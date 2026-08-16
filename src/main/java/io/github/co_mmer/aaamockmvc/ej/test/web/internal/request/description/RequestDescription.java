package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import static java.util.stream.Collectors.joining;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestDescription {

  private static final String REQUEST_PREFIX = "Request: ";

  @Since("2.0.2")
  public static String describe(Request request) {
    var queryDescription = getQueryDescription(request);
    var headersDescription = getHeadersDescription(request);
    var pathDescription = RequestPathDescription.describe(request.path());

    return Stream.of(
            REQUEST_PREFIX + request.method(),
            pathDescription,
            queryDescription,
            headersDescription,
            RequestBodyDescription.describe(request.body()))
        .filter(description -> !description.isEmpty())
        .collect(joining(System.lineSeparator()));
  }

  private static String getHeadersDescription(Request request) {
    return request.headers() == null
        ? StringUtils.EMPTY
        : RequestHeadersDescription.describe(request.headers());
  }

  private static String getQueryDescription(Request request) {
    return request.query() == null
        ? StringUtils.EMPTY
        : RequestQueryDescription.describe(request.query());
  }
}
