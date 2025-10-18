package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component.TestComponentBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Since("1.0.0")
public final class TestRequestPostStrategy extends TestRequestBaseStrategy {

  @Override
  protected void initRequestBuilder(TestArrangeResult context) {
    var uri = context.getUrl().getUri();
    this.requestBuilder =
        context.getBody().isNotEmptyFiles()
            ? MockMvcRequestBuilders.multipart(uri)
            : MockMvcRequestBuilders.post(uri);
  }

  @Override
  protected void applyBody(TestArrangeResult context) {
    TestComponentBody.apply(this.requestBuilder, context.getBody());
  }
}
