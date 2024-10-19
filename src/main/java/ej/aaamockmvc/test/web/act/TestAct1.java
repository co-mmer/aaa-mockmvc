package ej.aaamockmvc.test.web.act;

/**
 * This interface represents a contract for performing actions on HTTP requests.
 *
 * <p>It provides methods to execute an HTTP request and continue the action flow by returning an
 * instance of {@link TestAct2} for further configuration.
 *
 * @since 1.0.0
 */
public interface TestAct1 {

  /**
   * Executes the HTTP request defined in the implementing class and returns the current instance
   * for further action chaining.
   *
   * @return an instance of {@link TestAct2} for further configuration
   * @since 1.0.0
   */
  TestAct2 actPerform();
}
