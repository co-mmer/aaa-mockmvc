package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY_ARRAY;
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
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2_JSON;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A3;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3_A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A3_A4;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_B1NEW_B2NEW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectMatch;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("unchecked")
class TestAssertCollectionImplTest extends TestAssertBase {

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
    void GIVEN_null_WHEN_call_constructor_THEN_throwException() {
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
      impl.matchAll(element -> element.name().equals(A));
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
  }

  @Nested
  class matchAny {

    @Test
    void GIVEN_A1_A2_WHEN_matchAny_A_THEN_success() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(element -> element.name().equals(A));
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
      assertThrows(AssertionError.class, () -> impl.matchNone(element -> element.name().equals(A)));
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
  }

  @Nested
  class nextStep {

    @Test
    void headers() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers.getClass(), is(TestAssertHeadImpl.class));
    }
  }

  @Nested
  class combinationNotEmpty {

    @Test
    void isNotEmpty_contains1() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().contains(A1);
    }

    @Test
    void isNotEmpty_contains2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().contains(A1, A2);
    }

    @Test
    void isNotEmpty_containsAnyOrder() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().containsAnyOrder(List.of(A2, A1));
    }

    @Test
    void isNotEmpty_notContains1() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().notContains(A3);
    }

    @Test
    void isNotEmpty_notContains2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().notContains(A3, A4);
    }

    @Test
    void isNotEmpty_equals() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().isEqualTo(TEST_LIST_A1_A2);
    }

    @Test
    void isNotEmpty_matchAll1() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().matchAll(element -> element.name().equals(A));
    }

    @Test
    void isNotEmpty_matchAll2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty()
          .matchAll(element -> element.name().equals(A), element -> element.name().equals(A));
    }

    @Test
    void isNotEmpty_matchAny1() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().matchAny(element -> element.id() == ID1);
    }

    @Test
    void isNotEmpty_matchAny2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void isNotEmpty_matchNone1() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().matchNone(element -> element.id() == ID3);
    }

    @Test
    void isNotEmpty_matchNone2() {
      // Arrange
      useActResult(TEST_LIST_A1_A2_JSON.getBytes());
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.isNotEmpty().matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationSize {

    @Test
    void hasSize_contains1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).contains(A1);
    }

    @Test
    void hasSize_contains2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).contains(A1, A2);
    }

    @Test
    void hasSize_containsAnyOrder() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).containsAnyOrder(List.of(A2, A1));
    }

    @Test
    void hasSize_notContains1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).notContains(A3);
    }

    @Test
    void hasSize_notContains2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).notContains(A3, A4);
    }

    @Test
    void hasSize_isEqualTo() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).isEqualTo(TEST_LIST_A1_A2);
    }

    @Test
    void hasSize_matchAll1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).matchAll(element -> element.name().equals(A));
    }

    @Test
    void hasSize_matchAll2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2)
          .matchAll(element -> element.name().equals(A), element -> element.name().equals(A));
    }

    @Test
    void hasSize_matchAny1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).matchAny(element -> element.id() == ID1);
    }

    @Test
    void hasSize_matchAny2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void hasSize_matchNone1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).matchNone(element -> element.id() == ID3);
    }

    @Test
    void hasSize_matchNone_2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.hasSize(2).matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationContains {

    @Test
    void contains1_matchAny1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1).matchAny(element -> element.id() == ID1);
    }

    @Test
    void contains1_matchAny2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1).matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void contains1_matchAll1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1).matchAll(element -> element.name().equals(A));
    }

    @Test
    void contains1_matchAll2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1)
          .matchAll(element -> element.name().equals(A), element -> element.name().equals(A));
    }

    @Test
    void contains1_matchNone1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1).matchNone(element -> element.id() == ID3);
    }

    @Test
    void contains1_matchNone2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1).matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }

    @Test
    void contains2_matchAny1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1, A2).matchAny(element -> element.id() == ID1);
    }

    @Test
    void contains2_matchAny2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1, A2)
          .matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void contains2_matchAll1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1, A2).matchAll(element -> element.name().equals(A));
    }

    @Test
    void contains2_matchAll2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1, A2)
          .matchAll(element -> element.name().equals(A), element -> element.name().equals(A));
    }

    @Test
    void contains2_matchNone1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1, A2).matchNone(element -> element.id() == ID3);
    }

    @Test
    void contains_2_matchNone_2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.contains(A1, A2)
          .matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationNotContains {

    @Test
    void notContains1_matchAny1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3).matchAny(element -> element.id() == ID1);
    }

    @Test
    void notContains1_matchAny2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3).matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void notContains1_matchAll1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3).matchAll(element -> element.name().equals(A));
    }

    @Test
    void notContains1_matchAll2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3)
          .matchAll(element -> element.name().equals(A), element -> element.name().equals(A));
    }

    @Test
    void notContains1_matchNone1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3).matchNone(element -> element.id() == ID3);
    }

    @Test
    void notContains1_matchNone2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3)
          .matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }

    @Test
    void notContains2_matchAny1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3, A4).matchAny(element -> element.id() == ID1);
    }

    @Test
    void notContains2_matchAny2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3, A4)
          .matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void notContains2_matchAll1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3, A4).matchAll(element -> element.name().equals(A));
    }

    @Test
    void notContains2_matchAll2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3, A4)
          .matchAll(element -> element.name().equals(A), element -> element.name().equals(A));
    }

    @Test
    void notContains2_matchNone1() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3, A4).matchNone(element -> element.id() == ID3);
    }

    @Test
    void notContains2_matchNone2() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.notContains(A3, A4)
          .matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }
  }

  @Nested
  class combinationMatchAll {

    @Test
    void matchAll_matchAny() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAll(element -> element.name().equals(A)).matchAny(element -> element.id() == ID1);
    }

    @Test
    void matchAll_matchAny_vararg() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAll(element -> element.name().equals(A))
          .matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void matchAll_matchNone() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAll(element -> element.name().equals(A)).matchNone(element -> element.id() == ID3);
    }

    @Test
    void matchAll_matchNone_vararg() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAll(element -> element.name().equals(A))
          .matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }

    @Test
    void matchAll_vararg_matchAny() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch
          .matchAll(element -> element.name().equals(B), element -> element.status().equals(NEW))
          .matchAny(element -> element.id() == ID1);
    }

    @Test
    void matchAll_vararg_matchAny_vararg() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch
          .matchAll(element -> element.name().equals(B), element -> element.status().equals(NEW))
          .matchAny(element -> element.id() == ID1, element -> element.id() == ID2);
    }

    @Test
    void matchAll_vararg_matchNone() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch
          .matchAll(element -> element.name().equals(B))
          .matchNone(element -> element.id() == ID3);
    }

    @Test
    void matchAll_vararg_matchNone_vararg() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch
          .matchAll(element -> element.name().equals(B))
          .matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }

    @Test
    void matchAll_head() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAll(element -> element.name().equals(A)).headers();
    }

    @Test
    void matchAll_vararg_headers() {
      // Arrange
      useAssertResult(TEST_LIST_B1NEW_B2NEW);

      // Act & Assert
      implObjectMatch
          .matchAll(element -> element.name().equals(B), element -> element.status().equals(NEW))
          .headers();
    }
  }

  @Nested
  class combinationMatchAny {

    @Test
    void matchAny_matchNone() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(element -> element.id() == ID1).matchNone(element -> element.id() == ID3);
    }

    @Test
    void matchAny_matchNone_vararg() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(element -> element.id() == ID1)
          .matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }

    @Test
    void matchAny_vararg_matchNone() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(element -> element.id() == ID1, element -> element.id() == ID2)
          .matchNone(element -> element.id() == ID3);
    }

    @Test
    void matchAny_vararg_matchNone_vararg() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(element -> element.id() == ID1, element -> element.id() == ID2)
          .matchNone(element -> element.id() == ID3, element -> element.id() == ID4);
    }

    @Test
    void matchAny_headers() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(element -> element.name().equals(A)).headers();
    }

    @Test
    void matchAny_vararg_headers() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchAny(element -> element.name().equals(A), element -> element.id() == ID1).headers();
    }
  }

  @Nested
  class combinationMatchNone {

    @Test
    void matchNone_headers() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchNone(element -> element.name().equals(B)).headers();
    }

    @Test
    void matchNone_vararg_headers() {
      // Arrange
      useAssertResult(TEST_LIST_A1_A2);

      // Act & Assert
      impl.matchNone(element -> element.name().equals(B), element -> element.id() == ID3).headers();
    }
  }
}
