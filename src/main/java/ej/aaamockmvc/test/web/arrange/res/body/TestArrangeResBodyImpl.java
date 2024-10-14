package ej.aaamockmvc.test.web.arrange.res.body;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import ej.aaamockmvc.test.web.act.TestAct1Perform;
import ej.aaamockmvc.test.web.arrange.base.TestArrangeBaseAbstract;
import ej.aaamockmvc.test.web.arrange.utils.TestArrangeRequestBody;
import ej.aaamockmvc.test.web.request.context.TestRequestContext;
import java.util.List;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

/**
 * This class is responsible for arrangement of the request body for PATCH/POST/PUT requests.
 *
 * <p>Provides methods to configure raw content, JSON content, and files as the body of the request,
 * as well as the ability to execute the request.
 *
 * @since 1.0.0
 */
public final class TestArrangeResBodyImpl extends TestArrangeBaseAbstract
    implements TestArrange1ResBody, TestArrange2ResBody, TestArrange3ResBody {

  /**
   * Initializes the arrangement for a PATCH/POST/PUT request using the provided {@code
   * TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  public TestArrangeResBodyImpl(@NonNull TestRequestContext context) {
    super(context);
  }

  /**
   * Arranges raw content as the body for the PATCH/POST/PUT request with a specific media type.
   *
   * @param raw the raw content to be set as the request body
   * @param type the media type of the content (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code raw} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange3ResBody arrangeContent(@NonNull String raw, @NonNull MediaType type) {
    TestArrangeRequestBody.setContent(getBody(), raw, type);

    return this;
  }

  /**
   * Arranges JSON content as the body for the PATCH/POST/PUT request.
   *
   * @param json the JSON content to be set as the request body
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code json} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange3ResBody arrangeJson(@NonNull String json) {
    TestArrangeRequestBody.setContent(getBody(), json, APPLICATION_JSON);
    return this;
  }

  /**
   * Arranges a single file as the body for the PATCH/POST/PUT request.
   *
   * @param file the file to be included in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code file} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange2ResBody arrangeFile(@NonNull MockMultipartFile file) {
    TestArrangeRequestBody.addFile(getBody(), file);
    return this;
  }

  /**
   * Arranges multiple files as the body for the PATCH/POST/PUT request.
   *
   * @param files the list of files to be included in the request (must not be {@code null})
   * @return the current instance for further configuration
   * @throws NullPointerException if the {@code files} is {@code null}
   * @since 1.0.0
   */
  @Override
  public TestArrange3ResBody arrangeFiles(@NonNull List<MockMultipartFile> files) {
    TestArrangeRequestBody.addFiles(getBody(), files);
    return this;
  }

  /**
   * Executes the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  @Override
  public TestAct1Perform act() {
    return createActPerformImpl();
  }
}
