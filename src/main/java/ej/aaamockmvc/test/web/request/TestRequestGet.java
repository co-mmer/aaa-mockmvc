package ej.aaamockmvc.test.web.request;

import static ej.aaamockmvc.test.web.request.model.TestRequestType.GET;

import com.fasterxml.jackson.databind.ObjectMapper;
import ej.aaamockmvc.test.web.arrange.get.url.TestArrange1GetUrl;
import ej.aaamockmvc.test.web.arrange.get.url.TestArrangeGetUrlImpl;
import ej.aaamockmvc.test.web.request.base.TestRequestBase;
import ej.aaamockmvc.test.web.request.context.TestRequestBean;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP GET method.
 *
 * @since 1.0.0
 */
public final class TestRequestGet extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestGet} with the given configuration.
   *
   * @param bean the {@link TestRequestBean} containing the {@link MockMvc} and {@link ObjectMapper}
   *     required for the request (must not be {@code null})
   * @throws NullPointerException if {@code bean} is {@code null}
   * @since 1.0.0
   */
  public TestRequestGet(@NonNull TestRequestBean bean) {
    super(bean);
  }

  /**
   * Sets up the test request by arranging the necessary context for a GET request.
   *
   * @return an instance of {@code TestArrange1GetUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1GetUrl arrange() {
    var context = super.createContext(GET);
    return new TestArrangeGetUrlImpl(context);
  }
}
