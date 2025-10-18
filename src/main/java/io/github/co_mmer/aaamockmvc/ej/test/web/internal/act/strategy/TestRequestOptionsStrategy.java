package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Since("1.0.0")
public final class TestRequestOptionsStrategy extends TestRequestBaseStrategy {

  @Override
  protected void initRequestBuilder(TestArrangeResult content) {
    this.requestBuilder = MockMvcRequestBuilders.options(content.getUrl().getUri());
  }
}
