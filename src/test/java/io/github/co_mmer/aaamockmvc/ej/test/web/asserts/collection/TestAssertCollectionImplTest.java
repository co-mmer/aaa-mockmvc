package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.utils.StringUtils.EMPTY_ARRAY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A4;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.CLOSE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.ID1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.ID2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.ID3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.ID4;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.NEW;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_B1NEW_B2NEW_JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testmock.MockTestGenericMapper;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectMatch;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.web.servlet.ResultActions;

@SuppressWarnings("unchecked")
class TestAssertCollectionImplTest extends TestAssertBase {

  private TestAssertCollectionImpl impl;

  @BeforeEach
  void setUp() {
    initMockServer();
    this.impl = new TestAssertCollectionImpl(this.actions, new ObjectMapper());
  }

  @Nested
  class callConstructor {

    @ParameterizedTest()
    @MethodSource("provideNullParameters")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullParameters_WHEN_call_constructor_THEN_throw_NullPointerException(
        ResultActions actions, ObjectMapper objectMapper) {

      assertThrows(
          NullPointerException.class, () -> new TestAssertCollectionImpl(actions, objectMapper));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, new ObjectMapper()),
          Arguments.of(mock(ResultActions.class), null),
          Arguments.of(null, null));
    }
  }

  @Nested
  class assertContentIsNotEmpty {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentIsNotEmpty_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_ARRAY})
    @SneakyThrows
    void GIVEN_empty_WHEN_assertContentIsNotEmpty_THEN_assert_false(String value) {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      assertThrows(AssertionError.class, impl::assertContentIsNotEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentIsNotEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertException = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertException::assertContentIsNotEmpty);
    }
  }

  @Nested
  class assertContentIsEmpty {

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_ARRAY})
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentIsEmpty_THEN_assert_true(String value) {
      // Arrange
      useServerWithResponse(value);

      // Act & Assert
      impl.assertContentIsEmpty();
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentIsEmpty_THEN_return_assert_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, impl::assertContentIsEmpty);
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentIsEmpty_THEN_assert_false() {
      // Arrange
      useServerWithStringException();
      var testAssertCollection = new TestAssertCollectionImpl(actions, new ObjectMapper());

      // Act & Assert
      assertThrows(AssertionError.class, testAssertCollection::assertContentIsEmpty);
    }
  }

  @Nested
  class assertContentSize {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentSize_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentSize_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.assertContentSize(1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentSize_THEN_assert_is_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(AssertionFailedError.class, () -> impl.assertContentSize(1));
    }
  }

  @Nested
  class assertContentEquals {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentEquals_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentEquals(TestObjectSimple.class, TEST_LIST_A1_A2);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentEquals_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> impl.assertContentEquals(TestObjectSimple.class, TEST_LIST_A1_A3));
    }

    @Test
    void GIVEN_exception_WHEN_assertContentEquals_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToCollectionThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> impl.assertContentEquals(TestObjectSimple.class, TEST_LIST_A1_A2));

      mockTestGenericMapper.close();
    }
  }

  @Nested
  class assertContentContainsAnyOrder {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentContainsAnyOrder_THEN_assert_is_true() {

      // Arrange
      useServerWithResponse(TEST_LIST_A1_A3_JSON);

      // Act & Assert
      impl.assertContentContainsAnyOrder(TestObjectSimple.class, TEST_LIST_A3_A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentContainsAnyOrder_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> impl.assertContentContainsAnyOrder(TestObjectSimple.class, TEST_LIST_A1_A3));
    }

    @Test
    void GIVEN_exception_WHEN_assertContentContainsAnyOrder_THEN_assert_is_false() {
      // Arrange
      var mockTestGenericMapper = MockTestGenericMapper.mapToCollectionThrowException();

      // Act & Assert
      assertThrows(
          AssertionFailedError.class,
          () -> impl.assertContentContainsAnyOrder(TestObjectSimple.class, TEST_LIST_A1_A2));

      mockTestGenericMapper.close();
    }
  }

  @Nested
  class assertContentContains {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentContains_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, TEST_LIST_A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentContains_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> impl.assertContentContains(TestObjectSimple.class, TEST_LIST_A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentContains_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> impl.assertContentContains(TestObjectSimple.class, TEST_LIST_A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentContains_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentContains_varargs_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> impl.assertContentContains(TestObjectSimple.class, A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentContains_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> impl.assertContentContains(TestObjectSimple.class, A1));
    }
  }

  @Nested
  class assertContentNotContains {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentNotContains_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, TEST_LIST_A3);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentNotContains_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> impl.assertContentNotContains(TestObjectSimple.class, TEST_LIST_A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentNotContains_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> impl.assertContentNotContains(TestObjectSimple.class, TEST_LIST_A3));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentNotContains_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3);
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentNotContains_varargs_THEN_assert_is_false() {

      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> impl.assertContentNotContains(TestObjectSimple.class, A1));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentNotContains_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class, () -> impl.assertContentNotContains(TestObjectSimple.class, A3));
    }
  }

  @Nested
  class assertContentMatchAll {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentMatchAll_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentMatchAll_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAll(
                  TestObjectSimple.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAll_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAll(
                  TestObjectSimple.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentMatchAll_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchNone(
                  TestObjectMatch.class,
                  element -> element.name().equals(B),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_one_WHEN_assertContentMatchAll_varargs_THEN_assert_is_false() {

      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAll(
                  TestObjectMatch.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_all_WHEN_assertContentMatchAll_varargs_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAll(
                  TestObjectMatch.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(CLOSE)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAll_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAll(
                  TestObjectMatch.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }
  }

  @Nested
  class assertContentMatchAny {

    @Test
    @SneakyThrows
    void GIVEN_expected_WHEN_assertContentMatchAny_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAny(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_WHEN_assertContentMatchAny_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAny(
                  TestObjectSimple.class, element -> element.name().equals(B)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAny_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAny(
                  TestObjectSimple.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_one_WHEN_assertContentMatchAny_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchAny(
          TestObjectMatch.class,
          element -> element.name().equals(A),
          element -> element.name().equals(B));
    }

    @Test
    @SneakyThrows
    void GIVEN_expected_all_WHEN_assertContentMatchAny_varargs_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchAny(
          TestObjectMatch.class,
          element -> element.name().equals(B),
          element -> element.status().equals(NEW));
    }

    @Test
    @SneakyThrows
    void GIVEN_unexpected_all_WHEN_assertContentMatchAny_varargs_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAny(
                  TestObjectMatch.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(CLOSE)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchAny_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchAny(
                  TestObjectMatch.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }
  }

  @Nested
  class assertContentMatchNone {

    @Test
    @SneakyThrows
    void GIVEN_A1_A2_condition_B_WHEN_assertContentMatchNone_THEN_assert_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchNone(TestObjectSimple.class, element -> element.name().equals(B));
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_A2_condition_A_WHEN_assertContentMatchNone_THEN_assert_is_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchNone(
                  TestObjectSimple.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchNone_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchNone(
                  TestObjectSimple.class, element -> element.name().equals(A)));
    }

    @Test
    @SneakyThrows
    void GIVEN_B1NEW_B2NEW_condition_B_NEW_WHEN_assertContentMatchNone_varargs_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchNone(
                  TestObjectMatch.class,
                  element -> element.name().equals(B),
                  element -> element.status().equals(NEW)));
    }

    @Test
    @SneakyThrows
    void
        GIVEN_B1NEW_B2NEW_condition_B_CLOSE_WHEN_assertContentMatchNone_varargs_THEN_assert_false() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchNone(
                  TestObjectMatch.class,
                  element -> element.name().equals(B),
                  element -> element.status().equals(CLOSE)));
    }

    @Test
    @SneakyThrows
    void
        GIVEN_B1NEW_B2NEW_condition_A_CLOSE_WHEN_assertContentMatchNone_varargs_THEN_assert_is_true() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchNone(
          TestObjectMatch.class,
          element -> element.name().equals(A),
          element -> element.status().equals(CLOSE));
    }

    @Test
    @SneakyThrows
    void GIVEN_exception_WHEN_assertContentMatchNone_varargs_THEN_assert_false() {
      // Arrange
      useServerWithStringException();

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              impl.assertContentMatchNone(
                  TestObjectMatch.class,
                  element -> element.name().equals(A),
                  element -> element.status().equals(NEW)));
    }
  }

  @Nested
  class nextStep {

    @Test
    void assertHead() {
      // Arrange
      useHeader();

      // Act
      var assertHead = impl.assertHead();

      // Assert
      assertThat(assertHead.getClass(), is(TestAssertHeadImpl.class));
    }
  }

  @Nested
  class combinationIsNotEmpty {

    @Test
    @SneakyThrows
    void isNotEmpty_contains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty().assertContentContains(TestObjectSimple.class, A1);
    }

    @Test
    @SneakyThrows
    void isNotEmpty_contains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty().assertContentContains(TestObjectSimple.class, A1, A2);
    }

    @Test
    @SneakyThrows
    void isNotEmpty_containsAnyOrder() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty()
          .assertContentContainsAnyOrder(TestObjectSimple.class, List.of(A2, A1));
    }

    @Test
    @SneakyThrows
    void isNotEmpty_notContains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty().assertContentNotContains(TestObjectSimple.class, A3);
    }

    @Test
    @SneakyThrows
    void isNotEmpty_notContains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty().assertContentNotContains(TestObjectSimple.class, A3, A4);
    }

    @Test
    @SneakyThrows
    void isNotEmpty_equals() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty().assertContentEquals(TestObjectSimple.class, TEST_LIST_A1_A2);
    }

    @Test
    @SneakyThrows
    void isNotEmpty_matchAll_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty()
          .assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void isNotEmpty_matchAll_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty()
          .assertContentMatchAll(
              TestObjectSimple.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void isNotEmpty_matchAny_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty()
          .assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void isNotEmpty_matchAny_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty()
          .assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void isNotEmpty_matchNone_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty()
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void isNotEmpty_matchNone_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentIsNotEmpty()
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationSize {

    @Test
    @SneakyThrows
    void size_contains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2).assertContentContains(TestObjectSimple.class, A1);
    }

    @Test
    @SneakyThrows
    void size_contains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2).assertContentContains(TestObjectSimple.class, A1, A2);
    }

    @Test
    @SneakyThrows
    void size_containsAnyOrder() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2)
          .assertContentContainsAnyOrder(TestObjectSimple.class, List.of(A2, A1));
    }

    @Test
    @SneakyThrows
    void size_notContains_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2).assertContentNotContains(TestObjectSimple.class, A3);
    }

    @Test
    @SneakyThrows
    void size_notContains_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2).assertContentNotContains(TestObjectSimple.class, A3, A4);
    }

    @Test
    @SneakyThrows
    void size_equals() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2).assertContentEquals(TestObjectSimple.class, TEST_LIST_A1_A2);
    }

    @Test
    @SneakyThrows
    void size_matchAll_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2)
          .assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void size_matchAll_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2)
          .assertContentMatchAll(
              TestObjectSimple.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void size_matchAny_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2)
          .assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void size_matchAny_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2)
          .assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void size_matchNone_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void size_matchNone_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentSize(2)
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationContains {

    @Test
    @SneakyThrows
    void contains_1_matchAny_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1)
          .assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_1_matchAny_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1)
          .assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void contains_1_matchAll_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1)
          .assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_1_matchAll_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1)
          .assertContentMatchAll(
              TestObjectSimple.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void contains_1_matchNone_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_1_matchNone_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1)
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }

    @Test
    @SneakyThrows
    void contains_2_matchAny_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1, A2)
          .assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_2_matchAny_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1, A2)
          .assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void contains_2_matchAll_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1, A2)
          .assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_2_matchAll_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1, A2)
          .assertContentMatchAll(
              TestObjectSimple.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void contains_2_matchNone_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1, A2)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void contains_2_matchNone_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentContains(TestObjectSimple.class, A1, A2)
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationNotContains {

    @Test
    @SneakyThrows
    void notContains_1_matchAny_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3)
          .assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notContains_1_matchAny_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3)
          .assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void notContains_1_matchAll_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3)
          .assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notContains_1_matchAll_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3)
          .assertContentMatchAll(
              TestObjectSimple.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void notContains_1_matchNone_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notContains_1_matchNone_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3)
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }

    @Test
    @SneakyThrows
    void notContains_2_matchAny_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3, A4)
          .assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notContains_2_matchAny_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3, A4)
          .assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void notContains_2_matchAll_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3, A4)
          .assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notContains_2_matchAll_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3, A4)
          .assertContentMatchAll(
              TestObjectSimple.class,
              element -> element.name().equals(A),
              element -> element.name().equals(A));
    }

    @Test
    @SneakyThrows
    void notContains_2_matchNone_1() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3, A4)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void notContains_2_matchNone_2() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentNotContains(TestObjectSimple.class, A3, A4)
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationMatchAll {

    @Test
    @SneakyThrows
    void matchAll_matchAny() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A))
          .assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A))
          .assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void matchAll_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A))
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A))
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_matchAny() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchAll(
              TestObjectMatch.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertContentMatchAny(TestObjectMatch.class, element -> element.id() == ID1);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchAny_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchAll(
              TestObjectMatch.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertContentMatchAny(
              TestObjectMatch.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2);
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectMatch.class, element -> element.name().equals(B))
          .assertContentMatchNone(TestObjectMatch.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectMatch.class, element -> element.name().equals(B))
          .assertContentMatchNone(
              TestObjectMatch.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }

    @Test
    @SneakyThrows
    void matchAll_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAll(TestObjectSimple.class, element -> element.name().equals(A))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAll_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_B1NEW_B2NEW_JSON);

      // Act & Assert
      impl.assertContentMatchAll(
              TestObjectMatch.class,
              element -> element.name().equals(B),
              element -> element.status().equals(NEW))
          .assertHead();
    }
  }

  @Nested
  class combinationMatchAny {

    @Test
    @SneakyThrows
    void matchAny_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAny(TestObjectSimple.class, element -> element.id() == ID1)
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_matchNone() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2)
          .assertContentMatchNone(TestObjectSimple.class, element -> element.id() == ID3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    void matchAny_vararg_matchNone_vararg() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.id() == ID1,
              element -> element.id() == ID2)
          .assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.id() == ID3,
              element -> element.id() == ID4);
    }

    @Test
    @SneakyThrows
    void matchAny_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAny(TestObjectSimple.class, element -> element.name().equals(A))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchAny_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchAny(
              TestObjectSimple.class,
              element -> element.name().equals(A),
              element -> element.id() == ID1)
          .assertHead();
    }
  }

  @Nested
  class combinationMatchNone {

    @Test
    @SneakyThrows
    void matchNone_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchNone(TestObjectSimple.class, element -> element.name().equals(B))
          .assertHead();
    }

    @Test
    @SneakyThrows
    void matchNone_vararg_head() {
      // Arrange
      useServerWithResponse(TEST_LIST_A1_A2_JSON);

      // Act & Assert
      impl.assertContentMatchNone(
              TestObjectSimple.class,
              element -> element.name().equals(B),
              element -> element.id() == ID3)
          .assertHead();
    }
  }
}
