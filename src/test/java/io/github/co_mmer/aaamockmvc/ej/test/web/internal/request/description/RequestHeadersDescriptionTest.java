package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RequestHeadersDescriptionTest {

  private static final String ANY_HEADER_NAME = "X-Test-Header";
  private static final String ANY_VALUE = "value";
  private static final String ANOTHER_VALUE = "another value";

  private RequestHeaders headers;

  @BeforeEach
  void setUp() {
    this.headers = new RequestHeaders();
  }

  @Nested
  class DescribeHeaders {

    @Test
    void GIVEN_empty_headers_WHEN_describe_THEN_return_empty_description() {
      // Act
      var result = RequestHeadersDescription.describe(headers);

      // Assert
      assertThat(result, is(StringUtils.EMPTY));
    }

    @Test
    void GIVEN_header_WHEN_describe_THEN_return_header_description() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);

      // Act
      var result = RequestHeadersDescription.describe(headers);

      // Assert
      assertThat(result, is("Headers: {%s=[%s]}".formatted(ANY_HEADER_NAME, ANY_VALUE)));
    }

    @Test
    void GIVEN_header_with_multiple_values_WHEN_describe_THEN_return_all_values() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);
      headers.add(ANY_HEADER_NAME, ANOTHER_VALUE);

      // Act
      var result = RequestHeadersDescription.describe(headers);

      // Assert
      assertThat(
          result,
          is("Headers: {%s=[%s, %s]}".formatted(ANY_HEADER_NAME, ANY_VALUE, ANOTHER_VALUE)));
    }
  }
}
