package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.PUT;

import ej.test.aaamockmvc.context.TestRequestConfig;
import ej.test.aaamockmvc.request.arrange.res.url.TestArrange1ResUrl;
import ej.test.aaamockmvc.request.arrange.res.url.TestArrangeResUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;

/**
 * This class represents a specific test request using the HTTP PUT method.
 *
 * @since 1.0.0
 */
public final class TestRequestPut extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestPut} with the given configuration.
   *
   * @param config the {@link TestRequestConfig} used to configure the PUT request (must not be
   *     {@code null})
   * @throws NullPointerException if {@code config} is {@code null}
   * @since 1.0.0
   */
  public TestRequestPut(@NonNull TestRequestConfig config) {
    super(config);
  }

  /**
   * Sets up the test request by arranging the necessary context for a PUT request.
   *
   * @return an instance of {@code TestArrange1ResUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1ResUrl arrange() {
    var context = super.createContext(PUT);
    return new TestArrangeResUrlImpl(context);
  }
}
