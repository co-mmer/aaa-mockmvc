package io.github.co_mmer.aaamockmvc.test.web.arrange.info.url;

import java.net.URI;
import lombok.NonNull;

/**
 * Interface for arranging the URL of a HEAD/OPTIONS request.
 *
 * <p>Provides methods to configure the URL with or without path variables.
 *
 * @since 1.0.0
 */
public interface TestArrange1InfoUrl {

  /**
   * Arranges the URL for the HEAD/OPTIONS request.
   *
   * <p>This method sets the URL to be used for the request.
   *
   * @param url the URL template to be set for the request (must not be {@code null})
   * @return an instance of {@code TestArrange2InfoUrl} for further configuration
   * @throws NullPointerException if the {@code url} is {@code null}
   * @since 1.0.0
   */
  TestArrange2InfoUrl arrangeUrl(@NonNull String url);

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
   * @return the current {@code TestArrange2InfoUrl} instance for further configuration
   * @throws NullPointerException if the {@code url} is {@code null}
   * @throws IllegalArgumentException if there are not enough variables provided to fully expand the
   *     {@code url} template
   * @since 1.0.0
   */
  TestArrange2InfoUrl arrangeUrl(@NonNull String url, Object... variable);

  /**
   * Arranges the URI for the HEAD/OPTIONS request.
   *
   * <p>This method sets the URL template and inserts the given path parameters.
   *
   * @param uri the URI for the request (must not be {@code null})
   * @return an instance of {@code TestArrange2InfoUrl} for further configuration
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  TestArrange2InfoUrl arrangeUri(@NonNull URI uri);
}
