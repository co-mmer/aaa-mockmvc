package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestCookie;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestQuery;
import java.util.List;

public record TestArrangeResult(
    HttpMethod method,
    RequestPath path,
    RequestQuery query,
    RequestHeaders headers,
    List<RequestCookie> cookies,
    RequestBody body) {

}
