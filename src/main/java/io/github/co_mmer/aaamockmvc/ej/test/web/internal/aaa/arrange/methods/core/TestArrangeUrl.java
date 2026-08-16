package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
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
    super.context.getArrangeBuilder().method(method);
    getRequestPath().setValue(url, variable);
  }

  @Since("2.0.0")
  protected void setUri(HttpMethod method, URI uri) {
    super.context.getArrangeBuilder().method(method);
    getRequestPath().setValue(uri);
  }

  @Since("2.0.0")
  protected void setQuery(String key, String value) {
    getRequestQuery().add(key, value);
  }

  @Since("2.0.0")
  protected void setQuery(Map<String, String> keyValue) {
    getRequestQuery().addAll(keyValue);
  }
}
