package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.MediaType;

class RequestHeadersTest {

  private static final String ANY_HEADER_NAME = "X-Test-Header";

  private static final String ANOTHER_HEADER_NAME = "X-Another-Header";

  private static final String ANY_VALUE = "value";
  private static final String ANOTHER_VALUE = "another value";
  private static final String ANY_TOKEN = "Bearer token";

  private RequestHeaders headers;

  @BeforeEach
  void setUp() {
    this.headers = new RequestHeaders();
  }

  @Nested
  class SetHeaders {

    @Test
    void GIVEN_valid_headers_WHEN_set_THEN_store_string_values() {
      // Arrange
      var values = List.<Object>of(ANY_VALUE, 42, true);
      var source = Map.of(ANY_HEADER_NAME, values);

      // Act
      headers.set(source);

      // Assert
      var expected = Map.of(ANY_HEADER_NAME, List.of(ANY_VALUE, "42", "true"));
      assertThat(headers.values(), is(expected));
    }

    @Test
    void GIVEN_empty_headers_WHEN_set_THEN_remove_existing_headers() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);

      // Act
      headers.set(Map.of());

      // Assert
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_null_headers_WHEN_set_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(null));

      // Assert
      assertThat(ex.getMessage(), is("Headers must not be null"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_null_header_name_WHEN_set_THEN_return_meaningful_message() {
      // Arrange
      var source = new LinkedHashMap<String, List<Object>>();
      source.put(null, List.of(ANY_VALUE));

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(ex.getMessage(), is("Header name must not be null"));
      assertThat(headers.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void GIVEN_blank_header_name_WHEN_set_THEN_return_meaningful_message(String name) {
      // Arrange
      var source = Map.of(name, List.<Object>of(ANY_VALUE));

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(ex.getMessage(), is("Header name must not be blank"));
      assertThat(headers.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Invalid Header", "Header:", "Header/Name", "Header()"})
    void GIVEN_invalid_header_name_WHEN_set_THEN_return_meaningful_message(String name) {
      // Arrange
      var source = Map.of(name, List.<Object>of(ANY_VALUE));

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(
          ex.getMessage(), is("Header name '%s' contains invalid characters".formatted(name)));

      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_null_header_values_WHEN_set_THEN_return_meaningful_message() {
      // Arrange
      var source = new LinkedHashMap<String, List<Object>>();

      source.put(ANY_HEADER_NAME, null);

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(ex.getMessage(), is("Header 'X-Test-Header' must contain at least one value"));

      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_empty_header_values_WHEN_set_THEN_return_meaningful_message() {
      // Arrange
      var source = Map.of(ANY_HEADER_NAME, List.of());

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(ex.getMessage(), is("Header 'X-Test-Header' must contain at least one value"));

      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_null_header_value_WHEN_set_THEN_return_position() {
      // Arrange
      var values = new ArrayList<>();
      values.add(ANY_VALUE);
      values.add(null);

      var source = Map.of(ANY_HEADER_NAME, values);

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(
          ex.getMessage(), is("Header 'X-Test-Header' value at position 2 must not be null"));

      assertThat(headers.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"first line\rsecond line", "first line\nsecond line"})
    void GIVEN_header_value_with_line_break_WHEN_set_THEN_reject_value(String value) {
      // Arrange
      var source = Map.of(ANY_HEADER_NAME, List.<Object>of(value));

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Header 'X-Test-Header' value must not contain carriage return "
                  + "or line feed characters"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_invalid_headers_WHEN_set_THEN_leave_headers_unchanged() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);

      var source = new LinkedHashMap<String, List<Object>>();
      source.put(ANOTHER_HEADER_NAME, List.of(ANOTHER_VALUE));
      source.put(StringUtils.EMPTY, List.of(ANOTHER_VALUE));

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(ex.getMessage(), is("Header name must not be blank"));
      assertThat(headers.values(), is(Map.of(ANY_HEADER_NAME, List.of(ANY_VALUE))));
    }

    @Test
    void GIVEN_duplicate_header_names_WHEN_set_THEN_return_meaningful_message() {
      // Arrange
      var source = new LinkedHashMap<String, List<Object>>();
      source.put(ANY_HEADER_NAME, List.of(ANY_VALUE));
      source.put(ANY_HEADER_NAME.toLowerCase(), List.of(ANOTHER_VALUE));

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.set(source));

      // Assert
      assertThat(
          ex.getMessage(),
          is("Header 'x-test-header' is defined more than once using different casing"));

      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_source_headers_WHEN_set_THEN_protect_headers_from_source_changes() {
      // Arrange
      var sourceValues = new ArrayList<>();
      sourceValues.add(ANY_VALUE);

      var source = new LinkedHashMap<String, List<Object>>();

      source.put(ANY_HEADER_NAME, sourceValues);

      // Act
      headers.set(source);
      sourceValues.add(ANOTHER_VALUE);
      source.clear();

      // Assert
      var expected = Map.of(ANY_HEADER_NAME, List.of(ANY_VALUE));
      assertThat(headers.values(), is(expected));
    }
  }

  @Nested
  class AddHeader {

    @Test
    void GIVEN_valid_header_WHEN_add_THEN_store_header() {
      // Act
      headers.add(ANY_HEADER_NAME, ANY_VALUE);

      // Assert
      var expected = Map.of(ANY_HEADER_NAME, List.of(ANY_VALUE));
      assertThat(headers.values(), is(expected));
    }

    @Test
    void GIVEN_existing_header_WHEN_add_THEN_append_value() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);

      // Act
      headers.add(ANY_HEADER_NAME, ANOTHER_VALUE);

      // Assert
      var expected = Map.of(ANY_HEADER_NAME, List.of(ANY_VALUE, ANOTHER_VALUE));
      assertThat(headers.values(), is(expected));
    }

    @Test
    void GIVEN_existing_header_with_different_case_WHEN_add_THEN_append_value() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);

      // Act
      headers.add(ANY_HEADER_NAME.toLowerCase(), ANOTHER_VALUE);

      // Assert
      assertThat(headers.values().size(), is(1));
      assertThat(
          headers.values().get(ANY_HEADER_NAME.toUpperCase()),
          is(List.of(ANY_VALUE, ANOTHER_VALUE)));
    }

    @Test
    void GIVEN_header_created_by_set_WHEN_add_THEN_append_value() {
      // Arrange
      headers.set(Map.of(ANY_HEADER_NAME, List.<Object>of(ANY_VALUE)));

      // Act
      headers.add(ANY_HEADER_NAME, ANOTHER_VALUE);

      // Assert
      var expected = Map.of(ANY_HEADER_NAME, List.of(ANY_VALUE, ANOTHER_VALUE));
      assertThat(headers.values(), is(expected));
    }

    @Test
    void GIVEN_empty_header_value_WHEN_add_THEN_store_header() {
      // Act
      headers.add(ANY_HEADER_NAME, StringUtils.EMPTY);

      // Assert
      var expected = Map.of(ANY_HEADER_NAME, List.of(StringUtils.EMPTY));
      assertThat(headers.values(), is(expected));
    }

    @Test
    void GIVEN_null_header_name_WHEN_add_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.add(null, ANY_VALUE));

      // Assert
      assertThat(ex.getMessage(), is("Header name must not be null"));
      assertThat(headers.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void GIVEN_blank_header_name_WHEN_add_THEN_return_meaningful_message(String name) {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.add(name, ANY_VALUE));

      // Assert
      assertThat(ex.getMessage(), is("Header name must not be blank"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_invalid_header_name_WHEN_add_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class, () -> headers.add("Invalid Header", ANY_VALUE));

      // Assert
      assertThat(ex.getMessage(), is("Header name 'Invalid Header' contains invalid characters"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_null_header_value_WHEN_add_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> headers.add(ANY_HEADER_NAME, null));

      // Assert
      assertThat(ex.getMessage(), is("Header 'X-Test-Header' must not have a null value"));
      assertThat(headers.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"first line\rsecond line", "first line\nsecond line"})
    void GIVEN_header_value_with_line_break_WHEN_add_THEN_reject_value(String value) {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> headers.add(ANY_HEADER_NAME, value));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Header 'X-Test-Header' value must not contain carriage return "
                  + "or line feed characters"));
      assertThat(headers.values(), is(Map.of()));
    }
  }

  @Nested
  class SetAuthorization {

    @Test
    void GIVEN_valid_token_WHEN_auth_THEN_store_authorization_and_return_headers() {
      // Act
      var result = headers.auth(ANY_TOKEN);

      // Assert
      assertThat(result, is(sameInstance(headers)));
      assertThat(headers.values(), is(Map.of(AUTHORIZATION, List.of(ANY_TOKEN))));
    }

    @Test
    void GIVEN_existing_authorization_WHEN_auth_THEN_replace_token() {
      // Arrange
      headers.auth(ANY_TOKEN);

      // Act
      headers.auth(ANOTHER_VALUE);

      // Assert
      assertThat(headers.values(), is(Map.of(AUTHORIZATION, List.of(ANOTHER_VALUE))));
    }

    @Test
    void GIVEN_null_token_WHEN_auth_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.auth(null));

      // Assert
      assertThat(ex.getMessage(), is("Authorization token must not be null"));
      assertThat(headers.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void GIVEN_blank_token_WHEN_auth_THEN_return_meaningful_message(String token) {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.auth(token));

      // Assert
      assertThat(ex.getMessage(), is("Authorization token must not be blank"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_token_with_line_break_WHEN_auth_THEN_reject_token() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class, () -> headers.auth("Bearer token\nInjected"));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Header 'Authorization' value must not contain carriage return "
                  + "or line feed characters"));

      assertThat(headers.values(), is(Map.of()));
    }
  }

  @Nested
  class SetAcceptedMediaTypes {

    @Test
    void GIVEN_media_types_WHEN_accept_THEN_store_accept_header_and_return_headers() {
      // Act
      var result = headers.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN);

      // Assert
      assertThat(result, is(sameInstance(headers)));

      var expected =
          Map.of(
              ACCEPT,
              List.of(MediaType.APPLICATION_JSON.toString(), MediaType.TEXT_PLAIN.toString()));

      assertThat(headers.values(), is(expected));
    }

    @Test
    void GIVEN_null_media_types_WHEN_accept_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> headers.accept((MediaType[]) null));

      // Assert
      assertThat(ex.getMessage(), is("Accepted media types must not be null"));

      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_no_media_types_WHEN_accept_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.accept());

      // Assert
      assertThat(ex.getMessage(), is("Accept header must contain at least one media type"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_null_media_type_WHEN_accept_THEN_return_position() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> headers.accept(MediaType.APPLICATION_JSON, null));

      // Assert
      assertThat(ex.getMessage(), is("Accepted media type at position 2 must not be null"));
      assertThat(headers.values(), is(Map.of()));
    }
  }

  @Nested
  class SetContentType {

    @Test
    void GIVEN_media_type_WHEN_contentType_THEN_store_header_and_return_headers() {
      // Act
      var result = headers.contentType(MediaType.APPLICATION_JSON);

      // Assert
      assertThat(result, is(sameInstance(headers)));

      assertThat(
          headers.values(),
          is(Map.of(CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON.toString()))));
    }

    @Test
    void GIVEN_null_media_type_WHEN_contentType_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> headers.contentType((MediaType) null));

      // Assert
      assertThat(ex.getMessage(), is("Content type must not be null"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_valid_media_type_string_WHEN_contentType_THEN_store_header() {
      // Act
      headers.contentType(MediaType.APPLICATION_JSON_VALUE);

      // Assert
      assertThat(
          headers.values(), is(Map.of(CONTENT_TYPE, List.of(MediaType.APPLICATION_JSON_VALUE))));
    }

    @Test
    void GIVEN_null_media_type_string_WHEN_contentType_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> headers.contentType((String) null));

      // Assert
      assertThat(ex.getMessage(), is("Content type must not be null"));
      assertThat(headers.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void GIVEN_blank_media_type_WHEN_contentType_THEN_return_meaningful_message(String value) {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.contentType(value));

      // Assert
      assertThat(ex.getMessage(), is("Content type must not be blank"));
      assertThat(headers.values(), is(Map.of()));
    }

    @Test
    void GIVEN_invalid_media_type_WHEN_contentType_THEN_return_meaningful_message() {
      // Arrange
      var value = "application-json";

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> headers.contentType(value));

      // Assert
      assertThat(
          ex.getMessage(), is("Content type '%s' is not a valid media type".formatted(value)));

      assertThat(headers.values(), is(Map.of()));
    }
  }

  @Nested
  class ReadValues {

    @Test
    void GIVEN_new_headers_WHEN_values_THEN_return_empty_values() {
      // Act
      var result = headers.values();

      // Assert
      assertThat(result, is(Map.of()));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_header_values_WHEN_modify_externally_THEN_protect_headers() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);
      var values = headers.values();

      // Act & Assert
      assertThrows(
          UnsupportedOperationException.class,
          () -> values.put(ANOTHER_HEADER_NAME, List.of(ANOTHER_VALUE)));

      assertThrows(
          UnsupportedOperationException.class,
          () -> values.get(ANY_HEADER_NAME).add(ANOTHER_VALUE));
    }

    @Test
    void GIVEN_returned_header_values_WHEN_headers_change_THEN_keep_snapshot_unchanged() {
      // Arrange
      headers.add(ANY_HEADER_NAME, ANY_VALUE);
      var snapshot = headers.values();

      // Act
      headers.add(ANY_HEADER_NAME, ANOTHER_VALUE);

      // Assert
      var expected = Map.of(ANY_HEADER_NAME, List.of(ANY_VALUE));
      assertThat(snapshot, is(expected));
    }
  }
}
