package io.github.co_mmer.aaamockmvc.ej.test.web.request.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;

/**
 * Data Transfer Object (DTO) representing the headers of a test request.
 *
 * <p>This class holds the key-value pairs for custom headers, accepted media types, content types.
 *
 * @since 1.0.0
 */
@Setter
@Getter
public final class TestRequestHeadDto {

  private List<MediaType> accepts;
  private MediaType contentType;
  private Map<String, Object> keyValue;

  /**
   * Initializes a new {@code TestRequestHeadDto} with empty header key-value pairs, accepted media
   * types, and content types.
   *
   * @since 1.0.0
   */
  public TestRequestHeadDto() {
    this.accepts = new ArrayList<>();
    this.keyValue = new HashMap<>();
  }
}
