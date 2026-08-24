package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedBoolean;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedBytes;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedElements;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedHeaderName;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedHeaderValue;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedHeaderValues;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedLength;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedResponse;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedSize;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedStatus;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedString;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.matchConditions;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.unexpectedElements;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.unexpectedHeaderName;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeCollection;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeMap;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.ANY_VALUE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.ID1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.ID2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer;
import java.text.Normalizer.Form;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AssertValueTest {

  private static final byte[] ANY_BYTES = {1, 2, 3};
  private static final String ANY_NORMALIZED_VALUE = "normalized-response";
  private static final String NORMALIZED_VALUE_1 = "normalized-response-1";
  private static final String NORMALIZED_VALUE_2 = "normalized-response-2";

  private static final List<String> ANY_NORMALIZED_LIST =
      List.of(NORMALIZED_VALUE_1, NORMALIZED_VALUE_2);

  private static final Map<String, String> ANY_NORMALIZED_MAP = Map.of(A, ANY_NORMALIZED_VALUE);

  private static final Predicate<String> MATCHES = value -> true;
  private static final Predicate<String> DOES_NOT_MATCH = value -> false;

  private MockedStatic<TestArrangeNormalizer> normalizer;

  @BeforeEach
  void setUp() {
    normalizer = mockStatic(TestArrangeNormalizer.class);
  }

  @AfterEach
  void cleanUp() {
    normalizer.close();
  }

  private AssertValue<String, String> givenAssertValue(String value, String normalizedValue) {
    normalizer.when(() -> normalizeObject(value, Form.NFC)).thenReturn(normalizedValue);
    return expectedString(value);
  }

  @Nested
  @DisplayName("expectedBoolean(Boolean)")
  class ExpectedBoolean {

    @Test
    void GIVEN_a_boolean_WHEN_expectedBoolean_THEN_value_and_normalized_value_are_preserved() {
      // Act
      var result = expectedBoolean(Boolean.TRUE);

      // Assert
      assertThat(result.value(), sameInstance(Boolean.TRUE));
      assertThat(result.normalizedValue(), sameInstance(Boolean.TRUE));
      normalizer.verifyNoInteractions();
    }

    @Test
    void GIVEN_a_null_boolean_WHEN_expectedBoolean_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedBoolean(null));

      // Assert
      assertThat(error.getMessage(), is("Expected boolean must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedBytes(byte[])")
  class ExpectedBytes {

    @Test
    void GIVEN_bytes_WHEN_expectedBytes_THEN_array_and_normalized_array_are_preserved() {
      // Act
      var result = expectedBytes(ANY_BYTES);

      // Assert
      assertThat(result.value(), sameInstance(ANY_BYTES));
      assertThat(result.normalizedValue(), sameInstance(ANY_BYTES));
      normalizer.verifyNoInteractions();
    }

    @Test
    void GIVEN_null_bytes_WHEN_expectedBytes_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedBytes(null));

      // Assert
      assertThat(error.getMessage(), is("Expected byte must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedResponse(T)")
  class ExpectedResponse {

    @Test
    void GIVEN_a_response_WHEN_expectedResponse_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer.when(() -> normalizeObject(A1, Form.NFC)).thenReturn(ANY_NORMALIZED_VALUE);

      // Act
      var result = expectedResponse(A1);

      // Assert
      assertThat(result.value(), is(A1));
      assertThat(result.normalizedValue(), is(ANY_NORMALIZED_VALUE));
      normalizer.verify(() -> normalizeObject(A1, Form.NFC));
    }

    @Test
    void GIVEN_a_null_response_WHEN_expectedResponse_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedResponse(null));

      // Assert
      assertThat(error.getMessage(), is("Expected response must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedString(String)")
  class ExpectedString {

    @Test
    void GIVEN_a_string_WHEN_expectedString_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer
          .when(() -> normalizeObject(TEST_A1_JSON, Form.NFC))
          .thenReturn(ANY_NORMALIZED_VALUE);

      // Act
      var result = expectedString(TEST_A1_JSON);

      // Assert
      assertThat(result.value(), sameInstance(TEST_A1_JSON));
      assertThat(result.normalizedValue(), sameInstance(ANY_NORMALIZED_VALUE));
      normalizer.verify(() -> normalizeObject(TEST_A1_JSON, Form.NFC));
    }

    @Test
    void GIVEN_a_null_string_WHEN_expectedString_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedString(null));

      // Assert
      assertThat(error.getMessage(), is("Expected string must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedCollection(Collection)")
  class ExpectedCollection {

    @Test
    void
        GIVEN_a_collection_WHEN_expectedCollection_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer
          .when(() -> normalizeCollection(TEST_LIST_A1_A2, Form.NFC))
          .thenReturn(ANY_NORMALIZED_LIST);

      // Act
      var result = expectedCollection(TEST_LIST_A1_A2);

      // Assert
      assertThat(result.value(), sameInstance(TEST_LIST_A1_A2));
      assertThat(result.normalizedValue(), sameInstance(ANY_NORMALIZED_LIST));
      normalizer.verify(() -> normalizeCollection(TEST_LIST_A1_A2, Form.NFC));
    }

    @Test
    void GIVEN_a_null_collection_WHEN_expectedCollection_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedCollection(null));

      // Assert
      assertThat(error.getMessage(), is("Expected collection must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedElements(E...)")
  class ExpectedElements {

    @Test
    void GIVEN_elements_WHEN_expectedElements_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer.when(() -> normalizeObject(ID1, Form.NFC)).thenReturn(NORMALIZED_VALUE_1);
      normalizer.when(() -> normalizeObject(ID2, Form.NFC)).thenReturn(NORMALIZED_VALUE_2);

      // Act
      var result = expectedElements(ID1, ID2);

      // Assert
      assertThat(result.value(), arrayContaining(ID1, ID2));
      assertThat(result.normalizedValue(), contains(NORMALIZED_VALUE_1, NORMALIZED_VALUE_2));
      normalizer.verify(() -> normalizeObject(ID1, Form.NFC));
      normalizer.verify(() -> normalizeObject(ID2, Form.NFC));
    }

    @Test
    void
        GIVEN_a_null_elements_array_WHEN_expectedElements_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(IllegalArgumentException.class, () -> expectedElements((String[]) null));

      // Assert
      assertThat(error.getMessage(), is("Expected elements must not be null"));
      normalizer.verifyNoInteractions();
    }

    @Test
    void
        GIVEN_a_null_element_WHEN_expectedElements_THEN_position_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(IllegalArgumentException.class, () -> expectedElements(ANY_VALUE, null));

      // Assert
      assertThat(error.getMessage(), is("Expected element at position 2 must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("unexpectedElements(Collection/E...)")
  class UnexpectedElements {

    @Test
    void
        GIVEN_an_element_collection_WHEN_unexpectedElements_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer
          .when(() -> normalizeCollection(TEST_LIST_A1_A2, Form.NFC))
          .thenReturn(ANY_NORMALIZED_LIST);

      // Act
      var result = unexpectedElements(TEST_LIST_A1_A2);

      // Assert
      assertThat(result.value(), sameInstance(TEST_LIST_A1_A2));
      assertThat(result.normalizedValue(), sameInstance(ANY_NORMALIZED_LIST));
      normalizer.verify(() -> normalizeCollection(TEST_LIST_A1_A2, Form.NFC));
    }

    @Test
    void
        GIVEN_element_varargs_WHEN_unexpectedElements_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer.when(() -> normalizeObject(ID1, Form.NFC)).thenReturn(NORMALIZED_VALUE_1);
      normalizer.when(() -> normalizeObject(ID2, Form.NFC)).thenReturn(NORMALIZED_VALUE_2);

      // Act
      var result = unexpectedElements(ID1, ID2);

      // Assert
      assertThat(result.value(), arrayContaining(ID1, ID2));
      assertThat(result.normalizedValue(), contains(NORMALIZED_VALUE_1, NORMALIZED_VALUE_2));
      normalizer.verify(() -> normalizeObject(ID1, Form.NFC));
      normalizer.verify(() -> normalizeObject(ID2, Form.NFC));
    }

    @Test
    void
        GIVEN_a_null_element_collection_WHEN_unexpectedElements_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(
              IllegalArgumentException.class, () -> unexpectedElements((Collection<String>) null));

      // Assert
      assertThat(error.getMessage(), is("Unexpected elements must not be null"));
      normalizer.verifyNoInteractions();
    }

    @Test
    void
        GIVEN_a_null_element_array_WHEN_unexpectedElements_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(IllegalArgumentException.class, () -> unexpectedElements((String[]) null));

      // Assert
      assertThat(error.getMessage(), is("Unexpected elements must not be null"));
      normalizer.verifyNoInteractions();
    }

    @Test
    void
        GIVEN_a_null_unexpected_element_WHEN_unexpectedElements_THEN_position_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> unexpectedElements(A, null));

      // Assert
      assertThat(error.getMessage(), is("Unexpected element at position 2 must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedMap(Map)")
  class ExpectedMap {

    @Test
    void GIVEN_a_map_WHEN_expectedMap_THEN_normalized_result_is_an_unmodifiable_snapshot() {
      // Arrange
      var normalizedMap = new LinkedHashMap<>(ANY_NORMALIZED_MAP);
      normalizer.when(() -> normalizeMap(TEST_MAP_A1_A2, Form.NFC)).thenReturn(normalizedMap);

      // Act
      var result = expectedMap(TEST_MAP_A1_A2);
      normalizedMap.put(B, ANY_VALUE);

      // Assert
      assertThat(result.value(), sameInstance(TEST_MAP_A1_A2));
      assertThat(result.normalizedValue(), is(ANY_NORMALIZED_MAP));
      assertThrows(
          UnsupportedOperationException.class, () -> result.normalizedValue().put(B, ANY_VALUE));
      normalizer.verify(() -> normalizeMap(TEST_MAP_A1_A2, Form.NFC));
    }

    @Test
    void GIVEN_a_null_map_WHEN_expectedMap_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedMap(null));

      // Assert
      assertThat(error.getMessage(), is("Expected map must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedStatus(HttpStatus/Integer)")
  class ExpectedStatus {

    @Test
    void GIVEN_an_HttpStatus_WHEN_expectedStatus_THEN_numeric_status_is_normalized() {
      // Act
      var result = expectedStatus(HttpStatus.ACCEPTED);

      // Assert
      assertThat(result.value(), sameInstance(HttpStatus.ACCEPTED));
      assertThat(result.normalizedValue(), is(202));
      normalizer.verifyNoInteractions();
    }

    @Test
    void GIVEN_an_integer_status_WHEN_expectedStatus_THEN_status_is_preserved() {
      // Act
      var result = expectedStatus(ID1);

      // Assert
      assertThat(result.value(), is(ID1));
      assertThat(result.normalizedValue(), is(ID1));
      normalizer.verifyNoInteractions();
    }

    @Test
    void GIVEN_a_null_HttpStatus_WHEN_expectedStatus_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(IllegalArgumentException.class, () -> expectedStatus((HttpStatus) null));

      // Assert
      assertThat(error.getMessage(), is("Expected HTTP status must not be null"));
      normalizer.verifyNoInteractions();
    }

    @Test
    void GIVEN_a_null_integer_status_WHEN_expectedStatus_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(IllegalArgumentException.class, () -> expectedStatus((Integer) null));

      // Assert
      assertThat(error.getMessage(), is("Expected HTTP status must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedHeaderName(String)")
  class ExpectedHeaderName {

    @Test
    void
        GIVEN_a_header_name_WHEN_expectedHeaderName_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer.when(() -> normalizeObject(A, Form.NFC)).thenReturn(ANY_NORMALIZED_VALUE);

      // Act
      var result = expectedHeaderName(A);

      // Assert
      assertThat(result.value(), sameInstance(A));
      assertThat(result.normalizedValue(), sameInstance(ANY_NORMALIZED_VALUE));
      normalizer.verify(() -> normalizeObject(A, Form.NFC));
    }

    @Test
    void
        GIVEN_a_null_header_name_WHEN_expectedHeaderName_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedHeaderName(null));

      // Assert
      assertThat(error.getMessage(), is("Expected header name must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("unexpectedHeaderName(String)")
  class UnexpectedHeaderName {

    @Test
    void
        GIVEN_a_header_name_WHEN_unexpectedHeaderName_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer.when(() -> normalizeObject(A, Form.NFC)).thenReturn(ANY_NORMALIZED_VALUE);

      // Act
      var result = unexpectedHeaderName(A);

      // Assert
      assertThat(result.value(), sameInstance(A));
      assertThat(result.normalizedValue(), sameInstance(ANY_NORMALIZED_VALUE));
      normalizer.verify(() -> normalizeObject(A, Form.NFC));
    }

    @Test
    void
        GIVEN_a_null_header_name_WHEN_unexpectedHeaderName_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> unexpectedHeaderName(null));

      // Assert
      assertThat(error.getMessage(), is("Unexpected header name must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedHeaderValue(String)")
  class ExpectedHeaderValue {

    @Test
    void
        GIVEN_a_header_value_WHEN_expectedHeaderValue_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer.when(() -> normalizeObject(A, Form.NFC)).thenReturn(ANY_NORMALIZED_VALUE);

      // Act
      var result = expectedHeaderValue(A);

      // Assert
      assertThat(result.value(), sameInstance(A));
      assertThat(result.normalizedValue(), sameInstance(ANY_NORMALIZED_VALUE));
      normalizer.verify(() -> normalizeObject(A, Form.NFC));
    }

    @Test
    void
        GIVEN_a_null_header_value_WHEN_expectedHeaderValue_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedHeaderValue(null));

      // Assert
      assertThat(error.getMessage(), is("Expected header value must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedHeaderValues(String...)")
  class ExpectedHeaderValues {

    @Test
    void
        GIVEN_header_values_WHEN_expectedHeaderValues_THEN_value_and_normalized_value_are_preserved() {
      // Arrange
      normalizer.when(() -> normalizeObject(A, Form.NFC)).thenReturn(NORMALIZED_VALUE_1);
      normalizer.when(() -> normalizeObject(B, Form.NFC)).thenReturn(NORMALIZED_VALUE_2);

      // Act
      var result = expectedHeaderValues(A, B);

      // Assert
      assertThat(result.value(), arrayContaining(A, B));
      assertThat(result.normalizedValue(), contains(NORMALIZED_VALUE_1, NORMALIZED_VALUE_2));
      normalizer.verify(() -> normalizeObject(A, Form.NFC));
      normalizer.verify(() -> normalizeObject(B, Form.NFC));
    }

    @Test
    void
        GIVEN_a_null_header_values_array_WHEN_expectedHeaderValues_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(IllegalArgumentException.class, () -> expectedHeaderValues((String[]) null));

      // Assert
      assertThat(error.getMessage(), is("Expected header values must not be null"));
      normalizer.verifyNoInteractions();
    }

    @Test
    void
        GIVEN_a_null_header_value_WHEN_expectedHeaderValues_THEN_position_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedHeaderValues(A, null));

      // Assert
      assertThat(error.getMessage(), is("Expected header value at position 2 must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedSize(Integer)")
  class ExpectedSize {

    @Test
    void GIVEN_a_size_WHEN_expectedSize_THEN_value_is_preserved() {
      // Act
      var result = expectedSize(ID1);

      // Assert
      assertThat(result.value(), is(ID1));
      assertThat(result.normalizedValue(), is(ID1));
      normalizer.verifyNoInteractions();
    }

    @Test
    void GIVEN_a_null_size_WHEN_expectedSize_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedSize(null));

      // Assert
      assertThat(error.getMessage(), is("Expected size must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("expectedLength(Integer)")
  class ExpectedLength {

    @Test
    void GIVEN_a_length_WHEN_expectedLength_THEN_value_is_preserved() {
      // Act
      var result = expectedLength(ID1);

      // Assert
      assertThat(result.value(), is(ID1));
      assertThat(result.normalizedValue(), is(ID1));
      normalizer.verifyNoInteractions();
    }

    @Test
    void GIVEN_a_null_length_WHEN_expectedLength_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error = assertThrows(IllegalArgumentException.class, () -> expectedLength(null));

      // Assert
      assertThat(error.getMessage(), is("Expected length must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("matchConditions(Predicate...)")
  class MatchConditions {

    @Test
    void GIVEN_match_conditions_WHEN_matchConditions_THEN_array_is_preserved() {
      // Act
      var result = matchConditions(MATCHES, DOES_NOT_MATCH);

      // Assert
      assertThat(result.value(), arrayContaining(MATCHES, DOES_NOT_MATCH));
      assertThat(result.normalizedValue(), sameInstance(result.value()));
      normalizer.verifyNoInteractions();
    }

    @Test
    void
        GIVEN_a_null_conditions_array_WHEN_matchConditions_THEN_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(
              IllegalArgumentException.class, () -> matchConditions((Predicate<String>[]) null));

      // Assert
      assertThat(error.getMessage(), is("Match conditions must not be null"));
      normalizer.verifyNoInteractions();
    }

    @Test
    void
        GIVEN_a_null_match_condition_WHEN_matchConditions_THEN_position_IllegalArgumentException_is_thrown() {
      // Act
      var error =
          assertThrows(IllegalArgumentException.class, () -> matchConditions(MATCHES, null));

      // Assert
      assertThat(error.getMessage(), is("Match condition at position 2 must not be null"));
      normalizer.verifyNoInteractions();
    }
  }

  @Nested
  @DisplayName("value()")
  class Value {

    @Test
    void GIVEN_an_AssertValue_WHEN_value_THEN_original_value_is_returned() {
      // Arrange
      var value = givenAssertValue(A, ANY_NORMALIZED_VALUE);

      // Act
      var result = value.value();

      // Assert
      assertThat(result, sameInstance(A));
    }
  }

  @Nested
  @DisplayName("normalizedValue()")
  class NormalizedValue {

    @Test
    void GIVEN_an_AssertValue_WHEN_normalizedValue_THEN_normalized_value_is_returned() {
      // Arrange
      var value = givenAssertValue(A, ANY_NORMALIZED_VALUE);

      // Act
      var result = value.normalizedValue();

      // Assert
      assertThat(result, sameInstance(ANY_NORMALIZED_VALUE));
    }
  }

  @Nested
  @DisplayName("toString()")
  class ToString {

    @Test
    void GIVEN_an_AssertValue_WHEN_toString_THEN_original_value_string_is_returned() {
      // Arrange
      var value = givenAssertValue(A, ANY_NORMALIZED_VALUE);

      // Act
      var result = value.toString();

      // Assert
      assertThat(result, is(A));
    }
  }
}
