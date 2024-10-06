package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.GET;

import ej.test.aaamockmvc.request.arrange.get.url.TestArrange1GetUrl;
import ej.test.aaamockmvc.request.arrange.get.url.TestArrangeGetUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP GET method.
 *
 * @since 1.0.0
 */
public final class TestRequestGet extends TestRequestBase {

  /**
   * Constructs a new {@code TestRequestGet} object with the provided {@code MockMvc} instance.
   *
   * @param mvc the {@code MockMvc} instance to be used for making GET requests (must not be {@code
   *     null})
   * @throws NullPointerException if the {@code mvc} is {@code null}
   * @since 1.0.0s
   */
  public TestRequestGet(@NonNull MockMvc mvc) {
    super(mvc);
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
