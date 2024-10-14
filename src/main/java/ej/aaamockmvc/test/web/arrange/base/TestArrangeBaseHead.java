package ej.aaamockmvc.test.web.arrange.base;

import ej.aaamockmvc.test.web.arrange.utils.TestArrangeHeadUtils;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Abstract base class for arranging headers in a GET request.
 *
 * <p>Provides methods to configure common HTTP headers such as "Accept", "Content-Type", and custom
 * key-value pairs. This class is extended by concrete classes to arrange specific headers for
 * requests.
 *
 * @since 1.0.0
 */
public abstract class TestArrangeBaseHead extends TestArrangeBaseAbstract {

  private static final String AUTHORIZATION = "Authorization";

  /**
   * Initializes the arrangement for request headers using the provided {@code TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  protected TestArrangeBaseHead(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Sets the "Accept" header for the request.
   *
   * <p>Specifies the media types that the client expects in the response.
   *
   * @param types the acceptable media types for the response (must not be {@code null})
   * @throws NullPointerException if the {@code types} is {@code null}
   * @since 1.0.0
   */
  protected void setAccepts(@NonNull MediaType... types) {
    TestArrangeHeadUtils.setAccepts(getHead(), types);
  }

  /**
   * Sets the "Authorization" header for the request.
   *
   * <p>Adds the provided authorization value to the request headers using the "Authorization"
   * header key.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @since 1.0.0
   */
  protected void setAuth(String token) {
    TestArrangeHeadUtils.addKeyValue(getHead(), AUTHORIZATION, token);
  }

  /**
   * Sets the "Content-Type" header for the request.
   *
   * <p>Specifies the media types for the content being sent by the client.
   *
   * @param types the content types to be sent in the request (must not be {@code null})
   * @throws NullPointerException if the {@code types} is {@code null}
   * @since 1.0.0
   */
  protected void setContentTypes(@NonNull MediaType... types) {
    TestArrangeHeadUtils.setContentTypes(getHead(), types);
  }

  /**
   * Adds a custom key-value pair to the request headers.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @since 1.0.0
   */
  protected void put(String key, Object value) {
    TestArrangeHeadUtils.addKeyValue(getHead(), key, value);
  }

  /**
   * Adds multiple custom key-value pairs to the request headers.
   *
   * @param keyValue a map containing header names and their corresponding values (must not be
   *     {@code null})
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  protected void putAll(@NonNull Map<String, Object> keyValue) {
    TestArrangeHeadUtils.addKeyValue(getHead(), keyValue);
  }
}
