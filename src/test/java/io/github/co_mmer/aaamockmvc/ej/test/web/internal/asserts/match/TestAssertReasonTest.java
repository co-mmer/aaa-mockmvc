package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestAssertReasonTest {

  @ParameterizedTest
  @MethodSource("reasonOfCases")
  void GIVEN_reasonOfCases_WHEN_reasonOf_THEN_return_expected_name(
      TestStepDto step, String expected) {

    // Act
    var reason = TestAssertReason.reasonOf(step);

    // Assert
    assertThat(reason, is(expected));
  }

  private static Stream<Arguments> reasonOfCases() {
    return Stream.of(
        Arguments.of(null, ""),
        Arguments.of(new TestStepDto(""), "Step '<unset>'"),
        Arguments.of(new TestStepDto("   "), "Step '<unset>'"),
        Arguments.of(new TestStepDto("Create User"), "Step 'Create User'"));
  }

  @Test
  void GIVEN_name_what_WHEN_reasonOf_THEN_return_name() {
    // Act
    var reason = TestAssertReason.reasonOf(STEP, "ups");

    // Assert
    assertThat(reason, is("Step 'Create User' ⇒ ups"));
  }

  @ParameterizedTest
  @MethodSource("reasonOfWhoWithWhatCases")
  void GIVEN_step_who_with_what_WHEN_reasonOf_THEN_return_expected_message(
      TestStepDto step, String who, String with, String what, String expected) {

    // Act
    var reason = TestAssertReason.reasonContentOf(step, who, with, what);

    // Assert
    assertThat(reason, is(expected));
  }

  private static Stream<Arguments> reasonOfWhoWithWhatCases() {
    return Stream.of(
        Arguments.of(
            STEP,
            "content().asBoolean()",
            "{\"id\":1,\"name\":\"A\"}",
            "cannot be mapped to Boolean",
            "Step 'Create User' ⇒ assertion 'content().asBoolean()' failed: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to Boolean"),
        Arguments.of(
            STEP,
            null,
            "{\"id\":1}",
            "cannot be mapped to Boolean",
            "Step 'Create User' ⇒ Response body '{\"id\":1}' cannot be mapped to Boolean"),
        Arguments.of(
            STEP,
            "content().asBoolean()",
            null,
            "cannot be mapped to Boolean",
            "Step 'Create User' ⇒ assertion 'content().asBoolean()' failed cannot be mapped to Boolean"),
        Arguments.of(
            null,
            "content().asBoolean()",
            "raw",
            "is invalid",
            "assertion 'content().asBoolean()' failed: Response body 'raw' is invalid"));
  }
}
