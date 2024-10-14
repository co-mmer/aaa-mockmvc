package ej.aaamockmvc.test.web.arrange.utils;

import ej.aaamockmvc.test.web.request.model.TestRequestBodyDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

/**
 * Utility class for arranging the body in a test request.
 *
 * <p>This class provides static methods to add files or content to the request body. It is designed
 * to be used in a testing context for preparing multipart file uploads or setting raw content for
 * HTTP requests.
 *
 * <p>Since this class should not be instantiated, its constructor is private.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeRequestBody {

  /**
   * Adds a single file to the request body.
   *
   * @param destination the {@code TestRequestBodyDto} where the file will be added (must not be
   *     {@code null})
   * @param file the {@code MockMultipartFile} to be added (must not be {@code null})
   * @throws NullPointerException if the {@code destination} or {@code file} is {@code null}
   * @since 1.0.0
   */
  public static void addFile(
      @NonNull TestRequestBodyDto destination, @NonNull MockMultipartFile file) {

    var items = new ArrayList<>(destination.getFiles());
    items.add(file);

    destination.setFiles(items);
  }

  /**
   * Adds multiple files to the request body.
   *
   * @param destination the {@code TestRequestBodyDto} where the files will be added (must not be
   *     {@code null})
   * @param files the list of {@code MockMultipartFile} objects to be added (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code destination} or {@code files} is {@code null}
   * @since 1.0.0
   */
  public static void addFiles(
      @NonNull TestRequestBodyDto destination, @NonNull List<MockMultipartFile> files) {

    var items = new ArrayList<>(destination.getFiles());
    items.addAll(files);

    destination.setFiles(items);
  }

  /**
   * Sets raw content and its media type for the request body.
   *
   * @param destination the {@code TestRequestBodyDto} where the content will be set (must not be
   *     {@code null})
   * @param content the raw content to be set as the request body (must not be {@code null})
   * @param type the media type of the content
   * @throws NullPointerException if the {@code destination} or {@code content} or {@code type} is
   *     {@code null}
   * @since 1.0.0
   */
  public static void setContent(
      @NonNull TestRequestBodyDto destination, @NonNull String content, @NonNull MediaType type) {

    destination.setContent(content);
    destination.setContentType(type);
  }
}
