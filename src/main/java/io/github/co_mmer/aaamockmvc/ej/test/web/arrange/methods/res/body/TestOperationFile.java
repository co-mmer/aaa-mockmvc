package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body;

import lombok.NonNull;
import org.springframework.mock.web.MockMultipartFile;

interface TestOperationFile {

  /**
   * Arranges a single file as the body (multipart/form-data).
   *
   * <p>Switches the request to multipart; boundaries and encoding are handled by the underlying
   * client.
   *
   * @param file the file to include; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @throws NullPointerException if {@code file} is {@code null}
   * @since 1.0.0
   */
  TestArrange2ResBody file(@NonNull MockMultipartFile file);
}
