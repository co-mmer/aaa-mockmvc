package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.param;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.url.TestArrangeBaseParam;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrangeResBodyImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head.TestArrange2ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head.TestArrangeResHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import java.util.Map;
import lombok.NonNull;

/**
 * This class is responsible for configuring the query parameters for a PATCH/POST/PUT request.
 *
 * <p>It provides methods to arrange single key-value pairs as well as multiple key-value pairs for
 * the request's query parameters. Additionally, it allows for the arrangement headers, and body
 * content, as well as the execution of the PATCH/POST/PUT request.
 *
 * @since 1.0.0
 */
public final class TestArrangeResParamImpl extends TestArrangeBaseParam
    implements TestArrange1ResParam,
        TestArrange2ResParam,
        TestArrange3ResParam,
        TestArrange4ResParam,
        TestArrange5ResParam {

  /**
   * Initializes the arrangement for PATCH/POST/PUT request parameters using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeResParamImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges a single key-value pair for the PATCH/POST/PUT request parameters.
   *
   * @param key the parameter name
   * @param value the parameter value
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestArrange4ResParam arrangeKeyValue(String key, String value) {
    put(key, value);
    return this;
  }

  /**
   * Arranges multiple key-value pairs for the PATCH/POST/PUT request parameters.
   *
   * @param keyValue a map of parameter names and their corresponding values (must not be {@code
   *     null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange5ResParam arrangeKeyValue(@NonNull Map<String, String> keyValue) {
    putAll(keyValue);
    return this;
  }

  /**
   * Arranges the headers for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange2ResHead} to configure the request headers
   * @since 1.0.0
   */
  @Override
  public TestArrange2ResHead arrangeHead() {
    return new TestArrangeResHeadImpl(getContext());
  }

  /**
   * Arranges the body for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResBody} to configure the request body
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
