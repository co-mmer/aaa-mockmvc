package ej.aaamockmvc.test.web.arrange.get.head;

import ej.aaamockmvc.test.web.act.TestAct1Perform;

/**
 * Interface for arranging additional custom headers and executing the GET request.
 *
 * <p>This interface allows for the addition of custom headers as key-value pairs and provides a
 * method to execute the GET request after the headers have been arranged.
 *
 * @since 1.0.0
 */
public interface TestArrange5GetHead {

  /**
   * Arranges a custom header as a key-value pair for the GET request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5GetHead arrangeKeyValue(String key, Object value);

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
