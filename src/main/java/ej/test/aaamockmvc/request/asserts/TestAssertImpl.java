package ej.test.aaamockmvc.request.asserts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import ej.test.aaamockmvc.request.act.exception.TestAssertException;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * This class provides assertion methods for validating the result of HTTP requests.
 *
 * <p>It wraps {@link ResultActions} and {@link MockHttpServletResponse} to perform assertions on
 * the status and content of the response, including string and byte array content.
 *
 * @since 1.0.0
 */
public final class TestAssertImpl implements TestAssert {

  private final ResultActions actions;
  private final MockHttpServletResponse response;

  /**
   * Constructs an instance of {@code TestAssert} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.0.0
   */
  public TestAssertImpl(@NonNull ResultActions actions) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
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
   * Asserts that the content of the HTTP response is not an empty string.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStringContentIsNotEmpty() throws TestAssertException {
    var content = tryGetContentAsString();
    assertThat(content, is(not(Strings.EMPTY)));
    return this;
  }

  private String tryGetContentAsString() throws TestAssertException {
    try {
      return this.response.getContentAsString();
    } catch (Exception e) {
      throw new TestAssertException(e);
    }
  }

  /**
   * Asserts that the content of the HTTP response is an empty string.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStringContentIsEmpty() throws TestAssertException {
    var content = tryGetContentAsString();
    assertThat(content, is(Strings.EMPTY));
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the expected string.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @throws TestAssertException if an error occurs when retrieving the content
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestAssert assertStringContent(@NonNull String expectedString) throws TestAssertException {
    var content = tryGetContentAsString();
    assertThat(content, is(expectedString));
    return this;
  }

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByteContentIsNotEmpty() throws TestAssertException {
    var content = tryGetContentAsByteArray();
    assertThat(content.length, is(not(0)));
    return this;
  }

  private byte[] tryGetContentAsByteArray() throws TestAssertException {
    try {
      return this.response.getContentAsByteArray();
    } catch (Exception e) {
      throw new TestAssertException(e);
    }
  }

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByteContentIsEmpty() throws TestAssertException {
    var content = tryGetContentAsByteArray();
    assertThat(content.length, is(0));
    return this;
  }

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs when retrieving the content
   * @throws NullPointerException if the {@code expectedByte} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByteContent(byte[] expectedByte) throws TestAssertException {
    var content = tryGetContentAsByteArray();
    assertThat(content, is(expectedByte));
    return this;
  }

  /**
   * Asserts that the result of the test request matches the given {@link ResultMatcher}.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws TestAssertException if an error occurs during validation
   * @throws NullPointerException if the {@code matcher} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestAssert assertByResultMatcher(@NonNull ResultMatcher matcher)
      throws TestAssertException {
    try {
      this.actions.andExpect(matcher);
    } catch (Exception e) {
      throw new TestAssertException(e);
    }
    return this;
  }
}
