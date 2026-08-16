package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request;

import java.util.List;

public record Request(
    HttpMethod method,
    RequestPath path,
    RequestQuery query,
    RequestHeaders headers,
    List<RequestCookie> cookies,
    RequestBody body) {}
