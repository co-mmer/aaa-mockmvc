package ej.aaamockmvc.test.web.request.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.web.request.context.TestRequestBean;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import ej.aaamockmvc.test.web.request.context.TestRequestContextBuilder;
import ej.aaamockmvc.test.web.request.model.TestRequestDto;
import ej.aaamockmvc.test.web.request.model.TestRequestType;
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

  private final TestRequestBean bean;

  /**
   * Constructs a new {@code TestRequestBase} instance with the provided {@link TestRequestBean}.
   *
   * @param bean the {@link TestRequestBean} containing the {@link MockMvc} and {@link ObjectMapper}
   *     required for HTTP request handling (must not be {@code null})
   * @throws NullPointerException if the {@code bean} is {@code null}
   * @since 1.0.0
   */
  protected TestRequestBase(@NonNull TestRequestBean bean) {
    this.bean = bean;
  }

  /**
   * Creates a {@code TestRequestContext} for the specified request type.
   *
   * @param type the type of the test request (must not be {@code null})
   * @return a {@code TestRequestContext} for the specified request type
   * @throws NullPointerException if {@code type} is {@code null}
   * @since 1.0.0
   */
  protected TestRequestContext createContext(@NonNull TestRequestType type) {
    return TestRequestContextBuilder.getInstance()
        .withTestRequestBean(this.bean)
        .withTestRequest(new TestRequestDto(type))
        .build();
  }
}
