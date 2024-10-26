package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.param;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head.TestArrange2ResHead;

/**
 * Interface for arranging parameters for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure headers, the request body, and to execute the request.
 *
 * @since 1.0.0
 */
public interface TestArrange5ResParam {

  /**
   * Arranges the headers for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange2ResHead} to configure the request headers
   * @since 1.0.0
   */
  TestArrange2ResHead arrangeHead();

  /**
   * Arranges the body for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResBody} to configure the request body
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
