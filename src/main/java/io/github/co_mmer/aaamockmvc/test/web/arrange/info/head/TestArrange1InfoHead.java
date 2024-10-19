package io.github.co_mmer.aaamockmvc.test.web.arrange.info.head;

import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Interface for arranging HTTP headers for a HEAD/OPTIONS request.
 *
 * <p>Provides methods to configure common HTTP headers such as "Accept", "Authorization", and
 * "Content-Type", as well as custom key-value pairs.
 *
 * @since 1.0.0
 */
public interface TestArrange1InfoHead {

  /**
   * Arranges the "Accept" header for the HEAD/OPTIONS request.
   *
   * @param mediaTypes the acceptable media types for the response (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  TestArrange2InfoHead arrangeAccept(@NonNull MediaType... mediaTypes);

  /**
   * Arranges the "Authorization" header for the HEAD/OPTIONS request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange3InfoHead arrangeAuth(String token);

  /**
   * Arranges the "Content-Type" header for the HEAD/OPTIONS request.
   *
   * @param mediaTypes the content types for the request (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  TestArrange4InfoHead arrangeContentType(@NonNull MediaType... mediaTypes);

  /**
   * Arranges a custom header as a key-value pair for the HEAD/OPTIONS request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5InfoHead arrangeKeyValue(String key, Object value);

  /**
   * Arranges multiple custom headers as key-value pairs for the HEAD/OPTIONS request.
   *
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange6InfoHead arrangeKeyValue(@NonNull Map<String, Object> keyValue);
}
