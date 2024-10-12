package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.HEAD;

import ej.test.aaamockmvc.context.TestRequestConfig;
import ej.test.aaamockmvc.request.arrange.info.url.TestArrange1InfoUrl;
import ej.test.aaamockmvc.request.arrange.info.url.TestArrangeInfoUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;

/**
 * This class represents a specific test request using the HTTP HEAD method.
 *
 * @since 1.0.0
 */
public final class TestRequestHead extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestHead} with the given configuration.
   *
   * @param config the {@link TestRequestConfig} used to configure the HEAD request (must not be
   *     {@code null})
   * @throws NullPointerException if {@code config} is {@code null}
   * @since 1.0.0
   */
  public TestRequestHead(@NonNull TestRequestConfig config) {
    super(config);
  }

  /**
   * Sets up the test request by arranging the necessary context for a HEAD request.
   *
   * @return an instance of {@code TestArrange1InfoUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1InfoUrl arrange() {
    var context = super.createContext(HEAD);
    return new TestArrangeInfoUrlImpl(context);
  }
}
