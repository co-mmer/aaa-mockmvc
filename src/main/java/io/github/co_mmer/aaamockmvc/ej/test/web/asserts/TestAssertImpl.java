package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContent;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertContentImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustom;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustomImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssertStatusImpl;
import lombok.NonNull;
import org.springframework.test.web.servlet.ResultActions;

/**
 * This class provides implementation for assertions on HTTP responses in a testing context. It
 * allows various validations of response status, content, headers, and custom assertions.
 *
 * <p>It is primarily used to validate the results of HTTP requests performed with the {@code
 * MockMvc} framework in a Spring web application context. This class provides specific
 * implementations for asserting the status, content, and headers of an HTTP response, as well as
 * the option to define custom assertions.
 *
 * @since 1.0.0
 */
public final class TestAssertImpl implements TestAssert {

  private final ResultActions actions;
  private final ObjectMapper objectMapper;

  /**
   * Constructs an instance of {@code TestAssertImpl} with the provided {@code ResultActions} and
   * {@code ObjectMapper}.
   *
   * @param actions      the {@code ResultActions} from a performed HTTP request (must not be
   *                     {@code null})
   * @param objectMapper the {@code ObjectMapper} for JSON serialization (must not be {@code null})
   * @throws NullPointerException if either {@code actions} or {@code objectMapper} is {@code null}
   * @since 1.0.0
   */
  public TestAssertImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.objectMapper = objectMapper;
  }

  /**
   * Asserts that the HTTP response status matches the expected status code.
   *
   * <p>This method returns an instance of {@code TestAssertStatus} for asserting the status of the
   * HTTP response. It allows various validations of response status codes, ensuring that the
   * response matches the expected outcomes of the HTTP request.
   *
   * @return an instance of {@code TestAssertStatus} for further assertions on the response status
   * @since 1.1.0
   */
  @Override
  public TestAssert1Status assertStatus() {
    return new TestAssertStatusImpl(this.actions, this.objectMapper);
  }

  /**
   * Asserts that the HTTP response is valid for content assertions.
   *
   * <p>This method returns an instance of {@code TestAssertContent} for asserting the content of
   * the HTTP response. It allows various validations of response content, including checks for
   * emptiness and matching expected values.
   *
   * @return an instance of {@code TestAssertContent} for further assertions
   * @since 1.0.0
   */
  @Override
  public TestAssertContent assertContent() {
    return new TestAssertContentImpl(this.actions, this.objectMapper);
  }

  /**
   * @since 1.4.0
   */
  @Override
  public TestAssert1Collection assertCollection() {
    return new TestAssertCollectionImpl(this.actions, this.objectMapper);
  }

  /**
   * @since 1.4.0
   */
  @Override
  public TestAssert1Map assertMap() {
    return new TestAssertMapImpl(this.actions, this.objectMapper);
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

  /**
   * Asserts that the HTTP response matches custom validation logic.
   *
   * <p>This method returns an instance of {@code TestAssertCustom} for asserting custom
   * validations on the HTTP response. It allows users to define their own result matchers or custom
   * logic for validating the response, giving flexibility beyond standard status, content, and
   * header assertions.
   *
   * @return an instance of {@code TestAssertCustom} for custom assertions on the response
   * @since 1.1.0
   */
  @Override
  public TestAssertCustom assertCustom() {
    return new TestAssertCustomImpl(this.actions);
  }
}
