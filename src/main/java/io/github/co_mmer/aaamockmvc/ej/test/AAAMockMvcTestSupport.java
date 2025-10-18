package io.github.co_mmer.aaamockmvc.ej.test;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;
import lombok.NonNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * Test support for AAA-style MockMvc tests (Arrange–Act–Assert–Answer).
 *
 * <p><b>What it does:</b> Wires {@link AAAMockMvc} into the Spring TestContext (via {@link
 * AAAMockMvcConfig}) and exposes concise, <em>phase-specific</em> entry points: {@code arrange()} →
 * {@code actPerform()} → {@code asserts()} (optional {@code answer()}). This class performs no I/O
 * itself; it simply provides access to the DSL.
 *
 * <p><b>Typical usage (AAA):</b>
 *
 * <pre>{@code
 * @SpringBootTest
 * class UserApiTest extends AAAMockMvcTestSupport {
 *
 *   @Test
 *   void GIVEN_userId_WHEN_get_THEN_200_and_body() {
 *     arrange()
 *         .get("/api/users/{id}", 42);
 *
 *     actPerform()
 *         .perform();
 *
 *     asserts()
 *         .status()
 *         .isOk()
 *         .content()
 *         .asClass(User.class)
 *         .isNotNull();
 *   }
 * }
 * }</pre>
 *
 * <p><b>Preconditions:</b> Spring arrange is initialized and {@link AAAMockMvcConfig} is imported
 * so that {@link AAAMockMvc} is available. Subclasses should be standard Spring test classes (e.g.,
 * {@code @SpringBootTest}).
 *
 * @since 2.0.0
 */
@SuppressWarnings("java:S6813")
@Import(AAAMockMvcConfig.class)
@ExtendWith(AAAMockMvcExtension.class)
public abstract class AAAMockMvcTestSupport {

  @Autowired AAAMockMvc aaaMockMvc;

  /**
   * Starts the <b>arrange</b> phase to build the HTTP request (method, URL, headers, body).
   *
   * <p>No network I/O is performed here; this only mutates the request specification that will be
   * executed later by {@link #act()} → {@code perform()}.
   *
   * @return the arrange step, exposing only arrange-appropriate methods based on the current state
   * @since 2.0.0
   */
  protected final TestArrange arrange() {
    return aaaMockMvc.arrange();
  }

  /**
   * Enters the <b>actPerform</b> phase to execute the arranged request and capture the response
   * snapshot.
   *
   * <p>The actual execution happens when {@code perform()} is called on the returned object.
   *
   * @return the actPerform step, allowing {@code perform()} and optional actPerform-level
   *     configuration
   * @since 2.0.0
   */
  protected final TestAct act() {
    return aaaMockMvc.act();
  }

  /**
   * Enters the <b>asserts</b> phase to verify the stored response snapshot (status, headers, body).
   *
   * <p>Assertions reuse the immutable snapshot created during the actPerform phase; no additional
   * I/O occurs.
   *
   * @return the root assertion step for status, content, and headers
   * @since 2.0.0
   */
  protected final TestAssert asserts() {
    return aaaMockMvc.asserts();
  }

  /**
   * Provides <b>answer</b>-style read access to the stored response snapshot (outside the fluent
   * assertions), e.g., to fetch deserialized data for custom checks or subsequent steps.
   *
   * @return the answer API to retrieve the body as string/bytes/object/collection/map
   * @since 2.0.0
   */
  protected final TestAnswer answer() {
    return aaaMockMvc.answer();
  }

  /**
   * Runs a named AAA <b>step</b> as a single block and returns the <em>captured answer</em> from
   * within that block.
   *
   * <p><b>What it does:</b> Creates an isolated step context, invokes the given {@code block}
   * (where you call {@code arrange() → act().perform() → asserts()/answer()}), and then returns the
   * value produced by the {@code answer().asXxx(...)} call inside the block. If no answer was
   * captured, this method returns {@code null}. No additional network I/O occurs after {@code
   * perform()}—assertions and answers operate on the cached snapshot.
   *
   * <p><b>Semantics:</b>
   *
   * <ul>
   *   <li><b>No answer inside the block:</b> the return value is {@code null}.
   *   <li><b>Type inference:</b> {@code R} is inferred from assignment context (e.g., {@code
   *       UserResponse r = step(...)}). A mismatch between the captured answer type and {@code R}
   *       will cause a {@link ClassCastException} at runtime.
   *   <li><b>Isolated flow:</b> Each invocation uses a fresh step context; multiple sections can be
   *       executed in the same test safely.
   * </ul>
   *
   * <p><b>Typical usage — with implicit return:</b>
   *
   * <pre>{@code
   * UserResponse created = step("Create user", () -> {
   *   arrange()
   *      .post("/api/users")
   *      .body()
   *      .json(new User("alice"));
   *   act()
   *      .perform();
   *   asserts()
   *      .status()
   *      .isCreated()
   *      .content()
   *      .asClass(UserResponse.class)
   *      .isNotNull();
   *   answer().asObject(UserResponse.class);
   * });
   * }</pre>
   *
   * <p><b>Typical usage — no answer captured:</b>
   *
   * <pre>{@code
   * step("Update user", () -> {
   *   arrange()
   *      .put("/api/users/{id}", 42)
   *      .body()
   *      .json(new UpdateUser("alice", "active"));
   *   act()
   *     .perform();
   *   asserts()
   *      .status()
   *      .isOk();
   * });
   * }</pre>
   *
   * @param stepName human-readable step name used in logs and error messages (must not be {@code
   *     null} or blank)
   * @param block the step body; declare {@code arrange()}, execute with {@code act().perform()},
   *     and optionally capture an answer via {@code s.answer().asXxx(...)}
   * @param <R> the expected return type inferred from the assignment context
   * @return the value produced by the last answer call inside the block (one of):
   *     <ul>
   *       <li>{@code answer().asObject(Class)}
   *       <li>{@code answer().asList(Class)}
   *       <li>{@code answer().asSet(Class)}
   *       <li>{@code answer().asMap(Class, Class)}
   *       <li>{@code answer().asString()}
   *       <li>{@code answer().asByte()}
   *     </ul>
   *     or {@code null} if no answer was captured
   * @throws ClassCastException if the captured answer cannot be cast to {@code R}
   * @since 2.0.0
   */
  public final <R> R step(@NonNull String stepName, @NonNull Runnable block) {
    return aaaMockMvc.step(stepName, block);
  }
}
