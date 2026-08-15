package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

public record MultipartPart(String name, String filename, String contentType, byte[] content) {}
