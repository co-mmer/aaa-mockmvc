package ej.aaamockmvc.test.web.arrange.get.url;

import ej.aaamockmvc.test.web.act.TestAct1Perform;
import ej.aaamockmvc.test.web.arrange.base.TestArrangeBaseUrl;
import ej.aaamockmvc.test.web.arrange.get.head.TestArrange1GetHead;
import ej.aaamockmvc.test.web.arrange.get.head.TestArrangeGetHeadImpl;
import ej.aaamockmvc.test.web.arrange.get.param.TestArrange1GetParam;
import ej.aaamockmvc.test.web.arrange.get.param.TestArrangeGetParamImpl;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.net.URI;
import lombok.NonNull;

/**
 * This class is responsible for configuring the URL, parameters, and headers for a GET request.
 *
 * <p>It provides methods to set the request URL with or without path variables, arrange query
 * parameters, arrange headers, and finally execute the GET request.
 *
 * @since 1.0.0
 */
public final class TestArrangeGetUrlImpl extends TestArrangeBaseUrl
    implements TestArrange1GetUrl, TestArrange2GetUrl {

  /**
   * Initializes the arrangement for a GET request using the provided {@code TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null}) *
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeGetUrlImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the URL for the GET request.
   *
   * @param url the URL template to be set for the request (must not be {@code null})
   * @return the current {@code TestArrange2GetUrl} instance for further configuration
   * @throws NullPointerException if the {@code url} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2GetUrl arrangeUrl(@NonNull String url) {
    setUri(url);
    return this;
  }

  /**
   * Arranges the URL for the GET request with path variables.
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
  public TestArrange2GetUrl arrangeUrl(@NonNull String url, Object... variable) {
    setUri(url, variable);
    return this;
  }

  /**
   * Arranges the URI for the GET request.
   *
   * @param uri the {@code URI} to be set for the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2GetUrl arrangeUri(@NonNull URI uri) {
    setUri(uri);
    return this;
  }

  /**
   * Arranges the parameters for the GET request.
   *
   * @return an instance of {@code TestArrange1GetParam} to arrange request parameters
   * @since 1.0.0
   */
  @Override
  public TestArrange1GetParam arrangeParam() {
    return new TestArrangeGetParamImpl(getContext());
  }

  /**
   * Arranges the headers for the GET request.
   *
   * @return an instance of {@code TestArrange1GetHead} to arrange request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange1GetHead arrangeHead() {
    return new TestArrangeGetHeadImpl(getContext());
  }

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1Perform act() {
    return createActPerformImpl();
  }
}
