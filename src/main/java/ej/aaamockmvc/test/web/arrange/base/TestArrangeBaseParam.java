package ej.aaamockmvc.test.web.arrange.base;

import ej.aaamockmvc.test.web.arrange.utils.TestArrangeUrlUtils;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;

/**
 * Abstract base class for arranging query parameters in a request.
 *
 * <p>This class provides common functionality for managing query parameters, including methods for
 * adding single or multiple key-value pairs as parameters to a URL. It is extended by concrete
 * classes that handle different types of requests.
 *
 * @since 1.0.0
 */
public abstract class TestArrangeBaseParam extends TestArrangeBaseAbstract {

  /**
   * Initializes the parameter arrangement using the provided {@code TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  protected TestArrangeBaseParam(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Adds a single key-value pair as a query parameter to the request.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @since 1.0.0
   */
  protected void put(String key, String value) {
    TestArrangeUrlUtils.addParam(getUrl(), key, value);
  }

  /**
   * Adds multiple key-value pairs as query parameters to the request.
   *
   * @param keyValue a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  protected void putAll(@NonNull Map<String, String> keyValue) {
    TestArrangeUrlUtils.addParam(getUrl(), keyValue);
  }
}
