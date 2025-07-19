package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import lombok.Getter;

@Since("1.0.0")
@Getter
public final class TestRequestDto {

  private final TestRequestUrlDto url;
  private final TestRequestHeadDto head;
  private final TestRequestBodyDto body;

  @Since("1.0.0")
  public TestRequestDto() {
    this.url = new TestRequestUrlDto();
    this.body = new TestRequestBodyDto();
    this.head = new TestRequestHeadDto();
  }
}
