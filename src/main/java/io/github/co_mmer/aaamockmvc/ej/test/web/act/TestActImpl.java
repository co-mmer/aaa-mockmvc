package io.github.co_mmer.aaamockmvc.ej.test.web.act;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.exception.TestActException;
import io.github.co_mmer.aaamockmvc.ej.test.web.act.strategy.TestRequestStrategyFactory;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswerImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestDto;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * This class is responsible for executing HTTP requests and providing access to assertions and
 * results within a testing context. It allows users to assert the response of the HTTP request
 * through the {@link #asserts()} method and access the results via the {@link #answer()} method.
 *
 * <p>Utilizing a {@link TestRequestContext}, it manages request data and integrates with the
 * {@link
 * MockMvc} framework to perform the actual HTTP requests, enabling comprehensive testing
 * strategies.
 *
 * @since 1.0.0
 */
public final class TestActImpl implements TestAct1, TestAct2 {

  private final TestRequestBean bean;
  private final TestRequestDto request;
  private final TestRequestContext context;
  private MockHttpServletRequestBuilder requestBuilder;

  /**
   * Constructs an instance of {@code TestActImpl} with the provided {@link TestRequestContext}.
   *
   * <p>This constructor initializes the request and {@code MockMvc} instance that will be used to
   * perform the HTTP request defined in the test context.
   *
   * @param context the {@link TestRequestContext} containing the request data and
   *                {@link TestRequestBean} (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestActImpl(@NonNull TestRequestContext context) {
    this.context = context;
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
   * Retrieves the {@link TestAssert} instance for asserting the response of the HTTP request.
   *
   * <p>This method allows for further validation of the result using various assertion methods,
   * enabling comprehensive checks on the HTTP response to ensure it meets expected criteria.
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

  private ResultActions resultActions() throws TestActException {
    try {
      return this.bean.mvc().perform(this.requestBuilder);
    } catch (Exception e) {
      throw new TestActException(e);
    }
  }

  /**
   * Retrieves the {@link TestAnswer} instance for the executed HTTP request.
   *
   * <p>This method provides access to the response content and other aspects of the request's
   * outcome, enabling further validation and examination of the HTTP response.
   *
   * @return a {@code TestAnswer} instance for accessing the result of the request
   * @since 1.2.0
   */
  @Override
  public TestAnswer answer() {
    return new TestAnswerImpl(this.context, this.requestBuilder);
  }
}
