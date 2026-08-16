package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request;

public record MultipartPart(String name, String filename, String contentType, byte[] content) {}
