package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.execute;

import java.util.List;
import java.util.Map;

public record MockMvcExecutionResult(
    int status, String contentAsString, byte[] contentAsBytes, Map<String, List<String>> headers) {}
