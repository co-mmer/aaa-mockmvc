package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request;

public sealed interface RequestBody
    permits EmptyBody, TextBody, BinaryBody, FormBody, MultipartBody {}
