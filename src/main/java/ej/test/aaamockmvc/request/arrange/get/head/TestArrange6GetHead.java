package ej.test.aaamockmvc.request.arrange.get.head;

import ej.test.aaamockmvc.request.act.TestAct1Perform;

/**
 * Interface for executing the GET request after all headers have been arranged.
 *
 * <p>This interface represents the final step in the arrangement process, where the configured GET
 * request is executed.
 *
 * @since 1.0.0
 */
public interface TestArrange6GetHead {

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
