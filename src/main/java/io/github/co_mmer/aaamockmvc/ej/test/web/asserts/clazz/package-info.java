/**
 * Assertions for deserialized content objects of type {@code T}.
 *
 * <p><b>Scope:</b> Provides type-safe checks (nullability, equality, predicate-based matching) on
 * objects obtained via {@code content().asClass(T)}. The response body has already been
 * deserialized once using the configured mapper and is cached for all subsequent assertions.
 *
 * <p><b>General rules (apply to all methods in this package):</b>
 *
 * <ul>
 *   <li>All assertions throw {@link java.lang.AssertionError} on failure.
 *   <li>Unless explicitly documented otherwise, order of evaluation of predicates is not defined.
 *   <li>String comparisons are normalized using Unicode Normalization Form C (NFC).
 *   <li>Null arguments are not permitted; passing {@code null} will result in
 *       {@link java.lang.NullPointerException}.
 * </ul>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz.TestAssert1Class}.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.clazz;
