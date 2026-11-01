package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.FluentMatchers.allowsExactly;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.FluentMatchers.allowsNone;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.FluentMatchers.transition;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange1GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange2GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange3GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange5GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.header.TestArrange6GetHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange1GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange2GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.get.url.TestArrange3GetUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario.fluent.FluentAssert;
import org.junit.jupiter.api.Test;

class GetFluentEdgesTest {

  @Test
  void url_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1GetUrl.class, "query", TestArrange2GetUrl.class),
        allowsExactly("query", "headers"));

    FluentAssert.assertThat(
        transition(TestArrange1GetUrl.class, "query", TestArrange3GetUrl.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestArrange1GetUrl.class, "headers", TestArrange1GetHeader.class),
        allowsExactly("accept", "auth", "add", "set"));
  }

  @Test
  void header_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1GetHeader.class, "accept", TestArrange2GetHeader.class),
        allowsExactly("auth", "add", "set"));

    FluentAssert.assertThat(
        transition(TestArrange1GetHeader.class, "auth", TestArrange3GetHeader.class),
        allowsExactly("add", "set"));

    FluentAssert.assertThat(
        transition(TestArrange1GetHeader.class, "add", TestArrange5GetHeader.class),
        allowsExactly("add"));

    FluentAssert.assertThat(
        transition(TestArrange1GetHeader.class, "set", TestArrange6GetHeader.class), allowsNone());
  }
}
