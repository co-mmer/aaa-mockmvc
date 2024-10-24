package io.github.co_mmer.aaamockmvc.test.web.asserts.custom;

import lombok.NonNull;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * This interface provides a contract for custom assertions on HTTP responses in a testing context.
 * It allows validation of responses obtained from the {@code MockMvc} framework using custom result
 * matchers.
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
