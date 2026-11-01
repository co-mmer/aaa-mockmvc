package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RequestPathDescriptionTest {

  private static final String ANY_PATH = "/customers/42";

  private RequestPath path;

  @BeforeEach
  void setUp() {
    this.path = new RequestPath();
  }

  @Nested
  class DescribePath {

    @Test
    void GIVEN_path_WHEN_describe_THEN_return_path_description() {
      // Arrange
      path.setValue(ANY_PATH);

      // Act
      var result = RequestPathDescription.describe(path);

      // Assert
      assertThat(result, is("Path: " + ANY_PATH));
    }

    @Test
    void GIVEN_null_path_WHEN_describe_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> RequestPathDescription.describe(null));

      // Assert
      assertThat(ex.getMessage(), is("Request path must be set"));
    }

    @Test
    void GIVEN_unset_path_WHEN_describe_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> RequestPathDescription.describe(path));

      // Assert
      assertThat(ex.getMessage(), is("Request path must be set"));
    }
  }
}
