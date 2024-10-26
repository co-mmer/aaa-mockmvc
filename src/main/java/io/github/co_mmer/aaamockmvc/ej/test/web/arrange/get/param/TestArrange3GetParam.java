package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.param;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.head.TestArrange1GetHead;

/**
 * Interface for arranging parameters for a GET request.
 *
 * <p>Provides methods to configure headers and execute the GET request.
 *
 * @since 1.0.0
 */
public interface TestArrange3GetParam {

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
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
