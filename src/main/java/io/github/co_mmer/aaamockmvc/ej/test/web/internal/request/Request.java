package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;
import java.util.List;

public record Request(
    HttpMethod method,
    RequestPath path,
    RequestQuery query,
    RequestHeaders headers,
    List<RequestCookie> cookies,
    RequestBody body) {}
