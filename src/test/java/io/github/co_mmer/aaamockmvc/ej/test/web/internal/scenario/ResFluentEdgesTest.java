package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsExactly;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsNone;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.transition;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange1ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange2ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.body.TestArrange3ResBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange1ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange2ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange3ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange4ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange5ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.header.TestArrange6ResHead;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange1ResUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange2ResUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.res.url.TestArrange3ResUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentAssert;
import org.junit.jupiter.api.Test;

class ResFluentEdgesTest {

  @Test
  void url_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1ResUrl.class, "query", TestArrange2ResUrl.class),
        allowsExactly("query", "headers", "body"));

    FluentAssert.assertThat(
        transition(TestArrange1ResUrl.class, "query", TestArrange3ResUrl.class),
        allowsExactly("headers", "body"));

    FluentAssert.assertThat(
        transition(TestArrange1ResUrl.class, "headers", TestArrange1ResHead.class),
        allowsExactly("accept", "auth", "contentType", "add", "set"));

    FluentAssert.assertThat(
        transition(TestArrange1ResUrl.class, "body", TestArrange1ResBody.class),
        allowsExactly("raw", "json", "json", "file", "files"));
  }

  @Test
  void header_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1ResHead.class, "accept", TestArrange2ResHead.class),
        allowsExactly("accept", "auth", "contentType", "add", "set", "body"));

    FluentAssert.assertThat(
        transition(TestArrange1ResHead.class, "auth", TestArrange3ResHead.class),
        allowsExactly("add", "contentType", "set", "body"));

    FluentAssert.assertThat(
        transition(TestArrange1ResHead.class, "contentType", TestArrange4ResHead.class),
        allowsExactly("add", "set", "body"));

    FluentAssert.assertThat(
        transition(TestArrange1ResHead.class, "add", TestArrange5ResHead.class),
        allowsExactly("add", "body"));

    FluentAssert.assertThat(
        transition(TestArrange1ResHead.class, "set", TestArrange6ResHead.class),
        allowsExactly("body"));
  }

  @Test
  void body_transitions() {
    FluentAssert.assertThat(transition(TestArrange1ResBody.class, "raw", void.class), allowsNone());

    FluentAssert.assertThat(
        transition(TestArrange1ResBody.class, "json", void.class), allowsNone());

    FluentAssert.assertThat(
        transition(TestArrange1ResBody.class, "file", TestArrange2ResBody.class),
        allowsExactly("file", "files"));

    FluentAssert.assertThat(
        transition(TestArrange1ResBody.class, "files", TestArrange3ResBody.class),
        allowsExactly("files"));
  }
}
