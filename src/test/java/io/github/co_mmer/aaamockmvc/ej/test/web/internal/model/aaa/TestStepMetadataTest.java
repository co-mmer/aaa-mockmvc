package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class TestStepMetadataTest {

  private static final String UPDATE_USER = "update user";

  @Test
  void GIVEN_name_WHEN_name_THEN_return_name() {
    // Arrange
    var metadata = new TestStepDto(UPDATE_USER);

    // Act
    var stepName = metadata.name();

    // Assert
    assertThat(stepName, is(UPDATE_USER));
  }
}
