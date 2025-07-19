package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class TestStepMetadataTest {

  public static final String UPDATE_USER = "update user";

  @ParameterizedTest
  @NullAndEmptySource
  void GIVEN_blank_WHEN_name_THEN_default_name(String name) {
    // Arrange
    var metadata = new TestStepMetadata(name);

    // Act
    var stepName = metadata.name();

    // Assert
    assertThat(stepName, is("<unnamed step>"));
  }

  @Test
  void GIVEN_name_WHEN_name_THEN_name() {
    // Arrange
    var metadata = new TestStepMetadata(UPDATE_USER);

    // Act
    var stepName = metadata.name();

    // Assert
    assertThat(stepName, is(UPDATE_USER));
  }
}
