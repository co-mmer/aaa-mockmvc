package ej.aaamockmvc.test.web.arrange.res.head;

import ej.aaamockmvc.test.web.act.TestAct1;
import ej.aaamockmvc.test.web.arrange.res.body.TestArrange1ResBody;

/**
 * Interface for arranging custom headers and body for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure custom headers as single key-value pairs, arrange the body of
 * the request, and execute the PATCH/POST/PUT request.
 *
 * @since 1.0.0
 */
public interface TestArrange5ResHead {

  /**
   * Arranges a custom header as a key-value pair for the PATCH/POST/PUT request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5ResHead arrangeKeyValue(String key, Object value);

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
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
