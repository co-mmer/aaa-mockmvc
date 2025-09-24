package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.bytes;

/**
 * Entry point for raw <b>byte[]</b> content assertions.
 *
 * <p><b>What it does:</b> Exposes assertions for the HTTP response body at the byte level
 * (emptiness, length, exact equality). Use this when you want to verify the raw payload rather than
 * a deserialized representation.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *  .get("/api/binary");
 *
 * actPerform()
 *  .perform();
 *
 * asserts()
 *  .content()
 *  .asBytes()
 *  .isNotEmpty()
 *  .hasLength(128);
 *
 * }</pre>
 *
 * <p><b>Preconditions:</b> {@code actPerform().perform()} has been executed. Content assertions
 * apply only if the response contains a body. If no body is present, the cached string
 * representation is empty ({@code ""}) and the byte representation has length {@code 0}.
 *
 * @since 1.4.0
 */
public interface TestAssertLByte extends OperationHeader {}
