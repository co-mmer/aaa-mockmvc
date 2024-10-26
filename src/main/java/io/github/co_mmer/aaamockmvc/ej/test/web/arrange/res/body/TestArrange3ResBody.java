package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import java.util.List;
import lombok.NonNull;
import org.springframework.mock.web.MockMultipartFile;

/**
 * Interface for arranging the body of a PATCH/POST/PUT request with a single file.
 *
 * <p>Provides methods to configure a single file upload and execute the request.
 *
 * @since 1.0.0
 */
public interface TestArrange3ResBody {

  /**
   * Arranges multiple files as the body for the PATCH/POST/PUT request.
   *
   * @param files the list of files to be included in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code files} is {@code null}
   * @since 1.0.0
   */
  TestArrange3ResBody arrangeFiles(@NonNull List<MockMultipartFile> files);

  /**
   * Executes the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
