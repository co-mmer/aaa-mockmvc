package io.github.co_mmer.aaamockmvc.test.web.asserts.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.test.web.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.test.web.asserts.custom.TestAssertCustom;
import io.github.co_mmer.aaamockmvc.test.web.asserts.custom.TestAssertCustomImpl;
import io.github.co_mmer.aaamockmvc.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.test.web.asserts.head.TestAssertHeadImpl;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * This class provides implementation for assertions on HTTP response status in a testing context.
 * It allows various validations of the HTTP status of responses obtained from the {@code MockMvc}
 * framework.
 *
 * @since 1.1.0
 */
public final class TestAssertStatusImpl implements TestAssert1Status, TestAssert2Status {

  private static final int CLIENT_ERROR_MIN = 400;
  private static final int CLIENT_ERROR_MAX = 499;
  private static final int SERVER_ERROR_MIN = 500;
  private static final int SERVER_ERROR_MAX = 599;
  private static final int REDIRECT_MIN = 300;
  private static final int REDIRECT_MAX = 399;
  private final ResultActions actions;
  private final MockHttpServletResponse response;
  private final ObjectMapper objectMapper;

  /**
   * Constructs an instance of {@code TestAssertStatusImpl} with the provided {@code ResultActions}
   * and {@code ObjectMapper}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @param objectMapper the {@code ObjectMapper} for JSON serialization (must not be {@code null})
   * @throws NullPointerException if either {@code actions} or {@code objectMapper} is {@code null}
   * @since 1.1.0
   */
  public TestAssertStatusImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
    this.objectMapper = objectMapper;
  }

  /**
   * Asserts that the status of the HTTP response matches the given {@code HttpStatus}.
   *
   * @param status the expected {@code HttpStatus} of the response (must not be {@code null})
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatus(@NonNull HttpStatus status) {
    assertThat(this.response.getStatus(), is(status.value()));
    return this;
  }

  /**
   * Asserts that the status of the HTTP response matches the given status code.
   *
   * @param status the expected status code of the response
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatus(int status) {
    assertThat(this.response.getStatus(), is(status));
    return this;
  }

  /**
   * Asserts that the HTTP response status is 200 OK.
   *
   * <p>This method checks if the response status is HTTP 200 (OK). If the status does not match, an
   * assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsOk() {
    assertThat(this.response.getStatus(), is(HttpStatus.OK.value()));
    return this;
  }

  /**
   * Asserts that the HTTP response status is 201 Created.
   *
   * <p>This method checks if the response status is HTTP 201 (Created). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsCreated() {
    assertThat(this.response.getStatus(), is(HttpStatus.CREATED.value()));
    return this;
  }

  /**
   * Asserts that the HTTP response status is 202 Accepted.
   *
   * <p>This method checks if the response status is HTTP 202 (Accepted). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsAccepted() {
    assertThat(this.response.getStatus(), is(HttpStatus.ACCEPTED.value()));
    return this;
  }

  /**
   * Asserts that the HTTP response status is 404 Not Found.
   *
   * <p>This method checks if the response status is HTTP 404 (Not Found). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsNotFound() {
    assertThat(this.response.getStatus(), is(HttpStatus.NOT_FOUND.value()));
    return this;
  }

  /**
   * Asserts that the HTTP response status indicates a client error (4xx).
   *
   * <p>This method checks if the response status code is within the 400-499 range. If the status
   * does not match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsClientError() {
    assertThat(this.response.getStatus(), is(greaterThanOrEqualTo(CLIENT_ERROR_MIN)));
    assertThat(this.response.getStatus(), is(lessThanOrEqualTo(CLIENT_ERROR_MAX)));
    return this;
  }

  /**
   * Asserts that the HTTP response status indicates a server error (5xx).
   *
   * <p>This method checks if the response status code is within the 500-599 range. If the status
   * does not match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsServerError() {
    assertThat(this.response.getStatus(), is(greaterThanOrEqualTo(SERVER_ERROR_MIN)));
    assertThat(this.response.getStatus(), is(lessThanOrEqualTo(SERVER_ERROR_MAX)));
    return this;
  }

  /**
   * Asserts that the HTTP response status indicates a redirection (3xx).
   *
   * <p>This method checks if the response status code is within the 300-399 range. If the status
   * does not match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsRedirect() {
    assertThat(this.response.getStatus(), is(greaterThanOrEqualTo(REDIRECT_MIN)));
    assertThat(this.response.getStatus(), is(lessThanOrEqualTo(REDIRECT_MAX)));
    return this;
  }

  /**
   * Asserts that the HTTP response status is 403 Forbidden.
   *
   * <p>This method checks if the response status is HTTP 403 (Forbidden). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsAccessForbidden() {
    assertThat(this.response.getStatus(), is(HttpStatus.FORBIDDEN.value()));
    return this;
  }

  /**
   * Asserts that the HTTP response status is 401 Unauthorized.
   *
   * <p>This method checks if the response status is HTTP 401 (Unauthorized). If the status does not
   * match, an assertion failure is triggered.
   *
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusIsAccessUnauthorized() {
    assertThat(this.response.getStatus(), is(HttpStatus.UNAUTHORIZED.value()));
    return this;
  }

  /**
   * Asserts that the HTTP response status code is within a specified range.
   *
   * <p>This method checks if the status code of the response falls between the specified minimum
   * and maximum values (inclusive). If the actual status code does not fall within the range, an
   * assertion failure is triggered.
   *
   * @param minStatusCode the minimum expected status code (must be less than or equal to
   *     maxStatusCode)
   * @param maxStatusCode the maximum expected status code (must be greater than or equal to
   *     minStatusCode)
   * @return the current instance of {@code TestAssert2Status} for further assertions
   * @since 1.1.0
   */
  @Override
  public TestAssert2Status assertStatusInRange(int minStatusCode, int maxStatusCode) {
    assertThat(this.response.getStatus(), is(greaterThanOrEqualTo(minStatusCode)));
    assertThat(this.response.getStatus(), is(lessThanOrEqualTo(maxStatusCode)));
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
   * @since 1.1.0
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
   * @return an instance of {@code TestAssertHead} c on headers
   * @since 1.1.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }

  /**
   * Asserts that the HTTP response matches custom validation logic.
   *
   * <p>This method returns an instance of {@code TestAssertCustom} for asserting custom validations
   * on the HTTP response. It allows users to define their own result matchers or custom logic for
   * validating the response, giving flexibility beyond standard status, content, and header
   * assertions.
   *
   * @return an instance of {@code TestAssertCustom} for custom assertions on the response
   * @since 1.1.0
   */
  @Override
  public TestAssertCustom assertCustom() {
    return new TestAssertCustomImpl(this.actions);
  }
}
