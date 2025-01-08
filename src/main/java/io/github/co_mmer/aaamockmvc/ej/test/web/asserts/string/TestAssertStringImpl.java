package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <ul>
 *   <li>{@link #assertStringNotEmpty()}: Asserts that the content of the HTTP response is not
 *       empty.
 *   <li>{@link #assertStringEmpty()}: Asserts that the content of the HTTP response is empty.
 *   <li>{@link #assertStringLength(int)}: Asserts that the length of the response content matches
 *       the specified length.
 *   <li>{@link #assertStringEquals(String)}: Asserts that the content of the HTTP response matches
 *       the expected string.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 * </ul>
 *
 * @since 1.0.0
 */
public final class TestAssertStringImpl
    implements TestAssert1String, TestAssert2String, TestAssertLString {

  private final ResultActions actions;
  private final MockHttpServletResponse response;

  /**
   * Constructs an instance of {@code TestAssertContent} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.0.0
   */
  public TestAssertStringImpl(@NonNull ResultActions actions) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
  }

  /**
   * Asserts that the content of the HTTP response as a string is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert2String assertStringNotEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(false));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a string is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertLString assertStringEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(true));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the length of the string content of the HTTP response matches the specified value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param length the expected length of the HTTP response content
   * @return the current instance of {@code TestAssert2Content} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssert2String assertStringLength(int length) {
    try {
      assertThat(this.response.getContentAsString().length(), is(length));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given string.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertLString assertStringEquals(@NonNull String expectedString) {
    try {
      var content = this.response.getContentAsString();
      assertThat(normalizeObject(content), is(normalizeObject(expectedString)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
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
}
