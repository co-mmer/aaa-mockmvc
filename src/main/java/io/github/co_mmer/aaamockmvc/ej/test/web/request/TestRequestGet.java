package io.github.co_mmer.aaamockmvc.ej.test.web.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.url.TestArrange1GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.get.url.TestArrangeGetUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.base.TestRequestBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
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
    var context = super.createContext(TestRequestType.GET);
    return new TestArrangeGetUrlImpl(context);
  }
}
