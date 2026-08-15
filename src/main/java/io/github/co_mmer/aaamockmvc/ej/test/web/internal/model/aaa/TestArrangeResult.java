package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestBodyDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestHeadDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestUrlDto;
import lombok.Getter;

@Deprecated
@Since("2.0.0")
@Getter
public class TestArrangeResult {

  private final TestRequestUrlDto url;
  private final TestRequestHeadDto head;
  private final TestRequestBodyDto body;

  @Since("2.0.0")
  public TestArrangeResult() {
    this.url = new TestRequestUrlDto();
    this.body = new TestRequestBodyDto();
    this.head = new TestRequestHeadDto();
  }

  @Since("2.0.0")
  public String asMessage() {
    return String.join("\n", this.url.asMessage(), this.head.asMessage(), this.body.asMessage());
  }
}
