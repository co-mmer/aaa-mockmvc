package ej.test.aaamockmvc.request.arrange.delete.head;

import ej.test.aaamockmvc.request.act.TestAct1Perform;

/**
 * Interface for executing the DELETE request after all headers have been arranged.
 *
 * <p>This interface represents the final step in the arrangement process, where the configured
 * DELETE request is executed.
 *
 * @since 1.0.0
 */
public interface TestArrange6DeleteHead {

  /**
   * Executes the DELETE request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
