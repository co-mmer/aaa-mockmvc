package ej.aaamockmvc.test.web.request.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

/**
 * Data Transfer Object (DTO) representing the body of a test request.
 *
 * <p>This class holds the request content, its content type, and a list of multipart files that may
 * be included in the request body.
 *
 * @since 1.0.0
 */
@Getter
@Setter
public final class TestRequestBodyDto {

  private String content;
  private MediaType contentType;
  private List<MockMultipartFile> files;

  /**
   * Initializes a new {@code TestRequestBodyDto} with an empty list of multipart files.
   *
   * @since 1.0.0
   */
  public TestRequestBodyDto() {
    this.files = new ArrayList<>();
  }
}
