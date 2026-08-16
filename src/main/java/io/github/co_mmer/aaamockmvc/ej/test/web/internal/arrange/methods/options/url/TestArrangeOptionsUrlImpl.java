package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.options.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange1OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange1OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange2OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange3OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.options.header.TestArrangeOptionsHeaderImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request.HttpMethod;
import java.net.URI;
import java.util.Map;
import lombok.NonNull;

@Since("1.0.0")
public final class TestArrangeOptionsUrlImpl extends TestArrangeUrl
    implements TestArrange1OptionsUrl, TestArrange2OptionsUrl, TestArrange3OptionsUrl {

  @Since("2.0.0")
  public TestArrangeOptionsUrlImpl(
      @NonNull TestAAAContext context, @NonNull String url, Object... variable) {
    super(context);
    setUri(HttpMethod.OPTIONS, url, variable);
  }

  @Since("2.0.0")
  public TestArrangeOptionsUrlImpl(@NonNull TestAAAContext context, @NonNull URI uri) {
    super(context);
    setUri(HttpMethod.OPTIONS, uri);
  }

  @Override
  public TestArrange2OptionsUrl query(@NonNull String key, String value) {
    setQuery(key, value);
    return this;
  }

  @Override
  public TestArrange3OptionsUrl query(@NonNull Map<String, String> keyValue) {
    setQuery(keyValue);
    return this;
  }

  @Override
  public TestArrange1OptionsHeader headers() {
    return new TestArrangeOptionsHeaderImpl(this.context);
  }
}
