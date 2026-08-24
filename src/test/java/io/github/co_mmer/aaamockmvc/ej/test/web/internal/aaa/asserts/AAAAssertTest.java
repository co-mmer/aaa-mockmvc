package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedHeaderName;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedHeaderValue;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedHeaderValues;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedLength;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedSize;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.expectedStatus;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue.unexpectedHeaderName;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_A1_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_BOOLEAN;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_MAP_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_SET_A1_A2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.InvalidAssertionException;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertOperand;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.step.TestStepDto;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

final class AAAAssertTest {

  private static final String LINE_SEPARATOR = System.lineSeparator();

  private static final TestStepDto ANY_STEP = new TestStepDto("irrelevant step");
  private static final TestStepDto STEP_WITHOUT_NAME = new TestStepDto(null);
  private static final TestStepDto BLANK_STEP = new TestStepDto("   ");
  private static final TestStepDto NAMED_STEP = new TestStepDto("  Verify response  ");

  private static final AssertOperand<?, ?> ABSENT_COLLECTION = AssertOperand.collection(null);
  private static final AssertOperand<?, ?> ABSENT_BOOLEAN = AssertOperand.bool(null);
  private static final AssertOperand<?, ?> ABSENT_STRING = AssertOperand.string(null);
  private static final AssertOperand<?, ?> ABSENT_CLASS = AssertOperand.clazz(null);
  private static final AssertOperand<?, ?> ABSENT_MAP = AssertOperand.map(null);

  private static final AssertOperand<?, ?> PRESENT_BOOLEAN = AssertOperand.bool(TEST_BOOLEAN);
  private static final AssertOperand<?, ?> EMPTY_COLLECTION = AssertOperand.collection(List.of());
  private static final AssertOperand<?, ?> EMPTY_LIST = AssertOperand.list(List.of());
  private static final AssertOperand<?, ?> EMPTY_SET = AssertOperand.set(Set.of());
  private static final AssertOperand<?, ?> EMPTY_STRING = AssertOperand.string("");
  private static final AssertOperand<?, ?> EMPTY_BYTES = AssertOperand.bytes(new byte[0]);
  private static final AssertOperand<?, ?> EMPTY_MAP = AssertOperand.map(Map.of());
  private static final AssertOperand<?, ?> EMPTY_ARRAY = AssertOperand.clazz(new Object[0]);

  private static final AssertOperand<?, ?> NON_EMPTY_COLLECTION =
      AssertOperand.collection(TEST_LIST_A1_A2);
  private static final AssertOperand<?, ?> LIST_A1_A2 = AssertOperand.list(TEST_LIST_A1_A2);
  private static final AssertOperand<?, ?> NON_EMPTY_SET = AssertOperand.set(TEST_SET_A1_A2);
  private static final AssertOperand<?, ?> NON_EMPTY_STRING = AssertOperand.string(TEST_A1_JSON);
  private static final AssertOperand<?, ?> NON_EMPTY_BYTES =
      AssertOperand.bytes(TEST_A1_JSON.getBytes(StandardCharsets.UTF_8));
  private static final AssertOperand<?, ?> NON_EMPTY_MAP = AssertOperand.map(TEST_MAP_A1_A2);
  private static final AssertOperand<?, ?> NON_EMPTY_ARRAY =
      AssertOperand.clazz(TEST_LIST_A1_A2.toArray());

  private static final Predicate<Object> IS_A1 = A1::equals;
  private static final Predicate<Object> IS_A2 = A2::equals;
  private static final Predicate<Object> IS_A3 = A3::equals;
  private static final Predicate<Object> ALWAYS_TRUE = value -> true;
  private static final Predicate<Object> ALWAYS_FALSE = value -> false;

  @SuppressWarnings("unchecked")
  private static <V, N> AssertValue<V, N> assertValue(V value, N normalizedValue) {
    var result = (AssertValue<V, N>) mock(AssertValue.class);
    when(result.value()).thenReturn(value);
    when(result.normalizedValue()).thenReturn(normalizedValue);
    return result;
  }

  @SuppressWarnings("unchecked")
  private static <A, N> AssertOperand<A, N> assertOperand(A actual, N normalizedValue) {
    var result = (AssertOperand<A, N>) mock(AssertOperand.class);
    when(result.actual()).thenReturn(actual);
    when(result.normalizedValue()).thenReturn(normalizedValue);
    return result;
  }

  private static <E> AssertValue<Collection<E>, List<String>> collectionValue(Collection<E> value) {
    var normalizedValue = AssertOperand.collection(value).normalizedValue();
    return assertValue(value, normalizedValue);
  }

  @SafeVarargs
  private static AssertValue<Predicate<Object>[], Predicate<Object>[]> conditions(
      Predicate<Object>... conditions) {
    return assertValue(conditions, conditions);
  }

  private static AssertOperand<Map<String, List<String>>, Map<String, List<String>>> headers(
      String... values) {
    var headers = Map.of("X-Test", List.of(values));
    return assertOperand(headers, headers);
  }

  private static AssertOperand<HttpStatus, Integer> status(HttpStatus value) {
    return assertOperand(value, value == null ? null : value.value());
  }

  @Nested
  class Expect {

    @Test
    void WHEN_expect_is_called_THEN_AAAAssert_is_returned() {
      // Arrange

      // Act
      var result = AAAAssert.expect(ANY_STEP, LIST_A1_A2);

      // Assert
      assertThat(result, instanceOf(AAAAssert.class));
    }

