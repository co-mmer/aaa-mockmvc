package io.github.co_mmer.aaamockmvc.ej.test.web.scenario.step;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct;
import io.github.co_mmer.aaamockmvc.ej.test.web.answer.TestAnswer;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssert;

/**
 * Represents a single AAA step and exposes the phase entry points: {@link #arrange()},
 * {@link #act()}, {@link #asserts()}, and {@link #answer()}.
 *
 * <p><b>What it does:</b> Holds the state for one HTTP test interaction. You declare the request
 * in
 * the <i>Arrange</i> phase, execute it once in the <i>Act</i> phase ({@code perform()}), and then
 * verify or read the captured <i>snapshot</i> in the <i>Assert</i>/<i>Answer</i> phases. Assertions
 * and answer access operate on the cached snapshot—no additional network I/O occurs after
 * {@code perform()}.
 *
 * <p><b>Typical usage (AAA):</b>
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
 * <p><b>Preconditions:</b>
 *
 * <ul>
 *   <li>{@link #arrange()} must be called before {@link #act()}.
 *   <li>{@code perform()} must be called on the act step before {@link #asserts()} or {@link
 *       #answer()}.
 * </ul>
 *
 * @since 2.0.0
 */
public interface TestStep {

  /**
   * Starts the <b>Arrange</b> phase for this step.
   *
   * <p>Define method and target (URL/URI), add path/query parameters, set headers, and optionally
   * provide a body. This is declarative only; no I/O happens here.
   *
   * @return the arrange step for fluent request specification
   * @since 2.0.0
   */
  TestArrange arrange();

  /**
   * Enters the <b>Act</b> phase for this step.
   *
   * <p>Use the returned step to trigger execution via {@code perform()}. The response is captured
   * as an immutable snapshot (status, headers, body) for subsequent phases.
   *
   * @return the act step for executing the arranged request
   * @since 2.0.0
   */
  TestAct act();

  /**
   * Enters the <b>Assert</b> phase for this step.
   *
   * <p>Verify the status, headers, and content using the snapshot captured during {@code
   * perform()}. No additional network I/O is performed.
   *
   * @return the assert entry point for fluent assertions
   * @since 2.0.0
   */
  TestAssert asserts();

  /**
   * Enters the optional <b>Answer</b> phase for this step.
   *
   * <p>Read the same snapshot captured during {@code perform()} as string/bytes or map it to an
   * object/collection/map to drive follow-up steps.
   *
   * @return the answer entry point for retrieving data from the snapshot
   * @since 2.0.0
   */
  TestAnswer answer();
}
