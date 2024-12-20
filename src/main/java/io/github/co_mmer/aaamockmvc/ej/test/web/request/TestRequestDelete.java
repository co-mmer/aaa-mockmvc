package io.github.co_mmer.aaamockmvc.ej.test.web.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.delete.url.TestArrange1DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.delete.url.TestArrangeDeleteUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.base.TestRequestBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.ej.test.web.request.model.TestRequestType;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP DELETE method.
 *
 * @since 1.0.0
 */
public final class TestRequestDelete extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestDelete} with the given configuration.
   *
   * @param bean the {@link TestRequestBean} containing the {@link MockMvc} and {@link ObjectMapper}
   *     required for the request (must not be {@code null})
   * @throws NullPointerException if {@code bean} is {@code null}
   * @since 1.0.0
   */
  public TestRequestDelete(@NonNull TestRequestBean bean) {
    super(bean);
  }

  /**
   * Sets up the test request by arranging the necessary context for a DELETE request.
   *
   * @return an instance of {@code TestArrange1DeleteUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1DeleteUrl arrange() {
    var context = super.createContext(TestRequestType.DELETE);
    return new TestArrangeDeleteUrlImpl(context);
  }
}
