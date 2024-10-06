package ej.test.aaamockmvc.request.arrange.res.head;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.arrange.res.body.TestArrange1ResBody;

/**
 * Interface for arranging the body and executing a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure the body of the request and execute the PATCH/POST/PUT request.
 *
 * @since 1.0.0
 */
public interface TestArrange6ResHead {

  /**
   * Arranges the body for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResBody} to arrange request body
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
