package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;

/**
 * Interface for arranging additional custom headers and executing the HEAD/OPTIONS request.
 *
 * <p>This interface allows for the addition of custom headers as key-value pairs and provides a
 * method to execute the HEAD/OPTIONS request after the headers have been arranged.
 *
 * @since 1.0.0
 */
public interface TestArrange5InfoHead {

  /**
   * Arranges a custom header as a key-value pair for the HEAD/OPTIONS request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5InfoHead arrangeKeyValue(String key, Object value);

  /**
   * Executes the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
