package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head.TestArrange1ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.param.TestArrange1ResParam;

/**
 * Interface for further arranging the PATCH/POST/PUT request after the URL has been set.
 *
 * <p>Provides methods to configure query parameters, headers, body, and to execute the request.
 *
 * @since 1.0.0
 */
public interface TestArrange2ResUrl {

  /**
   * Arranges the query parameters for the PATCH/POST/PUT request.
   *
   * <p>This method provides an interface for configuring the request's query parameters.
   *
   * @return an instance of {@code TestArrange1ResParam} to configure query parameters
   * @since 1.0.0
   */
  TestArrange1ResParam arrangeParam();

  /**
   * Arranges the headers for the PATCH/POST/PUT request.
   *
   * <p>This method provides an interface for configuring the request's headers.
   *
   * @return an instance of {@code TestArrange1ResHead} to configure headers
   * @since 1.0.0
   */
  TestArrange1ResHead arrangeHead();

  /**
   * Arranges the body for the PATCH/POST/PUT request.
   *
   * <p>This method provides an interface for configuring the request's body.
   *
   * @return an instance of {@code TestArrange1ResBody} to configure body
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
