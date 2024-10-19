package io.github.co_mmer.aaamockmvc.test.web.act;

import io.github.co_mmer.aaamockmvc.test.web.act.exception.TestActException;
import io.github.co_mmer.aaamockmvc.test.web.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.test.web.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.test.web.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContext;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestDto;
import java.io.UnsupportedEncodingException;
import org.springframework.lang.NonNull;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * This class represents the implementation of the HTTP request execution process in the testing
 * context. It provides methods for configuring, executing, and retrieving results from HTTP
 * requests.
 *
 * <p>Using the {@code MockMvc} framework, this class simulates various HTTP requests defined by the
 * {@link TestRequestDto} and allows for result extraction and validation.
 *
 * @since 1.0.0
 */
public final class TestActImpl implements TestAct1, TestAct2 {

  private final TestRequestBean bean;
  private final TestRequestDto request;
  private MockHttpServletRequestBuilder requestBuilder;

  /**
   * Constructs an instance of {@code TestActImpl} with the provided {@link TestRequestContext}.
   *
   * <p>This constructor initializes the request and {@code MockMvc} instance that will be used to
   * perform the HTTP request defined in the test context.
   *
   * @param context the {@link TestRequestContext} containing the request data and {@link
   *     TestRequestBean} (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestActImpl(@NonNull TestRequestContext context) {
    this.request = context.request();
    this.bean = context.bean();
  }

  /**
   * Executes the HTTP request defined in the {@link TestRequestDto} and returns the current
   * instance for further action chaining.
   *
   * @return the current instance for further configuration
   * @since 1.0.0
   */
  @Override
  public TestAct2 actPerform() {
    var strategy = TestRequestStrategyFactory.resolve(this.request.getType());
    this.requestBuilder = strategy.apply(this.request);
    return this;
  }

  /**
   * Performs the HTTP request and returns the actions result.
   *
   * @return the {@link ResultActions} for the performed request
   * @throws TestActException if an error occurs while performing the request
   * @since 1.0.0
   */
  @Override
  public ResultActions resultActions() throws TestActException {
    try {
      return this.bean.mvc().perform(this.requestBuilder);
    } catch (Exception e) {
      throw new TestActException(e);
    }
  }

  /**
   * Performs the HTTP request but does not return the result.
   *
   * @throws TestActException if an error occurs while performing the request
   * @since 1.0.0
   */
  @Override
  public void resultVoid() throws TestActException {
    resultActions();
  }

  /**
   * Retrieves the content of the response as a string.
   *
   * @return the response content as a string
   * @throws TestActException if an error occurs while retrieving the content
   * @since 1.0.0
   */
  @Override
  public String resultAsString() throws TestActException {
    try {
      return getResponse().getContentAsString();
    } catch (UnsupportedEncodingException e) {
      throw new TestActException(e);
    }
  }

  private MockHttpServletResponse getResponse() throws TestActException {
    return resultActions().andReturn().getResponse();
  }

  /**
   * Retrieves the response as a byte array.
   *
   * @return the response content as a byte array
   * @throws TestActException if an error occurs while retrieving the response
   * @since 1.0.0
   */
  @Override
  public byte[] resultAsByte() throws TestActException {
    return getResponse().getContentAsByteArray();
  }

  /**
   * Retrieves the value of a specific header from the response.
   *
   * @param key the name of the header to retrieve (must not be {@code null})
   * @return the value of the specified header, or {@code null} if not present
   * @throws TestActException if an error occurs while retrieving the header
   * @since 1.0.0
   */
  @Override
  public String resultHeader(String key) throws TestActException {
    return getResponse().getHeader(key);
  }

  /**
   * Returns an instance of {@link TestAssert} for asserting the response of the HTTP request.
   *
   * <p>This method allows for further validation of the result using various assertion methods.
   *
   * @return a {@code TestAssert} instance for asserting the result of the request
   * @throws TestActException if an error occurs while performing the request
   * @since 1.0.0
   */
  @Override
  public TestAssert asserts() throws TestActException {
    var resultActions = resultActions();
    return new TestAssertImpl(resultActions, this.bean.objectMapper());
  }
}
