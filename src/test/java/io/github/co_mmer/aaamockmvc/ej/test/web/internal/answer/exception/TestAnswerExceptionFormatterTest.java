package io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer.exception;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.answer.TestAnswerExceptionFormatter.createMessage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepMetadata;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestAnswerExceptionFormatterTest {

  private static final String CONTENT = "{\"id\":1}";
  private static final String CONTENT_MAP = "{\"id\":1,\"name\":\"A\"}";
  private static final String ANSWER_STEP_NAME = "asExample";
  private static final TestStepMetadata STEP_WITH_NAME = new TestStepMetadata("MyStep");
  private static final TestStepMetadata STEP_BLANK_NAME = new TestStepMetadata("");

  @ParameterizedTest
  @MethodSource("messageCases")
  void GIVEN_variousSteps_WHEN_buildMessage_THEN_expectedMessage(
      TestStepMetadata step, String content, String expected) {

    // Act
    var msg = createMessage(step, ANSWER_STEP_NAME, content, "Number");

    // Assert
    assertThat(msg, is(expected));
  }

  private static Stream<Arguments> messageCases() {
    return Stream.of(
        Arguments.of(
            STEP_WITH_NAME,
            CONTENT,
            """
                Step 'MyStep'
                Answer step: asExample({"id":1})
                Failure: unable to map response body to 'Number'.
                """),
        Arguments.of(
            STEP_BLANK_NAME,
            "{}",
            """
                Step '<unnamed step>'
                Answer step: asExample({})
                Failure: unable to map response body to 'Number'.
                """),
        Arguments.of(
            null,
            CONTENT,
            """
                Answer step: asExample({"id":1})
                Failure: unable to map response body to 'Number'.
                """));
  }

  @ParameterizedTest
  @MethodSource("messageCasesMap")
  void GIVEN_variousSteps_WHEN_buildMapMessage_THEN_expectedMessage(
      TestStepMetadata step, String content, String expected) {

    // Act
    var msg = createMessage(step, ANSWER_STEP_NAME, content, "String", "User");

    // Assert
    assertThat(msg, is(expected));
  }

  private static Stream<Arguments> messageCasesMap() {
    return Stream.of(
        Arguments.of(
            STEP_WITH_NAME,
            CONTENT_MAP,
            """
                Step 'MyStep'
                Answer step: asExample({"id":1,"name":"A"})
                Failure: unable to map response body to 'Map<String, User>'.
                """),
        Arguments.of(
            STEP_BLANK_NAME,
            "{}",
            """
                Step '<unnamed step>'
                Answer step: asExample({})
                Failure: unable to map response body to 'Map<String, User>'.
                """),
        Arguments.of(
            null,
            CONTENT_MAP,
            """
                Answer step: asExample({"id":1,"name":"A"})
                Failure: unable to map response body to 'Map<String, User>'.
                """));
  }
}
