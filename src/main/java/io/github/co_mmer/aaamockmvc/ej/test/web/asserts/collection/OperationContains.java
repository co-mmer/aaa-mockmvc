package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import java.util.Collection;
import org.springframework.lang.NonNull;

interface OperationContains<E> {

  /**
   * Asserts that the collection contains all of the given elements (order does not matter;
   * additional elements may exist).
   *
   * @param expectedElements the elements that must be present; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any expected element is missing
   * @since 2.0.0
   */
  TestAssert3Collection<E> contains(@NonNull Collection<E> expectedElements);

  /**
   * Asserts that the collection contains all of the given elements (order does not matter;
   * additional elements may exist).
   *
   * @param expectedElements the elements that must be present; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any expected element is missing
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert3Collection<E> contains(@NonNull E... expectedElements);

  /**
   * Asserts that the collection contains exactly the elements of {@code expectedCollection},
   * regardless of order (multiplicity must match; no extras).
   *
   * @param expectedCollection the expected elements; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if elements differ (ignoring order)
   * @since 2.0.0
   */
  TestAssert3Collection<E> containsAnyOrder(@NonNull Collection<E> expectedCollection);

  /**
   * Asserts that none of the given elements are present in the collection.
   *
   * @param unexpectedElements elements that must be absent; must not be {@code null}
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any unexpected element is present
   * @since 2.0.0
   */
  TestAssert3Collection<E> notContains(@NonNull Collection<E> unexpectedElements);

  /**
   * Asserts that none of the given elements are present in the collection.
   *
   * @param unexpectedElements elements that must be absent; must not be {@code null} or empty
   * @return the next step in the fluent assertion chain, exposing only arrange-appropriate methods
   *     based on the current state.
   * @throws AssertionError if any unexpected element is present
   * @since 2.0.0
   */
  @SuppressWarnings("unchecked")
  TestAssert3Collection<E> notContains(@NonNull E... unexpectedElements);
}
