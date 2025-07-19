package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import org.springframework.test.web.servlet.MockMvc;

@Since("2.0.0")
public record TestEnvironment(MockMvc mvc, ObjectMapper objectMapper) {}
