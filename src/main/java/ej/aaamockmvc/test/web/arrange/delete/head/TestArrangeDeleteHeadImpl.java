package ej.aaamockmvc.test.web.arrange.delete.head;

import ej.aaamockmvc.test.web.act.TestAct1Perform;
import ej.aaamockmvc.test.web.arrange.base.head.TestArrangeBaseHead;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * This class is responsible for configuring the headers of a DELETE request.
 *
 * <p>It provides methods to set common HTTP headers such as "Accept", "Authorization", and
 * "Content-Type", as well as custom key-value header pairs. After configuring the headers, the
 * DELETE request can be executed.
 *
 * @since 1.0.0
 */
public final class TestArrangeDeleteHeadImpl extends TestArrangeBaseHead
    implements TestArrange1DeleteHead,
        TestArrange2DeleteHead,
        TestArrange3DeleteHead,
        TestArrange4DeleteHead,
        TestArrange5DeleteHead,
        TestArrange6DeleteHead {

  /**
   * Initializes the arrangement for DELETE request headers using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeDeleteHeadImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the "Accept" header for the DELETE request.
   *
   * @param mediaTypes the acceptable media types for the response (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2DeleteHead arrangeAccept(@NonNull MediaType... mediaTypes) {
    setAccepts(mediaTypes);
    return this;
  }

  /**
   * Arranges the "Authorization" header for the DELETE request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange3DeleteHead arrangeAuth(String token) {
    setAuth(token);
    return this;
  }

  /**
   * Arranges the "Content-Type" header for the DELETE request.
   *
   * @param mediaTypes the content types to be sent in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange4DeleteHead arrangeContentType(@NonNull MediaType... mediaTypes) {
    setContentTypes(mediaTypes);
    return this;
  }

  /**
   * Arranges a custom key-value pair to be added as a header in the DELETE request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange5DeleteHead arrangeKeyValue(String key, Object value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple custom key-value pairs to be added as headers in the DELETE request.
   *
   * @param keyValue a map containing header names and their corresponding values (must not be
   *     {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange6DeleteHead arrangeKeyValue(@NonNull Map<String, Object> keyValue) {
    putAll(keyValue);
    return this;
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
