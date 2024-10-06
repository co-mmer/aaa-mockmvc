package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.PATCH;

import ej.test.aaamockmvc.request.arrange.res.url.TestArrange1ResUrl;
import ej.test.aaamockmvc.request.arrange.res.url.TestArrangeResUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP PATCH method.
 *
 * @since 1.0.0
 */
public final class TestRequestPatch extends TestRequestBase {

  /**
   * Constructs a new {@code TestRequestPatch} object with the provided {@code MockMvc} instance.
   *
   * @param mvc the {@code MockMvc} instance to be used for making PATCH requests (must not be
   *     {@code null})
   * @throws NullPointerException if the {@code mvc} is {@code null}
   * @since 1.0.0
   */
  public TestRequestPatch(@NonNull MockMvc mvc) {
    super(mvc);
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
