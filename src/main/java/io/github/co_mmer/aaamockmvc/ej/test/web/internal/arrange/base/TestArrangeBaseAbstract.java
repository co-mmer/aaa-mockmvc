package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestEnvironment;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestQuery;
import lombok.NonNull;

@Since("1.0.0")
public abstract class TestArrangeBaseAbstract {

  protected final TestAAAContext context;

  protected TestArrangeBaseAbstract(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Since("2.0.2")
  protected RequestPath getRequestPath() {
    return this.context.getArrangeBuilder().path();
  }

  @Since("2.0.2")
  protected RequestQuery getRequestQuery() {
    return this.context.getArrangeBuilder().query();
  }

  @Since("2.0.2")
  protected RequestHeaders getRequestHeaders() {
    return this.context.getArrangeBuilder().headers();
  }

  @Since("2.0.2")
  protected RequestBody getRequestBody() {
    return this.context.getArrangeBuilder().body();
  }

  @Since("1.0.0")
  protected TestEnvironment getEnvironment() {
    return this.context.getEnvironment();
  }
}
