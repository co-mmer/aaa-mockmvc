package io.github.co_mmer.aaamockmvc.test.web.arrange.base;

import io.github.co_mmer.aaamockmvc.test.web.act.TestAct1;
import io.github.co_mmer.aaamockmvc.test.web.act.TestActImpl;
import io.github.co_mmer.aaamockmvc.test.web.request.context.TestRequestContext;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestBodyDto;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestHeadDto;
import io.github.co_mmer.aaamockmvc.test.web.request.model.TestRequestUrlDto;
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
   * Creates a new instance of {@code TestAct1} for executing actions.
   *
   * @return a new instance of {@code TestAct1}
   * @since 1.0.0
   */
  protected TestAct1 createActImpl() {
    return new TestActImpl(getContext());
  }
}
