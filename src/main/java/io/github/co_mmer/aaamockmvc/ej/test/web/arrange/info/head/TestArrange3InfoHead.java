package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.info.head;

import io.github.co_mmer.aaamockmvc.ej.test.web.act.TestAct1;
import java.util.Map;
import lombok.NonNull;

/**
 * Interface for arranging HTTP headers for a HEAD/OPTIONS request.
 *
 * <p>Provides methods to configure custom HTTP key-value headers.
 *
 * @since 1.0.0
 */
public interface TestArrange3InfoHead {

  /**
   * Arranges a custom header as a key-value pair for the HEAD/OPTIONS request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5InfoHead arrangeKeyValue(String key, Object value);

  /**
   * Arranges multiple custom headers as key-value pairs for the HEAD/OPTIONS request.
   *
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange6InfoHead arrangeKeyValue(@NonNull Map<String, Object> keyValue);

  /**
   * Executes the HEAD/OPTIONS request.
   *
   * @return an instance of {@code TestAct1} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1 act();
}
