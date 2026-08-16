package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeNormalizerException extends Exception {

  @Since("2.0.0")
  public TestArrangeNormalizerException(String message) {
    super(message);
  }
}
