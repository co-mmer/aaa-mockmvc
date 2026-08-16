package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange1ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange1ResUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange2ResUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange3ResUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.body.TestArrangeResBodyImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.header.TestArrangeResHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.HttpMethod;
import java.net.URI;
import java.util.Map;
import lombok.NonNull;

@Since("1.0.0")
public final class TestArrangeResUrlImpl extends TestArrangeUrl
    implements TestArrange1ResUrl, TestArrange2ResUrl, TestArrange3ResUrl {

  @Since("2.0.0")
  public TestArrangeResUrlImpl(
      @NonNull TestAAAContext context,
      @NonNull HttpMethod method,
      @NonNull String url,
      Object... variable) {
    super(context);
    setUri(method, url, variable);
  }

  @Since("2.0.0")
  public TestArrangeResUrlImpl(
      @NonNull TestAAAContext context, @NonNull HttpMethod method, @NonNull URI uri) {
    super(context);
    setUri(method, uri);
  }

  @Override
  public TestArrange2ResUrl query(@NonNull String key, String value) {
    setQuery(key, value);
    return this;
  }

  @Override
  public TestArrange3ResUrl query(@NonNull Map<String, String> keyValue) {
    setQuery(keyValue);
    return this;
  }

  @Override
  public TestArrange1ResHead headers() {
    return new TestArrangeResHeadImpl(this.context);
  }

  @Override
  public TestArrange1ResBody body() {
    return new TestArrangeResBodyImpl(this.context);
  }
}
