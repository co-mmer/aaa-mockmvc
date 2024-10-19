package io.github.co_mmer.aaamockmvc.test.web.arrange.info.url;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.arrange.base.url.TestArrangeBaseUrl;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.head.TestArrange1InfoHead;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.head.TestArrangeInfoHeadImpl;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.param.TestArrange1InfoParam;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.param.TestArrangeInfoParamImpl;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContext;
import java.net.URI;
import lombok.NonNull;

/**
 * This class is responsible for configuring the URL, parameters, and headers for a HEAD/OPTIONS
 * request.
 *
 * <p>It provides methods to set the request URL with or without path variables, arrange query
 * parameters, arrange headers, and finally execute the HEAD/OPTIONS request.
 *
 * @since 1.0.0
 */
public final class TestArrangeInfoUrlImpl extends TestArrangeBaseUrl
    implements TestArrange1InfoUrl, TestArrange2InfoUrl {

  /**
   * Initializes the arrangement for HEAD/OPTIONS request URLs using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeInfoUrlImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the URL template for the HEAD/OPTIONS request.
   *
   * @param url the URL to be set for the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2InfoUrl arrangeUrl(@NonNull String url) {
    setUri(url);
    return this;
  }

  /**
   * Arranges the URL for the HEAD/OPTIONS request with path variables.
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
  public TestArrange2InfoUrl arrangeUrl(@NonNull String url, Object... variable) {
    setUri(url, variable);
    return this;
  }

  /**
   * Arranges the URI for the HEAD/OPTIONS request.
   *
   * @param uri the {@code URI} to be set for the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2InfoUrl arrangeUri(@NonNull URI uri) {
    setUri(uri);
    return this;
  }

  /**
   * Arranges the parameters for the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestArrange1InfoParam} to arrange request parameters
   * @since 1.0.0
   */
  @Override
  public TestArrange1InfoParam arrangeParam() {
    return new TestArrangeInfoParamImpl(getContext());
  }

  /**
   * Arranges the headers for the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestArrange1InfoHead} to arrange request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange1InfoHead arrangeHead() {
    return new TestArrangeInfoHeadImpl(getContext());
  }

  /**
   * Executes the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1 act() {
    return createActImpl();
  }
}
