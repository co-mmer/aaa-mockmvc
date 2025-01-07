package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssertByteImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssertClassImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustom;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustomImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssertStringImpl;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting HTTP response statuses in tests.
 *
 * <ul>
 *   <li>{@link #assertStatus(HttpStatus)}: Asserts that the status of the HTTP response matches the
 *       given {@code HttpStatus}.
 *   <li>{@link #assertStatus(int)}: Asserts that the status of the HTTP response matches the given
 *       status code.
 *   <li>{@link #assertStatusIsOk()}: Asserts that the HTTP response status is 200 OK.
 *   <li>{@link #assertStatusIsCreated()}: Asserts that the HTTP response status is 201 Created.
 *   <li>{@link #assertStatusIsAccepted()}: Asserts that the HTTP response status is 202 Accepted.
 *   <li>{@link #assertStatusIsNotFound()}: Asserts that the HTTP response status is 404 Not Found.
 *   <li>{@link #assertStatusIsClientError()}: Asserts that the HTTP response status indicates a
 *       client error (4xx).
 *   <li>{@link #assertStatusIsServerError()}: Asserts that the HTTP response status indicates a
 *       server error (5xx).
 *   <li>{@link #assertStatusIsRedirect()}: Asserts that the HTTP response status indicates a
 *       redirection (3xx).
 *   <li>{@link #assertStatusIsAccessForbidden()}: Asserts that the HTTP response status is 403
 *       Forbidden.
 *   <li>{@link #assertStatusIsAccessUnauthorized()}: Asserts that the HTTP response status is 401
 *       Unauthorized.
 *   <li>{@link #assertStatusInRange(int, int)}: Asserts that the HTTP response status code is
 *       within a specified range.
 *   <li>{@link #assertContent()}: Provides assertion methods for validating the HTTP response
 *       content.
 *   <li>{@link #assertByte()}: Provides assertion methods for validating the HTTP response binary.
 *   <li>{@link #assertCollection()}: Provides assertion methods for validating the contents of an
 *       HTTP response collection.
 *   <li>{@link #assertMap()}: Provides assertion methods for validating the contents of an HTTP
 *       response map.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 *   <li>{@link #assertCustom()}: Provides assertion methods for validating the HTTP response based
 *       on custom logic.
 * </ul>
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
   * @param actions      the {@code ResultActions} from a performed HTTP request (must not be
   *                     {@code null})
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
   * @param minStatusCode the minimum expected status code (must be less than or equal to
   *                      maxStatusCode)
   * @param maxStatusCode the maximum expected status code (must be greater than or equal to
   *                      minStatusCode)
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
   * Provides assertion methods for validating the HTTP response content.
   *
   * <p>This method returns an instance of {@code TestAssertContent}, which provides assertion
   * methods for validating the content of the HTTP response, such as matching expected values or
   * checking for emptiness.
   *
   * @return an instance of {@code TestAssertContent} for asserting the response content
   * @since 1.1.0
   */
  @Override
  public TestAssert1String assertContent() {
    return new TestAssertStringImpl(this.actions);
  }

  /**
   * Provides assertion methods for validating the HTTP response content as class.
   *
   * <p>This method returns an instance of {@code TestAssertClass}, which provides assertion
   * methods
   * for validating the content of the HTTP response, such as matching expected values or checking
   * for emptiness.
   *
   * @return an instance of {@code TestAssertClass} for asserting the response content
   * @since 1.4.0
   */
  @Override
  public TestAssert1Class assertClass() {
    return new TestAssertClassImpl(this.actions, this.objectMapper);
  }

  /**
   * Provides assertion methods for validating the HTTP response byte.
   *
   * <p>This method returns an instance of {@code TestAssertBinary}, which provides assertion
   * methods for validating the content of the HTTP response, such as matching expected values or
   * checking for emptiness.
   *
   * @return an instance of {@code TestAssert1Byte} for asserting the response binary
   * @since 1.4.0
   */
  @Override
  public TestAssert1Byte assertByte() {
    return new TestAssertByteImpl(this.actions);
  }

  /**
   * Provides assertion methods for validating the contents of an HTTP response collection.
   *
   * <p>This method returns an instance of {@code TestAssert1Collection}, which provides assertion
   * methods for validating the contents of an HTTP response when the response is a collection.
   *
   * @return an instance of {@code TestAssert1Collection} for asserting the collection response
   * @since 1.4.0
   */
  @Override
  public TestAssert1Collection assertCollection() {
    return new TestAssertCollectionImpl(this.actions, this.objectMapper);
  }

  /**
   * Provides assertion methods for validating the contents of an HTTP response map.
   *
   * <p>This method returns an instance of {@code TestAssert1Map}, which provides assertion methods
   * for validating the contents of an HTTP response when the response is a map.
   *
   * @return an instance of {@code TestAssert1Map} for asserting the map response
   * @since 1.4.0
   */
  @Override
  public TestAssert1Map assertMap() {
    return new TestAssertMapImpl(this.actions, this.objectMapper);
  }

  /**
   * Provides assertion methods for validating the HTTP response headers.
   *
   * <p>This method returns an instance of {@code TestAssertHead}, which provides assertion methods
   * for validating the headers of the HTTP response, such as checking for the presence or absence
   * of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for asserting the response headers
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }

  /**
   * Provides assertion methods for validating the HTTP response based on custom logic.
   *
   * <p>This method returns an instance of {@code TestAssertCustom}, which allows users to define
   * custom validation logic or result matchers for the HTTP response, offering flexibility beyond
   * standard status, content, and header assertions.
   *
   * @return an instance of {@code TestAssertCustom} for custom assertions on the response
   * @since 1.1.0
   */
  @Override
  public TestAssertCustom assertCustom() {
    return new TestAssertCustomImpl(this.actions);
  }
}
