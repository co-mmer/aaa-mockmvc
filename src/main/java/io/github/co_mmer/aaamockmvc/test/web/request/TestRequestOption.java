package io.github.co_mmer.aaamockmvc.test.web.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.url.TestArrange1InfoUrl;
import io.github.co_mmer.aaamockmvc.test.web.arrange.info.url.TestArrangeInfoUrlImpl;
import io.github.co_mmer.aaamockmvc.test.web.request.base.TestRequestBase;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestBean;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestType;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP OPTIONS method.
 *
 * @since 1.0.0
 */
public final class TestRequestOption extends TestRequestBase {

  /**
   * Constructs a new instance of {@code TestRequestOption} with the given configuration.
   *
   * @param bean the {@link TestRequestBean} containing the {@link MockMvc} and {@link ObjectMapper}
   *     required for the request (must not be {@code null})
   * @throws NullPointerException if {@code bean} is {@code null}
   * @since 1.0.0
   */
  public TestRequestOption(@NonNull TestRequestBean bean) {
    super(bean);
  }

  /**
   * Sets up the test request by arranging the necessary context for a OPTIONS request.
   *
   * @return an instance of {@code TestArrange1InfoUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1InfoUrl arrange() {
    var context = super.createContext(TestRequestType.OPTIONS);
    return new TestArrangeInfoUrlImpl(context);
  }
}
