package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;

public record Request(
    HttpMethod method,
    RequestPath path,
    RequestQuery query,
    RequestHeaders headers,
    RequestBody body) {}
