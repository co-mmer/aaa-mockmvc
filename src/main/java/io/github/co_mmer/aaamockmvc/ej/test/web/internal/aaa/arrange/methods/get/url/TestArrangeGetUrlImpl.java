package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.get.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange1GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange1GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange2GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange3GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.core.TestArrangeUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.get.header.TestArrangeGetHeaderImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import java.net.URI;
import java.util.Map;
import lombok.NonNull;

@Since("1.0.0")
public final class TestArrangeGetUrlImpl extends TestArrangeUrl
    implements TestArrange1GetUrl, TestArrange2GetUrl, TestArrange3GetUrl {

  @Since("2.0.0")
  public TestArrangeGetUrlImpl(
      @NonNull TestAAAContext context, @NonNull String url, Object... variable) {
    super(context);
    setUri(HttpMethod.GET, url, variable);
  }

  @Since("2.0.0")
  public TestArrangeGetUrlImpl(@NonNull TestAAAContext context, @NonNull URI uri) {
    super(context);
    setUri(HttpMethod.GET, uri);
  }

  @Override
  public TestArrange2GetUrl query(@NonNull String key, String value) {
    setQuery(key, value);
    return this;
  }

  @Override
  public TestArrange3GetUrl query(@NonNull Map<String, String> keyValue) {
    setQuery(keyValue);
    return this;
  }

  @Override
  public TestArrange1GetHeader headers() {
    return new TestArrangeGetHeaderImpl(this.context);
  }
}
