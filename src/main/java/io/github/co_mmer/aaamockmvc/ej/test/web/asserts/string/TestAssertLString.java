package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.string;

/**
 * Assertions for the response body as a {@link String}.
 *
 * <p><b>What it does:</b> Provides string-specific checks such as emptiness, length, and equality
 * on the cached response body produced by {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/text");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asString()
 *  .isNotEmpty()
 *  .isEqualTo("aaa")
 *  .headers()
 *  ...
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed. If no body is present,
 * the cached string representation is empty ({@code ""}).
 *
 * @since 1.0.0
 */
public interface TestAssertLString extends OperationHeader {}
