package ej.test.aaamockmvc.request.arrange.delete.param;

import java.util.Map;
import lombok.NonNull;

/**
 * Interface for arranging query parameters for a DELETE request.
 *
 * <p>Provides methods to configure single key-value pairs as well as multiple key-value pairs for
 * the request's query parameters.
 *
 * @since 1.0.0
 */
public interface TestArrange1DeleteParam {

  /**
   * Arranges a single key-value pair for the DELETE request parameters.
   *
   * <p>This method sets the specified key and value as a query parameter for the request.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return an instance of {@code TestArrange2DeleteParam} for further configuration
   * @since 1.0.0
   */
  TestArrange2DeleteParam arrangeKeyValue(String key, String value);

  /**
   * Arranges multiple key-value pairs for the DELETE request parameters.
   *
   * <p>This method sets the provided map of key-value pairs as query parameters for the request.
   *
   * @param keyValue a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @return an instance of {@code TestArrange3DeleteParam} for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange3DeleteParam arrangeKeyValue(@NonNull Map<String, String> keyValue);
}
