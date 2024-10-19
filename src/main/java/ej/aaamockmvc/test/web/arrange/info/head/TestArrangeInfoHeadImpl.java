package ej.aaamockmvc.test.web.arrange.info.head;

import ej.aaamockmvc.test.web.act.TestAct1;
import ej.aaamockmvc.test.web.arrange.base.head.TestArrangeBaseHead;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * This class is responsible for configuring the headers of a HEAD/OPTIONS request.
 *
 * <p>It provides methods to set common HTTP headers such as "Accept", "Authorization", and
 * "Content-Type", as well as custom key-value header pairs. After configuring the headers, the
 * HEAD/OPTIONS request can be executed.
 *
 * @since 1.0.0
 */
public final class TestArrangeInfoHeadImpl extends TestArrangeBaseHead
    implements TestArrange1InfoHead,
        TestArrange2InfoHead,
        TestArrange3InfoHead,
        TestArrange4InfoHead,
        TestArrange5InfoHead,
        TestArrange6InfoHead {

  /**
   * Initializes the arrangement for HEAD/OPTIONS request headers using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeInfoHeadImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges the "Accept" header for the HEAD/OPTIONS request.
   *
   * @param mediaTypes the acceptable media types for the response (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2InfoHead arrangeAccept(@NonNull MediaType... mediaTypes) {
    setAccepts(mediaTypes);
    return this;
  }

  /**
   * Arranges the "Authorization" header for the HEAD/OPTIONS request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange3InfoHead arrangeAuth(String token) {
    setAuth(token);
    return this;
  }

  /**
   * Arranges the "Content-Type" header for the HEAD/OPTIONS request.
   *
   * @param mediaTypes the content types to be sent in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange4InfoHead arrangeContentType(@NonNull MediaType... mediaTypes) {
    setContentTypes(mediaTypes);
    return this;
  }

  /**
   * Arranges a custom key-value pair to be added as a header in the HEAD/OPTIONS request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange5InfoHead arrangeKeyValue(String key, Object value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple custom key-value pairs to be added as headers in the HEAD/OPTIONS request.
   *
   * @param keyValue a map containing header names and their corresponding values (must not be
   *     {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange6InfoHead arrangeKeyValue(@NonNull Map<String, Object> keyValue) {
    putAll(keyValue);
    return this;
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
