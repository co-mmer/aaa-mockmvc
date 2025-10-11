/**
 * A fluent testing framework for Spring’s {@link org.springframework.test.web.servlet.MockMvc} that
 * enforces the Arrange–Act–Assert–Answer (AAA) pattern.
 *
 * <p><b>Scope:</b> The library guides developers through each testing phase with a strongly-typed,
 * step-by-step DSL, ensuring a consistent and intuitive test structure. Common tasks such as
 * request setup, {@link com.fasterxml.jackson.databind.ObjectMapper}-based serialization, and
 * response assertions are fully abstracted, allowing developers to focus on test logic rather than
 * technical overhead.
 *
 * <p><b>Key entry points:</b>
 *
 * <ul>
 *   <li>{@link io.github.co_mmer.aaamockmvc.ej.test.AAAMockMvc} – DSL root exposing {@code
 *       arrange() → act().perform() → asserts()/answer()}.
 *   <li>{@link io.github.co_mmer.aaamockmvc.ej.test.AAAMockMvcTestSupport} – abstract base class
 *       for Spring tests, exposing convenient phase-specific methods.
 * </ul>
 *
 * <p>The individual AAA phases are organized into subpackages:
 *
 * <ul>
 *   <li><b>Arrange:</b> {@link io.github.co_mmer.aaamockmvc.ej.test.web.arrange} – define HTTP
 *       method, URL/query parameters, headers, and (for POST/PUT/PATCH) body.
 *   <li><b>Act:</b> {@link io.github.co_mmer.aaamockmvc.ej.test.web.act} – execute the request via
 *       {@code perform()} and capture the immutable response snapshot.
 *   <li><b>Asserts:</b> {@link io.github.co_mmer.aaamockmvc.ej.test.web.asserts} – verify status
 *       codes, headers, and response content with fluent assertions.
 *   <li><b>Answer:</b> {@link io.github.co_mmer.aaamockmvc.ej.test.web.answer} – read response
 *       content directly (string, bytes, POJOs, collections, maps).
 * </ul>
 *
 * <p><b>Typical usage:</b>
 *
 * <pre>{@code
 * @SpringBootTest
 * class UserApiTest extends AAAMockMvcTestSupport {
 *
 *   @Test
 *   void GIVEN_id_WHEN_getUser_THEN_return_not_null() {
 *     arrange()
 *       .get("/api/users/{id}", 42);
 *
 *     act()
 *       .perform();
 *
 *     asserts()
 *       .status()
 *       .isOk()
 *       .content()
 *       .asClass(User.class)
 *       .isNotNull();
 *   }
 * }
 * }</pre>
 *
 * @since 1.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test;
