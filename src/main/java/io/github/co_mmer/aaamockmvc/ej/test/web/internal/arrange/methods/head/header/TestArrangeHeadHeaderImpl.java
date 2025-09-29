package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.head.header;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange1HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange2HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange3HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange4HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange5HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange6HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core.TestArrangeHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

@Since("1.0.0")
public final class TestArrangeHeadHeaderImpl extends TestArrangeHeader
    implements TestArrange1HeadHeader,
    TestArrange2HeadHeader,
    TestArrange3HeadHeader,
    TestArrange4HeadHeader,
    TestArrange5HeadHeader,
    TestArrange6HeadHeader {

  @Since("2.0.0")
  public TestArrangeHeadHeaderImpl(@NonNull TestAAAContext context) {
    super(context);
  }

  @Override
  public TestArrange3HeadHeader auth(String token) {
    setAuth(token);
    return this;
  }

  @Override
  public TestArrange5HeadHeader add(String key, Object value) {
    put(key, value);
    return this;
  }

  @Override
  public TestArrange6HeadHeader set(@NonNull Map<String, List<Object>> keyValue) {
    putAll(keyValue);
    return this;
  }
}
