package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.description;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestQuery;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.TextBody;
import org.junit.jupiter.api.Test;

class RequestDescriptionTest {

  private static final String ANY_PATH = "/customers/42";
  private static final String ANY_PARAMETER_NAME = "active";
  private static final String ANY_PARAMETER_VALUE = "true";
  private static final String ANY_HEADER_NAME = "X-Test-Header";
  private static final String ANY_HEADER_VALUE = "value";
  private static final String ANY_BODY = "request body";

  private RequestPath getRequestPath() {
    var path = new RequestPath();
    path.setValue(ANY_PATH);
    return path;
  }

  @Test
  void GIVEN_request_without_optional_components_WHEN_describe_THEN_omit_empty_descriptions() {
    // Arrange
    var request =
        new Request(
            HttpMethod.GET,
            getRequestPath(),
            new RequestQuery(),
            new RequestHeaders(),
            new EmptyBody());

    // Act
    var result = RequestDescription.describe(request);

    // Assert
    var expected = String.join(System.lineSeparator(), "Request: GET", "Path: " + ANY_PATH);
    assertThat(result, is(expected));
  }

  @Test
  void GIVEN_request_with_all_components_WHEN_describe_THEN_return_complete_description() {
    // Arrange
    var query = new RequestQuery();
    var headers = new RequestHeaders();

    query.add(ANY_PARAMETER_NAME, ANY_PARAMETER_VALUE);
    headers.add(ANY_HEADER_NAME, ANY_HEADER_VALUE);

    var request =
        new Request(HttpMethod.POST, getRequestPath(), query, headers, new TextBody(ANY_BODY));

    // Act
    var result = RequestDescription.describe(request);

    // Assert
    var expected =
        String.join(
            System.lineSeparator(),
            "Request: POST",
            "Path: " + ANY_PATH,
            "Query: {%s=[%s]}".formatted(ANY_PARAMETER_NAME, ANY_PARAMETER_VALUE),
            "Headers: {%s=[%s]}".formatted(ANY_HEADER_NAME, ANY_HEADER_VALUE),
            "Body: text=" + ANY_BODY);

    assertThat(result, is(expected));
  }

  @Test
  void GIVEN_null_optional_components_WHEN_describe_THEN_omit_components() {
    // Arrange
    var request = new Request(HttpMethod.GET, getRequestPath(), null, null, null);

    // Act
    var result = RequestDescription.describe(request);

    // Assert
    var expected = String.join(System.lineSeparator(), "Request: GET", "Path: " + ANY_PATH);
    assertThat(result, is(expected));
  }
}
