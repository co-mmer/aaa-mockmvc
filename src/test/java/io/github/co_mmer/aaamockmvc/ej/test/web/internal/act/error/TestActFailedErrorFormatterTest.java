package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestArrange.ARRANGE_POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestStepMetadata;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestActFailedErrorFormatterTest {

  private static final TestStepMetadata STEP_WITH_NAME = new TestStepMetadata("MyStep");
  private static final TestStepMetadata STEP_BLANK_NAME = new TestStepMetadata("");

  @ParameterizedTest
  @MethodSource("messageCases")
  void GIVEN_variousSteps_WHEN_buildMessage_THEN_expectedMessage(
      TestStepMetadata step, Throwable cause, String expected) {

    // Act
    var msg = TestActFailedErrorFormatter.createMessage(step, ARRANGE_POST, cause);

    // Assert
    assertThat(msg, is(expected));
  }

  private static Stream<Arguments> messageCases() {
    return Stream.of(
        Arguments.of(
            STEP_WITH_NAME,
            new IllegalStateException("boom"),
            """
                Step 'MyStep'
                ACT failed: POST /test
                Request: POST /test | query: q=search&page=2
                Headers: accepts=application/json, application/xml | content-type=application/json | key-value={key1=[value1]}
                Body: 0 bytes | content-type=<none>
                Cause: IllegalStateException: boom
                """),
        Arguments.of(
            STEP_BLANK_NAME,
            new Exception(""),
            """
                Step '<unnamed step>'
                ACT failed: POST /test
                Request: POST /test | query: q=search&page=2
                Headers: accepts=application/json, application/xml | content-type=application/json | key-value={key1=[value1]}
                Body: 0 bytes | content-type=<none>
                Cause: Exception: <no message>
                """),
        Arguments.of(
            null,
            new Exception(""),
            """
                ACT failed: POST /test
                Request: POST /test | query: q=search&page=2
                Headers: accepts=application/json, application/xml | content-type=application/json | key-value={key1=[value1]}
                Body: 0 bytes | content-type=<none>
                Cause: Exception: <no message>
                """));
  }
}
