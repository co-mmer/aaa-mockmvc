package io.github.co_mmer.aaamockmvc.test.web.arrange.delete.head;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;

/**
 * Interface for arranging additional custom headers and executing the DELETE request.
 *
 * <p>This interface allows for the addition of custom headers as key-value pairs and provides a
 * method to execute the DELETE request after the headers have been arranged.
 *
 * @since 1.0.0
 */
public interface TestArrange5DeleteHead {

  /**
   * Arranges a custom header as a key-value pair for the DELETE request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5DeleteHead arrangeKeyValue(String key, Object value);

  /**
   * Executes the DELETE request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
