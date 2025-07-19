package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;

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
   * @return an instance of {@code TestAct} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct act();
}
