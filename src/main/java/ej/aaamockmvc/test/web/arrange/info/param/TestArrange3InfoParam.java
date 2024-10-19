package ej.aaamockmvc.test.web.arrange.info.param;

import ej.aaamockmvc.test.web.act.TestAct1;
import ej.aaamockmvc.test.web.arrange.info.head.TestArrange1InfoHead;

/**
 * Interface for arranging parameters for a HEAD/OPTIONS request.
 *
 * <p>Provides methods to configure headers and execute the HEAD/OPTIONS request.
 *
 * @since 1.0.0
 */
public interface TestArrange3InfoParam {

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
