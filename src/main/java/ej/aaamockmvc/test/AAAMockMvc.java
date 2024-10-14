package ej.aaamockmvc.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.web.request.TestRequestDelete;
import ej.aaamockmvc.test.web.request.TestRequestGet;
import ej.aaamockmvc.test.web.request.TestRequestHead;
import ej.aaamockmvc.test.web.request.TestRequestOption;
import ej.aaamockmvc.test.web.request.TestRequestPatch;
import ej.aaamockmvc.test.web.request.TestRequestPost;
import ej.aaamockmvc.test.web.request.TestRequestPut;
import ej.aaamockmvc.test.web.request.context.TestRequestBean;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * This class provides a simplified API for performing various HTTP requests (GET, POST, PUT,
 * DELETE, PATCH, etc.) using the MockMvc framework in a Spring web application context.
 *
 * <p>Each HTTP method corresponds to a method in this class that returns an instance of a specific
 * request class, which can be further configured before executing the request.
 *
 * <p>The class can be initialized either with a {@code WebApplicationContext} to automatically set
 * up the {@code MockMvc} instance, or directly with a preconfigured {@code MockMvc} object and
 * optional {@code ObjectMapper} for JSON serialization.
 *
 * @since 1.0.0
 */
public final class AAAMockMvc {

  private final TestRequestBean bean;

  /**
   * Constructs an instance of {@code AAAMockMvc} with a {@code WebApplicationContext} to set up a
   * {@code MockMvc} instance.
   *
   * @param webApplicationContext the {@code WebApplicationContext} to initialize the {@code
   *     MockMvc} (must not be {@code null})
   * @throws NullPointerException if the {@code webApplicationContext} is {@code null}
   * @since 1.0.0
   */
  public AAAMockMvc(@NonNull WebApplicationContext webApplicationContext) {
    var mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.bean = new TestRequestBean(mockMvc, new ObjectMapper());
  }

  /**
   * Constructs an instance of {@code AAAMockMvc} with a {@code WebApplicationContext} and a custom
   * {@code ObjectMapper} for JSON serialization.
   *
   * @param webApplicationContext the {@code WebApplicationContext} to initialize the {@code
   *     MockMvc} (must not be {@code null})
   * @param objectMapper the {@code ObjectMapper} to be used for JSON serialization
   * @throws NullPointerException if either the {@code webApplicationContext} or {@code
   *     objectMapper} is {@code null}
   * @since 1.0.0
   */
  public AAAMockMvc(
      @NonNull WebApplicationContext webApplicationContext, @NonNull ObjectMapper objectMapper) {
    var mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.bean = new TestRequestBean(mockMvc, objectMapper);
  }

  /**
   * Constructs an instance of {@code AAAMockMvc} with an existing {@code MockMvc} object.
   *
   * @param mockMvc the {@code MockMvc} object to be used for performing requests (must not be
   *     {@code null})
   * @throws NullPointerException if the {@code mockMvc} is {@code null}
   * @since 1.0.0
   */
  public AAAMockMvc(@NonNull MockMvc mockMvc) {
    this.bean = new TestRequestBean(mockMvc, new ObjectMapper());
  }

  /**
   * Constructs an instance of {@code AAAMockMvc} with an existing {@code MockMvc} object and a
   * custom {@code ObjectMapper} for JSON serialization.
   *
   * @param mockMvc the {@code MockMvc} object to be used for performing requests (must not be
   *     {@code null})
   * @param objectMapper the {@code ObjectMapper} to be used for JSON serialization
   * @throws NullPointerException if either the {@code mockMvc} or {@code objectMapper} is {@code
   *     null}
   * @since 1.0.0
   */
  public AAAMockMvc(@NonNull MockMvc mockMvc, @NonNull ObjectMapper objectMapper) {
    this.bean = new TestRequestBean(mockMvc, objectMapper);
  }

  /**
   * Returns an instance of {@code TestRequestGet} to perform GET requests.
   *
   * @return a {@code TestRequestGet} object for GET request configuration
   * @since 1.0.0
   */
  public TestRequestGet get() {
    return new TestRequestGet(this.bean);
  }

  /**
   * Returns an instance of {@code TestRequestPost} to perform POST requests.
   *
   * @return a {@code TestRequestPost} object for POST request configuration
   * @since 1.0.0
   */
  public TestRequestPost post() {
    return new TestRequestPost(this.bean);
  }

  /**
   * Returns an instance of {@code TestRequestPut} to perform PUT requests.
   *
   * @return a {@code TestRequestPut} object for PUT request configuration
   * @since 1.0.0
   */
  public TestRequestPut put() {
    return new TestRequestPut(this.bean);
  }

  /**
   * Returns an instance of {@code TestRequestPatch} to perform PATCH requests.
   *
   * @return a {@code TestRequestPatch} object for PATCH request configuration
   * @since 1.0.0
   */
  public TestRequestPatch patch() {
    return new TestRequestPatch(this.bean);
  }

  /**
   * Returns an instance of {@code TestRequestDelete} to perform DELETE requests.
   *
   * @return a {@code TestRequestDelete} object for DELETE request configuration
   * @since 1.0.0
   */
  public TestRequestDelete delete() {
    return new TestRequestDelete(this.bean);
  }

  /**
   * Returns an instance of {@code TestRequestOption} to perform OPTIONS requests.
   *
   * @return a {@code TestRequestOption} object for OPTIONS request configuration
   * @since 1.0.0
   */
  public TestRequestOption options() {
    return new TestRequestOption(this.bean);
  }

  /**
   * Returns an instance of {@code TestRequestHead} to perform HEAD requests.
   *
   * @return a {@code TestRequestHead} object for HEAD request configuration
   * @since 1.0.0
   */
  public TestRequestHead head() {
    return new TestRequestHead(this.bean);
  }
}
