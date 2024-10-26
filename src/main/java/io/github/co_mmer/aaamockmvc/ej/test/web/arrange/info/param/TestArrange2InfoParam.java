package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.param;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.head.TestArrange1InfoHead;

/**
 * Interface for arranging query parameters for a HEAD/OPTIONS request.
 *
 * <p>Provides methods to configure single key-value pair, headers and execute the HEAD/OPTIONS
 * request based on the arranged parameters.
 *
 * @since 1.0.0
 */
public interface TestArrange2InfoParam {

  /**
   * Arranges a single key-value pair for the HEAD/OPTIONS request parameters.
   *
   * <p>This method sets the specified key and value as a query parameter for the request.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return an instance of {@code TestArrange2InfoParam} for further configuration
   * @since 1.0.0
   */
  TestArrange2InfoParam arrangeKeyValue(String key, String value);

  /**
   * Arranges the headers for the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestArrange1InfoHead} to configure the request headers
   * @since 1.0.0
   */
  TestArrange1InfoHead arrangeHead();

  /**
   * Executes the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
