package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Implementation of the {@code TestAssertContent} interface for asserting content of HTTP responses
 * in tests.
 *
 * <p>This class provides various methods to assert the content of an HTTP response, including
 * checking for emptiness, matching expected values, and deserializing to specific types. It
 * utilizes the Spring's {@code ResultActions} and {@code MockHttpServletResponse} for handling the
 * HTTP response.
 *
 * <p>Each assertion method returns the current instance of {@code TestAssertContent} to allow for
 * method chaining.
 *
 * @since 1.0.0
 */
@Log4j2
public final class TestAssertContentImpl implements TestAssertContent {

  private final ResultActions actions;
  private final MockHttpServletResponse response;
  private final ObjectMapper objectMapper;

  /**
   * Constructs an instance of {@code TestAssertContent} with the provided {@code ResultActions}.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @param objectMapper the {@link ObjectMapper} used for JSON processing (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code actions} is {@code null}
   * @since 1.0.0
   */
  public TestAssertContentImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
    this.objectMapper = objectMapper;
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
  public TestAssertContent assertContentNotEmpty() {
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
  public TestAssertContent assertContentEmpty() {
    try {
      assertThat(this.response.getContentAsString().isEmpty(), is(true));
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
  public TestAssertContent assertContentEquals(@NonNull String expectedString) {
    try {
      var content = this.response.getContentAsString();
      assertThat(normalizeObject(content), is(normalizeObject(expectedString)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a byte array is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentByteIsNotEmpty() {
    try {
      var content = this.response.getContentAsByteArray();
      assertThat(content.length, is(not(0)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response as a byte array is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentByteIsEmpty() {
    try {
      var content = this.response.getContentAsByteArray();
      assertThat(content.length, is(0));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given byte array.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param expectedByte the expected byte content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContentEquals(byte[] expectedByte) {
    try {
      var content = this.response.getContentAsByteArray();
      assertThat(content, is(expectedByte));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given object of type {@code T}.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param expectedClass the class of the expected object (must not be {@code null})
   * @param expectedResponse the expected object (must not be {@code null})
   * @param <T> the type of the expected response
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public <T> TestAssertContent assertContentEquals(
      @NonNull Class<T> expectedClass, @NonNull T expectedResponse) {

    try {
      var result = this.actions.andReturn();
      var actual = mapTo(this.objectMapper, result, expectedClass);
      assertThat(normalizeObject(actual), is(normalizeObject(expectedResponse)));
    } catch (TestGenericMapperException e) {
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
   * @since 1.0.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
