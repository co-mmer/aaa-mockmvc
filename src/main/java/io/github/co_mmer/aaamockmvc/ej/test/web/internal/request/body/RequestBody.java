package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;

@Since("2.1.0")
public sealed interface RequestBody permits EmptyBody, TextBody, MultipartBody {}
