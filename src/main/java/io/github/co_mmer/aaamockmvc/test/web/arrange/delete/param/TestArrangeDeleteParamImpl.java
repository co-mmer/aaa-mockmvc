package io.github.co_mmer.aaamockmvc.test.web.arrange.delete.param;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.arrange.base.url.TestArrangeBaseParam;
import io.github.co_mmer.aaamockmvc.test.web.arrange.delete.head.TestArrange1DeleteHead;
import io.github.co_mmer.aaamockmvc.test.web.arrange.delete.head.TestArrangeDeleteHeadImpl;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;

/**
 * This class is responsible for configuring the query parameters for a DELETE request.
 *
 * <p>It provides methods to arrange single key-value pairs as well as multiple key-value pairs for
 * the request's query parameters. Additionally, it allows for the arrangement of headers and
 * execution of the DELETE request.
 *
 * @since 1.0.0
 */
public final class TestArrangeDeleteParamImpl extends TestArrangeBaseParam
    implements TestArrange1DeleteParam, TestArrange2DeleteParam, TestArrange3DeleteParam {

  /**
   * Initializes the arrangement for DELETE request parameters using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeDeleteParamImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges a single key-value pair for the DELETE request parameters.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange2DeleteParam arrangeKeyValue(String key, String value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple key-value pairs for the DELETE request parameters.
   *
   * @param keyValue a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange3DeleteParam arrangeKeyValue(@NonNull Map<String, String> keyValue) {
    putAll(keyValue);
    return this;
  }

  /**
   * Arranges the headers for the DELETE request.
   *
   * @return an instance of {@code TestArrange1DeleteHead} to configure the request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange1DeleteHead arrangeHead() {
    return new TestArrangeDeleteHeadImpl(getContext());
  }

  /**
   * Executes the DELETE request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1 act() {
    return createActImpl();
  }
}
