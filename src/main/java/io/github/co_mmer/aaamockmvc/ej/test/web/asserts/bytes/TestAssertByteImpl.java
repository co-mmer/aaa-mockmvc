package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY_ARRAY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting HTTP response byte content in tests.
 *
 * <ul>
 *   <li>{@link #assertByteNotEmpty()}: Asserts that the byte array content of the HTTP response is
 *       not empty.
 *   <li>{@link #assertByteEmpty()}: Asserts that the byte array content of the HTTP response is
 *       empty.
 *   <li>{@link #assertByteEquals(byte[])}: Asserts that the byte array content of the HTTP response
 *       matches the expected byte array.
 *   <li>{@link #assertByteLength(int)}: Asserts that the length of the byte array content of the
 *       HTTP response matches the specified value.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 * </ul>
 *
 * @since 1.4.0
 */
public final class TestAssertByteImpl implements TestAssert1Byte, TestAssert2Byte, TestAssertLByte {

  private final ResultActions actions;
  private final MockHttpServletResponse response;

  /**
   * Constructs an instance of {@code TestAssertContent} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.4.0
   */
  public TestAssertByteImpl(@NonNull ResultActions actions) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
  }

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert2Byte} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssert2Byte assertByteNotEmpty() {
    try {
      assertThat(this.response.getContentAsString(), not(anyOf(is(EMPTY), is(EMPTY_ARRAY))));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the byte array content of the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertLByte} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssertLByte assertByteEmpty() {
    try {
      assertThat(this.response.getContentAsString(), anyOf(is(EMPTY), is(EMPTY_ARRAY)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the length of the byte array content of the HTTP response matches the specified
   * value.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>Note: The length refers to the number of bytes in the serialized HTTP response body using
   * UTF-8 encoding. For example, an empty JSON array {@code []} has a byte length of 2.
   *
   * @param length the expected length of the HTTP response content
   * @return the current instance of {@code TestAssert2Byte} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssert2Byte assertByteLength(int length) {
    try {
      assertThat(this.response.getContentAsString().length(), is(length));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the byte array content of the HTTP response matches the expected byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte array content (must not be {@code null})
   * @return the current instance of {@code TestAssertLByte} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssertLByte assertByteEquals(byte[] expectedByte) {
    try {
      assertThat(this.response.getContentAsByteArray(), is(expectedByte));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the HTTP response is valid for a HEAD request.
   *
   * <p>This method returns an instance of {@code TestAssertHead} for asserting the headers of the
   * HTTP response. It allows various validations of response headers, such as checking for the
   * presence or absence of specific headers and comparing header values.
   *
   * @return an instance of {@code TestAssertHead} for further assertions on headers
   * @since 1.4.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
