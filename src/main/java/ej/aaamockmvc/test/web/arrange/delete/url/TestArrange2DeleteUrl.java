package ej.aaamockmvc.test.web.arrange.delete.url;

import ej.aaamockmvc.test.web.act.TestAct1;
import ej.aaamockmvc.test.web.arrange.delete.head.TestArrange1DeleteHead;
import ej.aaamockmvc.test.web.arrange.delete.param.TestArrange1DeleteParam;

/**
 * Interface for further arranging the DELETE request after the URL has been set.
 *
 * <p>Provides methods to configure query parameters, headers, and to execute the request.
 *
 * @since 1.0.0
 */
public interface TestArrange2DeleteUrl {

  /**
   * Arranges the query parameters for the DELETE request.
   *
   * <p>This method provides an interface for configuring the request's query parameters.
   *
   * @return an instance of {@code TestArrange1DeleteParam} to configure query parameters
   * @since 1.0.0
   */
  TestArrange1DeleteParam arrangeParam();

  /**
   * Arranges the headers for the DELETE request.
   *
   * <p>This method provides an interface for configuring the request's headers.
   *
   * @return an instance of {@code TestArrange1DeleteHead} to configure headers
   * @since 1.0.0
   */
  TestArrange1DeleteHead arrangeHead();

  /**
   * Executes the DELETE request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
