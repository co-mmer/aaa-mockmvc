package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component.TestComponentHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component.TestComponentUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import lombok.NonNull;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Since("1.0.0")
public abstract class TestRequestBaseStrategy implements TestRequestStrategy {

  protected MockHttpServletRequestBuilder requestBuilder;

  @Override
  public final MockHttpServletRequestBuilder apply(@NonNull TestArrangeResult context) {
    initRequestBuilder(context);
    TestComponentUrl.apply(this.requestBuilder, context.getUrl());
    TestComponentHead.apply(this.requestBuilder, context.getHead());

    applyBody(context);
    return this.requestBuilder;
  }

  @Since("2.0.0")
  protected abstract void initRequestBuilder(TestArrangeResult content);

  @Since("2.0.0")
  protected void applyBody(TestArrangeResult content) {
    // No implementation by default, meant to be overridden by subclasses as needed.
  }
}
