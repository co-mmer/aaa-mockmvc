package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;

@Since("1.0.0")
public abstract class TestArrangeHeader extends TestArrangeBaseAbstract {

  @Since("2.0.0")
  protected TestArrangeHeader(TestAAAContext context) {
    super(context);
  }

  @Since("1.0.0")
  protected void setAccepts(MediaType... types) {
    this.context.getRequestBuilder().headers().accept(types);
  }

  @Since("1.0.0")
  protected void setAuth(String token) {
    this.context.getRequestBuilder().headers().auth(token);
  }

  @Since("1.0.0")
  protected void setContentType(MediaType type) {
    this.context.getRequestBuilder().headers().contentType(type);
  }

  @Since("1.0.0")
  protected void put(String key, Object value) {
    this.context.getRequestBuilder().headers().add(key, String.valueOf(value));
  }

  @Since("1.0.0")
  protected void putAll(Map<String, List<Object>> keyValue) {
    this.context.getRequestBuilder().headers().set(keyValue);
  }
}
