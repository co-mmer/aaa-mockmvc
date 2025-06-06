package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.head.TestArrangeBaseHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrangeResBodyImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Implementation of header arrangement for PATCH/POST/PUT requests.
 *
 * <p>This class provides methods to configure various headers including "Accept", "Authorization",
 * "Content-Type", as well as custom key-value pairs for the request headers.
 *
 * @since 1.0.0
 */
public final class TestArrangeResHeadImpl extends TestArrangeBaseHead
    implements TestArrange1ResHead,
        TestArrange2ResHead,
        TestArrange3ResHead,
        TestArrange4ResHead,
        TestArrange5ResHead,
        TestArrange6ResHead {

  /**
   * Initializes the arrangement for PATCH/POST/PUT request headers using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeResHeadImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the "Accept" header for the PATCH/POST/PUT request.
   *
   * @param mediaTypes the acceptable media types for the response (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2ResHead arrangeAccept(@NonNull MediaType... mediaTypes) {
    setAccepts(mediaTypes);
    return this;
  }

  /**
   * Arranges the "Authorization" header for the PATCH/POST/PUT request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange3ResHead arrangeAuth(String token) {
    setAuth(token);
    return this;
  }

  /**
   * Arranges the "Content-Type" header for the PATCH/POST/PUT request.
   *
   * <p>When sending a request without a body, the `Content-Type` header may need to be explicitly
   * set to inform the server about the request's format and how it should be processed. If the
   * request includes a body, the `Content-Type` is either automatically determined or manually set
   * by the {@link #arrangeBody()} method.
   *
   * @param mediaType the content type to be sent in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code mediaType} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange4ResHead arrangeContentType(@NonNull MediaType mediaType) {
    setContentTypes(mediaType);
    return this;
  }

  /**
   * Arranges a custom key-value pair to be added as a header in the PATCH/POST/PUT request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange5ResHead arrangeKeyValue(String key, Object value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple custom key-value pairs to be added as headers in the PATCH/POST/PUT request.
   *
   * @param keyValue a map containing header names and their corresponding values (must not be
   *     {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange6ResHead arrangeKeyValue(@NonNull Map<String, Object> keyValue) {
    putAll(keyValue);
    return this;
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
