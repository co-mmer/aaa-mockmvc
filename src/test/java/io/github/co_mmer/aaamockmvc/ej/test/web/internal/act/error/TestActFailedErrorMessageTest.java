package io.github.co_mmer.aaamockmvc.ej.test.web.internal.act.error;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestArrange.ARRANGE_POST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class TestActFailedErrorMessageTest {

  @Test
  void WHEN_buildWithMessage_THEN_formats_full_error_line() {
    // Arrange
    var cause = new IllegalStateException("boom");

    // Act
    var msg = TestActFailedErrorMessage.build(ARRANGE_POST, cause);

    // Assert
    var expected =
        "ACT failed: "
            + ARRANGE_POST.getUrl().getMethod()
            + " "
            + ARRANGE_POST.getUrl().getUri()
            + "\n"
            + ARRANGE_POST.asMessage()
            + "\n"
            + "Cause: "
            + cause.getClass().getSimpleName()
            + ": boom";

    assertThat(msg, is(expected));
  }

  @Test
  void WHEN_buildWithBlankMessage_THEN_usesPlaceholder() {
    // Act
    var msg = TestActFailedErrorMessage.build(ARRANGE_POST, new Exception(""));

    // Assert
    var expected =
        "ACT failed: "
            + ARRANGE_POST.getUrl().getMethod()
            + " "
            + ARRANGE_POST.getUrl().getUri()
            + "\n"
            + ARRANGE_POST.asMessage()
            + "\n"
            + "Cause: Exception: <no message>";

    assertThat(msg, is(expected));
  }
}
