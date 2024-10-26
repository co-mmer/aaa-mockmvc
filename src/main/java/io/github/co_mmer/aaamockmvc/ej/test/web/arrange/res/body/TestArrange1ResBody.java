package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body;

import java.util.List;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

/**
 * Interface for arranging the body of a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure raw content, JSON content, and file uploads as the body of the
 * request.
 *
 * @since 1.0.0
 */
public interface TestArrange1ResBody {

  /**
   * Arranges raw content as the body for the PATCH/POST/PUT request with a specific media type.
   *
   * @param raw the raw content to be set as the request body (must not be {@code null})
   * @param type the media type of the content (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code type} is {@code null}
   * @since 1.0.0
   */
  TestArrange3ResBody arrangeContent(@NonNull String raw, @NonNull MediaType type);

  /**
   * Arranges JSON content as the body for the PATCH/POST/PUT request.
   *
   * @param json the JSON content to be set as the request body (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code json} is {@code null}
   * @since 1.0.0
   */
  TestArrange3ResBody arrangeJson(@NonNull String json);

  /**
   * Arranges a single file as the body for the PATCH/POST/PUT request.
   *
   * @param file the file to be included in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code file} is {@code null}
   * @since 1.0.0
   */
  TestArrange2ResBody arrangeFile(@NonNull MockMultipartFile file);

  /**
   * Arranges multiple files as the body for the PATCH/POST/PUT request.
   *
   * @param files the list of files to be included in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code files} is {@code null}
   * @since 1.0.0
   */
  TestArrange3ResBody arrangeFiles(@NonNull List<MockMultipartFile> files);
}
