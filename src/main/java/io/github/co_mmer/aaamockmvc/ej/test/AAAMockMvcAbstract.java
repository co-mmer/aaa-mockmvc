package io.github.co_mmer.aaamockmvc.ej.test;

import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestDelete;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestGet;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestOption;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPost;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.TestRequestPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * This class serves as an abstract base for performing REST operations using the {@link AAAMockMvc}
 * instance.
 *
 * <p>It provides protected methods for executing various types of HTTP requests such as GET, POST,
 * PUT, PATCH, DELETE, HEAD, and OPTIONS.
 *
 * @since 1.0.0
 */
@Import(AAAMockMvcConfig.class)
public abstract class AAAMockMvcAbstract {

  @Autowired private AAAMockMvc aaaMockMvc;

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
