package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;

@Since("2.0.2")
public record Request(
    HttpMethod method,
    RequestPath path,
    RequestQuery query,
    RequestHeaders headers,
    RequestBody body) {}
