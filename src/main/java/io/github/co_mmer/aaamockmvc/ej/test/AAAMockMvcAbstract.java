package io.github.co_mmer.aaamockmvc.ej.test;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.scenario.ScenarioFlow;
import io.github.co_mmer.aaamockmvc.ej.test.web.scenario.step.TestStep;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * Abstract base for AAA-style MockMvc tests.
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
 * class UserApiTest extends AAAMockMvcAbstract {
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
 * @since 1.0.0
 */
@SuppressWarnings("java:S6813")
@Import(AAAMockMvcConfig.class)
public abstract class AAAMockMvcAbstract {

  @Autowired private AAAMockMvc aaaMockMvc;

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

  public <R> R step(String sectionName, Consumer<TestStep> section) {
    return aaaMockMvc.step(sectionName, section);
  }

  public void scenario(String name, Consumer<ScenarioFlow> block) {
    aaaMockMvc.scenario(name, block);
  }
}
