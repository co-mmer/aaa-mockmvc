package ej.aaamockmvc.test.web.arrange.delete.url;

import ej.aaamockmvc.test.web.act.TestAct1Perform;
import ej.aaamockmvc.test.web.arrange.base.url.TestArrangeBaseUrl;
import ej.aaamockmvc.test.web.arrange.delete.head.TestArrange1DeleteHead;
import ej.aaamockmvc.test.web.arrange.delete.head.TestArrangeDeleteHeadImpl;
import ej.aaamockmvc.test.web.arrange.delete.param.TestArrange1DeleteParam;
import ej.aaamockmvc.test.web.arrange.delete.param.TestArrangeDeleteParamImpl;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.net.URI;
import lombok.NonNull;

/**
 * This class is responsible for configuring the URL, parameters, and headers for a DELETE request.
 *
 * <p>It provides methods to set the request URL with or without path variables, arrange query
 * parameters, arrange headers, and finally execute the DELETE request.
 *
 * @since 1.0.0
 */
public final class TestArrangeDeleteUrlImpl extends TestArrangeBaseUrl
    implements TestArrange1DeleteUrl, TestArrange2DeleteUrl {

  /**
   * Initializes the arrangement for DELETE request URLs using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeDeleteUrlImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the URL for the DELETE request.
   *
   * @param url the URL to be set for the request (must not be {@code null})
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange2DeleteUrl arrangeUrl(@NonNull String url) {
    setUri(url);
    return this;
  }

  /**
   * Arranges the URL for the DELETE request with path variables.
   *
   * <p>This method allows the setting of a URL template with optional variables that will be
   * substituted into the template. Supported types for the variables include {@code String}, {@code
   * Character}, {@code Integer}, {@code Double}, {@code Float}, and {@code Boolean}.
   *
   * @param url the URL template to be set for the request (must not be {@code null})
   * @param variable the variables to be used in the URL. Supported types are: {@code String},
   *     {@code Character}, {@code Integer}, {@code Double}, {@code Float}, and {@code Boolean}.
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code url} is {@code null}
   * @throws IllegalArgumentException if there are not enough variables provided to fully expand the
   *     {@code url} template
   * @since 1.0.0
   */
  @Override
  public TestArrange2DeleteUrl arrangeUrl(@NonNull String url, Object... variable) {
    setUri(url, variable);
    return this;
  }

  /**
   * Arranges the URI for the DELETE request.
   *
   * @param uri the {@code URI} to be set for the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2DeleteUrl arrangeUri(@NonNull URI uri) {
    setUri(uri);
    return this;
  }

  /**
   * Arranges the parameters for the DELETE request.
   *
   * @return an instance of {@code TestArrange1DeleteParam} to arrange request parameters
   * @since 1.0.0
   */
  @Override
  public TestArrange1DeleteParam arrangeParam() {
    return new TestArrangeDeleteParamImpl(getContext());
  }

  /**
   * Arranges the headers for the DELETE request.
   *
   * @return an instance of {@code TestArrange1DeleteHead} to arrange request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange1DeleteHead arrangeHead() {
    return new TestArrangeDeleteHeadImpl(getContext());
  }

  /**
   * Executes the DELETE request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1Perform act() {
    return createActPerformImpl();
  }
}
