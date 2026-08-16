package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestCookie;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.RequestQuery;
import java.util.List;

public record TestArrangeResult(
    HttpMethod method,
    RequestPath path,
    RequestQuery query,
    RequestHeaders headers,
    List<RequestCookie> cookies,
    RequestBody body) {}
