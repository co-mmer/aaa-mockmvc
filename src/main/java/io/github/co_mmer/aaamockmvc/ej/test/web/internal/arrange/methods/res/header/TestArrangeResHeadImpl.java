package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.header;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange1ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange2ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange3ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange4ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange5ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange6ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.body.TestArrangeResBodyImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.MediaType;

@Since("1.0.0")
public final class TestArrangeResHeadImpl extends TestArrangeHead
    implements TestArrange1ResHead,
        TestArrange2ResHead,
        TestArrange3ResHead,
        TestArrange4ResHead,
        TestArrange5ResHead,
        TestArrange6ResHead {

  @Since("2.0.0")
  public TestArrangeResHeadImpl(@NonNull TestAAAContext context) {
    super(context);
  }

  @Override
  public TestArrange2ResHead accept(@NonNull MediaType... mediaTypes) {
    setAccepts(mediaTypes);
    return this;
  }

  @Override
  public TestArrange3ResHead auth(String token) {
    setAuth(token);
    return this;
  }

  @Override
  public TestArrange4ResHead contentType(@NonNull MediaType mediaType) {
    setContentTypes(mediaType);
    return this;
  }

  @Override
  public TestArrange5ResHead add(String key, Object value) {
    put(key, value);
    return this;
  }

  @Override
  public TestArrange6ResHead set(@NonNull Map<String, List<Object>> keyValue) {
    putAll(keyValue);
    return this;
  }

  @Override
  public TestArrange1ResBody body() {
    return new TestArrangeResBodyImpl(this.context);
  }
}
