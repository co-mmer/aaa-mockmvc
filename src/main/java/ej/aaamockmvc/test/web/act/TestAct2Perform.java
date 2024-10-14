package ej.aaamockmvc.test.web.act;

import ej.aaamockmvc.test.web.act.exception.TestActException;
import ej.aaamockmvc.test.web.asserts.TestAssert;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.ResultActions;

/**
 * This interface represents a contract for performing actions and obtaining results from HTTP
 * requests.
 *
 * <p>It provides methods to assert the results of an HTTP request and retrieve response data,
 * allowing for verification of the request's outcome.
 *
 * @since 1.0.0
 */
public interface TestAct2Perform {

  /**
   * Retrieves the {@link ResultActions} from the executed HTTP request.
   *
   * @return the result actions of the request
   * @throws TestActException if an error occurs while retrieving the result actions
   * @since 1.0.0
   */
  ResultActions resultActions() throws TestActException;

  /**
   * Retrieves the response content as a string from the executed HTTP request.
   *
   * @return the response content as a string
   * @throws TestActException if an error occurs while retrieving the response content
   * @since 1.0.0
   */
  String resultAsString() throws TestActException;

  /**
   * Retrieves the response content as a byte array from the executed HTTP request.
   *
   * @return the response content as a byte array
   * @throws TestActException if an error occurs while retrieving the response content
   * @since 1.0.0
   */
  byte[] resultAsByte() throws TestActException;

  /**
   * Executes the HTTP request without returning any content.
   *
   * @throws TestActException if an error occurs during the execution
   * @since 1.0.0
   */
  void resultVoid() throws TestActException;

  /**
   * Retrieves the value of a specified response header from the executed HTTP request.
   *
   * @param key the name of the response header to retrieve
   * @return the value of the response header, or {@code null} if the header is not present
   * @throws TestActException if an error occurs while retrieving the header value
   * @since 1.0.0
   */
  @Nullable
  String resultHeader(String key) throws TestActException;

  /**
   * Returns an instance of {@link TestAssert} for asserting the response of the HTTP request.
   *
   * <p>This method allows for further validation of the result using various assertion methods.
   *
   * @return a {@code TestAssert} instance for asserting the result of the request
   * @throws TestActException if an error occurs while performing the request
   * @since 1.0.0
   */
  TestAssert asserts() throws TestActException;
}
