package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error.TestActFailedErrorFormatter.createMessage;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestArrange.ARRANGE_POST;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class TestActFailedErrorFormatterTest {

  private static final String EXPECTED =
      """
          Step 'Create User'
          ACT failed: POST /test
          Request: POST /test | query: q=search&page=2
          Headers: accepts=application/json, application/xml | content-type=application/json | key-value={key1=[value1]}
          Body: 0 bytes | content-type=<none>
          Cause: IllegalStateException: boom
          """;

  @Test
  void GIVEN_stepName_WHEN_buildMessage_THEN_expectedMessage() {
    // Act
    var msg = createMessage(STEP, ARRANGE_POST, new IllegalStateException("boom"));

    // Assert
    assertThat(msg, is(EXPECTED));
  }
}
