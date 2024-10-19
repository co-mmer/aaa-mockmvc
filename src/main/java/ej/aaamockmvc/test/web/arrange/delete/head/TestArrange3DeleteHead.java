package ej.aaamockmvc.test.web.arrange.delete.head;

import ej.aaamockmvc.test.web.act.TestAct1;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

/**
 * Interface for arranging HTTP headers for a DELETE request.
 *
 * <p>Provides methods to configure common HTTP headers such as "Content-Type", as well as custom
 * key-value pairs.
 *
 * @since 1.0.0
 */
public interface TestArrange3DeleteHead {

  /**
   * Arranges the "Content-Type" header for the DELETE request.
   *
   * @param mediaTypes the content types for the request (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code mediaTypes} is {@code null}
   * @since 1.0.0
   */
  TestArrange4DeleteHead arrangeContentType(@NonNull MediaType... mediaTypes);

  /**
   * Arranges a custom header as a key-value pair for the DELETE request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5DeleteHead arrangeKeyValue(String key, Object value);

  /**
   * Arranges multiple custom headers as key-value pairs for the DELETE request.
   *
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange6DeleteHead arrangeKeyValue(@NonNull Map<String, Object> keyValue);

  /**
   * Executes the DELETE request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
