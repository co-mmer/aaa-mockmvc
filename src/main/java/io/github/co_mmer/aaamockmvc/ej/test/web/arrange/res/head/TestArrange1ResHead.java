package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head;

import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Interface for arranging headers for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure standard headers such as "Accept", "Authorization", and
 * "Content-Type", as well as custom headers represented as key-value pairs.
 *
 * @since 1.0.0
 */
public interface TestArrange1ResHead {

  /**
   * Arranges the "Accept" header for the PATCH/POST/PUT request.
   *
   * @param mediaTypes the acceptable media types for the response (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  TestArrange2ResHead arrangeAccept(@NonNull MediaType... mediaTypes);

  /**
   * Arranges the "Authorization" header for the PATCH/POST/PUT request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange3ResHead arrangeAuth(String token);

  /**
   * Arranges the "Content-Type" header for the PATCH/POST/PUT request.
   *
   * @param mediaTypes the content types for the request (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  TestArrange4ResHead arrangeContentType(@NonNull MediaType... mediaTypes);

  /**
   * Arranges a custom header as a key-value pair for the PATCH/POST/PUT request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5ResHead arrangeKeyValue(String key, Object value);

  /**
   * Arranges multiple custom headers as key-value pairs for the PATCH/POST/PUT request.
   *
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange6ResHead arrangeKeyValue(@NonNull Map<String, Object> keyValue);
}
