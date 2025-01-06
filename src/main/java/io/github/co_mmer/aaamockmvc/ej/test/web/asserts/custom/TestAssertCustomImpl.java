package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom;

import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * Provides methods for custom assertions on HTTP responses in tests.
 *
 * <ul>
 *   <li>{@link #assertCustomResultMatcher(ResultMatcher)}: Asserts that the result of the test request matches the given {@link ResultMatcher}.</li>
 * </ul>
 *
 * @since 1.1.0
 */
public final class TestAssertCustomImpl implements TestAssertCustom {

  private final ResultActions actions;

  /**
   * Constructs an instance of {@code TestAssertCustomImpl} with the provided
   * {@code ResultActions}.
   *
   * <p>This constructor initializes the custom assertion handler for HTTP responses. The provided
   * {@code ResultActions} is used to perform the custom assertions by matching the response against
   * specified {@link ResultMatcher} conditions.
   *
   * @param actions the {@code ResultActions} from a performed HTTP request (must not be
   *                {@code null})
   * @throws NullPointerException if {@code actions} is {@code null}
   * @since 1.1.0
   */
  public TestAssertCustomImpl(@NonNull ResultActions actions) {
    this.actions = actions;
  }

  /**
   * Asserts that the result of the test request matches the given {@link ResultMatcher}.
   *
   * <p>If an error occurs, execution is terminated with a call to {@code Assertions.fail}, passing
   * the corresponding exception.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @since 1.1.0
   */
  @Override
  public TestAssertCustom assertCustomResultMatcher(@NonNull ResultMatcher matcher) {
    try {
      this.actions.andExpect(matcher);
    } catch (Exception e) {
      Assertions.fail(e);
    }
    return this;
  }
}
