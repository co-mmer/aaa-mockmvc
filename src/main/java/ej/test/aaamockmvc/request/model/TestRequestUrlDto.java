package ej.test.aaamockmvc.request.model;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing the URL details of a test request.
 *
 * <p>This class holds the uri and query parameters for a request URL.
 *
 * @since 1.0.0
 */
@Setter
@Getter
public final class TestRequestUrlDto {

  private URI uri;
  private Map<String, String> param;

  /**
   * Initializes a new {@code TestRequestUrlDto} with empty query parameters.
   *
   * @since 1.0.0
   */
  public TestRequestUrlDto() {
    this.param = new HashMap<>();
  }
}
