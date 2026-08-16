package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

public record MultipartPart(String name, String filename, String contentType, byte[] content) {

}
