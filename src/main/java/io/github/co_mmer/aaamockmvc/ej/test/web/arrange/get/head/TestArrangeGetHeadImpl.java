package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head.TestArrangeBaseHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * This class is responsible for configuring the headers of a GET request.
 *
 * <p>It provides methods to set common HTTP headers such as "Accept", "Authorization", and custom
 * key-value header pairs. After configuring the headers, the GET request can be executed.
 *
 * @since 1.0.0
 */
public final class TestArrangeGetHeadImpl extends TestArrangeBaseHead
    implements TestArrange1GetHead,
        TestArrange2GetHead,
        TestArrange3GetHead,
        TestArrange4GetHead,
        TestArrange5GetHead,
        TestArrange6GetHead {

  /**
   * Initializes the arrangement for GET request headers using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeGetHeadImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the "Accept" header for the GET request.
   *
   * @param mediaTypes the acceptable media types for the response (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2GetHead arrangeAccept(@NonNull MediaType... mediaTypes) {
    setAccepts(mediaTypes);
    return this;
  }

  /**
   * Arranges the "Authorization" header for the GET request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange3GetHead arrangeAuth(String token) {
    setAuth(token);
    return this;
  }

  /**
   * Arranges a custom key-value pair to be added as a header in the GET request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange5GetHead arrangeKeyValue(String key, Object value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple custom key-value pairs to be added as headers in the GET request.
   *
   * @param keyValue a map containing header names and their corresponding values (must not be
   *     {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange6GetHead arrangeKeyValue(@NonNull Map<String, Object> keyValue) {
    putAll(keyValue);
    return this;
  }

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1 act() {
    return createActImpl();
  }
}
