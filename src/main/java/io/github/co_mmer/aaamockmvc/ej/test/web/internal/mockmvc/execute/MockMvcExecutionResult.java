package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute;

import java.util.List;
import java.util.Map;

@SuppressWarnings("java:S6218")
public record MockMvcExecutionResult(
    int status, String contentAsString, byte[] contentAsBytes, Map<String, List<String>> headers) {}
