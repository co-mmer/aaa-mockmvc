package ej.test.aaamockmvc.request.arrange.base;

import ej.test.aaamockmvc.context.TestRequestContext;
import ej.test.aaamockmvc.request.arrange.utils.TestArrangeUrlUtils;
import java.net.URI;
import lombok.NonNull;

/**
 * Abstract base class for arranging the URL in a request.
 *
 * <p>Provides methods to set the URL and handle optional path variables. This class is extended by
 * concrete classes to configure the URL for GET requests.
 *
 * @since 1.0.0
 */
public abstract class TestArrangeBaseUrl extends TestArrangeBaseAbstract {

  /**
   * Initializes the arrangement for request URLs using the provided {@code TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  protected TestArrangeBaseUrl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Sets the URI for the request using a URL template.
   *
   * @param url the URL template to be set for the request (must not be {@code null})
   * @throws NullPointerException if the {@code url} is {@code null}
   * @since 1.0.0
   */
  protected void setUri(@NonNull String url) {
    TestArrangeUrlUtils.setUri(getUrl(), url);
  }

  /**
   * Sets the URI for the request using a URL template and optional path variables.
   *
   * <p>This method allows for the inclusion of path variables in the URL, which are substituted
   * into the URL at runtime. The supported types for path variables include {@code String}, {@code
   * Character}, {@code Integer}, {@code Double}, {@code Float}, and {@code Boolean}.
   *
   * @param url the URL template to be set for the request (must not be {@code null})
   * @param variable the optional path variables to be included in the URL
   * @throws NullPointerException if the {@code url} is {@code null}
   * @throws IllegalArgumentException if there are not enough variables provided to fully expand the
   *     {@code url} template
   * @since 1.0.0
   */
  protected void setUri(@NonNull String url, Object... variable) {
    TestArrangeUrlUtils.setUri(getUrl(), url, variable);
  }

  /**
   * Sets the URI for the request using a pre-constructed {@code URI} object.
   *
   * <p>This method is used when a fully formed {@code URI} is available and no path variables need
   * to be substituted.
   *
   * @param uri the {@code URI} to be set for the request (must not be {@code null})
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  protected void setUri(@NonNull URI uri) {
    TestArrangeUrlUtils.setUri(getUrl(), uri);
  }
}
