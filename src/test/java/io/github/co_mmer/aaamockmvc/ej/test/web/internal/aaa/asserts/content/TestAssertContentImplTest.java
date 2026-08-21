package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BOOLEAN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BOOLEAN_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;

class TestAssertContentImplTest {

  private TestAAAContext context;
  private TestAssertContentImpl impl;

  @BeforeEach
  void setUp() {
    this.context = TestContext.mockContext();
    impl = new TestAssertContentImpl(this.context);
  }

  @Nested
  class asBoolean {

    @Test
    void GIVEN_valid_json_AND_expectedClass_WHEN_asBoolean_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_BOOLEAN_JSON);

      // Act
      impl.asBoolean();

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(TEST_BOOLEAN));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asBoolean_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asBoolean();

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), nullValue());
    }

    @Test
    void GIVEN_invalid_mapping_WHEN_asBoolean_THEN_throw_AssertionFailedError() {
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asBoolean());

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asBoolean()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to Boolean"));
      assertThat(context.getAssertOperand(), nullValue());
    }
  }

  @Nested
  class asString {

    @Test
    void GIVEN_valid_string_WHEN_asString_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      impl.asString();

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(TEST_A1_JSON));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asString_THEN_store_blank_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asString();

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(body));
    }
  }

  @Nested
  class asBytes {

    @Test
    void GIVEN_valid_bytes_WHEN_asBytes_THEN_store_assertResult() {
      // Arrange
      var bytes = TEST_A1_JSON.getBytes(StandardCharsets.UTF_8);
      when(context.getActResult().contentAsBytes()).thenReturn(bytes);

      // Act
      impl.asBytes();

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(bytes));
    }

    @Test
    void GIVEN_empty_bytes_WHEN_asBytes_THEN_store_empty_array() {
      // Arrange
      var bytes = new byte[0];
      when(context.getActResult().contentAsBytes()).thenReturn(bytes);

      // Act
      impl.asBytes();

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat((byte[]) stored.actual(), is(new byte[0]));
    }

    @Test
    void GIVEN_null_bytes_WHEN_asBytes_THEN_store_null() {
      // Arrange
      when(context.getActResult().contentAsBytes()).thenReturn(null);

      // Act
      impl.asBytes();

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), nullValue());
    }
  }

  @Nested
  class asClass {

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asClass(null));
    }

    @Test
    void GIVEN_valid_json_AND_expectedClass_WHEN_asClass_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      impl.asClass(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(A1));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asClass_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asClass(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), nullValue());
    }

    @Test
    void GIVEN_invalid_mapping_WHEN_asClass_THEN_throw_AssertionFailedError() {
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asClass(String.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asClass()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to String"));
      assertThat(context.getAssertOperand(), nullValue());
    }
  }

  @Nested
  class asCollection {

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asCollection(null));
    }

    @Test
    void GIVEN_list_json_AND_elementClass_WHEN_asCollection_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      impl.asCollection(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asCollection_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asCollection(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), nullValue());
    }

    @Test
    void GIVEN_invalid_mapping_WHEN_asCollection_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asCollection(String.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asCollection()' — Reason: Response body '[{\"id\":1,\"name\":\"A\"},{\"id\":2,\"name\":\"A\"}]' cannot be mapped to String"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asCollection_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asCollection(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asCollection()' — Reason: Response body '{not-valid-json' cannot be mapped to TestObjectSimple"));
      assertThat(context.getAssertOperand(), nullValue());
    }
  }

  @Nested
  class asList {

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asList(null));
    }

    @Test
    void GIVEN_list_json_AND_elementClass_WHEN_asList_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      impl.asList(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asList_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asList(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), nullValue());
    }

    @Test
    void GIVEN_invalid_elementClass_WHEN_asList_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(String.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asList()' — Reason: Response body '[{\"id\":1,\"name\":\"A\"},{\"id\":2,\"name\":\"A\"}]' cannot be mapped to String"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asList_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asList()' — Reason: Response body '{not-valid-json' cannot be mapped to TestObjectSimple"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_object_json_instead_of_array_WHEN_asList_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asList()' — Reason: Response body '{\"id\":1,\"name\":\"A\"}' cannot be mapped to TestObjectSimple"));
      assertThat(context.getAssertOperand(), nullValue());
    }
  }

  @Nested
  class asSet {

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asSet(null));
    }

    @Test
    void GIVEN_set_json_AND_elementClass_WHEN_asSet_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Act
      impl.asSet(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(TEST_SET_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asSet_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asSet(TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), nullValue());
    }

    @Test
    void GIVEN_invalid_elementClass_WHEN_asSet_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_SET_A1_A2_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("'content().asSet()' — Reason: Response body "));
      assertThat(ex.getMessage(), containsString("cannot be mapped to String"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asSet_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "'content().asSet()' — Reason: Response body '{not-valid-json' cannot be mapped to TestObjectSimple"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_object_json_instead_of_array_WHEN_asSet_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("'content().asSet()' — Reason: Response body "));
      assertThat(ex.getMessage(), containsString("cannot be mapped to TestObjectSimple"));
      assertThat(context.getAssertOperand(), nullValue());
    }
  }

  @Nested
  class asMap {

    @Test
    @SuppressWarnings("all")
    void GIVEN_keyIsNull_and_valueIsNull_WHEN_asMap_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asMap(null, null));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_keyIsNull_WHEN_asMap_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asMap(null, TestObjectSimple.class));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_valueIsNull_WHEN_asMap_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asMap(Integer.class, null));
    }

    @Test
    void GIVEN_map_json_AND_keyClass_valueClass_WHEN_asMap_THEN_store_assertResult() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      impl.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), is(TEST_MAP_A1_A2));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asMap_THEN_store_null_in_assertResult(String body) {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(body);

      // Act
      impl.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      var stored = context.getAssertOperand();
      assertThat(stored, notNullValue());
      assertThat(stored.actual(), nullValue());
    }

    @Test
    void GIVEN_wrong_keyClass_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Boolean.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("'content().asMap()' — Reason: Response body "));
      assertThat(
          ex.getMessage(), containsString("cannot be mapped to Map<Boolean, TestObjectSimple>"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_wrong_valueClass_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asMap(Integer.class, String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("'content().asMap()' — Reason: Response body "));
      assertThat(ex.getMessage(), containsString("cannot be mapped to Map<Integer, String>"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_wrong_key_and_value_class_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asMap(String.class, String.class));

      // Assert
      assertThat(ex.getMessage(), containsString("'content().asMap()' — Reason: Response body "));
      assertThat(ex.getMessage(), containsString("cannot be mapped to Map<String, String>"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn("{not-valid-json");

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("'content().asMap()' — Reason: Response body "));
      assertThat(
          ex.getMessage(), containsString("cannot be mapped to Map<Integer, TestObjectSimple>"));
      assertThat(context.getAssertOperand(), nullValue());
    }

    @Test
    void GIVEN_array_json_instead_of_object_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      when(context.getActResult().contentAsString()).thenReturn(TEST_LIST_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), containsString("'content().asMap()' — Reason: Response body "));
      assertThat(
          ex.getMessage(), containsString("cannot be mapped to Map<Integer, TestObjectSimple>"));
      assertThat(context.getAssertOperand(), nullValue());
    }
  }
}
