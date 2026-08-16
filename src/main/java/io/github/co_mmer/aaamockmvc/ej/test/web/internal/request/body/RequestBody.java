package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body;

public sealed interface RequestBody permits EmptyBody, TextBody, MultipartBody {}
