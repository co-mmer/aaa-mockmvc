package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class TestAssertMatchTest {

  private static final Predicate<TestObjectSimple> EQUALS_A = element -> element.name().equals(A);
  private static final Predicate<TestObjectSimple> EQUALS_B = element -> element.name().equals(B);
  private static final Predicate<TestObjectSimple> EQUALS_1 = element -> element.id() == 1;
  private static final Predicate<TestObjectSimple> EQUALS_2 = element -> element.id() == 2;
  private static final List<TestObjectSimple> LIST_A1_A2 = List.of(A1, A2);

  @Nested
  class withCollection {

    @Nested
    class notNull {

      @ParameterizedTest
      @MethodSource("provideNullParameters")
      @SuppressWarnings("ConstantConditions")
      void GIVEN_provideNullParameters_WHEN_assertMatch_THEN_throw_NullPointerException(
          List<String> actual, Predicate<String>[] conditions) {
        assertThrows(
            NullPointerException.class,
            () -> TestAssertMatch.assertThat(actual).matchAll(conditions));
      }

      private static Stream<Arguments> provideNullParameters() {
        var mockPredicate = Mockito.mock(Predicate.class);
        return Stream.of(
            Arguments.of(null, null),
            Arguments.of(List.of("A"), null),
            Arguments.of(null, new Predicate[] {mockPredicate}));
      }
    }

    @Nested
    class matchAll {

      @Test
      void GIVEN_A1_A2_match_A_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(LIST_A1_A2).matchAll(EQUALS_A);
      }

      @Test
      void GIVEN_A1_A2_match_A2_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class,
            () -> TestAssertMatch.assertThat(LIST_A1_A2).matchAll(EQUALS_A, EQUALS_2));
      }
    }

    @Nested
    class matchAny {

      @Test
      void GIVEN_A1_A2_match_1_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(LIST_A1_A2).matchAny(EQUALS_1);
      }

      @Test
      void GIVEN_A1_A2_match_A_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(LIST_A1_A2).matchAny(EQUALS_A);
      }

      @Test
      void GIVEN_A1_A2_match_B_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class, () -> TestAssertMatch.assertThat(LIST_A1_A2).matchAny(EQUALS_B));
      }
    }

    @Nested
    class matchNone {

      @Test
      void GIVEN_A1_A2_match_B_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(LIST_A1_A2).matchNone(EQUALS_B);
      }

      @Test
      void GIVEN_A1_A2_match_A_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class, () -> TestAssertMatch.assertThat(LIST_A1_A2).matchNone(EQUALS_A));
      }

      @Test
      void GIVEN_A1_A2_match_1_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class, () -> TestAssertMatch.assertThat(LIST_A1_A2).matchNone(EQUALS_1));
      }

      @Test
      void GIVEN_A1_A2_match_A2_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class,
            () -> TestAssertMatch.assertThat(LIST_A1_A2).matchNone(EQUALS_A, EQUALS_2));
      }
    }
  }

  @Nested
  class withObject {

    @Nested
    class notNull {

      @ParameterizedTest
      @MethodSource("provideNullParameters")
      @SuppressWarnings("ConstantConditions")
      void GIVEN_provideNullParameters_WHEN_assertMatch_THEN_throw_NullPointerException(
          String actual, Predicate<String>[] conditions) {
        assertThrows(
            NullPointerException.class,
            () -> TestAssertMatch.assertThat(actual).matchAll(conditions));
      }

      private static Stream<Arguments> provideNullParameters() {
        var mockPredicate = Mockito.mock(Predicate.class);
        return Stream.of(
            Arguments.of(null, null),
            Arguments.of("A", null),
            Arguments.of(null, new Predicate[] {mockPredicate}));
      }
    }

    @Nested
    class matchAll {

      @Test
      void GIVEN_A1_match_A_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(A1).matchAll(EQUALS_A);
      }

      @Test
      void GIVEN_A1_match_A2_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class,
            () -> TestAssertMatch.assertThat(A1).matchAll(EQUALS_A, EQUALS_2));
      }
    }

    @Nested
    class matchAny {

      @Test
      void GIVEN_A1_match_1_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(A1).matchAny(EQUALS_1);
      }

      @Test
      void GIVEN_A1_match_A_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(A1).matchAny(EQUALS_A);
      }

      @Test
      void GIVEN_A1_match_B_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(AssertionError.class, () -> TestAssertMatch.assertThat(A1).matchAny(EQUALS_B));
      }
    }

    @Nested
    class matchNone {

      @Test
      void GIVEN_A1_match_B_WHEN_assertMatch_THEN_return_true() {
        TestAssertMatch.assertThat(A1).matchNone(EQUALS_B);
      }

      @Test
      void GIVEN_A1_match_A_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class, () -> TestAssertMatch.assertThat(A1).matchNone(EQUALS_A));
      }

      @Test
      void GIVEN_A1_match_1_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class, () -> TestAssertMatch.assertThat(A1).matchNone(EQUALS_1));
      }

      @Test
      void GIVEN_A1_match_A2_WHEN_assertMatch_THEN_throw_AssertionError() {
        assertThrows(
            AssertionError.class,
            () -> TestAssertMatch.assertThat(A1).matchNone(EQUALS_A, EQUALS_2));
      }
    }
  }
}
