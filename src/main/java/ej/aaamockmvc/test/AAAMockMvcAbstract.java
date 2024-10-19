package ej.aaamockmvc.test;

import ej.aaamockmvc.test.web.request.TestRequestDelete;
import ej.aaamockmvc.test.web.request.TestRequestGet;
import ej.aaamockmvc.test.web.request.TestRequestHead;
import ej.aaamockmvc.test.web.request.TestRequestOption;
import ej.aaamockmvc.test.web.request.TestRequestPatch;
import ej.aaamockmvc.test.web.request.TestRequestPost;
import ej.aaamockmvc.test.web.request.TestRequestPut;
import lombok.RequiredArgsConstructor;

/**
 * This class serves as an abstract base for performing REST operations using the {@link AAAMockMvc}
 * instance.
 *
 * <p>It provides protected methods for executing various types of HTTP requests such as GET, POST,
 * PUT, PATCH, DELETE, HEAD, and OPTIONS.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class AAAMockMvcAbstract {

  private final AAAMockMvc aaaMockMvc;

  /**
   * Prepares a GET request.
   *
   * @return an instance of {@link TestRequestGet} to configure the GET request
   * @since 1.0.0
   */
  protected final TestRequestGet get() {
    return aaaMockMvc.get();
  }

  /**
   * Prepares a POST request.
   *
   * @return an instance of {@link TestRequestPost} to configure the POST request
   * @since 1.0.0
   */
  protected final TestRequestPost post() {
    return aaaMockMvc.post();
  }

  /**
   * Prepares a PUT request.
   *
   * @return an instance of {@link TestRequestPut} to configure the PUT request
   * @since 1.0.0
   */
  protected final TestRequestPut put() {
    return aaaMockMvc.put();
  }

  /**
   * Prepares a PATCH request.
   *
   * @return an instance of {@link TestRequestPatch} to configure the PATCH request
   * @since 1.0.0
   */
  protected final TestRequestPatch patch() {
    return aaaMockMvc.patch();
  }

  /**
   * Prepares a DELETE request.
   *
   * @return an instance of {@link TestRequestDelete} to configure the DELETE request
   * @since 1.0.0
   */
  protected final TestRequestDelete delete() {
    return aaaMockMvc.delete();
  }

  /**
   * Prepares a HEAD request.
   *
   * @return an instance of {@link TestRequestHead} to configure the HEAD request
   * @since 1.0.0
   */
  protected final TestRequestHead head() {
    return aaaMockMvc.head();
  }

  /**
   * Prepares an OPTIONS request.
   *
   * @return an instance of {@link TestRequestOption} to configure the OPTIONS request
   * @since 1.0.0
   */
  protected final TestRequestOption options() {
    return aaaMockMvc.options();
  }
}
