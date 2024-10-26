package io.github.co_mmer.aaamockmvc.ej.test.web.mapper.exception;

/**
 * Custom exception class for handling errors that occur during the mapping of HTTP response results
 * to expected data types.
 *
 * <p>This exception extends {@link Exception} and is used to encapsulate lower-level exceptions
 * that may arise during the mapping process, providing a more specific context for error handling
 * in the testing framework.
 *
 * @since 1.0.0
 */
public final class TestGenericMapperException extends Exception {

  /**
   * Constructs a new {@code TestGenericMapperException} with the specified cause.
   *
   * <p>This constructor is used to wrap an existing {@link Throwable} that caused the mapping
   * error, preserving the original exception for further analysis.
   *
   * @param cause the cause of the exception (must not be {@code null})
   * @throws NullPointerException if the {@code cause} is {@code null}
   * @since 1.0.0
   */
  public TestGenericMapperException(Throwable cause) {
    super(cause);
  }
}
