package ej.aaamockmvc.test.web.arrange.get.head;

import ej.aaamockmvc.test.web.act.TestAct1;

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
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
