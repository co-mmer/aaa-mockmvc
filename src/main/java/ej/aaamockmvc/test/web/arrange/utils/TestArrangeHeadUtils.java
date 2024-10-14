package ej.aaamockmvc.test.web.arrange.utils;

import ej.aaamockmvc.test.web.request.model.TestRequestHeadDto;
import java.util.Arrays;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Utility class for arranging headers in a test request.
 *
 * <p>This class provides static methods to configure headers, including setting accept and content
 * type values, and adding key-value pairs to the request headers. This class cannot be
 * instantiated.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeHeadUtils {

  /**
   * Sets the acceptable media types for the request headers.
   *
   * @param destination the destination {@code TestRequestHeadDto} to set accepts on (must not be
   *     {@code null})
   * @param accepts the media types that the request can accept (must not be {@code null})
   * @throws NullPointerException if either {@code destination} or {@code accepts} is {@code null}
   * @since 1.0.0
   */
  public static void setAccepts(
      @NonNull TestRequestHeadDto destination, @NonNull MediaType... accepts) {

    destination.setAccepts(Arrays.stream(accepts).toList());
  }

  /**
   * Sets the content types for the request headers.
   *
   * @param destination the destination {@code TestRequestHeadDto} to set content types on (must not
   *     be {@code null})
   * @param contentTypes the media types that the request will send (must not be {@code null})
   * @throws NullPointerException if either {@code destination} or {@code contentTypes} is {@code
   *     null}
   * @since 1.0.0
   */
  public static void setContentTypes(
      @NonNull TestRequestHeadDto destination, @NonNull MediaType... contentTypes) {

    destination.setContentTypes(Arrays.stream(contentTypes).toList());
  }

  /**
   * Adds a key-value pair to the request headers.
   *
   * @param destination the destination {@code TestRequestHeadDto} to add key-value pair to (must
   *     not be {@code null})
   * @param key the header name (must not be {@code null})
   * @param value the header value
   * @throws NullPointerException if {@code destination} or {@code key} is {@code null}
   * @since 1.0.0
   */
  public static void addKeyValue(
      @NonNull TestRequestHeadDto destination, String key, Object value) {

    destination.getKeyValue().put(key, value);
  }

  /**
   * Adds multiple key-value pairs to the request headers.
   *
   * @param destination the destination {@code TestRequestHeadDto} to add key-value pairs to (must
   *     not be {@code null})
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @throws NullPointerException if either {@code destination} or {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  public static void addKeyValue(
      @NonNull TestRequestHeadDto destination, @NonNull Map<String, Object> keyValue) {

    destination.getKeyValue().putAll(keyValue);
  }
}
