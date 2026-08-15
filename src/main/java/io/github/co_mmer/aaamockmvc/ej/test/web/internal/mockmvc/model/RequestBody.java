package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

public sealed interface RequestBody
    permits EmptyBody, TextBody, BinaryBody, FormBody, MultipartBody {}
