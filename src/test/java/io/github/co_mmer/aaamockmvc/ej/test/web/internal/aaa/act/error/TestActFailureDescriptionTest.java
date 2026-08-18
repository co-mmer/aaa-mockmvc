package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.error;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext.mockContext;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description.RequestDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TestActFailureDescriptionTest {

  private static final String REQUEST_DESCRIPTION = "RequestDescription";
  private static final String STEP_DESCRIPTION = "StepDescription";
  private static final String CAUSE_MESSAGE = "Something went wrong";
  private static final IllegalStateException ANY_CAUSE = new IllegalStateException(CAUSE_MESSAGE);
  private static final TestStepDto STEP = new TestStepDto("any step");

  private TestAAAContext context;

  @BeforeEach
  void setUp() {
    this.context = mockContext();
  }

  @Test
  void GIVEN_step_and_cause_WHEN_describe_THEN_return_expected_description() {
    // Arrange
    var request = this.context.getArrangeBuilder().build();

    var mockedRequestDescription = Mockito.mockStatic(RequestDescription.class);
    var mockedStepDescription = Mockito.mockStatic(TestStepDescription.class);

    mockedRequestDescription
        .when(() -> RequestDescription.describe(request))
        .thenReturn(REQUEST_DESCRIPTION);

    mockedStepDescription
        .when(() -> TestStepDescription.describe(STEP))
        .thenReturn(STEP_DESCRIPTION);

    // Act
    var result = TestActFailureDescription.describe(STEP, request, ANY_CAUSE);

    // Assert
    var expected =
        """
            StepDescription
            ACT failed:
            RequestDescription
            Cause: Something went wrong""";

    assertThat(result, is(expected));
    mockedRequestDescription.close();
    mockedStepDescription.close();
  }

  @Test
  void GIVEN_no_step_WHEN_describe_THEN_omit_step_description() {
    // Arrange
    var request = this.context.getArrangeBuilder().build();
    var cause = new IllegalStateException(CAUSE_MESSAGE);

    var mockedRequestDescription = Mockito.mockStatic(RequestDescription.class);
    var mockedStepDescription = Mockito.mockStatic(TestStepDescription.class);

    mockedRequestDescription
        .when(() -> RequestDescription.describe(request))
        .thenReturn(REQUEST_DESCRIPTION);

    mockedStepDescription
        .when(() -> TestStepDescription.describe(null))
        .thenReturn(StringUtils.EMPTY);

    // Act
    var result = TestActFailureDescription.describe(null, request, cause);

    // Assert
    var expected =
        """
            ACT failed:
            RequestDescription
            Cause: Something went wrong""";

    assertThat(result, is(expected));
    mockedRequestDescription.close();
    mockedStepDescription.close();
  }

  @Test
  void GIVEN_cause_without_message_WHEN_describe_THEN_use_fallback_message() {
    // Arrange
    var request = this.context.getArrangeBuilder().build();
    var step = new TestStepDto("any step");
    var cause = new IllegalStateException();

    var mockedRequestDescription = Mockito.mockStatic(RequestDescription.class);
    var mockedStepDescription = Mockito.mockStatic(TestStepDescription.class);

    mockedRequestDescription
        .when(() -> RequestDescription.describe(request))
        .thenReturn(REQUEST_DESCRIPTION);

    mockedStepDescription
        .when(() -> TestStepDescription.describe(step))
        .thenReturn(STEP_DESCRIPTION);

    // Act
    var result = TestActFailureDescription.describe(step, request, cause);

    // Assert
    var expected =
        """
            StepDescription
            ACT failed:
            RequestDescription
            Cause: <no message>""";

    assertThat(result, is(expected));
    mockedRequestDescription.close();
    mockedStepDescription.close();
  }
}
