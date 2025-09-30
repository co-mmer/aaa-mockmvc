package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsExactly;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsNone;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.transition;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange1HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange3HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange5HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.header.TestArrange6HeadHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange1HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange2HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.head.url.TestArrange3HeadUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentAssert;
import org.junit.jupiter.api.Test;

class HeadFluentEdgesTest {

  @Test
  void url_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1HeadUrl.class, "query", TestArrange2HeadUrl.class),
        allowsExactly("query", "headers"));

    FluentAssert.assertThat(
        transition(TestArrange1HeadUrl.class, "query", TestArrange3HeadUrl.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestArrange1HeadUrl.class, "headers", TestArrange1HeadHeader.class),
        allowsExactly("auth", "add", "set"));
  }

  @Test
  void header_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1HeadHeader.class, "auth", TestArrange3HeadHeader.class),
        allowsExactly("add", "set"));

    FluentAssert.assertThat(
        transition(TestArrange1HeadHeader.class, "add", TestArrange5HeadHeader.class),
        allowsExactly("add"));

    FluentAssert.assertThat(
        transition(TestArrange1HeadHeader.class, "set", TestArrange6HeadHeader.class),
        allowsNone());
  }
}
