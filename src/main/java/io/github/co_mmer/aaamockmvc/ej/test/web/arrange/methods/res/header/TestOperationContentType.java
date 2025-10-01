package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header;

import lombok.NonNull;
import org.springframework.http.MediaType;

interface TestOperationContentType {

  /**
   * Sets the {@code Content-Type} header for the request body.
   *
   * <p>If the media type specifies a charset, it will be used to encode any string payloads
   * provided in the body step; otherwise UTF-8 is used by default.
   *
   * @param mediaType the content type to set; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange4ResHead contentType(@NonNull MediaType mediaType);
}
