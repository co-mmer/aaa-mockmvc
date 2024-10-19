package io.github.co_mmer.aaamockmvc.test.web.arrange.res.param;

import java.util.Map;
import lombok.NonNull;

/**
 * Interface for arranging parameters for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure single and multiple files as well as single key-value pairs and
 * multiple key-value pairs for the request's parameters.
 *
 * @since 1.0.0
 */
public interface TestArrange1ResParam {

  /**
   * Arranges a single key-value pair for the PATCH/POST/PUT request parameters.
   *
   * <p>This method sets the specified key and value as a query parameter for the request.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return an instance of {@code TestArrange4ResParam} for further configuration
   * @since 1.0.0
   */
  TestArrange4ResParam arrangeKeyValue(String key, String value);

  /**
   * Arranges multiple key-value pairs for the PATCH/POST/PUT request parameters.
   *
   * <p>This method sets the provided map of key-value pairs as query parameters for the request.
   *
   * @param keyValue a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @return an instance of {@code TestArrange5ResParam} for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange5ResParam arrangeKeyValue(@NonNull Map<String, String> keyValue);
}
