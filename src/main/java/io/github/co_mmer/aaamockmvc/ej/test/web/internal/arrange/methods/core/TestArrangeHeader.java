package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;

@Since("1.0.0")
public abstract class TestArrangeHeader extends TestArrangeBaseAbstract {

  private static final String AUTHORIZATION = "Authorization";

  @Since("2.0.0")
  protected TestArrangeHeader(TestAAAContext context) {
    super(context);
  }

  @Since("1.0.0")
  protected void setAccepts(MediaType... types) {
    TestArrangeValidator.nonNullAccepts(types);
    TestArrangeHeaderSetter.setAccepts(getHead(), types);
  }

  @Since("1.0.0")
  protected void setAuth(String token) {
    TestArrangeHeaderSetter.addKeyValue(getHead(), AUTHORIZATION, token);
  }

  @Since("1.0.0")
  protected void setContentType(MediaType type) {
    TestArrangeHeaderSetter.setContentType(getHead(), type);
  }

  @Since("1.0.0")
  protected void put(String key, Object value) {
    TestArrangeHeaderSetter.addKeyValue(getHead(), key, value);
  }

  @Since("1.0.0")
  protected void putAll(Map<String, List<Object>> keyValue) {
    TestArrangeHeaderSetter.addKeyValue(getHead(), keyValue);
  }
}
