package io.github.co_mmer.aaamockmvc.test.web.arrange.info.url;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.head.TestArrange1InfoHead;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.param.TestArrange1InfoParam;

/**
 * Interface for further arranging the HEAD/OPTIONS request after the URL has been set.
 *
 * <p>Provides methods to configure query parameters, headers, and to execute the request.
 *
 * @since 1.0.0
 */
public interface TestArrange2InfoUrl {

  /**
   * Arranges the query parameters for the HEAD/OPTIONS request.
   *
   * <p>This method provides an interface for configuring the request's query parameters.
   *
   * @return an instance of {@code TestArrange1InfoParam} to configure query parameters
   * @since 1.0.0
   */
  TestArrange1InfoParam arrangeParam();

  /**
   * Arranges the headers for the HEAD/OPTIONS request.
   *
   * <p>This method provides an interface for configuring the request's headers.
   *
   * @return an instance of {@code TestArrange1InfoHead} to configure headers
   * @since 1.0.0
   */
  TestArrange1InfoHead arrangeHead();

  /**
   * Executes the HEAD/OPTIONS request.
   *
   * <p>This method triggers the actual request and returns an interface for performing actions and
   * evaluating the response.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
