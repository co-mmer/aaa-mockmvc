package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.content;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
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
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool.BooleanValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bool.TestAssertBooleanImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes.ByteValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.bytes.TestAssertByteImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz.ObjectValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz.TestAssertClassImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection.CollectionValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection.TestAssertCollectionImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map.MapValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.map.TestAssertMapImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.StringValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestAssertStringImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.context.TestAAAContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedConstruction;
import org.opentest4j.AssertionFailedError;

class TestAssertContentImplTest {

  private TestAAAContext context;
  private TestAssertContentImpl impl;
  private Object actual;
  private List<MockedConstruction<?>> mockedConstructors;

  @BeforeEach
  void setUp() {
    this.context = TestContext.mockContext();
    this.actual = null;
    this.mockedConstructors =
        List.of(
            captureActual(TestAssertBooleanImpl.class),
            captureActual(TestAssertStringImpl.class),
            captureActual(TestAssertByteImpl.class),
            captureActual(TestAssertClassImpl.class),
            captureActual(TestAssertCollectionImpl.class),
            captureActual(TestAssertMapImpl.class));

    useBody("");
  }

  @AfterEach
  void tearDown() {
    for (var constructor : this.mockedConstructors) {
      constructor.close();
    }
  }

  private <T> MockedConstruction<T> captureActual(Class<T> type) {
    return mockConstruction(
        type, (mock, construction) -> this.actual = construction.arguments().get(1));
  }

  private void useBody(String body) {
    when(this.context.getActResult().contentAsString()).thenReturn(body);
    when(this.context.getActResult().contentAsBytes())
        .thenReturn(body.getBytes(StandardCharsets.UTF_8));
    this.impl = new TestAssertContentImpl(this.context);
  }

  private void useBytes(byte[] bytes) {
    when(this.context.getActResult().contentAsBytes()).thenReturn(bytes);
    when(this.context.getActResult().contentAsString())
        .thenReturn(new String(bytes, StandardCharsets.UTF_8));
    this.impl = new TestAssertContentImpl(this.context);
  }

  @Nested
  class asBoolean {

    @Test
    void GIVEN_empty_body_WHEN_asBoolean_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(EMPTY);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asBoolean());

