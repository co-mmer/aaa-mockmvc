package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.url.TestArrangeBaseUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrangeResBodyImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head.TestArrange1ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head.TestArrangeResHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.param.TestArrange1ResParam;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.param.TestArrangeResParamImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import java.net.URI;
import lombok.NonNull;

/**
 * This class is responsible for configuring the URL, parameters, headers, body for a PATCH/POST/PUT
 * request.
 *
 * <p>It provides methods to set the request URL with or without path variables, arrange query
 * parameters, arrange headers, and finally execute the PATCH/POST/PUT request.
 *
 * @since 1.0.0
 */
public final class TestArrangeResUrlImpl extends TestArrangeBaseUrl
    implements TestArrange1ResUrl, TestArrange2ResUrl {

  /**
   * Initializes the arrangement for PATCH/POST/PUT request URLs using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeResUrlImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the URL for the PATCH/POST/PUT request.
   *
   * @param url the URL template to be set for the request (must not be {@code null})
   * @return the current {@code TestArrange2ResUrl} instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange2ResUrl arrangeUrl(@NonNull String url) {
    setUri(url);
    return this;
  }

  /**
   * Arranges the URL for the PATCH/POST/PUT request with path variables.
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
  public TestArrange2ResUrl arrangeUrl(@NonNull String url, Object... variable) {
    setUri(url, variable);
    return this;
  }

  /**
   * Arranges the URI for the PATCH/POST/PUT request.
   *
   * @param uri the {@code URI} to be set for the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2ResUrl arrangeUri(@NonNull URI uri) {
    setUri(uri);
    return this;
  }

  /**
   * Arranges the parameters for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResParam} to arrange request parameters
   * @since 1.0.0
   */
  @Override
  public TestArrange1ResParam arrangeParam() {
    return new TestArrangeResParamImpl(getContext());
  }

  /**
   * Arranges the headers for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResHead} to arrange request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange1ResHead arrangeHead() {
    return new TestArrangeResHeadImpl(getContext());
  }

  /**
   * Arranges the body for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResBody} to arrange request body
   * @since 1.0.0
   */
  @Override
  public TestArrange1ResBody arrangeBody() {
    return new TestArrangeResBodyImpl(getContext());
  }

  /**
   * Executes the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1 act() {
    return createActImpl();
  }
}
