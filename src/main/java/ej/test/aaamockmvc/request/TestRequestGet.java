package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.GET;

import ej.test.aaamockmvc.context.TestRequestConfig;
import ej.test.aaamockmvc.request.arrange.get.url.TestArrange1GetUrl;
import ej.test.aaamockmvc.request.arrange.get.url.TestArrangeGetUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;

/**
 * This class represents a specific test request using the HTTP GET method.
 *
 * @since 1.0.0
 */
public final class TestRequestGet extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestGet} with the given configuration.
   *
   * @param config the {@link TestRequestConfig} used to configure the GET request (must not be
   *     {@code null})
   * @throws NullPointerException if {@code config} is {@code null}
   * @since 1.0.0
   */
  public TestRequestGet(@NonNull TestRequestConfig config) {
    super(config);
  }

  /**
   * Sets up the test request by arranging the necessary context for a GET request.
   *
   * @return an instance of {@code TestArrange1GetUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1GetUrl arrange() {
    var context = super.createContext(GET);
    return new TestArrangeGetUrlImpl(context);
  }
}