      // Assert
      assertThat(
          ex.getMessage(),
          is("asBoolean() requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_valid_json_AND_expectedClass_WHEN_asBoolean_THEN_pass_value_to_assertion() {
      // Arrange
      useBody(TEST_BOOLEAN_JSON);

      // Act
      impl.asBoolean();

      // Assert
      var stored = (BooleanValue) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.value(), is(TEST_BOOLEAN));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asBoolean_THEN_throw_AssertionFailedError(String body) {
      // Arrange
      useBody(body);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asBoolean());

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as Boolean."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_mapping_WHEN_asBoolean_THEN_throw_AssertionFailedError() {
      useBody(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asBoolean());

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as Boolean."));
      assertThat(actual, nullValue());
    }
  }

  @Nested
  class asString {

    @Test
    void GIVEN_valid_string_WHEN_asString_THEN_pass_value_to_assertion() {
      // Arrange
      useBody(TEST_A1_JSON);

      // Act
      impl.asString();

      // Assert
      var stored = (StringValue) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.value(), is(TEST_A1_JSON));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asString_THEN_pass_blank_to_assertion(String body) {
      // Arrange
      useBody(body);

      // Act
      impl.asString();

      // Assert
      var stored = (StringValue) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.value(), is(body));
    }

    @Test
    void GIVEN_empty_body_WHEN_asString_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asString());

      // Assert
      assertThat(
          ex.getMessage(),
          is("asString() requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }
  }

  @Nested
  class asBytes {

    @Test
    void GIVEN_valid_bytes_WHEN_asBytes_THEN_pass_value_to_assertion() {
      // Arrange
      var bytes = TEST_A1_JSON.getBytes(StandardCharsets.UTF_8);
      useBytes(bytes);

      // Act
      impl.asBytes();

      // Assert
      var stored = (ByteValue) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.value(), is(bytes));
    }

    @Test
    void GIVEN_empty_bytes_WHEN_asBytes_THEN_throw_AssertionFailedError() {
      // Arrange
      var bytes = new byte[0];
      useBytes(bytes);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asBytes());

      // Assert
      assertThat(
          ex.getMessage(),
          is("asBytes() requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }
  }

  @Nested
  class asClass {

    @Test
    void GIVEN_empty_body_WHEN_asClass_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asClass(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is("asClass(...) requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asClass(null));
    }

    @Test
    void GIVEN_valid_json_AND_expectedClass_WHEN_asClass_THEN_pass_value_to_assertion() {
      // Arrange
      useBody(TEST_A1_JSON);

      // Act
      impl.asClass(TestObjectSimple.class);

      // Assert
      var stored = (ObjectValue<?>) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.value(), is(A1));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asClass_THEN_throw_AssertionFailedError(String body) {
      // Arrange
      useBody(body);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asClass(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as TestObjectSimple."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_mapping_WHEN_asClass_THEN_throw_AssertionFailedError() {
      useBody(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asClass(String.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as String."));
      assertThat(actual, nullValue());
    }
  }

  @Nested
  class asCollection {

    @Test
    void GIVEN_empty_body_WHEN_asCollection_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("");

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asCollection(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is(
              "asCollection(...) requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asCollection(null));
    }

    @Test
    void GIVEN_list_json_AND_elementClass_WHEN_asCollection_THEN_pass_value_to_assertion() {
      // Arrange
      useBody(TEST_LIST_A1_A2_JSON);

      // Act
      impl.asCollection(TestObjectSimple.class);

      // Assert
      var stored = (CollectionValue<?>) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.values(), is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asCollection_THEN_throw_AssertionFailedError(String body) {
      // Arrange
      useBody(body);

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asCollection(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_mapping_WHEN_asCollection_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_LIST_A1_A2_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asCollection(String.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asCollection_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("{not-valid-json");

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asCollection(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }
  }

  @Nested
  class asList {

    @Test
    void GIVEN_empty_body_WHEN_asList_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is("asList(...) requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asList(null));
    }

    @Test
    void GIVEN_list_json_AND_elementClass_WHEN_asList_THEN_pass_value_to_assertion() {
      // Arrange
      useBody(TEST_LIST_A1_A2_JSON);

      // Act
      impl.asList(TestObjectSimple.class);

      // Assert
      var stored = (CollectionValue<?>) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.values(), is(TEST_LIST_A1_A2));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asList_THEN_throw_AssertionFailedError(String body) {
      // Arrange
      useBody(body);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_elementClass_WHEN_asList_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_LIST_A1_A2_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(String.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asList_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("{not-valid-json");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_object_json_instead_of_array_WHEN_asList_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asList(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }
  }

  @Nested
  class asSet {

    @Test
    void GIVEN_empty_body_WHEN_asSet_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is("asSet(...) requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }

    @Test
    @SuppressWarnings("all")
    void WHEN_null_THEN_throwException() {
      assertThrows(IllegalArgumentException.class, () -> impl.asSet(null));
    }

    @Test
    void GIVEN_set_json_AND_elementClass_WHEN_asSet_THEN_pass_value_to_assertion() {
      // Arrange
      useBody(TEST_SET_A1_A2_JSON);

      // Act
      impl.asSet(TestObjectSimple.class);

      // Assert
      var stored = (CollectionValue<?>) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.values(), is(TEST_SET_A1_A2));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asSet_THEN_throw_AssertionFailedError(String body) {
      // Arrange
      useBody(body);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_elementClass_WHEN_asSet_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_SET_A1_A2_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(String.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asSet_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("{not-valid-json");

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_object_json_instead_of_array_WHEN_asSet_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_A1_JSON);

      // Act
      var ex = assertThrows(AssertionFailedError.class, () -> impl.asSet(TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a collection."));
      assertThat(actual, nullValue());
    }
  }

  @Nested
  class asMap {

    @Test
    void GIVEN_empty_body_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("");

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(
          ex.getMessage(),
          is("asMap(...) requires a non-empty response body, but the response body was empty."));
      assertThat(actual, nullValue());
    }

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
    void GIVEN_map_json_AND_keyClass_valueClass_WHEN_asMap_THEN_pass_value_to_assertion() {
      // Arrange
      useBody(TEST_MAP_A1_A2_JSON);

      // Act
      impl.asMap(Integer.class, TestObjectSimple.class);

      // Assert
      var stored = (MapValue<?, ?>) actual;
      assertThat(stored, notNullValue());
      assertThat(stored.values(), is(TEST_MAP_A1_A2));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "   "})
    void GIVEN_blank_body_WHEN_asMap_THEN_throw_AssertionFailedError(String body) {
      // Arrange
      useBody(body);

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a map."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_wrong_keyClass_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Boolean.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a map."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_wrong_valueClass_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asMap(Integer.class, String.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a map."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_wrong_key_and_value_class_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_MAP_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(AssertionFailedError.class, () -> impl.asMap(String.class, String.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a map."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_invalid_json_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody("{not-valid-json");

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a map."));
      assertThat(actual, nullValue());
    }

    @Test
    void GIVEN_array_json_instead_of_object_WHEN_asMap_THEN_throw_AssertionFailedError() {
      // Arrange
      useBody(TEST_LIST_A1_A2_JSON);

      // Act
      var ex =
          assertThrows(
              AssertionFailedError.class, () -> impl.asMap(Integer.class, TestObjectSimple.class));

      // Assert
      assertThat(ex.getMessage(), is("The response body could not be read as a map."));
      assertThat(actual, nullValue());
    }
  }
}
