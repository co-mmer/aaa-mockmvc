package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header;

/**
 * HEAD arrange step for defining HTTP headers.
 *
 * <p><b>What it does:</b> Configures request headers for the pending HEAD request. Header names
 * are treated case-insensitively and multi-value headers are supported. This step only mutates the
 * request specification; no network I/O is performed until {@code actPerform().perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .head("/api/items")
 *   .headers()
 *   .accept(MediaType.APPLICATION_JSON)
 *   .auth("token-123")
 *   .add("X-Trace-Id", "1234");
 * }</pre>
 *
 * <p><b>Preconditions:</b> This is part of the arrange phase; {@code actPerform().perform()} has
 * not yet been executed.
 *
 * @since 1.0.0
 */
public interface TestArrange2HeadHeader extends TestOperationAuth, TestOperationAdd,
    TestOperationSet {

}
