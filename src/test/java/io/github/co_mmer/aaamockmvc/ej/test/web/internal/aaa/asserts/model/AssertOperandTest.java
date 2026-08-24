package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.unicode.UnicodeNormalizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class AssertOperandTest {

  private static final String A = "A";
  private static final String B = "B";
  private static final String C = "C";
  private static final String ANY_STRING = "ANY";
  private static final String ANY_OTHER_STRING = "ANY_OTHER";
  private static final String ANY_NORMALIZED_STRING = "ANY_NORMALIZED";
  private static final Boolean ANY_BOOLEAN = Boolean.TRUE;
  private static final Integer ANY_INTEGER = 42;
  private static final Class<String> ANY_CLASS = String.class;
  private static final byte A_BYTE = 1;
  private static final byte B_BYTE = 2;
  private static final byte C_BYTE = 3;
  private static final byte ANY_CHANGED_BYTE = 99;
  private static final List<String> ANY_NORMALIZED_COLLECTION = List.of(ANY_NORMALIZED_STRING);

  private static final Map<String, String> ANY_NORMALIZED_MAP =
      Map.of(ANY_STRING, ANY_NORMALIZED_STRING);
  private static final Map<String, List<String>> ANY_NORMALIZED_MAP_LIST =
      Map.of(ANY_STRING, List.of(ANY_NORMALIZED_STRING));

  private static MockedStatic<UnicodeNormalizer> mockNormalizeCollection() {
    var normalizer = mockStatic(UnicodeNormalizer.class);
    normalizer
        .when(() -> UnicodeNormalizer.normalizeCollection(any()))
        .thenReturn(ANY_NORMALIZED_COLLECTION);
    return normalizer;
  }

  private static MockedStatic<UnicodeNormalizer> mockNormalizeObject() {
    var normalizer = mockStatic(UnicodeNormalizer.class);
    normalizer
        .when(() -> UnicodeNormalizer.normalizeObject(any()))
        .thenReturn(ANY_NORMALIZED_STRING);
    return normalizer;
  }

  private static MockedStatic<UnicodeNormalizer> mockNormalizeMap() {
    var normalizer = mockStatic(UnicodeNormalizer.class);
    normalizer.when(() -> UnicodeNormalizer.normalizeMap(any())).thenReturn(ANY_NORMALIZED_MAP);
    return normalizer;
  }

  private static MockedStatic<UnicodeNormalizer> mockNormalizeMapList() {
    var normalizer = mockStatic(UnicodeNormalizer.class);
    normalizer
        .when(() -> UnicodeNormalizer.normalizeMapList(any()))
        .thenReturn(ANY_NORMALIZED_MAP_LIST);
    return normalizer;
  }

  @Nested
  @DisplayName("collection(Collection)")
  class CollectionMethodTests {

    @Test
    void GIVEN_null_WHEN_collection_THEN_values_are_null() {
      // Arrange
      var normalizer = mockStatic(UnicodeNormalizer.class);

      // Act
      var operand = AssertOperand.collection(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
      normalizer.verifyNoInteractions();
      normalizer.close();
    }

    @Test
    void GIVEN_values_WHEN_collection_THEN_values_are_snapshotted_and_normalizer_is_called() {
      // Arrange
      var value = new ArrayList<>(List.of(A, B));
      var normalizer = mockNormalizeCollection();

      // Act
      var operand = AssertOperand.collection(value);
      value.add(C);

      // Assert
      assertThat(operand.actual(), containsInAnyOrder(A, B));
      assertThat(operand.normalizedValue(), is(ANY_NORMALIZED_COLLECTION));
      normalizer.verify(() -> UnicodeNormalizer.normalizeCollection(any()), times(1));
      normalizer.close();
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_operand_is_created_WHEN_collection_THEN_actual_snapshot_is_unmodifiable() {
      // Arrange
      var normalizer = mockNormalizeCollection();
      var operand = AssertOperand.collection(List.of(ANY_STRING));

      // Act
      var exception =
          assertThrows(
              UnsupportedOperationException.class, () -> operand.actual().add(ANY_OTHER_STRING));

      // Assert
      assertThat(exception, is(not(nullValue())));
      normalizer.close();
    }
  }

  @Nested
  @DisplayName("list(List)")
  class ListMethodTests {

    @Test
    void GIVEN_null_WHEN_list_THEN_values_are_null() {
      // Arrange
      var normalizer = mockStatic(UnicodeNormalizer.class);

      // Act
      var operand = AssertOperand.list(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
      normalizer.verifyNoInteractions();
      normalizer.close();
    }

    @Test
    void GIVEN_values_WHEN_list_THEN_values_are_snapshotted_and_normalizer_is_called() {
      // Arrange
      var value = new ArrayList<>(List.of(A, B));
      var normalizer = mockNormalizeCollection();

      // Act
      var operand = AssertOperand.list(value);
      value.add(C);

      // Assert
      assertThat(operand.actual(), contains(A, B));
      assertThat(operand.normalizedValue(), is(ANY_NORMALIZED_COLLECTION));
      normalizer.verify(() -> UnicodeNormalizer.normalizeCollection(any()), times(1));
      normalizer.close();
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_operand_is_created_WHEN_list_THEN_actual_snapshot_is_unmodifiable() {
      // Arrange
      var normalizer = mockNormalizeCollection();
      var operand = AssertOperand.list(List.of(ANY_STRING));

      // Act
      var exception =
          assertThrows(
              UnsupportedOperationException.class, () -> operand.actual().add(ANY_OTHER_STRING));

      // Assert
      assertThat(exception, is(not(nullValue())));
      normalizer.close();
    }
  }

  @Nested
  @DisplayName("set(Set)")
  class SetMethodTests {

    @Test
    void GIVEN_null_WHEN_set_THEN_actual_and_normalized_value_are_null() {
      // Arrange
      var normalizer = mockStatic(UnicodeNormalizer.class);

      // Act
      var operand = AssertOperand.set(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
      normalizer.verifyNoInteractions();
      normalizer.close();
    }

    @Test
    void GIVEN_values_WHEN_set_THEN_ordered_values_are_snapshotted_and_normalizer_is_called() {
      // Arrange
      var value = new LinkedHashSet<>(List.of(A, B));
      var normalizer = mockNormalizeCollection();

      // Act
      var operand = AssertOperand.set(value);
      value.add(C);

      // Assert
      assertThat(operand.actual(), contains(A, B));
      assertThat(operand.normalizedValue(), is(new LinkedHashSet<>(ANY_NORMALIZED_COLLECTION)));
      normalizer.verify(() -> UnicodeNormalizer.normalizeCollection(any()), times(1));
      normalizer.close();
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_operand_is_created_WHEN_set_THEN_actual_snapshot_is_unmodifiable() {
      // Arrange
      var normalizer = mockNormalizeCollection();
      var operand = AssertOperand.set(Set.of(ANY_STRING));

      // Act
      var exception =
          assertThrows(
              UnsupportedOperationException.class, () -> operand.actual().add(ANY_OTHER_STRING));

      // Assert
      assertThat(exception, is(not(nullValue())));
      normalizer.close();
    }
  }

  @Nested
  @DisplayName("bool(Boolean)")
  class BoolMethodTests {

    @Test
    void GIVEN_null_WHEN_boolean_THEN_actual_and_normalized_value_are_null() {
      // Act
      var operand = AssertOperand.bool(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
    }

    @Test
    void GIVEN_value_WHEN_boolean_THEN_same_value_is_used_as_actual_and_normalized_value() {
      // Act
      var operand = AssertOperand.bool(ANY_BOOLEAN);

      // Assert
      assertThat(operand.actual(), is(ANY_BOOLEAN));
      assertThat(operand.normalizedValue(), is(ANY_BOOLEAN));
    }
  }

  @Nested
  @DisplayName("string(String)")
  class StringMethodTests {

    @Test
    void GIVEN_null_WHEN_string_THEN_actual_and_normalized_value_are_null() {
      // Act
      var operand = AssertOperand.string(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
    }

    @Test
    void GIVEN_value_WHEN_string_THEN_same_value_is_used_as_actual_and_normalized_value() {
      // Act
      var operand = AssertOperand.string(ANY_STRING);

      // Assert
      assertThat(operand.actual(), is(ANY_STRING));
      assertThat(operand.normalizedValue(), is(ANY_STRING));
    }
  }

  @Nested
  @DisplayName("clazz(Object)")
  class ClazzMethodTests {

    @Test
    void GIVEN_null_WHEN_object_THEN_actual_and_normalized_value_are_null() {
      // Arrange
      var normalizer = mockStatic(UnicodeNormalizer.class);

      // Act
      var operand = AssertOperand.clazz(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
      normalizer.verifyNoInteractions();
      normalizer.close();
    }

    @Test
    void GIVEN_object_WHEN_object_THEN_object_is_retained_and_normalizer_is_called() {
      // Arrange
      var normalizer = mockNormalizeObject();

      // Act
      var operand = AssertOperand.clazz(ANY_CLASS);

      // Assert
      assertThat(operand.actual(), is(sameInstance(ANY_CLASS)));
      assertThat(operand.normalizedValue(), is(ANY_NORMALIZED_STRING));
      normalizer.verify(() -> UnicodeNormalizer.normalizeObject(ANY_CLASS), times(1));
      normalizer.close();
    }
  }

  @Nested
  @DisplayName("integer(Integer)")
  class IntegerMethodTests {

    @Test
    void GIVBN_value_WHEN_integer_THEN_actual_and_normalized_value_are_null() {
      // Act
      var operand = AssertOperand.integer(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
    }

    @Test
    void GIVEN_value_WHEN_integer_THEN_same_value_is_used_as_actual_and_normalized_value() {
      // Act
      var operand = AssertOperand.integer(ANY_INTEGER);

      // Assert
      assertThat(operand.actual(), is(ANY_INTEGER));
      assertThat(operand.normalizedValue(), is(ANY_INTEGER));
    }
  }

  @Nested
  @DisplayName("bytes(byte[])")
  class BytesMethodTests {

    @Test
    void GIVEN_null_WHEN_bytes_THEN_actual_and_normalized_value_are_null() {
      // Act
      var operand = AssertOperand.bytes(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
    }

    @Test
    void GIVEN_bytes_WHEN_bytes_THEN_input_is_defensively_copied() {
      // Arrange
      var value = new byte[] {A_BYTE, B_BYTE, C_BYTE};

      // Act
      var operand = AssertOperand.bytes(value);
      value[0] = ANY_CHANGED_BYTE;

      // Assert
      assertArrayEquals(new byte[] {A_BYTE, B_BYTE, C_BYTE}, operand.actual());
      assertArrayEquals(new byte[] {A_BYTE, B_BYTE, C_BYTE}, operand.normalizedValue());
      assertThat(operand.actual(), is(not(sameInstance(value))));
      assertThat(operand.normalizedValue(), is(not(sameInstance(operand.actual()))));
    }

    @Test
    void GIVEN_bytes_are_changed_WHEN_actual_THEN_normalized_bytes_remain_unchanged() {
      // Arrange
      var operand = AssertOperand.bytes(new byte[] {A_BYTE, B_BYTE, C_BYTE});

      // Act
      operand.actual()[0] = ANY_CHANGED_BYTE;

      // Assert
      assertArrayEquals(new byte[] {ANY_CHANGED_BYTE, B_BYTE, C_BYTE}, operand.actual());
      assertArrayEquals(new byte[] {A_BYTE, B_BYTE, C_BYTE}, operand.normalizedValue());
    }
  }

  @Nested
  @DisplayName("map(Map)")
  class MapMethodTests {

    @Test
    void GIVEN_null_WHEN_map_THEN_actual_and_normalized_value_are_null() {
      // Arrange
      var normalizer = mockStatic(UnicodeNormalizer.class);

      // Act
      var operand = AssertOperand.map(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
      normalizer.verifyNoInteractions();
      normalizer.close();
    }

    @Test
    void GIVEN_map_WHEN_map_THEN_entries_are_snapshotted_and_normalizer_is_called() {
      // Arrange
      var value = new LinkedHashMap<String, String>();
      value.put(A, A);
      value.put(B, B);
      var normalizer = mockNormalizeMap();

      // Act
      var operand = AssertOperand.map(value);
      value.put(C, C);

      // Assert
      assertThat(operand.actual(), is(Map.of(A, A, B, B)));
      assertThat(operand.normalizedValue(), is(ANY_NORMALIZED_MAP));
      normalizer.verify(() -> UnicodeNormalizer.normalizeMap(any()), times(1));
      normalizer.close();
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_operand_is_created_WHEN_map_THEN_actual_snapshot_is_unmodifiable() {
      // Arrange
      var normalizer = mockNormalizeMap();
      var operand = AssertOperand.map(Map.of(ANY_STRING, ANY_STRING));

      // Act
      var exception =
          assertThrows(
              UnsupportedOperationException.class,
              () -> operand.actual().put(ANY_OTHER_STRING, ANY_OTHER_STRING));

      // Assert
      assertThat(exception, is(not(nullValue())));
      normalizer.close();
    }
  }

  @Nested
  @DisplayName("headers(Map)")
  class HeadersMethodTests {

    @Test
    void GIVEN_null_WHEN_headers_THEN_actual_and_normalized_value_are_null() {
      // Arrange
      var normalizer = mockStatic(UnicodeNormalizer.class);

      // Act
      var operand = AssertOperand.headers(null);

      // Assert
      assertThat(operand.actual(), is(nullValue()));
      assertThat(operand.normalizedValue(), is(nullValue()));
      normalizer.verifyNoInteractions();
      normalizer.close();
    }

    @Test
    void GIVEN_headers_WHEN_headers_THEN_values_are_snapshotted_and_normalizer_is_called() {
      // Arrange
      var headerValues = new ArrayList<>(List.of(A, B));
      var value = new LinkedHashMap<String, List<String>>();
      value.put(A, headerValues);
      var normalizer = mockNormalizeMapList();

      // Act
      var operand = AssertOperand.headers(value);
      headerValues.add(C);
      value.put(C, List.of(C));

      // Assert
      assertThat(operand.actual(), is(Map.of(A, List.of(A, B))));
      assertThat(operand.normalizedValue(), is(ANY_NORMALIZED_MAP_LIST));
      normalizer.verify(() -> UnicodeNormalizer.normalizeMapList(any()), times(1));
      normalizer.close();
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_operand_is_created_WHEN_headers_THEN_actual_map_and_value_lists_are_unmodifiable() {
      // Arrange
      var normalizer = mockNormalizeMapList();
      var operand = AssertOperand.headers(Map.of(ANY_STRING, List.of(ANY_STRING)));

      // Act
      var mapException =
          assertThrows(
              UnsupportedOperationException.class,
              () -> operand.actual().put(ANY_OTHER_STRING, List.of(ANY_OTHER_STRING)));
      var listException =
          assertThrows(
              UnsupportedOperationException.class,
              () -> operand.actual().get(ANY_STRING).add(ANY_OTHER_STRING));

      // Assert
      assertThat(mapException, is(not(nullValue())));
      assertThat(listException, is(not(nullValue())));
      normalizer.close();
    }
  }

  @Nested
  @DisplayName("actual()")
  class ActualMethodTests {

    @Test
    void WHEN_actual_THEN_original_operand_value_is_returned() {
      // Arrange
      var operand = AssertOperand.string(ANY_STRING);

      // Act
      var actual = operand.actual();

      // Assert
      assertThat(actual, is(ANY_STRING));
    }
  }

  @Nested
  @DisplayName("normalizedValue()")
  class NormalizedValueMethodTests {

    @Test
    void WHEN_normalized_THEN_normalized_operand_value_is_returned() {
      // Arrange
      var operand = AssertOperand.string(ANY_STRING);

      // Act
      var normalizedValue = operand.normalizedValue();

      // Assert
      assertThat(normalizedValue, is(ANY_STRING));
    }
  }
}
