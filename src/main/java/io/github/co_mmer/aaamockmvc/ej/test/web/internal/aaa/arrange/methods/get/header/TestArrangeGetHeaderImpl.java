package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.get.header;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange1GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange2GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange3GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange4GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange5GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange6GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.core.TestArrangeHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

@Since("1.0.0")
public final class TestArrangeGetHeaderImpl extends TestArrangeHeader
    implements TestArrange1GetHeader,
        TestArrange2GetHeader,
        TestArrange3GetHeader,
        TestArrange4GetHeader,
        TestArrange5GetHeader,
        TestArrange6GetHeader {

  @Since("2.0.0")
  public TestArrangeGetHeaderImpl(@NonNull TestAAAContext context) {
    super(context);
  }

  @Override
  public TestArrange2GetHeader accept(@NonNull MediaType... mediaTypes) {
    setAccepts(mediaTypes);
    return this;
  }

  @Override
  public TestArrange3GetHeader auth(String token) {
    setAuth(token);
    return this;
  }

  @Override
  public TestArrange5GetHeader add(String key, Object value) {
    put(key, value);
    return this;
  }

  @Override
  public TestArrange6GetHeader set(@NonNull Map<String, List<Object>> keyValue) {
    putAll(keyValue);
    return this;
  }
}
