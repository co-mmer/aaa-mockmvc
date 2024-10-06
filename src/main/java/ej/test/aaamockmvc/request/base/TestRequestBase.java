package ej.test.aaamockmvc.request.base;

import ej.test.aaamockmvc.context.TestRequestContext;
import ej.test.aaamockmvc.context.TestRequestContextBuilder;
import ej.test.aaamockmvc.request.model.TestRequestDto;
import ej.test.aaamockmvc.request.model.TestRequestType;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Base class for handling HTTP request methods in a test context.
 *
 * <p>This class is designed to be extended by specific request method classes (e.g., GET, POST) and
 * provides common functionalities for creating request contexts.
 *
 * @since 1.0.0
 */
public abstract class TestRequestBase {

  private final MockMvc mvc;

  /**
   * Initializes the base class with the provided {@code MockMvc} instance.
   *
   * @param mvc the {@code MockMvc} instance used to perform requests (must not be {@code null})
   * @throws NullPointerException if the {@code mvc} is {@code null}
   * @since 1.0.0
   */
  protected TestRequestBase(@NonNull MockMvc mvc) {
    this.mvc = mvc;
  }

  /**
   * Creates a new {@code TestRequestContext} based on the specified request type.
   *
   * @param type the type of the test request (must not be {@code null})
   * @return a new instance of {@code TestRequestContext}
   * @throws NullPointerException if the {@code type} is {@code null}
   * @since 1.0.0
   */
  protected TestRequestContext createContext(@NonNull TestRequestType type) {
    return TestRequestContextBuilder.getInstance()
        .withMockMvc(this.mvc)
        .withTestRequest(new TestRequestDto(type))
        .build();
  }
}
