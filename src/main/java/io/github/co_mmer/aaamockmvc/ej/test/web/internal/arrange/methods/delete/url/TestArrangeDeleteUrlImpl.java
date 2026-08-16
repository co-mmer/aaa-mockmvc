package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.delete.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange1DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange1DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange2DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange3DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.delete.header.TestArrangeDeleteHeaderImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.HttpMethod;
import java.net.URI;
import java.util.Map;
import lombok.NonNull;

@Since("1.0.0")
public final class TestArrangeDeleteUrlImpl extends TestArrangeUrl
    implements TestArrange1DeleteUrl, TestArrange2DeleteUrl, TestArrange3DeleteUrl {

  @Since("2.0.0")
  public TestArrangeDeleteUrlImpl(
      @NonNull TestAAAContext context, @NonNull String url, Object... variable) {
    super(context);
    setUri(HttpMethod.DELETE, url, variable);
  }

  @Since("2.0.0")
  public TestArrangeDeleteUrlImpl(@NonNull TestAAAContext context, @NonNull URI uri) {
    super(context);
    setUri(HttpMethod.DELETE, uri);
  }

  @Override
  public TestArrange2DeleteUrl query(@NonNull String key, String value) {
    setQuery(key, value);
    return this;
  }

  @Override
  public TestArrange3DeleteUrl query(@NonNull Map<String, String> keyValue) {
    setQuery(keyValue);
    return this;
  }

  @Override
  public TestArrange1DeleteHeader headers() {
    return new TestArrangeDeleteHeaderImpl(this.context);
  }
}
