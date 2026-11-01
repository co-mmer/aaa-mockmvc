package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RequestQueryTest {

  private static final String ANY_PARAMETER_NAME = "parameter";
  private static final String ANOTHER_PARAMETER_NAME = "another parameter";
  private static final String ANY_VALUE = "value";
  private static final String ANOTHER_VALUE = "another value";
  private static final String FIRST_PARAMETER = "first parameter";
  private static final String SECOND_PARAMETER = "second parameter";
  private static final String FIRST_VALUE = "first value";
  private static final String SECOND_VALUE = "second value";

  private RequestQuery query;

  @BeforeEach
  void setUp() {
    this.query = new RequestQuery();
  }

  @Nested
  class AddParameter {

    @Test
    void GIVEN_valid_parameter_WHEN_add_THEN_store_parameter() {
      // Act
      query.add(ANY_PARAMETER_NAME, ANY_VALUE);

      // Assert
      var expected = Map.of(ANY_PARAMETER_NAME, List.of(ANY_VALUE));
      assertThat(query.values(), is(expected));
    }

    @Test
    void GIVEN_existing_parameter_WHEN_add_THEN_append_value() {
      // Arrange
      query.add(ANY_PARAMETER_NAME, ANY_VALUE);

      // Act
      query.add(ANY_PARAMETER_NAME, ANOTHER_VALUE);

      // Assert
      var expected = Map.of(ANY_PARAMETER_NAME, List.of(ANY_VALUE, ANOTHER_VALUE));

      assertThat(query.values(), is(expected));
    }

    @Test
    void GIVEN_null_parameter_name_WHEN_add_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> query.add(null, ANY_VALUE));

      // Assert
      assertThat(ex.getMessage(), is("Query parameter name must not be null"));

      assertThat(query.values(), is(Map.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void GIVEN_blank_parameter_name_WHEN_add_THEN_return_meaningful_message(String name) {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> query.add(name, ANY_VALUE));

      // Assert
      assertThat(ex.getMessage(), is("Query parameter name must not be blank"));

      assertThat(query.values(), is(Map.of()));
    }

    @Test
    void GIVEN_null_parameter_value_WHEN_add_THEN_return_meaningful_message() {
      // Act
      var ex =
          assertThrows(IllegalArgumentException.class, () -> query.add(ANY_PARAMETER_NAME, null));

      // Assert
      assertThat(
          ex.getMessage(),
          is("Query parameter '%s' must not have a null value".formatted(ANY_PARAMETER_NAME)));

      assertThat(query.values(), is(Map.of()));
    }

    @Test
    void GIVEN_empty_parameter_value_WHEN_add_THEN_store_parameter() {
      // Act
      query.add(ANY_PARAMETER_NAME, StringUtils.EMPTY);

      // Assert
      var expected = Map.of(ANY_PARAMETER_NAME, List.of(StringUtils.EMPTY));

      assertThat(query.values(), is(expected));
    }
  }

  @Nested
  class AddParameters {

    @Test
    void GIVEN_valid_parameters_WHEN_addAll_THEN_store_parameters() {
      // Arrange
      var parameters =
          Map.of(
              FIRST_PARAMETER, FIRST_VALUE,
              SECOND_PARAMETER, SECOND_VALUE);

      // Act
      query.addAll(parameters);

      // Assert
      var expected =
          Map.of(
              FIRST_PARAMETER, List.of(FIRST_VALUE),
              SECOND_PARAMETER, List.of(SECOND_VALUE));

      assertThat(query.values(), is(expected));
    }

    @Test
    void GIVEN_null_parameters_WHEN_addAll_THEN_return_meaningful_message() {
      // Act
      var ex = assertThrows(IllegalArgumentException.class, () -> query.addAll(null));

      // Assert
      assertThat(ex.getMessage(), is("Query parameters must not be null"));

      assertThat(query.values(), is(Map.of()));
    }

    @Test
    void GIVEN_invalid_parameters_WHEN_addAll_THEN_leave_query_unchanged() {
      // Arrange
      query.add(ANY_PARAMETER_NAME, ANY_VALUE);

      var parameters = new LinkedHashMap<String, String>();
      parameters.put(ANY_PARAMETER_NAME, ANOTHER_VALUE);
      parameters.put(StringUtils.EMPTY, ANOTHER_VALUE);

      // Act
      var exception = assertThrows(IllegalArgumentException.class, () -> query.addAll(parameters));

      // Assert
      assertThat(exception.getMessage(), is("Query parameter name must not be blank"));

      var expected = Map.of(ANY_PARAMETER_NAME, List.of(ANY_VALUE));

      assertThat(query.values(), is(expected));
    }
  }

  @Nested
  class ReadValues {

    @Test
    void GIVEN_new_query_WHEN_values_THEN_return_empty_values() {
      // Act
      var result = query.values();

      // Assert
      assertThat(result, is(Map.of()));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_query_values_WHEN_modify_externally_THEN_protect_query() {
      // Arrange
      query.add(ANY_PARAMETER_NAME, ANY_VALUE);
      var values = query.values();

      // Act & Assert
      assertThrows(
          UnsupportedOperationException.class,
          () -> values.put(ANOTHER_PARAMETER_NAME, List.of(ANOTHER_VALUE)));

      assertThrows(
          UnsupportedOperationException.class,
          () -> values.get(ANY_PARAMETER_NAME).add(ANOTHER_VALUE));
    }

    @Test
    void GIVEN_returned_query_values_WHEN_query_changes_THEN_keep_snapshot_unchanged() {
      // Arrange
      query.add(ANY_PARAMETER_NAME, ANY_VALUE);
      var snapshot = query.values();

      // Act
      query.add(ANY_PARAMETER_NAME, ANOTHER_VALUE);

      // Assert
      var expected = Map.of(ANY_PARAMETER_NAME, List.of(ANY_VALUE));

      assertThat(snapshot, is(expected));
    }
  }
}
