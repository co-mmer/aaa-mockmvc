package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.map;

/**
 * Assertions for a deserialized {@link java.util.Map} with keys {@code K} and values {@code V}.
 *
 * <p><b>What it does:</b> Provides (non-)emptiness, size, and equality checks on the map produced
 * by a prior {@code content().asMap(K,V)} step. The response body has already been deserialized
 * once using the configured mapper and is cached for all subsequent assertions.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/stats");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asMap(String.class, Integer.class)
 *  .isNotEmpty()
 *  .hasSize(3)
 *  .isEqualTo(Map.of("count", 5, "errors", 0, "warnings", 1));
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and {@code
 * content().asMap(K,V)} successfully deserialized a JSON object into a Java map.
 *
 * @param <K> the key type of the asserted map
 * @param <V> the value type of the asserted map
 * @since 1.4.0
 */
public interface TestAssert1Map<K, V> extends OperationEquals<K, V> {

  /**
   * Asserts that the map is not empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the map is empty
   * @since 2.0.0
   */
  TestAssert2Map<K, V> isNotEmpty();

  /**
   * Asserts that the map is empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the map is not empty
   * @since 2.0.0
   */
  TestAssertLMap isEmpty();

  /**
   * Asserts that the map has the given size.
   *
   * @param size the expected number of entries
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if the size differs from {@code size}
   * @since 2.0.0
   */
  TestAssert3Map<K, V> hasSize(int size);
}
