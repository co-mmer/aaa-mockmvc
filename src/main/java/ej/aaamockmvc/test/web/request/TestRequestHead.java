package ej.aaamockmvc.test.web.request;

import static ej.aaamockmvc.test.web.request.model.TestRequestType.HEAD;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.web.arrange.info.url.TestArrange1InfoUrl;
import ej.aaamockmvc.test.web.arrange.info.url.TestArrangeInfoUrlImpl;
import ej.aaamockmvc.test.web.request.base.TestRequestBase;
import ej.aaamockmvc.test.web.request.context.TestRequestBean;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP HEAD method.
 *
 * @since 1.0.0
 */
public final class TestRequestHead extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestHead} with the given configuration.
   *
   * @param bean the {@link TestRequestBean} containing the {@link MockMvc} and {@link ObjectMapper}
   *     required for the request (must not be {@code null})
   * @throws NullPointerException if {@code bean} is {@code null}
   * @since 1.0.0
   */
  public TestRequestHead(@NonNull TestRequestBean bean) {
    super(bean);
  }

  /**
   * Sets up the test request by arranging the necessary context for a HEAD request.
   *
   * @return an instance of {@code TestArrange1InfoUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1InfoUrl arrange() {
    var context = super.createContext(HEAD);
    return new TestArrangeInfoUrlImpl(context);
  }
}
