package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.delete.header;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange1DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange2DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange3DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange4DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange5DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange6DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

@Since("1.0.0")
public final class TestArrangeDeleteHeaderImpl extends TestArrangeHeader
    implements TestArrange1DeleteHeader,
    TestArrange2DeleteHeader,
    TestArrange3DeleteHeader,
    TestArrange4DeleteHeader,
    TestArrange5DeleteHeader,
    TestArrange6DeleteHeader {

  @Since("2.0.0")
  public TestArrangeDeleteHeaderImpl(@NonNull TestAAAContext context) {
    super(context);
  }

  @Override
  public TestArrange2DeleteHeader accept(@NonNull MediaType... accepts) {
    setAccepts(accepts);
    return this;
  }

  @Override
  public TestArrange3DeleteHeader auth(String token) {
    setAuth(token);
    return this;
  }

  @Override
  public TestArrange5DeleteHeader add(String key, Object value) {
    put(key, value);
    return this;
  }

  @Override
  public TestArrange6DeleteHeader set(@NonNull Map<String, List<Object>> keyValue) {
    putAll(keyValue);
    return this;
  }
}
