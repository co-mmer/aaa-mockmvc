package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_QUERY1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_QUERY_EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestDataRequestUrlDto.TEST_REQUEST_URL_QUERY_NULL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import java.net.URI;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;

class TestRequestUrlDtoTest {

  @ParameterizedTest
  @MethodSource("useCaseQuery")
  void GIVEN_useCaseQuery_WHEN_containsHeadKeyValue_THEN_return_expected_boolean(
      TestRequestUrlDto url, boolean expectedBoolean) {

    // Act
    var result = url.isNotEmptyQuery();

    // Assert
    assertThat(result, is(expectedBoolean));
  }

  private static Stream<Arguments> useCaseQuery() {
    return Stream.of(
        Arguments.of(TEST_REQUEST_URL_QUERY_NULL, false),
        Arguments.of(TEST_REQUEST_URL_QUERY_EMPTY, false),
        Arguments.of(TEST_REQUEST_URL_QUERY1, true));
  }

  @Nested
  class asMessage {

    @Test
    void GIVEN_no_method_and_no_uri_and_no_query_WHEN_asMessage_THEN_return_placeholders() {
      // Arrange
      var dto = new TestRequestUrlDto();

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Request: <no method> <no uri>"));
    }

    @Test
    void GIVEN_only_method_WHEN_asMessage_THEN_contains_method_and_placeholder_uri() {
      // Arrange
      var dto = new TestRequestUrlDto();
      dto.setMethod(HttpMethod.POST);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Request: POST <no uri>"));
    }

    @Test
    @SneakyThrows
    void GIVEN_only_uri_WHEN_asMessage_THEN_contains_uri_and_placeholder_method() {
      // Arrange
      var dto = new TestRequestUrlDto();
      dto.setUri(new URI("http://localhost/api"));

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Request: <no method> http://localhost/api"));
    }

    @Test
    @SneakyThrows
    void GIVEN_method_and_uri_without_query_WHEN_asMessage_THEN_return_expected_string() {
      // Arrange
      var dto = new TestRequestUrlDto();
      dto.setMethod(HttpMethod.GET);
      dto.setUri(new URI("http://localhost/test"));

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Request: GET http://localhost/test"));
    }

    @Test
    @SneakyThrows
    void GIVEN_method_uri_and_query_WHEN_asMessage_THEN_include_query_in_message() {
      // Arrange
      var dto = new TestRequestUrlDto();
      dto.setMethod(HttpMethod.GET);
      dto.setUri(new URI("http://localhost/test"));
      dto.getQuery().put("a", "1");
      dto.getQuery().put("b", "xyz");

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(
          msg,
          anyOf(
              is("Request: GET http://localhost/test | query: a=1&b=xyz"),
              is("Request: GET http://localhost/test | query: b=xyz&a=1")));
    }

    @Test
    @SneakyThrows
    void GIVEN_query_with_null_value_WHEN_asMessage_THEN_include_key_and_null_value() {
      // Arrange
      var dto = new TestRequestUrlDto();
      dto.setMethod(HttpMethod.GET);
      dto.setUri(new URI("http://localhost/test"));
      dto.getQuery().put("foo", null);

      // Act
      var msg = dto.asMessage();

      // Assert
      assertThat(msg, is("Request: GET http://localhost/test | query: foo=null"));
    }
  }
}
