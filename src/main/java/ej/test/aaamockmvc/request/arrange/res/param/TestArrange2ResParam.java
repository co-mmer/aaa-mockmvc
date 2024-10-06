package ej.test.aaamockmvc.request.arrange.res.param;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.arrange.res.body.TestArrange1ResBody;
import ej.test.aaamockmvc.request.arrange.res.head.TestArrange2ResHead;
import java.util.Map;
import lombok.NonNull;

/**
 * Interface for arranging parameters for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure a single key-value pairs and multiple key-value pairs for the
 * request's parameters. Additionally, it includes methods for arranging headers, the request body,
 * and executing the request.
 *
 * @since 1.0.0
 */
public interface TestArrange2ResParam {

  /**
   * Arranges a single key-value pair for the PATCH/POST/PUT request parameters.
   *
   * <p>This method sets the specified key and value as a query parameter for the request.
   *
   * @param key the parameter name (must not be {@code null})
   * @param value the parameter value (must not be {@code null})
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

  /**
   * Arranges the headers for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange2ResHead} to configure the request headers
   * @since 1.0.0
   */
  TestArrange2ResHead arrangeHead();

  /**
   * Arranges the body for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResBody} to configure the request body
   * @since 1.0.0
   */
  TestArrange1ResBody arrangeBody();

  /**
   * Executes the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
