package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.content.TestAssertFailedError;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.mapper.exception.TestGenericMapperException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TestAssertContentImplTest {

  private TestAAAContext context;
  private TestAssertContentImpl impl;

  @BeforeEach
  void setUp() {
    this.context = TestContext.mockContext();
    impl = new TestAssertContentImpl(this.context);
  }

  @Nested
  class asString {

    @Test
    @SneakyThrows
    void GIVEN_valid_string_WHEN_asString_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      impl.asString();

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(TEST_A1_JSON));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_null_or_empty_body_WHEN_asString_THEN_store_value_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asString();

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(body));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    @SneakyThrows
    void GIVEN_blank_body_WHEN_asString_THEN_store_blank_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asString();

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(body));
    }
  }

  @Nested
  class asBytes {

    @Test
    @SneakyThrows
    void GIVEN_valid_bytes_WHEN_asBytes_THEN_store_assertResult() {
      // Arrange
      var bytes = TEST_A1_JSON.getBytes(StandardCharsets.UTF_8);
      when(context.getActResult().contentAsBytes()).thenReturn(bytes);

      // Act
      impl.asBytes();

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(bytes));
    }

    @Test
    @SneakyThrows
    void GIVEN_empty_bytes_WHEN_asBytes_THEN_store_empty_array() {
      // Arrange
      var bytes = new byte[0];
      when(context.getActResult().contentAsBytes()).thenReturn(bytes);

      // Act
      impl.asBytes();

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat((byte[]) stored.actualContent(), is(new byte[0]));
    }

    @Test
    @SneakyThrows
    void GIVEN_null_bytes_WHEN_asBytes_THEN_store_null() {
      // Arrange
      when(context.getActResult().contentAsBytes()).thenReturn(null);

      // Act
      impl.asBytes();

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }
  }

  @Nested
  class asClass {

    @Test
    @SneakyThrows
    void GIVEN_valid_json_AND_expectedClass_WHEN_asClass_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      impl.asClass(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(A1));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_null_or_empty_body_WHEN_asClass_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asClass(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    @SneakyThrows
    void GIVEN_blank_body_WHEN_asClass_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asClass(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_mapping_WHEN_asClass_THEN_throw_TestAssertFailedError_wrapping_TestGenericMapperException() {
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asClass(String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asClass"));
      assertThat(ex.getMessage(), containsString("String"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }
  }

  @Nested
  class asCollection {

    @Test
    @SneakyThrows
    void GIVEN_list_json_AND_elementClass_WHEN_asCollection_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      impl.asCollection(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_null_or_empty_body_WHEN_asCollection_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asCollection(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    @SneakyThrows
    void GIVEN_blank_body_WHEN_asCollection_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asCollection(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_mapping_WHEN_asCollection_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asCollection(String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asCollection"));
      assertThat(ex.getMessage(), containsString("String"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_json_WHEN_asCollection_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex =
          assertThrows(
              TestAssertFailedError.class, () -> impl.asCollection(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asCollection"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }
  }

  @Nested
  class asList {

    @Test
    @SneakyThrows
    void GIVEN_list_json_AND_elementClass_WHEN_asList_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      impl.asList(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_null_or_empty_body_WHEN_asList_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asList(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    @SneakyThrows
    void GIVEN_blank_body_WHEN_asList_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asList(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_elementClass_WHEN_asList_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asList(String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asList"));
      assertThat(ex.getMessage(), containsString("String"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_json_WHEN_asList_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asList"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void GIVEN_object_json_instead_of_array_WHEN_asList_THEN_throwTestAssertFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asList"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }
  }

  @Nested
  class asSet {

    @Test
    @SneakyThrows
    void GIVEN_set_json_AND_elementClass_WHEN_asSet_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Act
      impl.asSet(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(TEST_SET_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_null_or_empty_body_WHEN_asSet_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asSet(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    @SneakyThrows
    void GIVEN_blank_body_WHEN_asSet_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asSet(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_elementClass_WHEN_asSet_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asSet(String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asSet"));
      assertThat(ex.getMessage(), containsString("String"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_json_WHEN_asSet_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asSet"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void GIVEN_object_json_instead_of_array_WHEN_asSet_THEN_throwTestAssertFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var ex = assertThrows(TestAssertFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asSet"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }
  }

  @Nested
  class asMap {

    @Test
    @SneakyThrows
    void GIVEN_map_json_AND_keyClass_valueClass_WHEN_asMap_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      impl.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), is(TEST_MAP_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @SneakyThrows
    void GIVEN_null_or_empty_body_WHEN_asMap_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    @SneakyThrows
    void GIVEN_blank_body_WHEN_asMap_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      var stored = context.getAssertResult();
      assertThat(stored, notNullValue());
      assertThat(stored.actualContent(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_wrong_keyClass_WHEN_asMap_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(
              TestAssertFailedError.class, () -> impl.asMap(Boolean.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asMap"));
      assertThat(ex.getMessage(), containsString("Boolean"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_wrong_valueClass_WHEN_asMap_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(TestAssertFailedError.class, () -> impl.asMap(Integer.class, String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asMap"));
      assertThat(ex.getMessage(), containsString("String"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_wrong_key_and_value_class_WHEN_asMap_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(TestAssertFailedError.class, () -> impl.asMap(String.class, String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asMap"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void
        GIVEN_invalid_json_WHEN_asMap_THEN_throwTestAssertFailedError_wrappingTestGenericMapperException() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex =
          assertThrows(
              TestAssertFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asMap"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }

    @Test
    @SneakyThrows
    void GIVEN_array_json_instead_of_object_WHEN_asMap_THEN_throwTestAssertFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(
              TestAssertFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("asMap"));
      assertThat(ex.getCause(), instanceOf(TestGenericMapperException.class));
      assertThat(context.getAssertResult(), nullValue());
    }
  }
}
