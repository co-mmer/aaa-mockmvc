/**
 * Answer-phase entry point for reading HTTP response payloads.
 *
 * <p><b>Scope:</b> Provides the DSL entry point
 * {@link io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer} for retrieving the response
 * body from the immutable snapshot produced by {@code act().perform()}. The body can be accessed as
 * raw string/bytes or deserialized into scalar values, objects, collections, or maps using the
 * configured mapper. No additional network I/O is performed; values are resolved once and cached
 * for reuse.
 *
 * <p><b>General rules:</b></p>
 * <ul>
 *   <li>All methods operate on the stored response snapshot; the HTTP call has already completed.</li>
 *   <li>If no body is present, {@code asString()} returns {@code ""} and {@code asByte()} returns an empty array.</li>
 *   <li>All deserialization errors throw {@link io.github.co_mmer.aaamockmvc.ej.test.web.answer.exception.TestAnswerFailed}.</li>
 *   <li>All validations throw {@link java.lang.NullPointerException} for {@code null} arguments.</li>
 * </ul>
 *
 * <p><b>Typical usage (AAA):</b></p>
 *
 * <pre>{@code
 * arrange()
 *   .get("/api/users");
 *
 * act()
 *   .perform();
 *
 * List<User> users = answer().asList(User.class);
 * }</pre>
 *
 * <p><b>Entry point:</b> The public API surface is
 * {@link io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer}.
 *
 * @since 1.2.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.answer;
