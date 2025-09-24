package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

/**
 * Assertions for a deserialized {@link java.util.Collection} of elements {@code E}.
 *
 * <p><b>What it does:</b> Provides size, (non-)emptiness, membership and predicate-based checks on
 * the collection produced by a prior {@code content().asCollection(E)} / {@code asList(E)} / {@code
 * asSet(E)} step. The response body has already been deserialized once using the configured mapper
 * and is cached for all subsequent assertions.
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
 * @since 1.4.0
 */
public interface TestAssert2Collection<E>
    extends OperationMatchAny<E>,
        OperationMatchAll<E>,
        OperationMatchNone<E>,
        OperationContains<E>,
        OperationEquals<E>,
        OperationHeader {}
