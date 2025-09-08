package io.github.co_mmer.aaamockmvc.ej.test;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * JUnit 5 extension that ensures the AAA-MockMvc thread-local context is cleaned up <em>before</em>
 * and <em>after</em> each test.
 *
 * <p><b>What it does:</b> Calls {@link AAAMockMvc#clearContext()} in both lifecycle hooks to
 * prevent context leakage between tests or steps. This improves test isolation and robustness, even
 * when a previous test failed midway.
 *
 * <p><b>Typical usage (explicit):</b>
 *
 * <pre>{@code
 * @Import({AAAMockMvcConfig.class})
 * @ExtendWith({AAAMockMvcExtension.class})
 * class UserIT {
 *
 * }
 * }</pre>
 *
 * <p><b>Notes:</b>
 *
 * <ul>
 *   <li>No I/O or framework mutation occurs; this extension only clears internal thread-local
 *       state.
 *   <li>Safe with parallel execution: the context is per-thread and cleared in the same thread that
 *       runs the test.
 * </ul>
 *
 * @since 2.0.0
 */
public final class AAAMockMvcExtension implements BeforeEachCallback, AfterEachCallback {

  /**
   * Clears any previous AAA context to guarantee a clean slate for the upcoming test.
   *
   * @since 2.0.0
   */
  @Override
  public void beforeEach(ExtensionContext context) {
    AAAMockMvc.clearContext();
  }

  /**
   * Clears any remaining AAA context to avoid leaking state into subsequent tests.
   *
   * @since 2.0.0
   */
  @Override
  public void afterEach(ExtensionContext context) {
    AAAMockMvc.clearContext();
  }
}
