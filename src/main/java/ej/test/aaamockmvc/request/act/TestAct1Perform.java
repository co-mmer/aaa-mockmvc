package ej.test.aaamockmvc.request.act;

/**
 * This interface represents a contract for performing actions on HTTP requests.
 *
 * <p>It provides methods to execute an HTTP request and continue the action flow by returning an
 * instance of {@link TestAct2Perform} for further configuration.
 *
 * @since 1.0.0
 */
public interface TestAct1Perform {

  /**
   * Executes the HTTP request defined in the implementing class and returns the current instance
   * for further action chaining.
   *
   * @return an instance of {@link TestAct2Perform} for further configuration
   * @since 1.0.0
   */
  TestAct2Perform actPerform();
}
