package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body;

import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.mock.web.MockMultipartFile;

interface TestOperationFiles {

  /**
   * Arranges multiple files as the body (multipart/form-data).
   *
   * <p>Switches the request to multipart and adds all provided files as parts.
   *
   * @param files the files to include; must not be {@code null}
   * @return the next step in the arrange chain, exposing only arrange-appropriate methods based on
   *     the current state.
   * @since 2.0.0
   */
  TestArrange3ResBody files(@NonNull List<MockMultipartFile> files);
}
