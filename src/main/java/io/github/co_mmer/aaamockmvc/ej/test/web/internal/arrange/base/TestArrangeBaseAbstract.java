package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestEnvironment;
import lombok.NonNull;

@Since("1.0.0")
public abstract class TestArrangeBaseAbstract {

  protected final TestAAAContext context;

  protected TestArrangeBaseAbstract(@NonNull TestAAAContext context) {
    this.context = context;
  }

  @Since("1.0.0")
  protected TestEnvironment getEnvironment() {
    return this.context.getEnvironment();
  }
}
