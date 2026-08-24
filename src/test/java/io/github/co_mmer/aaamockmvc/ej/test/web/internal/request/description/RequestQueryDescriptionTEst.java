package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestQuery;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RequestQueryDescriptionTest {

  private static final String ANY_PARAMETER_NAME = "parameter";
  private static final String ANY_VALUE = "value";
  private static final String ANOTHER_VALUE = "another value";

  private RequestQuery query;

  @BeforeEach
  void setUp() {
    this.query = new RequestQuery();
  }

  @Nested
  class DescribeQuery {

    @Test
    void GIVEN_empty_query_WHEN_describe_THEN_return_empty_description() {
      // Act
      var result = RequestQueryDescription.describe(query);

      // Assert
      assertThat(result, is(StringUtils.EMPTY));
    }

    @Test
    void GIVEN_parameter_WHEN_describe_THEN_return_query_description() {
      // Arrange
      query.add(ANY_PARAMETER_NAME, ANY_VALUE);

      // Act
      var result = RequestQueryDescription.describe(query);

      // Assert
      assertThat(result, is("Query: {%s=[%s]}".formatted(ANY_PARAMETER_NAME, ANY_VALUE)));
    }

    @Test
    void GIVEN_parameter_with_multiple_values_WHEN_describe_THEN_return_all_values() {
      // Arrange
      query.add(ANY_PARAMETER_NAME, ANY_VALUE);
      query.add(ANY_PARAMETER_NAME, ANOTHER_VALUE);

      // Act
      var result = RequestQueryDescription.describe(query);

      // Assert
      assertThat(
          result,
          is("Query: {%s=[%s, %s]}".formatted(ANY_PARAMETER_NAME, ANY_VALUE, ANOTHER_VALUE)));
    }
  }
}
