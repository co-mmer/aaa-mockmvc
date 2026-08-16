package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import org.springframework.test.web.servlet.MockMvc;

@Since("2.0.0")
public record TestEnvironment(MockMvc mvc, ObjectMapper objectMapper) {}
