package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.head.url;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange1HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange1HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange2HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange3HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.core.TestArrangeUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange.methods.head.header.TestArrangeHeadHeaderImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import java.net.URI;
import java.util.Map;
import lombok.NonNull;

@Since("1.0.0")
public final class TestArrangeHeadUrlImpl extends TestArrangeUrl
    implements TestArrange1HeadUrl, TestArrange2HeadUrl, TestArrange3HeadUrl {

  @Since("2.0.0")
  public TestArrangeHeadUrlImpl(
      @NonNull TestAAAContext context, @NonNull String url, Object... variable) {
    super(context);
    setUri(HttpMethod.HEAD, url, variable);
  }

  @Since("2.0.0")
  public TestArrangeHeadUrlImpl(@NonNull TestAAAContext context, @NonNull URI uri) {
    super(context);
    setUri(HttpMethod.HEAD, uri);
  }

  @Override
  public TestArrange2HeadUrl query(@NonNull String key, String value) {
    setQuery(key, value);
    return this;
  }

  @Override
  public TestArrange3HeadUrl query(@NonNull Map<String, String> keyValue) {
    setQuery(keyValue);
    return this;
  }

  @Override
  public TestArrange1HeadHeader headers() {
    return new TestArrangeHeadHeaderImpl(this.context);
  }
}
