package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class RequestPathTest {

  private static final String ANY_PATH = "/customers/42";
  private static final URI ANY_URI = URI.create(ANY_PATH);
  private static final String ANY_PATH_TEMPLATE = "/customers/{id}";
  private static final String ANY_PATH_VARIABLE = "42";
  private static final String INVALID_PATH = "/customer path";

  private RequestPath path;

  @BeforeEach
  void setUp() {
    this.path = new RequestPath();
  }

  @Nested
  class SetStringPath {

    @Test
    void GIVEN_valid_path_WHEN_setValue_THEN_store_uri() {
      // Act
      path.setValue(ANY_PATH);

      // Assert
      assertThat(path.value(), is(ANY_URI));
    }

    @Test
    void GIVEN_null_path_WHEN_setValue_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> path.setValue((String) null));

      // Assert
      assertThat(ex.getMessage(), is("Request path must not be null"));
      assertThat(path.value(), is(nullValue()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void GIVEN_blank_path_WHEN_setValue_THEN_return_meaningful_message(String value) {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> path.setValue(value));

      // Assert
      assertThat(ex.getMessage(), is("Request path must not be blank"));
      assertThat(path.value(), is(nullValue()));
    }

    @Test
    void GIVEN_invalid_uri_WHEN_setValue_THEN_return_meaningful_message_and_keep_path() {
      // Arrange
      path.setValue(ANY_PATH);

      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> path.setValue(INVALID_PATH));

      // Assert
      assertThat(ex.getMessage(), is("Request path '/customer path' is not a valid URI"));
      assertThat(path.value(), is(ANY_URI));
    }
  }

  @Nested
  class SetUriPath {

    @Test
    void GIVEN_valid_uri_WHEN_setValue_THEN_store_uri() {
      // Act
      path.setValue(ANY_URI);

      // Assert
      assertThat(path.value(), is(ANY_URI));
    }

    @Test
    void GIVEN_null_uri_WHEN_setValue_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> path.setValue((URI) null));

      // Assert
      assertThat(ex.getMessage(), is("Request path must not be null"));
      assertThat(path.value(), is(nullValue()));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_empty_uri_WHEN_setValue_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> path.setValue(URI.create("")));

      // Assert
      assertThat(ex.getMessage(), is("Request path must not be blank"));
      assertThat(path.value(), is(nullValue()));
    }
  }

  @Nested
  class ExpandPathVariables {

    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("supportedPathVariables")
    void GIVEN_supported_path_variable_WHEN_setValue_THEN_expand_path(
        Object variable, String expectedValue) {
      // Act
      path.setValue(ANY_PATH_TEMPLATE, variable);

      // Assert
      assertThat(path.value(), is(URI.create("/customers/" + expectedValue)));
    }

    @Test
    void GIVEN_path_variable_with_spaces_WHEN_setValue_THEN_encode_path() {
      // Act
      path.setValue(ANY_PATH_TEMPLATE, "customer id");

      // Assert
      assertThat(path.value(), is(URI.create("/customers/customer%20id")));
    }

    @Test
    void GIVEN_null_path_WHEN_setValue_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class, () -> path.setValue(null, ANY_PATH_VARIABLE));

      // Assert
      assertThat(ex.getMessage(), is("Request path must not be null"));
      assertThat(path.value(), is(nullValue()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void GIVEN_blank_path_WHEN_setValue_THEN_return_meaningful_message(String value) {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class, () -> path.setValue(value, ANY_PATH_VARIABLE));

      // Assert
      assertThat(ex.getMessage(), is("Request path must not be blank"));
      assertThat(path.value(), is(nullValue()));
    }

    @Test
    void GIVEN_null_path_variables_WHEN_setValue_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> path.setValue(ANY_PATH_TEMPLATE, (Object[]) null));

      // Assert
      assertThat(ex.getMessage(), is("Path variables must not be null"));
      assertThat(path.value(), is(nullValue()));
    }

    @Test
    void GIVEN_null_path_variable_WHEN_setValue_THEN_return_position() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> path.setValue("/customers/{customerId}/orders/{orderId}", 42, null));

      // Assert
      assertThat(ex.getMessage(), is("Path variable at position 2 must not be null"));
      assertThat(path.value(), is(nullValue()));
    }

    @Test
    void GIVEN_unsupported_path_variable_WHEN_setValue_THEN_return_type_and_keep_path() {
      // Arrange
      path.setValue(ANY_PATH);
      var unsupportedVariable = new Object();

      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () ->
                  path.setValue(
                      "/customers/{customerId}/orders/{orderId}", 42, unsupportedVariable));

      // Assert
      assertThat(
          ex.getMessage(),
          is("Path variable at position 2 has unsupported type " + "'java.lang.Object'"));

      assertThat(path.value(), is(ANY_URI));
    }

    @Test
    void GIVEN_missing_path_variable_WHEN_setValue_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(
              IllegalArgumentException.class,
              () -> path.setValue(ANY_PATH_TEMPLATE, new Object[0]));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "Request path template '/customers/{id}' could not be expanded with the provided variables"));

      assertThat(path.value(), is(nullValue()));
    }

    private static Stream<Arguments> supportedPathVariables() {
      var uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

      return Stream.of(
          arguments("text", "text"),
          arguments('a', "a"),
          arguments((byte) 1, "1"),
          arguments((short) 2, "2"),
          arguments(3, "3"),
          arguments(4L, "4"),
          arguments(1.5F, "1.5"),
          arguments(2.5D, "2.5"),
          arguments(new BigInteger("12345678901234567890"), "12345678901234567890"),
          arguments(new BigDecimal("1234567890.123456789"), "1234567890.123456789"),
          arguments(true, "true"),
          arguments(uuid, uuid.toString()),
          arguments(AnyPathVariable.VALUE, "VALUE"));
    }
  }

  @Nested
  class ReadValue {

    @Test
    void GIVEN_new_path_WHEN_value_THEN_return_null() {
      // Act
      var result = path.value();

      // Assert
      assertThat(result, is(nullValue()));
    }
  }

  private enum AnyPathVariable {
    VALUE
  }
}
