package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes.TestAssert1Byte;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustom;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestAssert1String;

/**
 * Provides methods for asserting HTTP response statuses in tests.
 *
 * <ul>
 *   <li>{@link #assertContentAsString()}: Provides assertion methods for validating the HTTP
 *       response content.
 *   <li>{@link #assertContentAsClass()} ()}: Provides assertion methods for validating the HTTP
 *       response content as class.
 *   <li>{@link #assertContentAsByte()}: Provides assertion methods for validating the HTTP response
 *       byte.
 *   <li>{@link #assertContentAsCollection()}: Provides assertion methods for validating the
 *       contents of an HTTP response collection.
 *   <li>{@link #assertContentAsMap()}: Provides assertion methods for validating the contents of an
 *       HTTP response map.
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.
 *   <li>{@link #assertCustom()}: Provides assertion methods for validating the HTTP response based
 *       on custom logic.
 * </ul>
 *
 * @since 1.1.0
 */
public interface TestAssert2Status {

  /**
   * Provides assertion methods for validating the HTTP response content.
   *
   * <p>This method returns an instance of {@code TestAssertContent}, which provides assertion
   * methods for validating the content of the HTTP response, such as matching expected values or
   * checking for emptiness.
   *
   * @return an instance of {@code TestAssertContent} for asserting the response content
   * @since 1.1.0
   */
  TestAssert1String assertContentAsString();

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
  TestAssert1Class assertContentAsClass();

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
  TestAssert1Byte assertContentAsByte();

  /**
   * Provides assertion methods for validating the contents of an HTTP response collection.
   *
   * <p>This method returns an instance of {@code TestAssert1Collection}, which provides assertion
   * methods for validating the contents of an HTTP response when the response is a collection.
   *
   * @return an instance of {@code TestAssert1Collection} for asserting the collection response
   * @since 1.4.0
   */
  TestAssert1Collection assertContentAsCollection();

  /**
   * Provides assertion methods for validating the contents of an HTTP response map.
   *
   * <p>This method returns an instance of {@code TestAssert1Map}, which provides assertion methods
   * for validating the contents of an HTTP response when the response is a map.
   *
   * @return an instance of {@code TestAssert1Map} for asserting the map response
   * @since 1.4.0
   */
  TestAssert1Map assertContentAsMap();

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
  TestAssertHead assertHead();

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
  TestAssertCustom assertCustom();
}
