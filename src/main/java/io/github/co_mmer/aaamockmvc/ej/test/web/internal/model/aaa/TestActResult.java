package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Since("2.0.0")
public record TestActResult(
    int status, Map<String, List<String>> headers, byte[] content, Charset charset) {

  public String contentAsString() {
    return new String(this.content, this.charset);
  }

  public byte[] contentAsBytes() {
    return this.content;
  }
}
