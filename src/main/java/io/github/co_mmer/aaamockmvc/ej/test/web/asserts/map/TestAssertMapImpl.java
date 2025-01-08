package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.mapper.TestGenericMapper.mapToMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception.TestGenericMapperException;
import java.util.Map;
import lombok.NonNull;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Provides assertion methods for validating HTTP response maps.
 *
 * <ul>
 *   <li>{@link #assertMapNotEmpty()}: Asserts that the map in the HTTP response is not empty.
 *   <li>{@link #assertMapEmpty()}: Asserts that the map in the HTTP response is empty.
 *   <li>{@link #assertMapSize(int)}: Asserts that the size of the map in the HTTP response matches
 *       the given size.
 *   <li>{@link #assertMapEquals(Class, Class, Map)}: Asserts that the map in the HTTP response
 *       matches the expected map of key-value pairs.
 *   <li>{@link #assertHead()}: Asserting properties of response headers for HTTP HEAD requests.*
 * </ul>
 *
 * @since 1.4.0
 */
public final class TestAssertMapImpl
    implements TestAssert1Map, TestAssert2Map, TestAssert3Map, TestAssertLMap {

  private final ResultActions actions;
  private final MockHttpServletResponse response;
  private final ObjectMapper objectMapper;

  /**
   * Constructs a new {@code TestAssertMapImpl} instance with the given {@code ResultActions} and
   * {@code ObjectMapper}.
   *
   * @param actions the {@code ResultActions} containing the HTTP response (must not be {@code
   *     null})
   * @param objectMapper the {@code ObjectMapper} for JSON deserialization (must not be {@code
   *     null})
   * @throws NullPointerException if any of the parameters is {@code null}
   * @since 1.4.0
   */
  public TestAssertMapImpl(@NonNull ResultActions actions, @NonNull ObjectMapper objectMapper) {
    this.actions = actions;
    this.response = actions.andReturn().getResponse();
    this.objectMapper = objectMapper;
  }

  /**
   * Asserts that the map in the HTTP response is not empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssert2Map} for further assertions
   * @throws AssertionError if the response collection is empty or invalid
   * @since 1.4.0
   */
  @Override
  public TestAssert2Map assertMapNotEmpty() {
    try {
      assertThat(this.response.getContentAsString(), not(anyOf(is(Strings.EMPTY), is("{}"))));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the map in the HTTP response is empty.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @return the current instance of {@code TestAssertLMap} for further assertions
   * @throws AssertionError if the response collection is not empty
   * @since 1.4.0
   */
  @Override
  public TestAssertLMap assertMapEmpty() {
    try {
      assertThat(this.response.getContentAsString(), anyOf(is(Strings.EMPTY), is("{}")));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the size of the collection in the HTTP response matches the given size.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param size the expected size of the collection
   * @return the current instance of {@code TestAssert3Map} for further assertions
   * @throws AssertionError if the collection size does not match the expected size
   * @since 1.4.0
   */
  @Override
  public TestAssert3Map assertMapSize(int size) {
    try {
      this.actions.andExpect(jsonPath("$.length()", is(size)));
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }

  /**
   * Asserts that the map in the HTTP response matches the expected map of key-value pairs.
   *
   * <p>Both maps are normalized before comparison to ensure consistent results.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param keyClass the class of the keys in the map (must not be {@code null})
   * @param valueClass the class of the values in the map (must not be {@code null})
   * @param expectedMap the expected map of key-value pairs (must not be {@code null})
   * @param <K> the type of the keys in the map
   * @param <V> the type of the values in the map
   * @return the current instance of {@code TestAssertLMap} for further assertions
   * @throws AssertionError if the maps do not match
   * @since 1.4.0
   */
  @Override
  public <K, V> TestAssertLMap assertMapEquals(
      @NonNull Class<K> keyClass, @NonNull Class<V> valueClass, @NonNull Map<K, V> expectedMap) {
    try {
      var actual = mapToMap(this.objectMapper, this.actions.andReturn(), keyClass, valueClass);
      assertThat(normalizeMap(actual), is(normalizeMap(expectedMap)));
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
   * @since 1.4.0
   */
  @Override
  public TestAssertHead assertHead() {
    return new TestAssertHeadImpl(this.actions);
  }
}
