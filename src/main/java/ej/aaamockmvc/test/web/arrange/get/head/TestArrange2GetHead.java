package ej.aaamockmvc.test.web.arrange.get.head;

import ej.aaamockmvc.test.web.act.TestAct1Perform;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Interface for arranging HTTP headers for a GET request.
 *
 * <p>Provides methods to configure common HTTP headers such as "Authorization", and "Content-Type",
 * as well as custom key-value pairs.
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
   * Arranges the "Content-Type" header for the GET request.
   *
   * @param mediaTypes the content types for the request (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  TestArrange4GetHead arrangeContentType(@NonNull MediaType... mediaTypes);

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
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
