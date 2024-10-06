package ej.test.aaamockmvc.request.arrange.get.url;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.arrange.get.head.TestArrange1GetHead;
import ej.test.aaamockmvc.request.arrange.get.param.TestArrange1GetParam;

/**
 * Interface for further arranging the GET request after the URL has been set.
 *
 * <p>Provides methods to configure query parameters, headers, and to execute the request.
 *
 * @since 1.0.0
 */
public interface TestArrange2GetUrl {

  /**
   * Arranges the query parameters for the GET request.
   *
   * <p>This method provides an interface for configuring the request's query parameters.
   *
   * @return an instance of {@code TestArrange1GetParam} to configure query parameters
   * @since 1.0.0
   */
  TestArrange1GetParam arrangeParam();

  /**
   * Arranges the headers for the GET request.
   *
   * <p>This method provides an interface for configuring the request's headers.
   *
   * @return an instance of {@code TestArrange1GetHead} to configure headers
   * @since 1.0.0
   */
  TestArrange1GetHead arrangeHead();

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
