package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.param;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head.TestArrange2ResHead;

/**
 * Interface for arranging parameters for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure single key-value pair for the request's parameters.
 * Additionally, it includes methods for arranging headers, the request body, and executing the
 * request.
 *
 * @since 1.0.0
 */
public interface TestArrange4ResParam {

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
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
