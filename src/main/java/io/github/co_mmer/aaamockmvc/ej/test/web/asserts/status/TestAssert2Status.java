package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.status;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.binary.TestAssert1Binary;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection.TestAssert1Collection;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssert1Content;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom.TestAssertCustom;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map.TestAssert1Map;

/**
 * Provides methods for asserting HTTP response statuses in tests.
 *
 * <ul>
 *   <li>{@link #assertContent()}: Provides assertion methods for validating the HTTP response content.</li>
 *   <li>{@link #assertBinary()}: Provides assertion methods for validating the HTTP response binary.</li>
 *   <li>{@link #assertCollection()}: Provides assertion methods for validating the contents of an HTTP response collection.</li>
 *   <li>{@link #assertMap()}: Provides assertion methods for validating the contents of an HTTP response map.</li>
 *   <li>{@link #assertHead()}: Provides assertion methods for validating the HTTP response headers.</li>
 *   <li>{@link #assertCustom()}: Provides assertion methods for validating the HTTP response based on custom logic.</li>
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
  TestAssert1Content assertContent();

  /**
   * Provides assertion methods for validating the HTTP response binary.
   *
   * <p>This method returns an instance of {@code TestAssert1Binary}, which provides assertion
   * methods for validating the content of the HTTP response, such as matching expected values or
   * checking for emptiness.
   *
   * @return an instance of {@code TestAssertBinary} for asserting the response binary
   * @since 1.4.0
   */
  TestAssert1Binary assertBinary();

  /**
   * Provides assertion methods for validating the contents of an HTTP response collection.
   *
   * <p>This method returns an instance of {@code TestAssert1Collection}, which provides assertion
   * methods for validating the contents of an HTTP response when the response is a collection.
   *
   * @return an instance of {@code TestAssert1Collection} for asserting the collection response
   * @since 1.4.0
   */
  TestAssert1Collection assertCollection();

  /**
   * Provides assertion methods for validating the contents of an HTTP response map.
   *
   * <p>This method returns an instance of {@code TestAssert1Map}, which provides assertion methods
   * for validating the contents of an HTTP response when the response is a map.
   *
   * @return an instance of {@code TestAssert1Map} for asserting the map response
   * @since 1.4.0
   */
  TestAssert1Map assertMap();

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
