package io.github.co_mmer.aaamockmvc.test.web.answer;

import io.github.co_mmer.aaamockmvc.test.web.answer.exception.TestAnswerException;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContext;
import java.io.UnsupportedEncodingException;
import org.springframework.lang.NonNull;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * This class represents the implementation of the {@link TestAnswer} interface, providing methods
 * for executing HTTP requests and retrieving their responses in a testing context.
 *
 * @since 1.2.0
 */
public final class TestAnswerImpl implements TestAnswer {

  private final TestRequestBean bean;
  private final MockHttpServletRequestBuilder requestBuilder;

  /**
   * Constructs an instance of {@code TestAnswerImpl} with the specified request context and request
   * builder.
   *
   * @param context the {@link TestRequestContext} containing the request context and associated
   *     beans (must not be {@code null})
   * @param requestBuilder the {@link MockHttpServletRequestBuilder} used to build and perform the
   *     HTTP request (must not be {@code null})
   * @throws NullPointerException if either {@code context} or {@code requestBuilder} is {@code
   *     null}
   * @since 1.2.0
   */
  public TestAnswerImpl(
      @NonNull TestRequestContext context, @NonNull MockHttpServletRequestBuilder requestBuilder) {

    this.bean = context.bean();
    this.requestBuilder = requestBuilder;
  }

  /**
   * Performs the HTTP request and returns the actions result.
   *
   * @return the {@link ResultActions} for the performed request
   * @throws TestAnswerException if an error occurs while performing the request
   * @since 1.2.0
   */
  @Override
  public ResultActions answerAsResultActions() throws TestAnswerException {
    try {
      return this.bean.mvc().perform(this.requestBuilder);
    } catch (Exception e) {
      throw new TestAnswerException(e);
    }
  }

  /**
   * Retrieves the content of the response as a string.
   *
   * @return the response content as a string
   * @throws TestAnswerException if an error occurs while retrieving the content
   * @since 1.2.0
   */
  @Override
  public String answerAsString() throws TestAnswerException {
    try {
      return getResponse().getContentAsString();
    } catch (UnsupportedEncodingException e) {
      throw new TestAnswerException(e);
    }
  }

  private MockHttpServletResponse getResponse() throws TestAnswerException {
    return answerAsResultActions().andReturn().getResponse();
  }

  /**
   * Retrieves the response as a byte array.
   *
   * @return the response content as a byte array
   * @throws TestAnswerException if an error occurs while retrieving the response
   * @since 1.2.0
   */
  @Override
  public byte[] answerAsByte() throws TestAnswerException {
    return getResponse().getContentAsByteArray();
  }

  /**
   * Retrieves the value of a specific header from the response.
   *
   * @param key the name of the header to retrieve (must not be {@code null})
   * @return the value of the specified header, or {@code null} if not present
   * @throws TestAnswerException if an error occurs while retrieving the header
   * @since 1.2.0
   */
  @Override
  public String answerHeader(String key) throws TestAnswerException {
    return getResponse().getHeader(key);
  }

  /**
   * Performs the HTTP request but does not return the result.
   *
   * @throws TestAnswerException if an error occurs while performing the request
   * @since 1.2.0
   */
  @Override
  public void answerVoid() throws TestAnswerException {
    answerAsResultActions();
  }
}
