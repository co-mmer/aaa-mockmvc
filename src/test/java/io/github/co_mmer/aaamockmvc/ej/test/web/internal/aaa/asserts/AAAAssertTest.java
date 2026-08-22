package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

  private static <E> AssertValue<Collection<E>, List<String>> collectionValue(
      Collection<E> value) {
    var normalizedValue = AssertOperand.collection(value).normalizedValue();
    return assertValue(value, normalizedValue);
  }

  @SafeVarargs
  private static AssertValue<Predicate<Object>[], Predicate<Object>[]> conditions(
      Predicate<Object>... conditions) {
    return assertValue(conditions, conditions);
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
                  + "Actual:   "
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
        String useCase, AssertOperand<?, ?> operand, int expectedSize) {
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
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(1));

      // Assert
      assertThat(error.getMessage(), containsString("The response body had a different size."));
      assertThat(error.getMessage(), containsString("Actual:   size 2"));
    }

    @Test
    void GIVEN_negative_expected_size_WHEN_toHaveSize_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(-1));

      // Assert
      assertThat(error.getMessage(), containsString("Expected: size -1"));
      assertThat(error.getMessage(), containsString("Actual:   size 0"));
    }

    @Test
    void GIVEN_absent_body_WHEN_toHaveSize_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_STRING);

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(0));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_toHaveSize_fails_THEN_trimmed_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, AssertOperand.string(A));

      // Act
      var error = assertThrows(AssertionError.class, () -> assertion.toHaveSize(2));

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
          arguments("empty collection", EMPTY_COLLECTION, 0),
          arguments("collection", NON_EMPTY_COLLECTION, 2),
          arguments("empty list", EMPTY_LIST, 0),
          arguments("list", LIST_A1_A2, 2),
          arguments("empty set", EMPTY_SET, 0),
          arguments("set", NON_EMPTY_SET, 2),
          arguments("empty string", EMPTY_STRING, 0),
          arguments("string", NON_EMPTY_STRING, TEST_A1_JSON.length()),
          arguments("empty bytes", EMPTY_BYTES, 0),
          arguments(
              "bytes", NON_EMPTY_BYTES, TEST_A1_JSON.getBytes(StandardCharsets.UTF_8).length),
          arguments("empty map", EMPTY_MAP, 0),
          arguments("map", NON_EMPTY_MAP, 2),
          arguments("empty array", EMPTY_ARRAY, 0),
          arguments("array", NON_EMPTY_ARRAY, 2));
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
              "collection",
              collection,
              assertValue(TEST_LIST_A1_A2, collection.normalizedValue())),
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
    void GIVEN_null_assert_value_WHEN_toHaveSameTypeAndValueAs_THEN_NPE_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, AssertOperand.string(A));

      // Act
      var error =
          assertThrows(
              NullPointerException.class, () -> assertion.toHaveSameTypeAndValueAs(null));

      // Assert
      assertThat(error.getMessage(), equalTo("Assert value must not be null"));
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

    // todo Fehler ?
    @Test
    void GIVEN_empty_expected_collection_WHEN_toContain_THEN_no_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of());

      // Act
      assertion.toContain(expectedValue);

      // Assert
      // The method returning normally is the assertion.
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

    // todo fehler ?
    @Test
    void GIVEN_empty_body_WHEN_notToContain_THEN_no_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);
      var unexpectedValue = collectionValue(List.of(A1));

      // Act
      assertion.notToContain(unexpectedValue);

      // Assert
      // The method returning normally is the assertion.
    }

    @Test
    void GIVEN_one_unexpected_element_is_present_WHEN_notToContain_THEN_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var unexpectedValue = collectionValue(List.of(A1, A3));

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.notToContain(unexpectedValue));

      // Assert
      assertThat(
          error.getMessage(), containsString("The response body contained an unexpected element."));
    }

    // todo vs empty_body
    @Test
    void GIVEN_absent_body_WHEN_notToContain_THEN_AssertionError_for_absent_body_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_COLLECTION);
      var unexpectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.notToContain(unexpectedValue));

      // Assert
      assertThat(error.getMessage(), containsString("The response body was absent."));
    }

    @Test
    void GIVEN_named_step_WHEN_notToContain_fails_THEN_step_is_in_message() {
      // Arrange
      var assertion = AAAAssert.expect(NAMED_STEP, LIST_A1_A2);
      var unexpectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(AssertionError.class, () -> assertion.notToContain(unexpectedValue));

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

    // todo fehler ?
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
              AssertionError.class,
              () -> assertion.toContainExactlyInAnyOrder(expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response body contained different elements."));
    }

    @Test
    void GIVEN_additional_actual_element_WHEN_toContainExactlyInAnyOrder_THEN_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, LIST_A1_A2);
      var expectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toContainExactlyInAnyOrder(expectedValue));

      // Assert
      assertThat(
          error.getMessage(),
          containsString("The response body contained different elements."));
    }

    @Test
    void GIVEN_absent_body_WHEN_toContainExactlyInAnyOrder_THEN_absent_error_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, ABSENT_COLLECTION);
      var expectedValue = collectionValue(List.of(A1));

      // Act
      var error =
          assertThrows(
              AssertionError.class,
              () -> assertion.toContainExactlyInAnyOrder(expectedValue));

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
              AssertionError.class,
              () -> assertion.toContainExactlyInAnyOrder(expectedValue));

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
      var conditions =
          conditions(Objects::nonNull, value -> value.equals(A1) || value.equals(A2));

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

    // todo fehler ?
    @Test
    void GIVEN_empty_collection_WHEN_toMatchAll_THEN_no_AssertionError_is_thrown() {
      // Arrange
      var assertion = AAAAssert.expect(ANY_STEP, EMPTY_LIST);
      var conditions = conditions(ALWAYS_FALSE);

      // Act
      assertion.toMatchAll(conditions);

      // Assert
      // An empty collection satisfies all conditions vacuously.
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
          error.getMessage(),
          containsString("At least one value did not match every condition."));
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