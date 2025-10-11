/**
 * Act-phase for executing the arranged HTTP request and capturing the response snapshot.
 *
 * <p><b>Scope:</b> Provides the DSL entry point {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct} which performs the request built during the
 * arrange phase against the underlying {@link org.springframework.test.web.servlet.MockMvc}. The
 * result (status, headers, body as bytes/string) is stored as an immutable snapshot and reused by
 * subsequent {@code asserts()} or {@code answer()} calls. No additional I/O is performed once the
 * snapshot is captured.
 *
 * <p><b>General rules:</b>
 *
 * <ul>
 *   <li>HTTP error statuses (4xx/5xx) are <em>not</em> thrown as exceptions; they are captured in
 *       the snapshot and should be asserted in later steps.
 *   <li>Infrastructure or runtime failures (e.g. serialization issues, transport errors) throw
 *       {@link io.github.co_mmer.aaamockmvc.ej.test.web.act.error.TestActFailedError}.
 *   <li>The response snapshot is immutable and shared across all subsequent assertions/answers in
 *       the same AAA flow.
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/items");
 *
 * act()
 *   .perform();
 *
 * asserts()
 *   .status()
 *   .isOk();
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct}.
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.act;
