package ej.test.aaamockmvc.request.arrange.get.url;

import java.net.URI;
import lombok.NonNull;

/**
 * Interface for arranging the URL of a GET request.
 *
 * <p>Provides methods to configure the URL with or without path variables.
 *
 * @since 1.0.0
 */
public interface TestArrange1GetUrl {

  /**
   * Arranges the URL for the GET request.
   *
   * <p>This method sets the URL to be used for the request.
   *
   * @param url the URL template to be set for the request (must not be {@code null})
   * @return an instance of {@code TestArrange2GetUrl} for further configuration
   * @throws NullPointerException if the {@code url} is {@code null}
   * @since 1.0.0
   */
  TestArrange2GetUrl arrangeUrl(@NonNull String url);

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
   * @return the current {@code TestArrange2GetUrl} instance for further configuration
   * @throws NullPointerException if the {@code url} is {@code null}
   * @throws IllegalArgumentException if there are not enough variables provided to fully expand the
   *     {@code url} template
   * @since 1.0.0
   */
  TestArrange2GetUrl arrangeUrl(@NonNull String url, Object... variable);

  /**
   * Arranges the URI for the GET request.
   *
   * <p>This method allows the direct setting of a {@link URI} for the request.
   *
   * @param uri the URI to be set for the request (must not be {@code null})
   * @return the current {@code TestArrange2GetUrl} instance for further configuration
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  TestArrange2GetUrl arrangeUri(@NonNull URI uri);
}
