package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.HEAD;

import ej.test.aaamockmvc.request.arrange.info.url.TestArrange1InfoUrl;
import ej.test.aaamockmvc.request.arrange.info.url.TestArrangeInfoUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP HEAD method.
 *
 * @since 1.0.0
 */
public final class TestRequestHead extends TestRequestBase {

  /**
   * Constructs a new {@code TestRequestHead} object with the provided {@code MockMvc} instance.
   *
   * @param mvc the {@code MockMvc} instance to be used for making HEAD requests (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code mvc} is {@code null}
   * @since 1.0.0
   */
  public TestRequestHead(@NonNull MockMvc mvc) {
    super(mvc);
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
