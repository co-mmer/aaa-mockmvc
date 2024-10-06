package ej.test.aaamockmvc.request.arrange.delete.param;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.arrange.delete.head.TestArrange1DeleteHead;

/**
 * Interface for arranging parameters for a DELETE request.
 *
 * <p>Provides methods to configure headers and execute the DELETE request.
 *
 * @since 1.0.0
 */
public interface TestArrange3DeleteParam {

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
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
