package io.github.co_mmer.aaamockmvc.test.web.arrange.delete.param;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.arrange.delete.head.TestArrange1DeleteHead;

/**
 * Interface for arranging query parameters for a DELETE request.
 *
 * <p>Provides methods to configure single key-value pair, headers and execute the DELETE request
 * based on the arranged parameters.
 *
 * @since 1.0.0
 */
public interface TestArrange2DeleteParam {

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
   * Arranges the headers for the DELETE request.
   *
   * @return an instance of {@code TestArrange1DeleteHead} to configure the request headers
   * @since 1.0.0
   */
  TestArrange1DeleteHead arrangeHead();

  /**
   * Executes the DELETE request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
