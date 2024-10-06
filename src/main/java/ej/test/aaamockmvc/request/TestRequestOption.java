package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.OPTIONS;

import ej.test.aaamockmvc.request.arrange.info.url.TestArrange1InfoUrl;
import ej.test.aaamockmvc.request.arrange.info.url.TestArrangeInfoUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP OPTIONS method.
 *
 * @since 1.0.0
 */
public final class TestRequestOption extends TestRequestBase {

  /**
   * Constructs a new {@code TestRequestOption} object with the provided {@code MockMvc} instance.
   *
   * @param mvc the {@code MockMvc} instance to be used for making OPTIONS requests (must not be
   *     {@code null})
   * @throws NullPointerException if the {@code mvc} is {@code null}
   * @since 1.0.0
   */
  public TestRequestOption(@NonNull MockMvc mvc) {
    super(mvc);
  }

  /**
   * Sets up the test request by arranging the necessary context for a OPTIONS request.
   *
   * @return an instance of {@code TestArrange1InfoUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1InfoUrl arrange() {
    var context = super.createContext(OPTIONS);
    return new TestArrangeInfoUrlImpl(context);
  }
}
