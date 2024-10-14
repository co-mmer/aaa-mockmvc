package ej.aaamockmvc.test.web.arrange.get.param;

import ej.aaamockmvc.test.web.act.TestAct1Perform;
import ej.aaamockmvc.test.web.arrange.get.head.TestArrange1GetHead;

/**
 * Interface for arranging parameters for a GET request.
 *
 * <p>Provides methods to configure single key-value pair, headers and execute the GET request based
 * on the arranged parameters.
 *
 * @since 1.0.0
 */
public interface TestArrange2GetParam {

  /**
   * Arranges a single key-value pair for the GET request parameters.
   *
   * <p>This method sets the specified key and value as a query parameter for the request.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return an instance of {@code TestArrange2GetParam} for further configuration
   * @since 1.0.0
   */
  TestArrange2GetParam arrangeKeyValue(String key, String value);

  /**
   * Arranges the headers for the GET request.
   *
   * @return an instance of {@code TestArrange1GetHead} to configure the request headers
   * @since 1.0.0
   */
  TestArrange1GetHead arrangeHead();

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
