package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.net.URI;
import java.util.Map;
import org.springframework.http.HttpMethod;

@Since("2.0.0")
public abstract class TestArrangeUrl extends TestArrangeBaseAbstract {

  @Since("2.0.0")
  protected TestArrangeUrl(TestAAAContext context) {
    super(context);
  }

  @Since("2.0.0")
  protected void setUri(HttpMethod method, String url, Object... variable) {
    TestArrangeUrlSetter.setUri(super.getUrl(), method, url, variable);
  }

  @Since("2.0.0")
  protected void setUri(HttpMethod method, URI uri) {
    TestArrangeUrlSetter.setUri(super.getUrl(), method, uri);
  }

  @Since("2.0.0")
  protected void setQuery(String key, String value) {
    TestArrangeUrlSetter.addQuery(super.getUrl(), key, value);
  }

  @Since("2.0.0")
  protected void setQuery(Map<String, String> keyValue) {
    TestArrangeUrlSetter.addQuery(super.getUrl(), keyValue);
  }
}
