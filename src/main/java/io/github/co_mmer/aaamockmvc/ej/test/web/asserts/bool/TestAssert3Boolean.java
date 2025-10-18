package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bool;

/**
 * Entry point for raw <b>Boolean</b> content assertions.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/feature/enabled");
 *
 * act()
 *   .perform();
 *
 * asserts()
 *   .content()
 *   .asBoolean()
 *   .isTrue()
 *   .headers()
 *   ...
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code act().perform()} has been executed; assertions operate on the
 * stored snapshot.
 *
 * @since 2.0.0
 */
public interface TestAssert3Boolean extends OperationHeader {}
