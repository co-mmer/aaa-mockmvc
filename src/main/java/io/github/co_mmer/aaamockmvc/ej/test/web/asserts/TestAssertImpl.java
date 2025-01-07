package io.github.co_mmer.aaamockmvc.ej.test.web.asserts;

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
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssert1Status;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status.TestAssertStatusImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssertStringImpl;
import lombok.NonNull;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides methods for asserting different aspects of an HTTP response.
 *
 * <p>This class provides various assertion methods for validating the HTTP response, such as
 * checking the status, content, byte data, headers, and custom assertions. Each method returns an
 * instance of a specific assertion class for further validation.
 *
 * <ul>
 *   <li>{@link #assertStatus()}: Provides assertion methods for validating the HTTP response
 *       status.
 *   <li>{@link #assertContentAsString()}: Provides assertion methods for validating the HTTP
 *       response content.
 *   <li>{@link #assertContentAsClass()} ()}: Provides assertion methods for validating the HTTP
 *       response content as class.
 *   <li>{@link #assertContentAsByte()}: Provides assertion methods for validating the HTTP response
 *       byte data.
 *   <li>{@link #assertContentAsCollection()}: Provides assertion methods for validating the
 *       contents of an HTTP response collection.
 *   <li>{@link #assertContentAsMap()}: Provides assertion methods for validating the contents of an
 *       HTTP response map.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 *   <li>{@link #assertCustom()}: Provides assertion methods for validating the HTTP response with
 *       custom logic.
 * </ul>
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
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be {@code
   *     null})
   * @param objectMapper the {@code ObjectMapper} for JSON serialization (must not be {@code null})
   * @throws NullPointerException if either {@code actions} or {@code objectMapper} is {@code null}
   * @since 1.0.0
   */
  public TestAssertImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.objectMapper = objectMapper;
  }

  /**
   * Provides assertion methods for validating the HTTP response status.
   *
   * <p>This method returns an instance of {@code TestAssert1Status}, which provides assertion
   * methods for checking the HTTP response status code.
   *
   * @return an instance of {@code TestAssert1Status} for asserting the response status
   * @since 1.0.0
   */
  @Override
  public TestAssert1Status assertStatus() {
    return new TestAssertStatusImpl(this.actions, this.objectMapper);
  }

  /**
   * Provides assertion methods for validating the HTTP response content.
   *
   * <p>This method returns an instance of {@code TestAssertContent}, which provides assertion
   * methods for validating the content of the HTTP response, such as matching expected values or
   * checking for emptiness.
   *
   * @return an instance of {@code TestAssertContent} for asserting the response content
   * @since 1.0.0
   */
  @Override
  public TestAssert1String assertContentAsString() {
    return new TestAssertStringImpl(this.actions);
  }

  /**
   * Provides assertion methods for validating the HTTP response content as class.
   *
   * <p>This method returns an instance of {@code TestAssertClass}, which provides assertion methods
   * for validating the content of the HTTP response, such as matching expected values or checking
   * for emptiness.
   *
   * @return an instance of {@code TestAssertClass} for asserting the response content
   * @since 1.4.0
   */
  @Override
  public TestAssert1Class assertContentAsClass() {
    return new TestAssertClassImpl(this.actions, this.objectMapper);
  }

  /**
   * Provides assertion methods for validating the HTTP response byte.
   *
   * <p>This method returns an instance of {@code TestAssert1Byte}, which provides assertion methods
   * for validating the content of the HTTP response, such as matching expected values or checking
   * for emptiness.
   *
   * @return an instance of {@code TestAssert1Byte} for asserting the response byte
   * @since 1.4.0
   */
  @Override
  public TestAssert1Byte assertContentAsByte() {
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
  public TestAssert1Collection assertContentAsCollection() {
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
  public TestAssert1Map assertContentAsMap() {
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
