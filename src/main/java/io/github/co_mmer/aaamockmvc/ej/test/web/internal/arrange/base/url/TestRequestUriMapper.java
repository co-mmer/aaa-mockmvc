package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.net.URI;
import java.util.List;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

@Since("1.0.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestRequestUriMapper {

  @Since("1.0.0")
  public static URI mapTo(@NonNull String url, @NonNull List<Object> variables) {
    return UriComponentsBuilder.fromUriString(url)
        .buildAndExpand(variables.toArray())
        .encode()
        .toUri();
  }
}