    @Test
    void GIVEN_step_and_operand_WHEN_expect_is_called_THEN_both_are_used() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBePresent);

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }
  }

  @Nested
  class NotToBeEmpty {

    @ParameterizedTest(name = "{0}")
    @MethodSource("nonEmptyOperands")
    void GIVEN_non_empty_body_WHEN_notToBeEmpty_THEN_no_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> nonEmptyOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, nonEmptyOperand);

      // Act
      assertion.notToBeEmpty();

      // Assert
      // The method returning normally is the assertion.
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("emptyOperands")
    void GIVEN_empty_body_WHEN_notToBeEmpty_THEN_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> emptyOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, emptyOperand);

      // Act
      var error = assertThrows(AssertionError.class, assertion::notToBeEmpty);

      // Assert
      assertThat(error.getMessage(), containsString("The response body was empty."));
    }

    @Test
    void GIVEN_absent_body_WHEN_notToBeEmpty_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, assertion::notToBeEmpty);

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_notToBeEmpty_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, EMPTY_STRING);

      // Act
      var error = assertThrows(AssertionError.class, assertion::notToBeEmpty);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: not empty"
                  + LINE_SEPARATOR
                  + "Actual:   \"\""
                  + LINE_SEPARATOR
                  + "Reason:   The response body was empty."));
    }

    private static Stream<Arguments> nonEmptyOperands() {
      return Stream.of(
          arguments("collection", NON_EMPTY_COLLECTION),
          arguments("list", LIST_A1_A2),
          arguments("set", NON_EMPTY_SET),
          arguments("string", NON_EMPTY_STRING),
          arguments("bytes", NON_EMPTY_BYTES),
          arguments("map", NON_EMPTY_MAP),
          arguments("array", NON_EMPTY_ARRAY));
    }

    private static Stream<Arguments> emptyOperands() {
      return Stream.of(
          arguments("collection", EMPTY_COLLECTION),
          arguments("list", EMPTY_LIST),
          arguments("set", EMPTY_SET),
          arguments("string", EMPTY_STRING),
          arguments("bytes", EMPTY_BYTES),
          arguments("map", EMPTY_MAP),
          arguments("array", EMPTY_ARRAY));
    }
  }

  @Nested
  class ToHaveSize {

    @ParameterizedTest(name = "{0}")
    @MethodSource("sizedOperands")
    void GIVEN_body_with_expected_size_WHEN_toHaveSize_THEN_no_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> operand, AssertValue<?, Integer> expectedSize) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, operand);

      // Act
      assertion.toHaveSize(expectedSize);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_body_with_different_size_WHEN_toHaveSize_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(expectedSize(1)));

      // Assert
      assertThat(error.getMessage(), containsString("The response body had a different size."));
      assertThat(error.getMessage(), containsString("Actual:   size 2"));
    }

    @Test
    void GIVEN_negative_expected_size_WHEN_toHaveSize_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(expectedSize(-1)));

      // Assert
      assertThat(error.getMessage(), containsString("Expected: size -1"));
      assertThat(error.getMessage(), containsString("Actual:   size 0"));
    }

    @Test
    void GIVEN_absent_body_WHEN_toHaveSize_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(expectedSize(0)));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toHaveSize_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(expectedSize(2)));

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: size 2"
                  + LINE_SEPARATOR
                  + "Actual:   size 1"
                  + LINE_SEPARATOR
                  + "Reason:   The response body had a different size."));
    }

    private static Stream<Arguments> sizedOperands() {
      return Stream.of(
          arguments("empty collection", EMPTY_COLLECTION, expectedSize(0)),
          arguments("collection", NON_EMPTY_COLLECTION, expectedSize(2)),
          arguments("empty list", EMPTY_LIST, expectedSize(0)),
          arguments("list", LIST_A1_A2, expectedSize(2)),
          arguments("empty set", EMPTY_SET, expectedSize(0)),
          arguments("set", NON_EMPTY_SET, expectedSize(2)),
          arguments("empty string", EMPTY_STRING, expectedSize(0)),
          arguments("string", NON_EMPTY_STRING, expectedSize(TEST_A1_JSON.length())),
          arguments("empty bytes", EMPTY_BYTES, expectedSize(0)),
          arguments(
              "bytes",
              NON_EMPTY_BYTES,
              expectedSize(TEST_A1_JSON.getBytes(StandardCharsets.UTF_8).length)),
          arguments("empty map", EMPTY_MAP, expectedSize(0)),
          arguments("map", NON_EMPTY_MAP, expectedSize(2)),
          arguments("empty array", EMPTY_ARRAY, expectedSize(0)),
          arguments("array", NON_EMPTY_ARRAY, expectedSize(2)));
    }
  }

  @Nested
  class ToHaveLength {

    @ParameterizedTest(name = "{0}")
    @MethodSource("lengthOperands")
    void GIVEN_body_with_expected_length_WHEN_toHaveLength_THEN_no_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> operand, AssertValue<?, Integer> expectedLength) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, operand);

      // Act
      assertion.toHaveLength(expectedLength);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_body_with_different_length_WHEN_toHaveLength_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.toHaveLength(expectedLength(1)));

      // Assert
      assertThat(error.getMessage(), containsString("The response body had a different length."));
      assertThat(error.getMessage(), containsString("Actual:   length 2"));
    }

    @Test
    void GIVEN_negative_expected_length_WHEN_toHaveLength_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.toHaveLength(expectedLength(-1)));

      // Assert
      assertThat(error.getMessage(), containsString("Expected: length -1"));
      assertThat(error.getMessage(), containsString("Actual:   length 0"));
    }

    @Test
    void GIVEN_absent_body_WHEN_toHaveLength_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_STRING);

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.toHaveLength(expectedLength(0)));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toHaveLength_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.toHaveLength(expectedLength(2)));

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: length 2"
                  + LINE_SEPARATOR
                  + "Actual:   length 1"
                  + LINE_SEPARATOR
                  + "Reason:   The response body had a different length."));
    }

    private static Stream<Arguments> lengthOperands() {
      return Stream.of(
          arguments("empty collection", EMPTY_COLLECTION, expectedLength(0)),
          arguments("collection", NON_EMPTY_COLLECTION, expectedLength(2)),
          arguments("empty list", EMPTY_LIST, expectedLength(0)),
          arguments("list", LIST_A1_A2, expectedLength(2)),
          arguments("empty set", EMPTY_SET, expectedLength(0)),
          arguments("set", NON_EMPTY_SET, expectedLength(2)),
          arguments("empty string", EMPTY_STRING, expectedLength(0)),
          arguments("string", NON_EMPTY_STRING, expectedLength(TEST_A1_JSON.length())),
          arguments("empty bytes", EMPTY_BYTES, expectedLength(0)),
          arguments(
              "bytes",
              NON_EMPTY_BYTES,
              expectedLength(TEST_A1_JSON.getBytes(StandardCharsets.UTF_8).length)),
          arguments("empty map", EMPTY_MAP, expectedLength(0)),
          arguments("map", NON_EMPTY_MAP, expectedLength(2)),
          arguments("empty array", EMPTY_ARRAY, expectedLength(0)),
          arguments("array", NON_EMPTY_ARRAY, expectedLength(2)));
    }
  }

  @Nested
  class ToEqual {

    @ParameterizedTest(name = "{0}")
    @MethodSource("equalValues")
    void GIVEN_equal_normalized_values_WHEN_toEqual_THEN_no_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> operand, AssertValue<?, ?> expectedValue) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, operand);

      // Act
      assertion.toEqual(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_different_normalized_values_WHEN_toEqual_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.string(A));
      var expectedValue = assertValue("B", "B");

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toEqual(expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response body differed from the expected value."));
    }

    @Test
    void GIVEN_different_types_with_same_normalized_value_WHEN_toEqual_THEN_no_error_is_thrown() {
      // Arrange
      var actual = AssertOperand.clazz(A1);
      var assertion = AAAAssert.expect(ANY_STEP, actual);
      var expectedValue = assertValue(TEST_A1_JSON, actual.normalizedValue());

      // Act
      assertion.toEqual(expectedValue);

      // Assert
      // toEqual compares normalized values independently of their original types.
    }

    @Test
    void GIVEN_absent_body_WHEN_toEqual_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_STRING);
      var expectedValue = assertValue(A, A);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toEqual(expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toEqual_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));
      var expectedValue = assertValue("B", "B");

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toEqual(expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: B"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   The response body differed from the expected value."));
    }

    private static Stream<Arguments> equalValues() {
      var collection = AssertOperand.collection(TEST_LIST_A1_A2);
      var list = AssertOperand.list(TEST_LIST_A1_A2);
      var set = AssertOperand.set(TEST_SET_A1_A2);
      var clazz = AssertOperand.clazz(A1);
      var bytes = AssertOperand.bytes(TEST_A1_JSON.getBytes(StandardCharsets.UTF_8));
      var map = AssertOperand.map(TEST_MAP_A1_A2);

      return Stream.of(
          arguments(
              "collection", collection, assertValue(TEST_LIST_A1_A2, collection.normalizedValue())),
          arguments("list", list, assertValue(TEST_LIST_A1_A2, list.normalizedValue())),
          arguments("set", set, assertValue(TEST_SET_A1_A2, set.normalizedValue())),
          arguments("boolean", PRESENT_BOOLEAN, assertValue(TEST_BOOLEAN, TEST_BOOLEAN)),
          arguments("string", AssertOperand.string(A), assertValue(A, A)),
          arguments("class", clazz, assertValue(A1, clazz.normalizedValue())),
          arguments(
              "bytes",
              bytes,
              assertValue(
                  TEST_A1_JSON.getBytes(StandardCharsets.UTF_8),
                  TEST_A1_JSON.getBytes(StandardCharsets.UTF_8))),
          arguments("map", map, assertValue(TEST_MAP_A1_A2, map.normalizedValue())));
    }
  }

  @Nested
  class ToHaveSameTypeAndValueAs {

    @Test
    void GIVEN_same_type_and_value_WHEN_toHaveSameTypeAndValueAs_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.clazz(A1));
      var expectedValue = assertValue(A1, TEST_A1_JSON);

      // Act
      assertion.toHaveSameTypeAndValueAs(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_different_value_WHEN_toHaveSameTypeAndValueAs_THEN_only_type_is_compared() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.clazz(A1));
      var expectedValue = assertValue(A2, "any normalized value");

      // Act
      assertion.toHaveSameTypeAndValueAs(expectedValue);

      // Assert
      // The current implementation compares the runtime types only.
    }

    @Test
    void GIVEN_different_type_WHEN_toHaveSameTypeAndValueAs_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.bool(TEST_BOOLEAN));
      var expectedValue = assertValue(A, A);

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toHaveSameTypeAndValueAs(expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body had a different type."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toHaveSameTypeAndValueAs_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_STRING);
      var expectedValue = assertValue(A, A);

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toHaveSameTypeAndValueAs(expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toHaveSameTypeAndValueAs_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.bool(TEST_BOOLEAN));
      var expectedValue = assertValue(A, A);

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toHaveSameTypeAndValueAs(expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: A"
                  + LINE_SEPARATOR
                  + "Actual:   true"
                  + LINE_SEPARATOR
                  + "Reason:   The response body had a different type."));
    }
  }

  @Nested
  class ToContain {

    @Test
    void GIVEN_all_expected_elements_are_present_WHEN_toContain_THEN_no_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A1));

      // Act
      assertion.toContain(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_expected_elements_in_different_order_WHEN_toContain_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A2, A1));

      // Act
      assertion.toContain(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_empty_expected_collection_WHEN_toContain_THEN_InvalidAssertionException_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of());

      // Act
      var error =
          assertThrows(InvalidAssertionException.class, () -> assertion.toContain(expectedValue));

      // Assert
      var expectedMessage =
          System.lineSeparator()
              + """
              `toContain()` was called with an empty collection.

              Reason:
              Checking whether a collection contains an empty collection is always true.
              This creates a false-positive "green test" that does not verify production behavior.

              How to fix:
              -> To verify that the collection is empty, use:
                 isEmpty()

              -> To verify the number of elements, use:
                 hasSize(expectedSize)
              """
                  .strip();

      assertThat(expectedMessage, is(error.getMessage()));
    }

    @Test
    void GIVEN_duplicate_expected_element_WHEN_toContain_THEN_occurrence_count_is_ignored() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A1, A1));

      // Act
      assertion.toContain(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_missing_expected_element_WHEN_toContain_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A3));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toContain(expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response body did not contain every expected element."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toContain_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_COLLECTION);
      var expectedValue = collectionValue(List.of(A1));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toContain(expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toContain_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A3));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toContain(expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: contains"));
    }
  }

  @Nested
  class NotToContain {

    @Test
    void GIVEN_no_unexpected_element_is_present_WHEN_notToContain_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var unexpectedValue = collectionValue(List.of(A3));

      // Act
      assertion.notToContain(unexpectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_empty_unexpected_collection_WHEN_notToContain_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var unexpectedValue = collectionValue(List.of());

      // Act
      assertion.notToContain(unexpectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_empty_body_WHEN_notToContain_THEN_InvalidAssertionException_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);
      var unexpectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(
              InvalidAssertionException.class, () -> assertion.notToContain(unexpectedValue));

      // Assert
      var expectedMessage =
          System.lineSeparator()
              + """
              `notToContain()` was called for an empty response collection.

              Reason:
              Checking whether an empty response collection does not contain an element
              is always true. This creates a false-positive "green test" that does not verify
              the provided unexpected value.

              How to fix:
              -> If the response collection must contain elements, assert this first with:
                 isNotEmpty()

              -> If an empty response collection is expected, use:
                 isEmpty()
              """
                  .strip();

      assertThat(expectedMessage, is(error.getMessage()));
    }

    @Test
    void GIVEN_one_unexpected_element_is_present_WHEN_notToContain_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var unexpectedValue = collectionValue(List.of(A1, A3));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.notToContain(unexpectedValue));

      // Assert
      assertThat(
          error.getMessage(), containsString("The response body contained an unexpected element."));
    }

    @Test
    void GIVEN_absent_body_WHEN_notToContain_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_COLLECTION);
      var unexpectedValue = collectionValue(List.of(A1));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.notToContain(unexpectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_notToContain_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, LIST_A1_A2);
      var unexpectedValue = collectionValue(List.of(A1));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.notToContain(unexpectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: does not contain"));
    }
  }

  @Nested
  class ToContainExactlyInAnyOrder {

    @Test
    void GIVEN_reordered_elements_WHEN_toContainExactlyInAnyOrder_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A2, A1));

      // Act
      assertion.toContainExactlyInAnyOrder(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_equal_duplicate_counts_WHEN_toContainExactlyInAnyOrder_THEN_no_error_is_thrown() {
      // Arrange
      var actual = AssertOperand.list(List.of(A1, A1, A2));
      var assertion = AAAAssert.expect(ANY_STEP, actual);
      var expectedValue = collectionValue(List.of(A2, A1, A1));

      // Act
      assertion.toContainExactlyInAnyOrder(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_empty_collections_WHEN_toContainExactlyInAnyOrder_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);
      var expectedValue = collectionValue(List.of());

      // Act
      assertion.toContainExactlyInAnyOrder(expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_different_duplicate_counts_WHEN_toContainExactlyInAnyOrder_THEN_error_is_thrown() {
      // Arrange
      var actual = AssertOperand.list(List.of(A1, A1, A2));
      var assertion = AAAAssert.expect(ANY_STEP, actual);
      var expectedValue = collectionValue(List.of(A1, A2, A2));

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainExactlyInAnyOrder(expectedValue));

      // Assert
      assertThat(
          error.getMessage(), containsString("The response body contained different elements."));
    }

    @Test
    void GIVEN_additional_actual_element_WHEN_toContainExactlyInAnyOrder_THEN_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainExactlyInAnyOrder(expectedValue));

      // Assert
      assertThat(
          error.getMessage(), containsString("The response body contained different elements."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toContainExactlyInAnyOrder_THEN_absent_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_COLLECTION);
      var expectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainExactlyInAnyOrder(expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toContainExactlyInAnyOrder_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainExactlyInAnyOrder(expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: contains in any order"));
    }
  }

  @Nested
  class ToMatchAll {

    @Test
    void GIVEN_every_value_matches_every_condition_WHEN_toMatchAll_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions(Objects::nonNull, value -> value.equals(A1) || value.equals(A2));

      // Act
      assertion.toMatchAll(conditions);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_single_value_matches_every_condition_WHEN_toMatchAll_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.clazz(A1));
      var conditions = conditions(IS_A1, ALWAYS_TRUE);

      // Act
      assertion.toMatchAll(conditions);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_empty_collection_WHEN_toMatchAll_THEN_InvalidAssertionException_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);
      var conditions = conditions(ALWAYS_FALSE);

      // Act
      var error =
          assertThrows(InvalidAssertionException.class, () -> assertion.toMatchAll(conditions));

      // Assert
      var expectedMessage =
          System.lineSeparator()
              + """
              `toMatchAll()` was called for an empty response collection.

              Reason:
              Checking whether all elements of an empty response collection match the conditions
              is always true. This creates a false-positive "green test" that does not verify
              the provided conditions.

              How to fix:
              -> If the response collection must contain elements, assert this first with:
                 isNotEmpty().toMatchAll(...);

              -> If an empty response collection is expected, use:
                 isEmpty()
              """
                  .strip();

      assertThat(expectedMessage, is(error.getMessage()));
    }

    @Test
    void GIVEN_no_conditions_WHEN_toMatchAll_THEN_no_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions();

      // Act
      assertion.toMatchAll(conditions);

      // Assert
      // Every value satisfies an empty set of conditions.
    }

    @Test
    void GIVEN_one_value_fails_one_condition_WHEN_toMatchAll_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions(IS_A1);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAll(conditions));

      // Assert
      assertThat(
          error.getMessage(), containsString("At least one value did not match every condition."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toMatchAll_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_CLASS);
      var conditions = conditions(ALWAYS_TRUE);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAll(conditions));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toMatchAll_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));
      var conditions = conditions(ALWAYS_FALSE);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAll(conditions));

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: matches all conditions"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   At least one value did not match every condition."));
    }
  }

  @Nested
  class ToMatchAny {

    @Test
    void GIVEN_one_value_matches_one_condition_WHEN_toMatchAny_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions(IS_A3, IS_A2);

      // Act
      assertion.toMatchAny(conditions);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_single_value_matches_condition_WHEN_toMatchAny_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.clazz(A1));
      var conditions = conditions(IS_A1);

      // Act
      assertion.toMatchAny(conditions);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_no_value_matches_any_condition_WHEN_toMatchAny_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions(IS_A3);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAny(conditions));

      // Assert
      assertThat(error.getMessage(), containsString("No value matched any condition."));
    }

    @Test
    void GIVEN_empty_collection_WHEN_toMatchAny_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);
      var conditions = conditions(ALWAYS_TRUE);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAny(conditions));

      // Assert
      assertThat(error.getMessage(), containsString("No value matched any condition."));
    }

    @Test
    void GIVEN_no_conditions_WHEN_toMatchAny_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions();

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAny(conditions));

      // Assert
      assertThat(error.getMessage(), containsString("No value matched any condition."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toMatchAny_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_CLASS);
      var conditions = conditions(ALWAYS_TRUE);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAny(conditions));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toMatchAny_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));
      var conditions = conditions(ALWAYS_FALSE);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchAny(conditions));

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: matches any conditions"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   No value matched any condition."));
    }
  }

  @Nested
  class ToMatchNone {

    @Test
    void GIVEN_no_value_matches_any_condition_WHEN_toMatchNone_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions(IS_A3);

      // Act
      assertion.toMatchNone(conditions);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_single_value_matches_no_condition_WHEN_toMatchNone_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.clazz(A1));
      var conditions = conditions(IS_A3, ALWAYS_FALSE);

      // Act
      assertion.toMatchNone(conditions);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_empty_collection_WHEN_toMatchNone_THEN_no_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);
      var conditions = conditions(ALWAYS_TRUE);

      // Act
      assertion.toMatchNone(conditions);

      // Assert
      // No value in an empty collection can match a condition.
    }

    @Test
    void GIVEN_no_conditions_WHEN_toMatchNone_THEN_no_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions();

      // Act
      assertion.toMatchNone(conditions);

      // Assert
      // No value can match an empty set of conditions.
    }

    @Test
    void GIVEN_one_value_matches_condition_WHEN_toMatchNone_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var conditions = conditions(IS_A2);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchNone(conditions));

      // Assert
      assertThat(error.getMessage(), containsString("At least one value matched a condition."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toMatchNone_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_CLASS);
      var conditions = conditions(ALWAYS_FALSE);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchNone(conditions));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toMatchNone_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));
      var conditions = conditions(ALWAYS_TRUE);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toMatchNone(conditions));

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: matches no conditions"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   At least one value matched a condition."));
    }
  }

  @Nested
  class ToContainKey {

    @Test
    void GIVEN_expected_header_key_is_present_WHEN_toContainKey_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Test");

      // Act
      assertion.toContainKey(expectedKey);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_expected_header_key_is_missing_WHEN_toContainKey_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Missing");

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toContainKey(expectedKey));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response headers did not contain the expected key."));
    }

    @Test
    void GIVEN_absent_headers_WHEN_toContainKey_THEN_absent_body_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, assertOperand(null, null));
      var expectedKey = expectedHeaderName("X-Test");

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toContainKey(expectedKey));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toContainKey_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Missing");

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toContainKey(expectedKey));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: X-Missing"));
    }
  }

  @Nested
  class NotToContainKey {

    @Test
    void GIVEN_unexpected_header_key_is_missing_WHEN_notToContainKey_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var unexpectedKey = unexpectedHeaderName("X-Missing");

      // Act
      assertion.notToContainKey(unexpectedKey);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_unexpected_header_key_is_present_WHEN_notToContainKey_THEN_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var unexpectedKey = unexpectedHeaderName("X-Test");

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.notToContainKey(unexpectedKey));

      // Assert
      assertThat(
          error.getMessage(), containsString("The response headers contained the unexpected key."));
    }

    @Test
    void GIVEN_absent_headers_WHEN_notToContainKey_THEN_absent_body_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, assertOperand(null, null));
      var unexpectedKey = unexpectedHeaderName("X-Test");

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.notToContainKey(unexpectedKey));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_notToContainKey_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, headers(A));
      var unexpectedKey = unexpectedHeaderName("X-Test");

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.notToContainKey(unexpectedKey));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: does not contain key X-Test"));
    }
  }

  @Nested
  class ToContainEntry {

    @Test
    void GIVEN_header_contains_expected_value_WHEN_toContainEntry_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A, "B"));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValue = expectedHeaderValue("B");

      // Act
      assertion.toContainEntry(expectedKey, expectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_header_key_is_missing_WHEN_toContainEntry_THEN_key_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Missing");
      var expectedValue = expectedHeaderValue(A);

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainEntry(expectedKey, expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response headers did not contain the expected key."));
    }

    @Test
    void GIVEN_header_value_is_missing_WHEN_toContainEntry_THEN_value_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValue = expectedHeaderValue("B");

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainEntry(expectedKey, expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response header did not contain the expected value."));
    }

    @Test
    void GIVEN_header_value_is_not_a_collection_WHEN_toContainEntry_THEN_error_is_thrown() {
      // Arrange
      var actualHeaders = Map.of("X-Test", A);
      var assertion = AAAAssert.expect(ANY_STEP, assertOperand(actualHeaders, actualHeaders));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValue = expectedHeaderValue(A);

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainEntry(expectedKey, expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response header did not contain the expected value."));
    }

    @Test
    void GIVEN_absent_headers_WHEN_toContainEntry_THEN_absent_body_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, assertOperand(null, null));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValue = expectedHeaderValue(A);

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainEntry(expectedKey, expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toContainEntry_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValue = expectedHeaderValue("B");

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toContainEntry(expectedKey, expectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: B"));
    }
  }

  @Nested
  class ToContainEntryExactly {

    @Test
    void
        GIVEN_header_values_match_in_any_order_WHEN_toContainEntryExactly_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A, "B"));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValues = expectedHeaderValues("B", A);

      // Act
      assertion.toContainEntryExactly(expectedKey, expectedValues);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_header_duplicate_counts_match_WHEN_toContainEntryExactly_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A, A, "B"));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValues = expectedHeaderValues(A, "B", A);

      // Act
      assertion.toContainEntryExactly(expectedKey, expectedValues);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void
        GIVEN_empty_expected_values_WHEN_toContainEntryExactly_THEN_InvalidAssertionException_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValues = expectedHeaderValues();

      // Act
      var error =
          assertThrows(
              InvalidAssertionException.class,
              () -> assertion.toContainEntryExactly(expectedKey, expectedValues));

      // Assert
      var expectedMessage =
          LINE_SEPARATOR
              + """
              `toContainEntryExactly()` was called with an empty collection.

              Reason:
              An exact header entry assertion requires at least one expected value.
              Without an expected value, the assertion does not verify a header value.

              How to fix:
              -> Provide at least one expected header value.
              """
                  .strip();

      assertThat(error.getMessage(), is(expectedMessage));
    }

    @Test
    void GIVEN_header_key_is_missing_WHEN_toContainEntryExactly_THEN_key_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Missing");
      var expectedValues = expectedHeaderValues(A);

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toContainEntryExactly(expectedKey, expectedValues));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response headers did not contain the expected key."));
    }

    @Test
    void GIVEN_header_values_differ_WHEN_toContainEntryExactly_THEN_value_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, headers(A, A, "B"));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValues = expectedHeaderValues(A, "B", "B");

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toContainEntryExactly(expectedKey, expectedValues));

      // Assert
      assertThat(
          error.getMessage(), containsString("The response header contained different values."));
    }

    @Test
    void GIVEN_header_value_is_not_a_collection_WHEN_toContainEntryExactly_THEN_error_is_thrown() {
      // Arrange
      var actualHeaders = Map.of("X-Test", A);
      var assertion = AAAAssert.expect(ANY_STEP, assertOperand(actualHeaders, actualHeaders));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValues = expectedHeaderValues(A);

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toContainEntryExactly(expectedKey, expectedValues));

      // Assert
      assertThat(
          error.getMessage(), containsString("The response header contained different values."));
    }

    @Test
    void GIVEN_absent_headers_WHEN_toContainEntryExactly_THEN_absent_body_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, assertOperand(null, null));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValues = expectedHeaderValues(A);

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toContainEntryExactly(expectedKey, expectedValues));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toContainEntryExactly_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, headers(A));
      var expectedKey = expectedHeaderName("X-Test");
      var expectedValues = expectedHeaderValues("B");

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toContainEntryExactly(expectedKey, expectedValues));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: [B]"));
    }
  }

  @Nested
  class ToHaveStatus {

    @Test
    void GIVEN_equal_numeric_status_WHEN_toHaveStatus_THEN_no_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, status(HttpStatus.OK));

      // Act
      assertion.toHaveStatus(expectedStatus(200));

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_different_status_WHEN_toHaveStatus_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, status(HttpStatus.NOT_FOUND));

      // Act
      var error =
          assertThrows(
              AssertionError.class, () -> assertion.toHaveStatus(expectedStatus(HttpStatus.OK)));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response status differed from the expected status."));
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_absent_status_WHEN_toHaveStatus_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, status(null));

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.toHaveStatus(expectedStatus(200)));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response status differed from the expected status."));
      assertThat(error.getMessage(), containsString("Actual:   null"));
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_named_step_WHEN_toHaveStatus_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, status(HttpStatus.NOT_FOUND));

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.toHaveStatus(expectedStatus(200)));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: 200"));
      assertThat(error.getMessage(), containsString("Actual:   404"));
    }
  }

  @Nested
  class ToBeInRange {

    @ParameterizedTest
    @MethodSource("statusesInRange")
    void GIVEN_status_is_in_inclusive_range_WHEN_toBeInRange_THEN_no_error_is_thrown(
        AssertOperand<?, Integer> actualStatus) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, actualStatus);

      // Act
      assertion.toBeInRange(expectedStatus(200), expectedStatus(299));

      // Assert
      // The method returning normally is the assertion.
    }

    @ParameterizedTest
    @MethodSource("statusesOutsideRange")
    @SuppressWarnings("java:S5778")
    void GIVEN_status_is_outside_range_WHEN_toBeInRange_THEN_AssertionError_is_thrown(
        AssertOperand<?, Integer> actualStatus) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, actualStatus);

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toBeInRange(expectedStatus(200), expectedStatus(299)));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response status was outside the expected range."));
    }

    @Test
    @SuppressWarnings("java:S5778")
    void
        GIVEN_minimum_is_greater_than_maximum_WHEN_toBeInRange_THEN_InvalidAssertionException_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, status(HttpStatus.OK));

      // Act
      var error =
          assertThrows(
              InvalidAssertionException.class,
              () -> assertion.toBeInRange(expectedStatus(300), expectedStatus(200)));

      // Assert
      var expectedMessage =
          LINE_SEPARATOR
              + """
              `isInRange()` was called with an invalid range.

              Reason:
              The minimum value must not be greater than the maximum value.

              Actual range:
              -> minimum: 300
              -> maximum: 200

              How to fix:
              -> Provide a minimum value that is less than or equal to the maximum value.
              """
                  .strip();

      assertThat(error.getMessage(), is(expectedMessage));
    }

    @Test
    @SuppressWarnings("java:S5778")
    void GIVEN_named_step_WHEN_toBeInRange_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, status(HttpStatus.NOT_FOUND));

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toBeInRange(expectedStatus(200), expectedStatus(299)));

      // Assert
      assertThat(error.getMessage(), containsString("Step:     Verify response"));
      assertThat(error.getMessage(), containsString("Expected: in range [200, 299]"));
    }

    private static Stream<AssertOperand<?, Integer>> statusesInRange() {
      return Stream.of(status(HttpStatus.OK), assertOperand(299, 299));
    }

    private static Stream<AssertOperand<?, Integer>> statusesOutsideRange() {
      return Stream.of(assertOperand(199, 199), status(HttpStatus.NOT_FOUND));
    }
  }

  @Nested
  class ToBePresent {

    @ParameterizedTest(name = "{0}")
    @MethodSource("presentOperands")
    void GIVEN_present_body_WHEN_toBePresent_THEN_no_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> presentOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, presentOperand);

      // Act
      assertion.toBePresent();

      // Assert
      // The method returning normally is the assertion.
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("absentOperands")
    void GIVEN_absent_body_WHEN_toBePresent_THEN_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> absentOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, absentOperand);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBePresent);

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_step_name_is_null_WHEN_toBePresent_fails_THEN_message_has_no_step() {
      // Arrange
      var assertion = AAAAssert.expect(STEP_WITHOUT_NAME, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBePresent);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Expected: not null"
                  + LINE_SEPARATOR
                  + "Actual:   null"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was absent."));
    }

    @Test
    void GIVEN_step_name_is_blank_WHEN_toBePresent_fails_THEN_message_has_no_step() {
      // Arrange
      var assertion = AAAAssert.expect(BLANK_STEP, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBePresent);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Expected: not null"
                  + LINE_SEPARATOR
                  + "Actual:   null"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toBePresent_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBePresent);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: not null"
                  + LINE_SEPARATOR
                  + "Actual:   null"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was absent."));
    }

    private static Stream<Arguments> presentOperands() {
      return Stream.of(
          arguments("empty collection", EMPTY_COLLECTION),
          arguments("empty list", EMPTY_LIST),
          arguments("empty set", EMPTY_SET),
          arguments("boolean", PRESENT_BOOLEAN),
          arguments("empty string", EMPTY_STRING),
          arguments("empty bytes", EMPTY_BYTES),
          arguments("empty map", EMPTY_MAP),
          arguments("empty array", EMPTY_ARRAY));
    }

    private static Stream<Arguments> absentOperands() {
      return Stream.of(
          arguments("collection", ABSENT_COLLECTION),
          arguments("boolean", ABSENT_BOOLEAN),
          arguments("string", ABSENT_STRING),
          arguments("class", ABSENT_CLASS),
          arguments("map", ABSENT_MAP));
    }
  }

  @Nested
  class ToBeAbsent {

    @ParameterizedTest(name = "{0}")
    @MethodSource("absentOperands")
    void GIVEN_absent_body_WHEN_toBeAbsent_THEN_no_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> absentOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, absentOperand);

      // Act
      assertion.toBeAbsent();

      // Assert
      // The method returning normally is the assertion.
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("presentOperands")
    void GIVEN_present_body_WHEN_toBeAbsent_THEN_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> presentOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, presentOperand);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeAbsent);

      // Assert
      assertThat(error.getMessage(), containsString("The response body was present."));
    }

    @Test
    void GIVEN_step_name_is_null_WHEN_toBeAbsent_fails_THEN_message_has_no_step() {
      // Arrange
      var assertion = AAAAssert.expect(STEP_WITHOUT_NAME, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeAbsent);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Expected: null"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was present."));
    }

    @Test
    void GIVEN_step_name_is_blank_WHEN_toBeAbsent_fails_THEN_message_has_no_step() {
      // Arrange
      var assertion = AAAAssert.expect(BLANK_STEP, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeAbsent);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Expected: null"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was present."));
    }

    @Test
    void GIVEN_named_step_WHEN_toBeAbsent_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeAbsent);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: null"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was present."));
    }

    private static Stream<Arguments> absentOperands() {
      return Stream.of(
          arguments("collection", ABSENT_COLLECTION),
          arguments("boolean", ABSENT_BOOLEAN),
          arguments("string", ABSENT_STRING),
          arguments("class", ABSENT_CLASS),
          arguments("map", ABSENT_MAP));
    }

    private static Stream<Arguments> presentOperands() {
      return Stream.of(
          arguments("empty collection", EMPTY_COLLECTION),
          arguments("empty list", EMPTY_LIST),
          arguments("empty set", EMPTY_SET),
          arguments("boolean", PRESENT_BOOLEAN),
          arguments("empty string", EMPTY_STRING),
          arguments("empty bytes", EMPTY_BYTES),
          arguments("empty map", EMPTY_MAP),
          arguments("empty array", EMPTY_ARRAY));
    }
  }

  @Nested
  class ToBeEmpty {

    @ParameterizedTest(name = "{0}")
    @MethodSource("emptyOperands")
    void GIVEN_empty_body_WHEN_toBeEmpty_THEN_no_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> emptyOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, emptyOperand);

      // Act
      assertion.toBeEmpty();

      // Assert
      // The method returning normally is the assertion.
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("nonEmptyOperands")
    void GIVEN_non_empty_body_WHEN_toBeEmpty_THEN_AssertionError_is_thrown(
        String useCase, AssertOperand<?, ?> nonEmptyOperand) {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, nonEmptyOperand);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeEmpty);

      // Assert
      assertThat(error.getMessage(), containsString("The response body was not empty."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toBeEmpty_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeEmpty);

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_step_name_is_null_WHEN_toBeEmpty_fails_THEN_message_has_no_step() {
      // Arrange
      var assertion = AAAAssert.expect(STEP_WITHOUT_NAME, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeEmpty);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Expected: empty"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was not empty."));
    }

    @Test
    void GIVEN_step_name_is_blank_WHEN_toBeEmpty_fails_THEN_message_has_no_step() {
      // Arrange
      var assertion = AAAAssert.expect(BLANK_STEP, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeEmpty);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Expected: empty"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was not empty."));
    }

    @Test
    void GIVEN_named_step_WHEN_toBeEmpty_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, assertion::toBeEmpty);

      // Assert
      assertThat(
          error.getMessage(),
          equalTo(
              LINE_SEPARATOR
                  + "Step:     Verify response"
                  + LINE_SEPARATOR
                  + "Expected: empty"
                  + LINE_SEPARATOR
                  + "Actual:   A"
                  + LINE_SEPARATOR
                  + "Reason:   The response body was not empty."));
    }

    private static Stream<Arguments> emptyOperands() {
      return Stream.of(
          arguments("collection", EMPTY_COLLECTION),
          arguments("list", EMPTY_LIST),
          arguments("set", EMPTY_SET),
          arguments("string", EMPTY_STRING),
          arguments("bytes", EMPTY_BYTES),
          arguments("map", EMPTY_MAP),
          arguments("array", EMPTY_ARRAY));
    }

    private static Stream<Arguments> nonEmptyOperands() {
      return Stream.of(
          arguments("collection", NON_EMPTY_COLLECTION),
          arguments("list", LIST_A1_A2),
          arguments("set", NON_EMPTY_SET),
          arguments("string", NON_EMPTY_STRING),
          arguments("bytes", NON_EMPTY_BYTES),
          arguments("map", NON_EMPTY_MAP),
          arguments("array", NON_EMPTY_ARRAY));
    }
  }
}
