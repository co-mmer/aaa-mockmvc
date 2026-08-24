package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;

@Since("2.0.0")
public record TestAnswerResult<T>(T actualContent) {}
