package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.net.URI;
import java.util.Map;

@Since("2.0.0")
public abstract class TestArrangeUrl extends TestArrangeBaseAbstract {

  @Since("2.0.0")
  protected TestArrangeUrl(TestAAAContext context) {
    super(context);
  }

  @Since("2.0.0")
  protected void setUri(HttpMethod method, String url, Object... variable) {
    // TestArrangeUrlSetter.setUri(super.getUrl(), method, url, variable);

    context.getRequestBuilder().method(method);
    context.getRequestBuilder().path().setValue(url, variable);
  }

  @Since("2.0.0")
  protected void setUri(HttpMethod method, URI uri) {
    // TestArrangeUrlSetter.setUri(super.getUrl(), method, uri);

    context.getRequestBuilder().method(method);
    context.getRequestBuilder().path().setValue(uri);
  }

  @Since("2.0.0")
  protected void setQuery(String key, String value) {
    TestArrangeUrlSetter.addQuery(super.getUrl(), key, value);
    this.context.getRequestBuilder().query().add(key, value);
  }

  @Since("2.0.0")
  protected void setQuery(Map<String, String> keyValue) {
    TestArrangeUrlSetter.addQuery(super.getUrl(), keyValue);
    this.context.getRequestBuilder().query().addAll(keyValue);
  }
}
