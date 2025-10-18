/**
 * AAA step orchestration — defines the per-step entry points for Arrange, Act, Assert, and Answer.
 *
 * <p><b>Scope:</b> This package models a single HTTP test interaction as a <em>step</em> and
 * exposes the phase entry points via {@link
 * io.github.co_mmer.aaamockmvc.ej.test.web.scenario.step.TestStep}: {@code arrange() →
 * act().perform() → asserts()/answer()}. The step holds request/response state and ensures that
 * assertions and answers operate on the immutable response snapshot captured during {@code
 * perform()} — no additional I/O occurs after execution.
 *
 * @since 2.0.0
 */
package io.github.co_mmer.aaamockmvc.ej.test.web.scenario.step;
