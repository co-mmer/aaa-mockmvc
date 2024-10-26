package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import lombok.NonNull;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Implementation of the {@code TestAssertHead} interface for asserting headers of HTTP responses in
 * tests.
 *
 * <p>This class provides various methods to assert the headers of an HTTP response, including
 * checking for the presence of headers, ensuring their absence, and validating their values. It
 * utilizes Spring's {@code ResultActions} and {@code MockHttpServletResponse} for handling the HTTP
 * response.
 *
 * <p>Each assertion method returns the current instance of {@code TestAssertHead} to allow for
 * method chaining.
 *
 * @since 1.0.0
 */
public final class TestAssertHeadImpl implements TestAssertHead {

  private final MockHttpServletResponse response;

  /**
   * Constructs an instance of {@code TestAssert} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.0.0
   */
  public TestAssertHeadImpl(@NonNull ResultActions actions) {
    this.response = actions.andReturn().getResponse();
  }

  /**
   * Asserts that the HTTP response contains the specified header.
   *
   * <p>This method checks if the response includes the header with the given key.
   *
   * @param expectedKey the key of the header to check (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code expectedKey} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHeadContains(@NonNull String expectedKey) {
    var containsHeader = this.response.containsHeader(expectedKey);
    assertThat(containsHeader, is(true));
    return this;
  }

  /**
   * Asserts that the HTTP response does not contain the specified header.
   *
   * <p>This method checks if the response does not include the header with the given key.
   *
   * @param notExpectedKey the key of the header that should not be present (must not be {@code
   *     null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code expectedKey} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHeadNotContains(@NonNull String notExpectedKey) {
    var containsHeader = this.response.containsHeader(notExpectedKey);
    assertThat(containsHeader, is(false));
    return this;
  }

  /**
   * Asserts that the specified header in the HTTP response matches the expected value.
   *
   * <p>This method compares the value of the response header with the expected value.
   *
   * @param expectedKey the key of the header to check (must not be {@code null})
   * @param expectedValue the expected value of the header
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code expectedKey} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHeadEquals(@NonNull String expectedKey, String expectedValue) {
    var value = this.response.getHeader(expectedKey);
    var cleanedValue = value != null ? value.replace("\"", "") : null;
    assertThat(cleanedValue, is(expectedValue));
    return this;
  }
}
