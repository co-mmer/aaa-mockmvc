package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.res.body.TestArrange1ResBody;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Interface for arranging headers and body for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure standard headers such as "Accept", "Authorization", and
 * "Content-Type", as well as custom headers represented as key-value pairs. Additionally, this
 * interface allows arranging the request body and executing the PATCH/POST/PUT request.
 *
 * @since 1.0.0
 */
public interface TestArrange2ResHead {

  /**
   * Arranges the "Accept" header for the PATCH/POST/PUT request.
   *
   * @param mediaTypes the acceptable media types for the response (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  TestArrange2ResHead arrangeAccept(@NonNull MediaType... mediaTypes);

  /**
   * Arranges the "Authorization" header for the PATCH/POST/PUT request.
   *
   * @param token the authorization token (e.g., "Bearer eyJhbGciOiJIU...") to be set
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange3ResHead arrangeAuth(String token);

  /**
   * Arranges the "Content-Type" header for the PATCH/POST/PUT request.
   *
   * <p>When sending a request without a body, the `Content-Type` header may need to be explicitly
   * set to inform the server about the request's format and how it should be processed. If the
   * request includes a body, the `Content-Type` is either automatically determined or manually set
   * by the {@link #arrangeBody()} method.
   *
   * @param mediaType the content type for the request (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaType} is {@code null}
   * @since 1.0.0
   */
  TestArrange4ResHead arrangeContentType(@NonNull MediaType mediaType);

  /**
   * Arranges a custom header as a key-value pair for the PATCH/POST/PUT request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5ResHead arrangeKeyValue(String key, Object value);

  /**
   * Arranges multiple custom headers as key-value pairs for the PATCH/POST/PUT request.
   *
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange6ResHead arrangeKeyValue(@NonNull Map<String, Object> keyValue);

  /**
   * Arranges the body for the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestArrange1ResBody} to arrange request body
   * @since 1.0.0
   */
  TestArrange1ResBody arrangeBody();

  /**
   * Executes the PATCH/POST/PUT request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
