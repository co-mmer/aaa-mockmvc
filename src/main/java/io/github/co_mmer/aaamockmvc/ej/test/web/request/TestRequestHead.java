package io.github.co_mmer.aaamockmvc.ej.test.web.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.url.TestArrange1InfoUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.url.TestArrangeInfoUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.base.TestRequestBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
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
    var context = super.createContext(TestRequestType.HEAD);
    return new TestArrangeInfoUrlImpl(context);
  }
}
