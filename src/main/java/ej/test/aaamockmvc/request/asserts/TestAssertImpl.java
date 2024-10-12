package ej.test.aaamockmvc.request.asserts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.test.aaamockmvc.request.asserts.content.TestAssertContent;
import ej.test.aaamockmvc.request.asserts.content.TestAssertContentImpl;
import ej.test.aaamockmvc.request.asserts.head.TestAssertHead;
import ej.test.aaamockmvc.request.asserts.head.TestAssertHeadImpl;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * This class provides implementation for assertions on HTTP responses in a testing context. It
 * allows various validations of response status, content, and headers.
 *
 * <p>It is primarily used for validating the results of HTTP requests performed with the {@code
 * MockMvc} framework in a Spring web application context.
 *
 * @since 1.0.0
 */
public final class TestAssertImpl implements TestAssert {

  private final ResultActions actions;
  private final MockHttpServletResponse response;
  private final ObjectMapper objectMapper;

  /**
   * Constructs an instance of {@code TestAssertImpl} with the provided {@code ResultActions} and
   * {@code ObjectMapper}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @param objectMapper the {@code ObjectMapper} for JSON serialization (must not be {@code null})
   * @throws NullPointerException if either {@code actions} or {@code objectMapper} is {@code null}
   * @since 1.0.0
   */
  public TestAssertImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
    this.objectMapper = objectMapper;
  }

  /**
   * Asserts that the status of the HTTP response matches the given {@code HttpStatus}.
   *
   * @param status the expected {@code HttpStatus} of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStatus(@NonNull HttpStatus status) {
    assertThat(this.response.getStatus(), is(status.value()));
    return this;
  }

  /**
   * Asserts that the status of the HTTP response matches the given status code.
   *
   * @param status the expected status code of the response
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStatus(int status) {
    assertThat(this.response.getStatus(), is(status));
    return this;
  }

  /**
   * Asserts that the result of the test request matches the given {@link ResultMatcher}.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByResultMatcher(@NonNull ResultMatcher matcher) {
    try {
      this.actions.andExpect(matcher);
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the HTTP response is valid for content assertions.
   *
   * <p>This method returns an instance of {@code TestAssertContent} for asserting the content of
   * the HTTP response. It allows various validations of response content, including checks for
   * emptiness and matching expected values.
   *
   * @return an instance of {@code TestAssertContent} for further assertions
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContent() {
    return new TestAssertContentImpl(this.actions, this.objectMapper);
  }

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for further assertions on headers
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
