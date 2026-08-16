package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.util.List;
import java.util.Map;

@Since("2.0.2")
public record TestActResult(
    int status, Map<String, List<String>> headers, byte[] contentAsBytes, String contentAsString) {}
