package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_ARRAY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.CLOSE;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.NEW;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3_A4;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_B1NEW_B2NEW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.match.TestAssertMatch;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectMatch;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings({"java:S2699", "unchecked"})
class TestAssertCollectionImplTest extends TestAssertBase {

  private static final Predicate<TestObjectSimple> PREDICATE_A =
      element -> element.name().equals(A);
  private static final Predicate<TestObjectSimple> PREDICATE_B =
      element -> element.name().equals(B);

  private TestAssertCollectionImpl<TestObjectSimple> impl;
  private TestAssertCollectionImpl<TestObjectMatch> implObjectMatch;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.impl = new TestAssertCollectionImpl<>(context);
    this.implObjectMatch = new TestAssertCollectionImpl<>(context);
  }

  @Nested
  class callConstructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> new TestAssertCollectionImpl<>(null));
    }
  }

  @Nested
  class isNotEmpty {

    @Test
    void GIVEN_A1_A2_WHEN_isNotEmpty_THEN_success() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());

      // Act & Assert
      impl.isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_ARRAY})
    void GIVEN_blank_WHEN_isNotEmpty_THEN_failed(String value) {
      // Arrange
      useActResult(value.getBytes());

      // Act & Assert
      assertThrows(AssertionError.class, impl::isNotEmpty);
    }
  }

  @Nested
  class isEmpty {

    @ParameterizedTest
    @ValueSource(strings = {EMPTY, EMPTY_ARRAY})
    void GIVEN_blank_WHEN_isEmpty_THEN_success(String value) {
      // Arrange
      useActResult(value.getBytes());

      // Act & Assert
      impl.isEmpty();
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEmpty_THEN_failed() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());

      // Act & Assert
      assertThrows(AssertionError.class, impl::isEmpty);
    }
  }

  @Nested
  class hasSize {

    @Test
    void GIVEN_A1_A2_WHEN_hasSize2_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2);
    }

    @Test
    void GIVEN_A1_A2_WHEN_hasSize1_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.hasSize(1));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_A1_A2_WHEN_isEqualTo_A1_A2_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isEqualTo(TEST_LIST_A1_A2);
    }

    @Test
    void GIVEN_A1_A2_WHEN_isEqualTo_A1_A3_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.isEqualTo(TEST_LIST_A1_A3));
    }
  }

  @Nested
  class containsAnyOrder {

    @Test
    void GIVEN_A1_A3_WHEN_containsAnyOrder_A3_A1_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A3);

      // Act & Assert
      impl.containsAnyOrder(TEST_LIST_A3_A1);
    }

    @Test
    void GIVEN_A1_A2_WHEN_containsAnyOrder_A1_A3_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.containsAnyOrder(TEST_LIST_A1_A3));
    }
  }

  @Nested
  class contains {

    @Test
    void GIVEN_A1_A2_WHEN_contains_A1_A2_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(TEST_LIST_A1_A2);
    }

    @Test
    void GIVEN_A1_A2_WHEN_contains_A1_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1);
    }

    @Test
    void GIVEN_A1_A2_WHEN_contains_A1_A3_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.contains(TEST_LIST_A1_A3));
    }

    @Test
    void GIVEN_A1_A2_WHEN_contains_A3_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.contains(A3));
    }
  }

  @Nested
  class notContains {

    @Test
    void GIVEN_A1_A2_WHEN_notContains_A3_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3);
    }

    @Test
    void GIVEN_A1_A2_WHEN_notContains_A3_A4_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(TEST_LIST_A3_A4);
    }

    @Test
    void GIVEN_A1_A2_WHEN_notContains_A1_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.notContains(A1));
    }

    @Test
    void GIVEN_A1_A2_WHEN_notContains_A1_A2_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.notContains(TEST_LIST_A1_A2));
    }
  }

  @Nested
  class matchAll {

    @Test
    void GIVEN_A1_A2_WHEN_matchAll_A_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAll(PREDICATE_A);
    }

    @Test
    void GIVEN_A1_A2_WHEN_matchAll_B_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.matchAll(element -> element.name().equals(B)));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchAll_B_NEW_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch.matchAll(
          element -> element.name().equals(B), element -> element.status().equals(NEW));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchAll_A_NEW_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              implObjectMatch.matchAll(
                  element -> element.name().equals(A), element -> element.status().equals(NEW)));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchAll_A_CLOSE_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              implObjectMatch.matchAll(
                  element -> element.name().equals(A), element -> element.status().equals(CLOSE)));
    }

    @Test
    void GIVEN_A1_A2_WHEN_matchAll_N_Predicate_THEN_success() {
      // Arrange
      var mocked = mockStatic(TestAssertMatch.class);
      var match = mock(TestAssertMatch.class);
      mocked.when(() -> TestAssertMatch.assertThat(TEST_LIST_A1_A2)).thenReturn(match);
      useAssertResult(TEST_LIST_A1_A2);

      // Act
      impl.matchAll(PREDICATE_B);

      // Assert
      var p1 = new Predicate[] {PREDICATE_B};
      verify(match).matchAll(p1);

      impl.matchAll(PREDICATE_B, PREDICATE_B);
      var p2 = new Predicate[] {PREDICATE_B, PREDICATE_B};
      verify(match).matchAll(p2);

      impl.matchAll(PREDICATE_B, PREDICATE_B, PREDICATE_B);
      var p3 = new Predicate[] {PREDICATE_B, PREDICATE_B, PREDICATE_B};
      verify(match).matchAll(p3);

      mocked.close();
    }
  }

  @Nested
  class matchAny {

    @Test
    void GIVEN_A1_A2_WHEN_matchAny_A_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(PREDICATE_A);
    }

    @Test
    void GIVEN_A1_A2_WHEN_matchAny_B_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.matchAny(element -> element.name().equals(B)));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchAny_A_B_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch.matchAny(
          element -> element.name().equals(A), element -> element.name().equals(B));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchAny_B_NEW_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch.matchAny(
          element -> element.name().equals(B), element -> element.status().equals(NEW));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchAny_A_CLOSE_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              implObjectMatch.matchAny(
                  element -> element.name().equals(A), element -> element.status().equals(CLOSE)));
    }

    @Test
    void GIVEN_A1_A2_WHEN_matchAny_N_Predicate_THEN_success() {
      // Arrange
      var mocked = mockStatic(TestAssertMatch.class);
      var match = mock(TestAssertMatch.class);
      mocked.when(() -> TestAssertMatch.assertThat(TEST_LIST_A1_A2)).thenReturn(match);
      useAssertResult(TEST_LIST_A1_A2);

      // Act
      impl.matchAny(PREDICATE_B);

      // Assert
      var p1 = new Predicate[] {PREDICATE_B};
      verify(match).matchAny(p1);

      impl.matchAny(PREDICATE_B, PREDICATE_B);
      var p2 = new Predicate[] {PREDICATE_B, PREDICATE_B};
      verify(match).matchAny(p2);

      impl.matchAny(PREDICATE_B, PREDICATE_B, PREDICATE_B);
      var p3 = new Predicate[] {PREDICATE_B, PREDICATE_B, PREDICATE_B};
      verify(match).matchAny(p3);

      mocked.close();
    }
  }

  @Nested
  class matchNone {

    @Test
    void GIVEN_A1_A2_WHEN_matchNone_B_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchNone(element -> element.name().equals(B));
    }

    @Test
    void GIVEN_A1_A2_WHEN_matchNone_A_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      assertThrows(AssertionError.class, () -> impl.matchNone(PREDICATE_A));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchNone_B_NEW_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              implObjectMatch.matchNone(
                  element -> element.name().equals(B), element -> element.status().equals(NEW)));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchNone_B_CLOSE_THEN_failed() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              implObjectMatch.matchNone(
                  element -> element.name().equals(B), element -> element.status().equals(CLOSE)));
    }

    @Test
    void GIVEN_B1NEW_B2NEW_WHEN_matchNone_A_CLOSE_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch.matchNone(
          element -> element.name().equals(A), element -> element.status().equals(CLOSE));
    }

    @Test
    void GIVEN_A1_A2_WHEN_matchNone_B_Predicate_THEN_success() {
      // Arrange
      var mocked = mockStatic(TestAssertMatch.class);
      var match = mock(TestAssertMatch.class);
      mocked.when(() -> TestAssertMatch.assertThat(TEST_LIST_A1_A2)).thenReturn(match);
      useAssertResult(TEST_LIST_A1_A2);

      // Act
      impl.matchNone(PREDICATE_B);

      // Assert
      var p1 = new Predicate[] {PREDICATE_B};
      verify(match).matchNone(p1);

      impl.matchNone(PREDICATE_B, PREDICATE_B);
      var p2 = new Predicate[] {PREDICATE_B, PREDICATE_B};
      verify(match).matchNone(p2);

      impl.matchNone(PREDICATE_B, PREDICATE_B, PREDICATE_B);
      var p3 = new Predicate[] {PREDICATE_B, PREDICATE_B, PREDICATE_B};
      verify(match).matchNone(p3);

      mocked.close();
    }
  }

  @Nested
  class nextStep {

    @Test
    void headers() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
