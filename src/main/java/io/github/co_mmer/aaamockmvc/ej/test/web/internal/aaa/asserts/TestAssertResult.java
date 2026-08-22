package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;

@Since("2.0.0")
@Deprecated(forRemoval = true)
public record TestAssertResult<T>(T actualContent) {}
