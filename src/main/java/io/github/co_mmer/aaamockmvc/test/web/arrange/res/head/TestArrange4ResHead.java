package io.github.co_mmer.aaamockmvc.test.web.arrange.res.head;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.arrange.res.body.TestArrange1ResBody;
import java.util.Map;
import lombok.NonNull;

/**
 * Interface for arranging custom headers and body for a PATCH/POST/PUT request.
 *
 * <p>Provides methods to configure custom headers represented as single or multiple key-value
 * pairs, as well as arranging the body of the request and executing the PATCH/POST/PUT request.
 *
 * @since 1.0.0
 */
public interface TestArrange4ResHead {

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
