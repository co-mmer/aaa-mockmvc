package ej.test.aaamockmvc.request.arrange.info.param;

import ej.test.aaamockmvc.context.TestRequestContext;
import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.arrange.base.TestArrangeBaseParam;
import ej.test.aaamockmvc.request.arrange.info.head.TestArrange1InfoHead;
import ej.test.aaamockmvc.request.arrange.info.head.TestArrangeInfoHeadImpl;
import java.util.Map;
import lombok.NonNull;

/**
 * This class is responsible for configuring the query parameters for a HEAD/OPTIONS request.
 *
 * <p>It provides methods to arrange single key-value pairs as well as multiple key-value pairs for
 * the request's query parameters. Additionally, it allows for the arrangement of headers and
 * execution of the HEAD/OPTIONS request.
 *
 * @since 1.0.0
 */
public final class TestArrangeInfoParamImpl extends TestArrangeBaseParam
    implements TestArrange1InfoParam, TestArrange2InfoParam, TestArrange3InfoParam {

  /**
   * Initializes the arrangement for HEAD/OPTIONS request parameters using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeInfoParamImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges a single key-value pair for the HEAD/OPTIONS request parameters.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange2InfoParam arrangeKeyValue(String key, String value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple key-value pairs for the HEAD/OPTIONS request parameters.
   *
   * @param keyValue a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange3InfoParam arrangeKeyValue(@NonNull Map<String, String> keyValue) {
    putAll(keyValue);
    return this;
  }

  /**
   * Arranges the headers for the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestArrange1InfoHead} to configure the request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange1InfoHead arrangeHead() {
    return new TestArrangeInfoHeadImpl(getContext());
  }

  /**
   * Executes the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1Perform act() {
    return createActPerformImpl();
  }
}
