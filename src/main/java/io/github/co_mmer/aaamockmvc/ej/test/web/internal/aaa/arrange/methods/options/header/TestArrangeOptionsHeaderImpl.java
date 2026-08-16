package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.options.header;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange1OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange2OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange3OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange4OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange5OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange6OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.core.TestArrangeHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import java.util.Map;
import org.springframework.lang.NonNull;

@Since("1.0.0")
public final class TestArrangeOptionsHeaderImpl extends TestArrangeHeader
    implements TestArrange1OptionsHeader,
        TestArrange2OptionsHeader,
        TestArrange3OptionsHeader,
        TestArrange4OptionsHeader,
        TestArrange5OptionsHeader,
        TestArrange6OptionsHeader {

  @Since("2.0.0")
  public TestArrangeOptionsHeaderImpl(@NonNull TestAAAContext context) {
    super(context);
  }

  @Override
  public TestArrange3OptionsHeader auth(String token) {
    setAuth(token);
    return this;
  }

  @Override
  public TestArrange5OptionsHeader add(String key, Object value) {
    put(key, value);
    return this;
  }

  @Override
  public TestArrange6OptionsHeader set(@NonNull Map<String, List<Object>> keyValue) {
    putAll(keyValue);
    return this;
  }
}
