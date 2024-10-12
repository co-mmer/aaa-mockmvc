package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.DELETE;

import ej.test.aaamockmvc.context.TestRequestConfig;
import ej.test.aaamockmvc.request.arrange.delete.url.TestArrange1DeleteUrl;
import ej.test.aaamockmvc.request.arrange.delete.url.TestArrangeDeleteUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;

/**
 * This class represents a specific test request using the HTTP DELETE method.
 *
 * @since 1.0.0
 */
public final class TestRequestDelete extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestDelete} with the given configuration.
   *
   * @param config the {@link TestRequestConfig} used to configure the DELETE request (must not be
   *     {@code null})
   * @throws NullPointerException if {@code config} is {@code null}
   * @since 1.0.0
   */
  public TestRequestDelete(@NonNull TestRequestConfig config) {
    super(config);
  }

  /**
   * Sets up the test request by arranging the necessary context for a DELETE request.
   *
   * @return an instance of {@code TestArrange1DeleteUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1DeleteUrl arrange() {
    var context = super.createContext(DELETE);
    return new TestArrangeDeleteUrlImpl(context);
  }
}
