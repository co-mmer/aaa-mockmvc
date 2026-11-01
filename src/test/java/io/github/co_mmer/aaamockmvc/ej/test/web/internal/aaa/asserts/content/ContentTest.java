package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.act.model.TestActResult;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.MappingType;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.TestGenericMapperException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class ContentTest {

  private static final String TEXT = "Test";
  private static final String WHITESPACE = " \t\n";
  private static final String BOOLEAN_JSON = "true";
  private static final String STRING_JSON = "\"A\"";
  private static final String COLLECTION_JSON = "[\"A\",\"B\"]";
  private static final String EMPTY_COLLECTION_JSON = "[]";
  private static final String NULL_ELEMENT_JSON = "[\"A\",null]";
  private static final String MAP_JSON = "{\"A\":\"1\"}";
  private static final String EMPTY_MAP_JSON = "{}";
  private static final String NULL_JSON = "null";
  private static final String EMPTY_STRING_JSON = "\"\"";
  private static final List<String> VALUES_A_B = List.of("A", "B");
  private static final Set<String> SET_A_B = Set.of("A", "B");
  private static final Map<String, String> MAP_A_1 = Map.of("A", "1");
  private static final List<String> VALUES_WITH_NULL = Arrays.asList("A", null);
  private static final Set<String> SET_WITH_NULL = Collections.singleton(null);
  private static final MappingType<String> STRING_TYPE = new MappingType<>(String.class);
  private static final TestGenericMapperException MAPPING_ERROR =
      new TestGenericMapperException(new IllegalArgumentException("Invalid test input."));

  private static final Content CONTENT_EMPTY =
      new Content(new TestActResult(200, Map.of(), new byte[0], ""));

  private static final Content CONTENT_TEXT =
      new Content(new TestActResult(200, Map.of(), TEXT.getBytes(UTF_8), TEXT));

  private static final Content CONTENT_WHITESPACE =
      new Content(new TestActResult(200, Map.of(), WHITESPACE.getBytes(UTF_8), WHITESPACE));

  private static final Content CONTENT_BOOLEAN =
      new Content(new TestActResult(200, Map.of(), BOOLEAN_JSON.getBytes(UTF_8), BOOLEAN_JSON));

  private static final Content CONTENT_STRING =
      new Content(new TestActResult(200, Map.of(), STRING_JSON.getBytes(UTF_8), STRING_JSON));

  private static final Content CONTENT_COLLECTION =
      new Content(
          new TestActResult(200, Map.of(), COLLECTION_JSON.getBytes(UTF_8), COLLECTION_JSON));

  private static final Content CONTENT_EMPTY_COLLECTION =
      new Content(
          new TestActResult(
              200, Map.of(), EMPTY_COLLECTION_JSON.getBytes(UTF_8), EMPTY_COLLECTION_JSON));

  private static final Content CONTENT_NULL_ELEMENT =
      new Content(
          new TestActResult(200, Map.of(), NULL_ELEMENT_JSON.getBytes(UTF_8), NULL_ELEMENT_JSON));

  private static final Content CONTENT_MAP =
      new Content(new TestActResult(200, Map.of(), MAP_JSON.getBytes(UTF_8), MAP_JSON));

  private static final Content CONTENT_EMPTY_MAP =
      new Content(new TestActResult(200, Map.of(), EMPTY_MAP_JSON.getBytes(UTF_8), EMPTY_MAP_JSON));

  private static final Content CONTENT_JSON_NULL =
      new Content(new TestActResult(200, Map.of(), NULL_JSON.getBytes(UTF_8), NULL_JSON));

  private static final Content CONTENT_EMPTY_STRING =
      new Content(
          new TestActResult(200, Map.of(), EMPTY_STRING_JSON.getBytes(UTF_8), EMPTY_STRING_JSON));

  private static final String MISSING_RESPONSE_MESSAGE =
      "Execute perform() before entering content assertions.";
  private static final String UNEXPECTED_MAPPER_MESSAGE =
      "The mapper must not be called for an empty response body.";
  private static final String BOOLEAN_MAPPING_MESSAGE =
      "The response body could not be read as Boolean.";
  private static final String CLASS_MAPPING_MESSAGE =
      "The response body could not be read as String.";
  private static final String COLLECTION_MAPPING_MESSAGE =
      "The response body could not be read as a collection.";
  private static final String MAP_MAPPING_MESSAGE = "The response body could not be read as a map.";
  private static final String NULL_COLLECTION_MESSAGE =
      "Expected a response collection, but found JSON null.";
  private static final String NULL_ELEMENT_MESSAGE =
      "The response collection must not contain null elements.";
  private static final String NULL_MAP_MESSAGE = "Expected a response map, but found JSON null.";

  private static final String STRING_BODY_REQUIRED_MESSAGE =
      "asString() requires a non-empty response body, but the response body was empty.";

  private static final String BYTES_BODY_REQUIRED_MESSAGE =
      "asBytes() requires a non-empty response body, but the response body was empty.";

  private static final String BOOLEAN_BODY_REQUIRED_MESSAGE =
      "asBoolean() requires a non-empty response body, but the response body was empty.";

  private static final String CLASS_BODY_REQUIRED_MESSAGE =
      "asClass(...) requires a non-empty response body, but the response body was empty.";

  private static final String COLLECTION_BODY_REQUIRED_MESSAGE =
      "asCollection(...) requires a non-empty response body, but the response body was empty.";

  private static final String LIST_BODY_REQUIRED_MESSAGE =
      "asList(...) requires a non-empty response body, but the response body was empty.";

  private static final String SET_BODY_REQUIRED_MESSAGE =
      "asSet(...) requires a non-empty response body, but the response body was empty.";

  private static final String MAP_BODY_REQUIRED_MESSAGE =
      "asMap(...) requires a non-empty response body, but the response body was empty.";

  @Test
  void GIVEN_no_response_WHEN_constructor_THEN_fail_before_content_assertions() {
    // Act
    var exception = assertThrows(IllegalStateException.class, () -> new Content(null));

    // Assert
    assertThat(exception.getMessage(), is(MISSING_RESPONSE_MESSAGE));
  }

  @Test
  void GIVEN_response_WHEN_original_bytes_change_THEN_content_retains_snapshot() {
    // Arrange
    var bytes = TEXT.getBytes(UTF_8);
    var content = new Content(new TestActResult(200, Map.of(), bytes, TEXT));

    // Act
    bytes[0] = 'X';

    // Assert
    assertThat(content.asBytes().value(), is(TEXT.getBytes(UTF_8)));
  }

  @Nested
  class asString {

    @Test
    void GIVEN_empty_body_WHEN_asString_THEN_assertion_failed() {
      // Act
      var exception = assertThrows(AssertionFailedError.class, CONTENT_EMPTY::asString);

      // Assert
      assertThat(exception.getMessage(), is(STRING_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asString_THEN_original_content_is_returned() {
      // Act
      var result = CONTENT_TEXT.asString();

      // Assert
      assertThat(result.value(), is(TEXT));
    }

    @Test
    void GIVEN_whitespace_WHEN_asString_THEN_original_content_is_returned() {
      // Act
      var result = CONTENT_WHITESPACE.asString();

      // Assert
      assertThat(result.value(), is(WHITESPACE));
    }
  }

  @Nested
  class asBytes {

    @Test
    void GIVEN_empty_body_WHEN_asBytes_THEN_assertion_failed() {
      // Act
      var exception = assertThrows(AssertionFailedError.class, CONTENT_EMPTY::asBytes);

      // Assert
      assertThat(exception.getMessage(), is(BYTES_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asBytes_THEN_original_content_is_returned() {
      // Act
      var result = CONTENT_TEXT.asBytes();

      // Assert
      assertThat(result.value(), is(TEXT.getBytes(UTF_8)));
    }

    @Test
    void GIVEN_whitespace_WHEN_asBytes_THEN_original_content_is_returned() {
      // Act
      var result = CONTENT_WHITESPACE.asBytes();

      // Assert
      assertThat(result.value(), is(WHITESPACE.getBytes(UTF_8)));
    }
  }

  @Nested
  class asBoolean {

    @Test
    void GIVEN_empty_body_WHEN_asBoolean_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_EMPTY.asBoolean(json -> fail(UNEXPECTED_MAPPER_MESSAGE)));

      // Assert
      assertThat(exception.getMessage(), is(BOOLEAN_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asBoolean_THEN_mapper_receives_original_body_and_result_is_returned() {
      // Act
      var result =
          CONTENT_BOOLEAN.asBoolean(
              json -> {
                assertThat(json, is(BOOLEAN_JSON));
                return true;
              });

      // Assert
      assertThat(result.value(), is(true));
    }

    @Test
    void GIVEN_mapping_error_WHEN_asBoolean_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_BOOLEAN.asBoolean(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(BOOLEAN_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_whitespace_WHEN_asBoolean_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_WHITESPACE.asBoolean(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(BOOLEAN_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_json_null_WHEN_asBoolean_THEN_nullable_value_is_returned() {
      // Act
      var result = CONTENT_JSON_NULL.asBoolean(json -> null);

      // Assert
      assertThat(result.value(), nullValue());
    }
  }

  @Nested
  class asClass {

    @Test
    void GIVEN_empty_body_WHEN_asClass_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_EMPTY.asClass(STRING_TYPE, json -> fail(UNEXPECTED_MAPPER_MESSAGE)));

      // Assert
      assertThat(exception.getMessage(), is(CLASS_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asClass_THEN_mapper_receives_original_body_and_result_is_returned() {
      // Act
      var result =
          CONTENT_STRING.asClass(
              STRING_TYPE,
              json -> {
                assertThat(json, is(STRING_JSON));
                return "A";
              });

      // Assert
      assertThat(result.value(), is("A"));
    }

    @Test
    void GIVEN_mapping_error_WHEN_asClass_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_STRING.asClass(
                      STRING_TYPE,
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(CLASS_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_whitespace_WHEN_asClass_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_WHITESPACE.asClass(
                      STRING_TYPE,
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(CLASS_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_json_null_WHEN_asClass_THEN_nullable_value_is_returned() {
      // Act
      var result = CONTENT_JSON_NULL.asClass(STRING_TYPE, json -> null);

      // Assert
      assertThat(result.value(), nullValue());
    }

    @Test
    void GIVEN_json_empty_string_WHEN_asClass_THEN_empty_string_is_returned() {
      // Act
      var result = CONTENT_EMPTY_STRING.asClass(STRING_TYPE, json -> "");

      // Assert
      assertThat(result.value(), is(""));
    }
  }

  @Nested
  class asCollection {

    @Test
    void GIVEN_empty_body_WHEN_asCollection_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_EMPTY.asCollection(json -> fail(UNEXPECTED_MAPPER_MESSAGE)));

      // Assert
      assertThat(exception.getMessage(), is(COLLECTION_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asCollection_THEN_mapper_receives_original_body_and_result_is_returned() {
      // Act
      var result =
          CONTENT_COLLECTION.asCollection(
              json -> {
                assertThat(json, is(COLLECTION_JSON));
                return VALUES_A_B;
              });

      // Assert
      assertThat(result.values(), is(VALUES_A_B));
    }

    @Test
    void GIVEN_mapping_error_WHEN_asCollection_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_COLLECTION.asCollection(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(COLLECTION_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_whitespace_WHEN_asCollection_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_WHITESPACE.asCollection(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(COLLECTION_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_json_null_WHEN_asCollection_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> CONTENT_JSON_NULL.asCollection(json -> null));

      // Assert
      assertThat(exception.getMessage(), is(NULL_COLLECTION_MESSAGE));
    }

    @Test
    void GIVEN_json_empty_container_WHEN_asCollection_THEN_empty_value_is_returned() {
      // Act
      var result = CONTENT_EMPTY_COLLECTION.asCollection(json -> List.of());

      // Assert
      assertThat(result.values(), is(List.of()));
    }

    @Test
    void GIVEN_null_element_WHEN_asCollection_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_NULL_ELEMENT.asCollection(json -> VALUES_WITH_NULL));

      // Assert
      assertThat(exception.getMessage(), is(NULL_ELEMENT_MESSAGE));
    }
  }

  @Nested
  class asList {

    @Test
    void GIVEN_empty_body_WHEN_asList_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_EMPTY.asList(json -> fail(UNEXPECTED_MAPPER_MESSAGE)));

      // Assert
      assertThat(exception.getMessage(), is(LIST_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asList_THEN_mapper_receives_original_body_and_result_is_returned() {
      // Act
      var result =
          CONTENT_COLLECTION.asList(
              json -> {
                assertThat(json, is(COLLECTION_JSON));
                return VALUES_A_B;
              });

      // Assert
      assertThat(result.values(), is(VALUES_A_B));
    }

    @Test
    void GIVEN_mapping_error_WHEN_asList_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_COLLECTION.asList(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(COLLECTION_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_whitespace_WHEN_asList_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_WHITESPACE.asList(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(COLLECTION_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_json_null_WHEN_asList_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> CONTENT_JSON_NULL.asList(json -> null));

      // Assert
      assertThat(exception.getMessage(), is(NULL_COLLECTION_MESSAGE));
    }

    @Test
    void GIVEN_json_empty_container_WHEN_asList_THEN_empty_value_is_returned() {
      // Act
      var result = CONTENT_EMPTY_COLLECTION.asList(json -> List.of());

      // Assert
      assertThat(result.values(), is(List.of()));
    }

    @Test
    void GIVEN_null_element_WHEN_asList_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_NULL_ELEMENT.asList(json -> VALUES_WITH_NULL));

      // Assert
      assertThat(exception.getMessage(), is(NULL_ELEMENT_MESSAGE));
    }
  }

  @Nested
  class asSet {

    @Test
    void GIVEN_empty_body_WHEN_asSet_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_EMPTY.asSet(json -> fail(UNEXPECTED_MAPPER_MESSAGE)));

      // Assert
      assertThat(exception.getMessage(), is(SET_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asSet_THEN_mapper_receives_original_body_and_result_is_returned() {
      // Act
      var result =
          CONTENT_COLLECTION.asSet(
              json -> {
                assertThat(json, is(COLLECTION_JSON));
                return SET_A_B;
              });

      // Assert
      assertThat(result.values(), is(SET_A_B));
    }

    @Test
    void GIVEN_mapping_error_WHEN_asSet_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_COLLECTION.asSet(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(COLLECTION_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_whitespace_WHEN_asSet_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_WHITESPACE.asSet(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(COLLECTION_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_json_null_WHEN_asSet_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> CONTENT_JSON_NULL.asSet(json -> null));

      // Assert
      assertThat(exception.getMessage(), is(NULL_COLLECTION_MESSAGE));
    }

    @Test
    void GIVEN_json_empty_container_WHEN_asSet_THEN_empty_value_is_returned() {
      // Act
      var result = CONTENT_EMPTY_COLLECTION.asSet(json -> Set.of());

      // Assert
      assertThat(result.values(), is(Set.of()));
    }

    @Test
    void GIVEN_null_element_WHEN_asSet_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class, () -> CONTENT_NULL_ELEMENT.asSet(json -> SET_WITH_NULL));

      // Assert
      assertThat(exception.getMessage(), is(NULL_ELEMENT_MESSAGE));
    }
  }

  @Nested
  class asMap {

    @Test
    void GIVEN_empty_body_WHEN_asMap_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () -> CONTENT_EMPTY.asMap(json -> fail(UNEXPECTED_MAPPER_MESSAGE)));

      // Assert
      assertThat(exception.getMessage(), is(MAP_BODY_REQUIRED_MESSAGE));
    }

    @Test
    void GIVEN_body_WHEN_asMap_THEN_mapper_receives_original_body_and_result_is_returned() {
      // Act
      var result =
          CONTENT_MAP.asMap(
              json -> {
                assertThat(json, is(MAP_JSON));
                return MAP_A_1;
              });

      // Assert
      assertThat(result.values(), is(MAP_A_1));
    }

    @Test
    void GIVEN_mapping_error_WHEN_asMap_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_MAP.asMap(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(MAP_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_whitespace_WHEN_asMap_THEN_assertion_failed_with_mapping_cause() {
      // Act
      var exception =
          assertThrows(
              AssertionFailedError.class,
              () ->
                  CONTENT_WHITESPACE.asMap(
                      json -> {
                        throw MAPPING_ERROR;
                      }));

      // Assert
      assertThat(exception.getMessage(), is(MAP_MAPPING_MESSAGE));
      assertThat(exception.getCause(), sameInstance(MAPPING_ERROR));
    }

    @Test
    void GIVEN_json_null_WHEN_asMap_THEN_assertion_failed() {
      // Act
      var exception =
          assertThrows(AssertionFailedError.class, () -> CONTENT_JSON_NULL.asMap(json -> null));

      // Assert
      assertThat(exception.getMessage(), is(NULL_MAP_MESSAGE));
    }

    @Test
    void GIVEN_json_empty_container_WHEN_asMap_THEN_empty_value_is_returned() {
      // Act
      var result = CONTENT_EMPTY_MAP.asMap(json -> Map.of());

      // Assert
      assertThat(result.values(), is(Map.of()));
    }
  }
}
