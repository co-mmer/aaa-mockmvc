package io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.builder;

import java.net.URI;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Utility class for creating HTTP request builders for testing purposes.
 *
 * <p>This class provides static methods to create {@link MockHttpServletRequestBuilder} instances
 * for different HTTP methods such as GET, POST, PUT, PATCH, DELETE, HEAD, and OPTIONS.
 *
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestBuilder {

  /**
   * Creates a GET request builder with the specified URI.
   *
   * @param uri the URI for the GET request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a GET request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder get(@NonNull URI uri) {
    return MockMvcRequestBuilders.get(uri);
  }

  /**
   * Creates a POST request builder with the specified URI.
   *
   * @param uri the URI for the POST request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a POST request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder post(@NonNull URI uri) {
    return MockMvcRequestBuilders.post(uri);
  }

  /**
   * Creates a multipart POST request builder with the specified URI.
   *
   * @param uri the URI for the multipart POST request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a multipart POST request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder postMultipart(@NonNull URI uri) {
    return MockMvcRequestBuilders.multipart(uri);
  }

  /**
   * Creates a PUT request builder with the specified URI.
   *
   * @param uri the URI for the PUT request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a PUT request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder put(@NonNull URI uri) {
    return MockMvcRequestBuilders.put(uri);
  }

  /**
   * Creates a multipart PUT request builder with the specified URI.
   *
   * @param uri the URI for the multipart PUT request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a multipart PUT request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder putMultipart(@NonNull URI uri) {
    var requestBuilder = MockMvcRequestBuilders.multipart(uri);
    setMethod(requestBuilder, HttpMethod.PUT);
    return requestBuilder;
  }

  private static void setMethod(
      MockMultipartHttpServletRequestBuilder requestBuilder, HttpMethod method) {

    var requestPostProcessor = TestRequestBuilderUtils.setMethod(method);
    requestBuilder.with(requestPostProcessor);
  }

  /**
   * Creates a PATCH request builder with the specified URI.
   *
   * @param uri the URI for the PATCH request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a PATCH request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder patch(@NonNull URI uri) {
    return MockMvcRequestBuilders.patch(uri);
  }

  /**
   * Creates a multipart PATCH request builder with the specified URI.
   *
   * @param uri the URI for the multipart PATCH request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a multipart PATCH request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder patchMultipart(@NonNull URI uri) {
    var requestBuilder = MockMvcRequestBuilders.multipart(uri);
    setMethod(requestBuilder, HttpMethod.PATCH);
    return requestBuilder;
  }

  /**
   * Creates a DELETE request builder with the specified URI.
   *
   * @param uri the URI for the DELETE request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a DELETE request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder delete(@NonNull URI uri) {
    return MockMvcRequestBuilders.delete(uri);
  }

  /**
   * Creates a HEAD request builder with the specified URI.
   *
   * @param uri the URI for the HEAD request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for a HEAD request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder head(@NonNull URI uri) {
    return MockMvcRequestBuilders.head(uri);
  }

  /**
   * Creates an OPTIONS request builder with the specified URI.
   *
   * @param uri the URI for the OPTIONS request (must not be {@code null})
   * @return a {@link MockHttpServletRequestBuilder} configured for an OPTIONS request
   * @throws NullPointerException if the {@code uri} is {@code null}
   * @since 1.0.0
   */
  public static MockHttpServletRequestBuilder options(@NonNull URI uri) {
    return MockMvcRequestBuilders.options(uri);
  }
}
