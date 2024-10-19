package io.github.co_mmer.aaamockmvc.test.web.request.model;

import lombok.Getter;
import lombok.NonNull;

/**
 * Data Transfer Object (DTO) representing a complete HTTP request used in test scenarios.
 *
 * <p>This class encapsulates the request type, URL, headers, and body of an HTTP request. It is
 * primarily used to construct and manage the various components of a test HTTP request.
 *
 * @since 1.0.0
 */
@Getter
public final class TestRequestDto {

  private final TestRequestType type;
  private final TestRequestUrlDto url;
  private final TestRequestHeadDto head;
  private final TestRequestBodyDto body;

  /**
   * Initializes a new {@code TestRequestDto} with the specified request type.
   *
   * <p>The URL, headers, and body are initialized with default empty objects.
   *
   * @param type the HTTP request type (must not be {@code null})
   * @throws NullPointerException if the {@code type} is {@code null}
   * @since 1.0.0
   */
  public TestRequestDto(@NonNull TestRequestType type) {
    this.type = type;
    this.url = new TestRequestUrlDto();
    this.body = new TestRequestBodyDto();
    this.head = new TestRequestHeadDto();
  }
}
