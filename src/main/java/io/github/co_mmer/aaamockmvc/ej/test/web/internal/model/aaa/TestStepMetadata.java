package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;

public record TestStepMetadata(String name) {

  @Override
  public String name() {
    return StringUtils.isBlank(this.name) ? "<unnamed step>" : this.name;
  }
}
