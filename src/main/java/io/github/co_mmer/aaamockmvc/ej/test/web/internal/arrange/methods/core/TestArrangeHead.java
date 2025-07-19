package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.TestArrangeBaseAbstract;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.validation.TestArrangeValidator;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

@Since("1.0.0")
public abstract class TestArrangeHead extends TestArrangeBaseAbstract {

  private static final String AUTHORIZATION = "Authorization";

  @Since("2.0.0")
  protected TestArrangeHead(@NonNull TestAAAContext context) {
    super(context);
  }

  @Since("1.0.0")
  protected void setAccepts(@NonNull MediaType... types) {
    TestArrangeValidator.nonNullAccepts(types);
    TestArrangeHeadUtils.setAccepts(getHead(), types);
  }

  @Since("1.0.0")
  protected void setAuth(String token) {
    TestArrangeHeadUtils.addKeyValue(getHead(), AUTHORIZATION, token);
  }

  @Since("1.0.0")
  protected void setContentTypes(@NonNull MediaType type) {
    TestArrangeHeadUtils.setContentTypes(getHead(), type);
  }

  @Since("1.0.0")
  protected void put(String key, Object value) {
    TestArrangeHeadUtils.addKeyValue(getHead(), key, value);
  }

  @Since("1.0.0")
  protected void putAll(@NonNull Map<String, List<Object>> keyValue) {
    TestArrangeHeadUtils.addKeyValue(getHead(), keyValue);
  }
}
