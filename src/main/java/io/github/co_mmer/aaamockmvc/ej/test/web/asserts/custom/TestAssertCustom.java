package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.custom;

import lombok.NonNull;
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
public interface TestAssertCustom {

  /**
   * Asserts that the HTTP response matches the given {@link ResultMatcher}.
   *
   * @param matcher the {@code ResultMatcher} to be used for validation (must not be {@code null})
   * @return the current instance of {@code TestAssert} for method chaining
   * @throws NullPointerException if the {@code matcher} is {@code null}
   * @since 1.1.0
   */
  TestAssertCustom assertCustomResultMatcher(@NonNull ResultMatcher matcher);
}
