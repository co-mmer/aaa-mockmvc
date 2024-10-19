package ej.aaamockmvc.test.web.request;

import static ej.aaamockmvc.test.web.request.model.TestRequestType.PATCH;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.web.arrange.res.url.TestArrange1ResUrl;
import ej.aaamockmvc.test.web.arrange.res.url.TestArrangeResUrlImpl;
import ej.aaamockmvc.test.web.request.base.TestRequestBase;
import ej.aaamockmvc.test.web.request.context.TestRequestBean;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP PATCH method.
 *
 * @since 1.0.0
 */
public final class TestRequestPatch extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestPatch} with the given configuration.
   *
   * @param bean the {@link TestRequestBean} containing the {@link MockMvc} and {@link ObjectMapper}
   *     required for the request (must not be {@code null})
   * @throws NullPointerException if {@code bean} is {@code null}
   * @since 1.0.0
   */
  public TestRequestPatch(@NonNull TestRequestBean bean) {
    super(bean);
  }

  /**
   * Sets up the test request by arranging the necessary context for a PATCH request.
   *
   * @return an instance of {@code TestArrange1ResUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1ResUrl arrange() {
    var context = super.createContext(PATCH);
    return new TestArrangeResUrlImpl(context);
  }
}