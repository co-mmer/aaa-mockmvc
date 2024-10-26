package io.github.co_mmer.aaamockmvc.ej.test.web.answer;

import io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerException;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.ResultActions;

/**
 * This interface defines methods for handling responses from HTTP requests in a testing context. It
 * provides various ways to retrieve the results of executed HTTP requests, including response
 * content as strings, byte arrays, and headers.
 *
 * @since 1.2.0
 */
public interface TestAnswer {

  /**
   * Retrieves the {@link ResultActions} from the executed HTTP request.
   *
   * @return the result actions of the request
   * @throws TestAnswerException if an error occurs while retrieving the result actions
   * @since 1.2.0
   */
  ResultActions answerAsResultActions() throws TestAnswerException;

  /**
   * Retrieves the response content as a string from the executed HTTP request.
   *
   * @return the response content as a string
   * @throws TestAnswerException if an error occurs while retrieving the response content
   * @since 1.2.0
   */
  String answerAsString() throws TestAnswerException;

  /**
   * Retrieves the response content as a byte array from the executed HTTP request.
   *
   * @return the response content as a byte array
   * @throws TestAnswerException if an error occurs while retrieving the response content
   * @since 1.2.0
   */
  byte[] answerAsByte() throws TestAnswerException;

  /**
   * Retrieves the value of a specified response header from the executed HTTP request.
   *
   * @param key the name of the response header to retrieve
   * @return the value of the response header, or {@code null} if the header is not present
   * @throws TestAnswerException if an error occurs while retrieving the header value
   * @since 1.2.0
   */
  @Nullable
  String answerHeader(String key) throws TestAnswerException;

  /**
   * Executes the HTTP request without returning any content.
   *
   * @throws TestAnswerException if an error occurs during the execution
   * @since 1.2.0
   */
  void answerVoid() throws TestAnswerException;
}
