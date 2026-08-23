package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import java.util.Map;
import org.springframework.lang.NonNull;

@Since("2.1.0")
public record TestActResult(
    int status,
    Map<String, List<String>> headers,
    @NonNull byte[] contentAsBytes,
    @NonNull String contentAsString) {}
