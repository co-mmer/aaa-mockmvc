package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import java.util.Map;
import lombok.NonNull;

/**
 * Interface for arranging HTTP headers for a GET request.
 *
 * <p>Provides methods to configure common HTTP headers such as "Authorization", as well as custom
 * key-value pairs.
 *
 * @since 1.0.0
 */
public interface TestArrange2GetHead {

  /**
   * Arranges the "Authorization" header for the GET request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange3GetHead arrangeAuth(String token);

  /**
   * Arranges a custom header as a key-value pair for the GET request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5GetHead arrangeKeyValue(String key, Object value);

  /**
   * Arranges multiple custom headers as key-value pairs for the GET request.
   *
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange6GetHead arrangeKeyValue(@NonNull Map<String, Object> keyValue);

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
