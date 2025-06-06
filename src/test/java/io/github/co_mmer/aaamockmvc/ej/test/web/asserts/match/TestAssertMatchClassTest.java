package io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match;

import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.ALL;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.ANY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.asserts.match.TestAssertMatchType.NONE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
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

class TestAssertMatchClassTest {

  private static final Predicate<TestObjectSimple> EQUALS_A = element -> element.name().equals(A);
  private static final Predicate<TestObjectSimple> EQUALS_B = element -> element.name().equals(B);
  private static final List<Predicate<TestObjectSimple>> LIST_EQUALS_A = List.of(EQUALS_A);
  private static final List<Predicate<TestObjectSimple>> LIST_EQUALS_B = List.of(EQUALS_B);

  private static final Predicate<TestObjectSimple> EQUALS_1 = element -> element.id() == 1;
  private static final Predicate<TestObjectSimple> EQUALS_2 = element -> element.id() == 2;
  private static final List<Predicate<TestObjectSimple>> LIST_EQUALS_1 = List.of(EQUALS_1);

  private static final List<Predicate<TestObjectSimple>> LIST_EQUALS_A2 =
      List.of(EQUALS_A, EQUALS_2);

  @Nested
  class notNull {

    @ParameterizedTest()
    @MethodSource("provideNullParameters")
    @SuppressWarnings("ConstantConditions")
    void GIVEN_provideNullParameters_WHEN_assertMatch_THEN_throw_NullPointerException(
        TestAssertMatchType type, String actual, List<Predicate<String>> conditions) {

      assertThrows(
          NullPointerException.class,
          () -> TestAssertMatchClass.assertMatch(type, actual, conditions));
    }

    private static Stream<Arguments> provideNullParameters() {
      return Stream.of(
          Arguments.of(null, null, null),
          Arguments.of(ALL, null, null),
          Arguments.of(ALL, "A", null),
          Arguments.of(ALL, null, List.of(Mockito.mock(Predicate.class))));
    }
  }

  @Nested
  class matchAll {

    @Test
    void GIVEN_A1_match_A_WHEN_assertMatch_THEN_return_true() {
      TestAssertMatchClass.assertMatch(ALL, A1, LIST_EQUALS_A);
    }

    @Test
    void GIVEN_A1_match_A2_WHEN_assertMatch_THEN_throw_AssertionError() {
      assertThrows(
          AssertionError.class, () -> TestAssertMatchClass.assertMatch(ALL, A1, LIST_EQUALS_A2));
    }
  }

  @Nested
  class matchAny {

    @Test
    void GIVEN_A1_match_1_WHEN_assertMatch_THEN_return_true() {
      TestAssertMatchClass.assertMatch(ANY, A1, LIST_EQUALS_1);
    }

    @Test
    void GIVEN_A1_match_A_WHEN_assertMatch_THEN_return_true() {
      TestAssertMatchClass.assertMatch(ANY, A1, LIST_EQUALS_A);
    }

    @Test
    void GIVEN_A1_match_B_WHEN_assertMatch_THEN_throw_AssertionError() {
      assertThrows(
          AssertionError.class, () -> TestAssertMatchClass.assertMatch(ANY, A1, LIST_EQUALS_B));
    }
  }

  @Nested
  class matchNone {

    @Test
    void GIVEN_A1_match_B_WHEN_assertMatch_THEN_return_true() {
      TestAssertMatchClass.assertMatch(NONE, A1, LIST_EQUALS_B);
    }

    @Test
    void GIVEN_A1_match_A_WHEN_assertMatch_THEN_throw_AssertionError() {
      assertThrows(
          AssertionError.class, () -> TestAssertMatchClass.assertMatch(NONE, A1, LIST_EQUALS_A));
    }

    @Test
    void GIVEN_A1_match_1_WHEN_assertMatch_THEN_throw_AssertionError() {
      assertThrows(
          AssertionError.class, () -> TestAssertMatchClass.assertMatch(NONE, A1, LIST_EQUALS_1));
    }

    @Test
    void GIVEN_A1_match_A2_WHEN_assertMatch_THEN_throw_AssertionError() {
      assertThrows(
          AssertionError.class, () -> TestAssertMatchClass.assertMatch(NONE, A1, LIST_EQUALS_A2));
    }
  }
}
