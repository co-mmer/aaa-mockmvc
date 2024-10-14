package ej.aaamockmvc.test.web.arrange.get.param;

import ej.aaamockmvc.test.web.act.TestAct1Perform;
import ej.aaamockmvc.test.web.arrange.base.TestArrangeBaseParam;
import ej.aaamockmvc.test.web.arrange.get.head.TestArrange1GetHead;
import ej.aaamockmvc.test.web.arrange.get.head.TestArrangeGetHeadImpl;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;

/**
 * This class is responsible for configuring the query parameters for a GET request.
 *
 * <p>It provides methods to arrange single key-value pairs as well as multiple key-value pairs for
 * the request's query parameters. Additionally, it allows for the arrangement of headers and
 * execution of the GET request.
 *
 * @since 1.0.0
 */
public final class TestArrangeGetParamImpl extends TestArrangeBaseParam
    implements TestArrange1GetParam, TestArrange2GetParam, TestArrange3GetParam {

  /**
   * Initializes the arrangement for GET request parameters using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeGetParamImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges a single key-value pair for the GET request parameters.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange2GetParam arrangeKeyValue(String key, String value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple key-value pairs for the GET request parameters.
   *
   * @param keyValue a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange3GetParam arrangeKeyValue(@NonNull Map<String, String> keyValue) {
    putAll(keyValue);
    return this;
  }

  /**
   * Arranges the headers for the GET request.
   *
   * @return an instance of {@code TestArrange1GetHead} to configure the request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange1GetHead arrangeHead() {
    return new TestArrangeGetHeadImpl(getContext());
  }

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1Perform act() {
    return createActPerformImpl();
  }
}
