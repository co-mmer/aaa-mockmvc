package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.strategy.component;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.TestRequestUrlDto;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@Since("1.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestComponentUrl {

  @Since("1.0.0")
  public static void apply(
      @NonNull MockHttpServletRequestBuilder builder, @NonNull TestRequestUrlDto requestUrlDto) {

    if (requestUrlDto.isNotEmptyQuery()) {
      requestUrlDto.getQuery().forEach(builder::param);
    }
  }
}
