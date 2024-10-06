package ej.test.aaamockmvc.request.arrange.base;

import ej.test.aaamockmvc.context.TestRequestContext;
import ej.test.aaamockmvc.request.act.TestAct1Perform;
import ej.test.aaamockmvc.request.act.TestActPerformImpl;
import ej.test.aaamockmvc.request.model.TestRequestBodyDto;
import ej.test.aaamockmvc.request.model.TestRequestHeadDto;
import ej.test.aaamockmvc.request.model.TestRequestUrlDto;
import lombok.NonNull;

/**
 * Abstract base class for arranging request components.
 *
 * <p>This class holds the context for the request and provides methods to access the URL, headers,
 * and body of the request. It serves as a foundation for other arrangement classes.
 *
 * @since 1.0.0
 */
public abstract class TestArrangeBaseAbstract {

  private final TestRequestContext context;

  /**
   * Initializes the base class with the provided {@code TestRequestContext}.
   *
   * @param context the context that manages the state of the request (must not be {@code null})
   * @throws NullPointerException if the {@code context} is {@code null}
   * @since 1.0.0
   */
  protected TestArrangeBaseAbstract(@NonNull TestRequestContext context) {
    this.context = context;
  }

  /**
   * Retrieves the current {@code TestRequestContext}.
   *
   * @return the current request context
   * @since 1.0.0
   */
  protected TestRequestContext getContext() {
    return this.context;
  }

  /**
   * Retrieves the URL information for the current request.
   *
   * @return the {@code TestRequestUrlDto} containing the URL details
   * @since 1.0.0
   */
  protected TestRequestUrlDto getUrl() {
    return this.context.request().getUrl();
  }

  /**
   * Retrieves the headers for the current request.
   *
   * @return the {@code TestRequestHeadDto} containing the header details
   * @since 1.0.0
   */
  protected TestRequestHeadDto getHead() {
    return this.context.request().getHead();
  }

  /**
   * Retrieves the body of the current request.
   *
   * @return the {@code TestRequestBodyDto} containing the body details
   * @since 1.0.0
   */
  protected TestRequestBodyDto getBody() {
    return this.context.request().getBody();
  }

  /**
   * Creates a new instance of {@code TestAct1Perform} for executing actions.
   *
   * @return a new instance of {@code TestAct1Perform}
   * @since 1.0.0
   */
  protected TestAct1Perform createActPerformImpl() {
    return new TestActPerformImpl(getContext());
  }
}
