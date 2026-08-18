package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDescription;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TestStepDescriptionTest {

  @Test
  void GIVEN_null_step_WHEN_describe_THEN_return_empty_description() {
    // Act
    var result = TestStepDescription.describe(null);

    // Assert
    assertThat(result, is(StringUtils.EMPTY));
  }

  @Test
  void GIVEN_step_with_name_WHEN_describe_THEN_return_step_description() {
    // Arrange
    var step = new TestStepDto("Create customer");

    // Act
    var result = TestStepDescription.describe(step);

    // Assert
    assertThat(result, is("Step 'Create customer'"));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\t", "\n"})
  void GIVEN_step_with_blank_name_WHEN_describe_THEN_return_empty_description(String name) {
    // Arrange
    var step = new TestStepDto(name);

    // Act
    var result = TestStepDescription.describe(step);

    // Assert
    assertThat(result, is(StringUtils.EMPTY));
  }
}
