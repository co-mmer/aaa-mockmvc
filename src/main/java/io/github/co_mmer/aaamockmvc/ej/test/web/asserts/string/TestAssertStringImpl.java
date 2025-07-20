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
 *   <li>{@link #assertContentIsNotEmpty()}: Asserts that the content of the HTTP response is not
 *       empty.
 *   <li>{@link #assertContentIsEmpty()}: Asserts that the content of the HTTP response is empty.
 *   <li>{@link #assertContentLength(int)}: Asserts that the length of the response content matches
 *       the specified length.
 *   <li>{@link #assertContentEquals(String)}: Asserts that the content of the HTTP response matches
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
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be
   *                {@code null})
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.0.0
   * @deprecated Use {@link #assertContentIsNotEmpty()}} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssert2String assertStringNotEmpty() {
    assertContentIsNotEmpty();
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a string is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.0.0
   * @deprecated Use {@link #assertContentIsEmpty()}} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssertLString assertStringEmpty() {
    assertContentIsEmpty();
    return this;
  }

  /**
   * Asserts that the length of the string content of the HTTP response matches the specified
   * value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedLength the expected length of the HTTP response content
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.4.0
   * @deprecated Use {@link #assertContentEquals(String)}} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssert2String assertStringLength(int expectedLength) {
    assertContentLength(expectedLength);
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.0.0
   * @deprecated Use {@link #assertContentEquals(String)}} instead.
   */
  @Deprecated(since = "1.6", forRemoval = true)
  @Override
  public TestAssertLString assertStringEquals(@NonNull String expectedString) {
    assertContentEquals(expectedString);
    return this;
  }

  /**
   * Asserts that the string content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.6.0
   */
  @Override
  public TestAssert2String assertContentIsNotEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(false));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the string content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.6.0
   */
  @Override
  public TestAssertLString assertContentIsEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(true));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the length of the string content of the HTTP response matches the specified
   * value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedLength the expected length of the HTTP response content
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.6.0
   */
  @Override
  public TestAssert2String assertContentLength(int expectedLength) {
    try {
      assertThat(this.response.getContentAsString().length(), is(expectedLength));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the string content of the HTTP response matches the expected string.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @throws NullPointerException if the {@code expectedString} is {@code null}
   * @since 1.6.0
   */
  @Override
  public TestAssertLString assertContentEquals(@NonNull String expectedString) {
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
   * @return the next step in the fluent assertion chain, exposing only context-appropriate methods
   * based on the current state.
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
