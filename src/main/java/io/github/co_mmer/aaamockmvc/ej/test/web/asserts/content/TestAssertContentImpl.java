package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting HTTP response content in tests.
 *
 * <p>This class enables various assertions on the content of HTTP responses, such as verifying that
 * the content is empty or not empty, matching specific string values, or comparing against expected
 * objects.
 *
 * @since 1.0.0
 */
public final class TestAssertContentImpl
    implements TestAssert1Content, TestAssert2Content, TestAssertLContent {

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
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssert2Content assertContentNotEmpty() {
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
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertLContent assertContentEmpty() {
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
   * @param length the expected length of the HTTP response content
   * @return the current instance of {@code TestAssert2Content} for method chaining
   * @since 1.4.0
   */
  @Override
  public TestAssert2Content assertContentLength(int length) {
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
   * <p>As of version 1.3.0, both the actual and expected response content are normalized using
   * Unicode Normalization Form C (NFC) to ensure consistent text representation across different
   * Unicode formats.
   *
   * @param expectedString the expected content of the response (must not be {@code null})
   * @return the current instance of {@code TestAssertContent} for method chaining
   * @since 1.0.0
   */
  @Override
  public TestAssertLContent assertContentEquals(@NonNull String expectedString) {
    try {
      var content = this.response.getContentAsString();
      assertThat(normalizeObject(content), is(normalizeObject(expectedString)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the content of the HTTP response matches the given object of type {@code T}.
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
  public <T> TestAssertLContent assertContentEquals(
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
