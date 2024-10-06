package ej.test.aaamockmvc.request.arrange.get.head;

import ej.test.aaamockmvc.request.act.TestAct1Perform;
import java.util.Map;
import lombok.NonNull;

/**
 * Interface for arranging additional custom headers and executing the GET request.
 *
 * <p>This interface allows for the addition of custom headers as key-value pairs and provides a
 * method to execute the GET request after the headers have been arranged.
 *
 * @since 1.0.0
 */
public interface TestArrange4GetHead {

  /**
   * Arranges a custom header as a key-value pair for the GET request.
   *
   * @param key the name of the header
   * @param value the value of the header
   * @return the next step in the header arrangement process
   * @since 1.0.0
   */
  TestArrange5GetHead arrangeKeyValue(String key, Object value);

  /**
   * Arranges multiple custom headers as key-value pairs for the GET request.
   *
   * @param keyValue a map of header names and their corresponding values (must not be {@code null})
   * @return the next step in the header arrangement process
   * @throws NullPointerException if the {@code keyValue} is {@code null}
   * @since 1.0.0
   */
  TestArrange6GetHead arrangeKeyValue(@NonNull Map<String, Object> keyValue);

  /**
   * Executes the GET request.
   *
   * @return an instance of {@code TestAct1Perform} to execute the request and evaluate the response
   * @since 1.0.0
   */
  TestAct1Perform act();
}
