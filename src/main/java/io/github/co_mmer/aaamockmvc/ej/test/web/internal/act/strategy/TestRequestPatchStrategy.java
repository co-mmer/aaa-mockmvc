package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy;

import static org.springframework.http.HttpMethod.PATCH;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.builder.TestRequestBuilderUtils;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component.TestComponentBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestArrangeResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Since("1.0.0")
public final class TestRequestPatchStrategy extends TestRequestBaseStrategy {

  @Override
  protected void initRequestBuilder(TestArrangeResult content) {
    var uri = content.getUrl().getUri();
    this.requestBuilder =
        content.getBody().isNotEmptyFiles()
            ? MockMvcRequestBuilders.multipart(uri).with(TestRequestBuilderUtils.setMethod(PATCH))
            : MockMvcRequestBuilders.patch(content.getUrl().getUri());
  }

  @Override
  protected void applyBody(TestArrangeResult content) {
    TestComponentBody.apply(this.requestBuilder, content.getBody());
  }
}
