package ej.test.aaamockmvc.request;

import static ej.test.aaamockmvc.request.model.TestRequestType.DELETE;

import ej.test.aaamockmvc.request.arrange.delete.url.TestArrange1DeleteUrl;
import ej.test.aaamockmvc.request.arrange.delete.url.TestArrangeDeleteUrlImpl;
import ej.test.aaamockmvc.request.base.TestRequestBase;
import lombok.NonNull;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class represents a specific test request using the HTTP DELETE method.
 *
 * @since 1.0.0
 */
public final class TestRequestDelete extends TestRequestBase {

  /**
   * Constructs a new {@code TestRequestDelete} object with the provided {@code MockMvc} instance.
   *
   * @param mvc the {@code MockMvc} instance to be used for making DELETE requests (must not be
   *     {@code null})
   * @throws NullPointerException if the {@code mvc} is {@code null}
   * @since 1.0.0
   */
  public TestRequestDelete(@NonNull MockMvc mvc) {
    super(mvc);
  }

  /**
   * Sets up the test request by arranging the necessary context for a DELETE request.
   *
   * @return an instance of {@code TestArrange1DeleteUrl} for further URL arrangement
   * @since 1.0.0
   */
  public TestArrange1DeleteUrl arrange() {
    var context = super.createContext(DELETE);
    return new TestArrangeDeleteUrlImpl(context);
  }
}
