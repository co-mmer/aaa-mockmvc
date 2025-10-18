package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsExactly;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsNone;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.transition;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange1DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange2DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange3DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange5DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.header.TestArrange6DeleteHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange1DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange2DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.delete.url.TestArrange3DeleteUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentAssert;
import org.junit.jupiter.api.Test;

class DeleteFluentEdgesTest {

  @Test
  void url_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1DeleteUrl.class, "query", TestArrange2DeleteUrl.class),
        allowsExactly("query", "headers"));

    FluentAssert.assertThat(
        transition(TestArrange1DeleteUrl.class, "query", TestArrange3DeleteUrl.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestArrange1DeleteUrl.class, "headers", TestArrange1DeleteHeader.class),
        allowsExactly("accept", "auth", "add", "set"));
  }

  @Test
  void header_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1DeleteHeader.class, "accept", TestArrange2DeleteHeader.class),
        allowsExactly("auth", "add", "set"));

    FluentAssert.assertThat(
        transition(TestArrange1DeleteHeader.class, "auth", TestArrange3DeleteHeader.class),
        allowsExactly("add", "set"));

    FluentAssert.assertThat(
        transition(TestArrange1DeleteHeader.class, "add", TestArrange5DeleteHeader.class),
        allowsExactly("add"));

    FluentAssert.assertThat(
        transition(TestArrange1DeleteHeader.class, "set", TestArrange6DeleteHeader.class),
        allowsNone());
  }
}
