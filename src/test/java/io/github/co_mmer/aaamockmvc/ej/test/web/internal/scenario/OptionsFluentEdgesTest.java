package io.github.co_mmer.aaamockmvc.ej.test.web.internal.scenario;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsExactly;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.allowsNone;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentMatchers.transition;

import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange1OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange3OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange5OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.header.TestArrange6OptionsHeader;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange1OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange2OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.arrange.methods.options.url.TestArrange3OptionsUrl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.fluent.FluentAssert;
import org.junit.jupiter.api.Test;

class OptionsFluentEdgesTest {

  @Test
  void url_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1OptionsUrl.class, "query", TestArrange2OptionsUrl.class),
        allowsExactly("query", "headers"));

    FluentAssert.assertThat(
        transition(TestArrange1OptionsUrl.class, "query", TestArrange3OptionsUrl.class),
        allowsExactly("headers"));

    FluentAssert.assertThat(
        transition(TestArrange1OptionsUrl.class, "headers", TestArrange1OptionsHeader.class),
        allowsExactly("auth", "add", "set"));
  }

  @Test
  void header_transitions() {
    FluentAssert.assertThat(
        transition(TestArrange1OptionsHeader.class, "auth", TestArrange3OptionsHeader.class),
        allowsExactly("add", "set"));

    FluentAssert.assertThat(
        transition(TestArrange1OptionsHeader.class, "add", TestArrange5OptionsHeader.class),
        allowsExactly("add"));

    FluentAssert.assertThat(
        transition(TestArrange1OptionsHeader.class, "set", TestArrange6OptionsHeader.class),
        allowsNone());
  }
}
