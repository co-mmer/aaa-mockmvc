package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;

@Since("2.0.0")
public record TestAssertResult<T>(T actualContent) {}
