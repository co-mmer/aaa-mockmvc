package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.TestArrange;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange1DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange1GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange1HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange1OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange1ResUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.delete.url.TestArrangeDeleteUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.get.url.TestArrangeGetUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.head.url.TestArrangeHeadUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.options.url.TestArrangeOptionsUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.res.url.TestArrangeResUrlImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model.RequestBuilder;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import java.net.URI;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Since("2.0.0")
@RequiredArgsConstructor
public final class TestArrangeImpl implements TestArrange {

  private final TestAAAContext context;

  @Override
  public TestArrange1GetUrl get(@NonNull String url, Object... variable) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeGetUrlImpl(this.context, url, variable);
  }

  @Override
  public TestArrange1GetUrl get(@NonNull URI uri) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeGetUrlImpl(this.context, uri);
  }

  @Override
  public TestArrange1DeleteUrl delete(@NonNull String url, Object... variable) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeDeleteUrlImpl(this.context, url, variable);
  }

  @Override
  public TestArrange1DeleteUrl delete(@NonNull URI uri) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeDeleteUrlImpl(this.context, uri);
  }

  @Override
  public TestArrange1OptionsUrl options(@NonNull String url, Object... variable) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeOptionsUrlImpl(this.context, url, variable);
  }

  @Override
  public TestArrange1OptionsUrl options(@NonNull URI uri) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeOptionsUrlImpl(this.context, uri);
  }

  @Override
  public TestArrange1HeadUrl head(@NonNull String url, Object... variable) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeHeadUrlImpl(this.context, url, variable);
  }

  @Override
  public TestArrange1HeadUrl head(@NonNull URI uri) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeHeadUrlImpl(this.context, uri);
  }

  @Override
  public TestArrange1ResUrl post(@NonNull String url, Object... variable) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeResUrlImpl(this.context, HttpMethod.POST, url, variable);
  }

  @Override
  public TestArrange1ResUrl post(@NonNull URI uri) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeResUrlImpl(this.context, HttpMethod.POST, uri);
  }

  @Override
  public TestArrange1ResUrl put(@NonNull String url, Object... variable) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeResUrlImpl(this.context, HttpMethod.PUT, url, variable);
  }

  @Override
  public TestArrange1ResUrl put(@NonNull URI uri) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeResUrlImpl(this.context, HttpMethod.PUT, uri);
  }

  @Override
  public TestArrange1ResUrl patch(@NonNull String url, Object... variable) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeResUrlImpl(this.context, HttpMethod.PATCH, url, variable);
  }

  @Override
  public TestArrange1ResUrl patch(@NonNull URI uri) {
    this.context.setRequestBuilder(new RequestBuilder());
    return new TestArrangeResUrlImpl(this.context, HttpMethod.PATCH, uri);
  }
}
