package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.binary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting HTTP response byte content in tests.
 *
 * <ul>
 *   <li>{@link #assertBinaryByteNotEmpty()}: Asserts that the byte array content of the HTTP response is not empty.</li>
 *   <li>{@link #assertBinaryByteEmpty()}: Asserts that the byte array content of the HTTP response is empty.</li>
 *   <li>{@link #assertBinaryByteEquals(byte[])}: Asserts that the byte array content of the HTTP response matches the expected byte array.</li>
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.</li>
 * </ul>
 *
 * @since 1.4.0
 */
public final class TestAssertBinaryImpl
    implements TestAssert1Binary, TestAssert2Binary, TestAssertLBinary {

  private final ResultActions actions;
  private final MockHttpServletResponse response;

  /**
   * Constructs an instance of {@code TestAssertContent} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be
   *                {@code null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.4.0
   */
  public TestAssertBinaryImpl(@NonNull ResultActions actions) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
  }

  /**
   * Asserts that the byte array content of the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertBinary} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssert2Binary assertBinaryByteNotEmpty() {
    try {
      assertThat(this.response.getContentAsString(), not(anyOf(is(Strings.EMPTY), is("[]"))));
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
   * @return the current instance of {@code TestAssertBinary} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssertLBinary assertBinaryByteEmpty() {
    try {
      assertThat(this.response.getContentAsString(), anyOf(is(Strings.EMPTY), is("[]")));
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
   * @return the current instance of {@code TestAssertBinary} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssertLBinary assertBinaryByteEquals(byte[] expectedByte) {
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
