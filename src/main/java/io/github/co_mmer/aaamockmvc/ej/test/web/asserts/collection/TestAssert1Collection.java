package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

/**
 * Assertions for a deserialized {@link java.util.Collection} of elements {@code E}.
 *
 * <p><b>What it does:</b> Provides size, (non-)emptiness, membership and predicate-based checks on
 * the collection produced by a prior {@code content().asCollection(E)} / {@code asList(E)} /
 * {@code asSet(E)} step. The response body has already been deserialized once using the configured
 * mapper and is cached for all subsequent assertions.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/items");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asCollection(Item.class)
 *  .isNotEmpty()
 *  .hasSize(3)
 *  .contains(new Item("A"), new Item("B"))
 *  .matchAny(item -> item.active());
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed and {@code
 * content().asCollection(E)} (or {@code asList}/{@code asSet}) successfully deserialized a JSON
 * array into a Java collection.
 *
 * @param <E> the element type of the asserted collection
 * @since 1.4.0
 */
public interface TestAssert1Collection<E>
    extends OperationEquals<E>,
    OperationContains<E>,
    OperationMatchAny<E>,
    OperationMatchAll<E>,
    OperationMatchNone<E> {

  /**
   * Asserts that the collection is not empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the collection is empty
   * @since 2.0.0
   */
  TestAssert2Collection<E> isNotEmpty();

  /**
   * Asserts that the collection is empty.
   *
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the collection is not empty
   * @since 2.0.0
   */
  TestAssertLCollection isEmpty();

  /**
   * Asserts that the collection has the given size.
   *
   * @param expectedSize the expected number of elements
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   * based on the current state.
   * @throws AssertionError if the size differs from {@code expectedSize}
   * @since 2.0.0
   */
  TestAssert2Collection<E> hasSize(int expectedSize);
}
