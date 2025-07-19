package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.NEW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectMatch;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.function.Predicate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestAssertClassImplTest extends TestAssertBase {

  public static final Predicate<TestObjectSimple> PREDICATE_NULL = null;
  public static final Predicate<TestObjectSimple>[] PREDICATES_NULL = null;
  private static final Predicate<TestObjectSimple> PREDICATE_NAME_EQUALS_A =
      element -> element.name().equals(A);
  private static final Predicate<TestObjectSimple> PREDICATE_ID_EQUALS_2 =
      element -> element.id() == 2;
  private static final Predicate<TestObjectSimple> PREDICATE_ID_EQUALS_1 =
      element -> element.id() == 1;

  private TestAssertClassImpl<TestObjectSimple> testAssertClass;
  private TestAssertClassImpl<TestObjectMatch> testAssertClassObjectMatch;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.testAssertClass = new TestAssertClassImpl<>(context);
    this.testAssertClassObjectMatch = new TestAssertClassImpl<>(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> new TestAssertClassImpl<>(null));
    }
  }

  @Nested
  class isNotNull {

    @Test
    @SneakyThrows
    void GIVEN_A1_WHEN_isNotNull_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull();
    }

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_isNotNull_THEN_assert_false() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertClass::isNotNull);
    }
  }

  @Nested
  class isNull {

    @Test
    @SneakyThrows
    void GIVEN_null_WHEN_isNull_THEN_assert_true() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      testAssertClass.isNull();
    }

    @Test
    @SneakyThrows
    void GIVEN_A1_WHEN_isNull_THEN_return_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      assertThrows(AssertionError.class, testAssertClass::isNull);
    }
  }

  @Nested
  class isEqualTo {

    @Test
    @SneakyThrows
    void GIVEN_A1_WHEN_isEqualTo_A1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isEqualTo(A1);
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A2_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertClass.isEqualTo(A2));
    }

    @Test
    @SuppressWarnings("all")
    void WHEN_isEqualTo_Null_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> testAssertClass.isEqualTo(null));
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A1_THEN_normalizeObjectIsCalled() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useAssertResult(A1);

      // Act
      testAssertClass.isEqualTo(A1);

      // Assert
      mockTestArrangeNormalizer.verify(() -> normalizeObject(any()), times(2));
      mockTestArrangeNormalizer.close();
    }
  }

  @Nested
  class matchAll {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchAll_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> testAssertClass.matchAll(PREDICATE_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchAll_A_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAll(PREDICATE_NAME_EQUALS_A);
    }

    @Test
    void GIVEN_A1_WHEN_matchAll_A_1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAll(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    void GIVEN_A1_WHEN_matchAll_A_2_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var exception =
          assertThrows(
              AssertionError.class,
              () -> testAssertClass.matchAll(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
      assertThat(exception.getMessage(), not(EMPTY));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchAll_vararg_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> testAssertClass.matchAll(PREDICATES_NULL));
    }
  }

  @Nested
  class matchAny {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchAny_THEN_throw_NullPointerException() {
      assertThrows(NullPointerException.class, () -> testAssertClass.matchAny(PREDICATE_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAny(PREDICATE_ID_EQUALS_1);
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_B_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertClass.matchAny(element -> element.name().equals(B)));
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_B_1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAny(element -> element.name().equals(B), PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchAny_vararg_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> testAssertClass.matchAny(PREDICATES_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_B_3_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () ->
              testAssertClass.matchAny(
                  element -> element.name().equals(B), element -> element.id() == 3));
    }
  }

  @Nested
  class matchNone {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchNone_THEN_throw_NullPointerException() {
      assertThrows(NullPointerException.class, () -> testAssertClass.matchNone(PREDICATE_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_2_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchNone(PREDICATE_ID_EQUALS_2);
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_A_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      assertThrows(AssertionError.class, () -> testAssertClass.matchNone(PREDICATE_NAME_EQUALS_A));
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_B_2_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchNone(element -> element.name().equals(B), PREDICATE_ID_EQUALS_2);
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_A_3_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertClass.matchNone(PREDICATE_NAME_EQUALS_A, element -> element.id() == 3));
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_A_1_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      assertThrows(
          AssertionError.class,
          () -> testAssertClass.matchNone(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchNone_vararg_THEN_throwException() {
      assertThrows(NullPointerException.class, () -> testAssertClass.matchNone(PREDICATES_NULL));
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_headers_THEN_return_expected_class() {
      // Act
      var headers = testAssertClass.headers();

      // Assert
      assertThat(headers.getClass(), is(TestAssertHeadImpl.class));
    }
  }

  @Nested
  class combinationNotEmpty {

    @Test
    void isNotNull_isEqualTo() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull().isEqualTo(A1);
    }

    @Test
    void isNotNull_matchAll() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull().matchAll(PREDICATE_NAME_EQUALS_A);
    }

    @Test
    @SuppressWarnings("unchecked")
    void isNotNull_matchAll_vararg() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull().matchAll(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    void isNotNull_matchAny() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull().matchAny(PREDICATE_NAME_EQUALS_A);
    }

    @Test
    @SuppressWarnings("unchecked")
    void isNotNull_matchAny_vararg() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull().matchAny(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    void isNotNull_matchNone() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull().matchNone(element -> element.name().equals(B));
    }

    @Test
    @SuppressWarnings("unchecked")
    void isNotNull_matchNone_vararg() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass
          .isNotNull()
          .matchNone(element -> element.name().equals(B), PREDICATE_ID_EQUALS_2);
    }

    @Test
    void isNotNull_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isNotNull().headers();
    }
  }

  @Nested
  class combinationIsNull {

    @Test
    void isNull_headers() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      testAssertClass.isNull().headers();
    }
  }

  @Nested
  class combinationIsEqualTo {

    @Test
    void isEqualTo_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.isEqualTo(A1).headers();
    }
  }

  @Nested
  class combinationMatchAll {

    @Test
    void matchAll_matchAny() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAll(PREDICATE_NAME_EQUALS_A).matchAny(PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SuppressWarnings("unchecked")
    void matchAll_matchAny_vararg() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass
          .matchAll(PREDICATE_NAME_EQUALS_A)
          .matchAny(PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2);
    }

    @Test
    void matchAll_matchNone() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAll(PREDICATE_NAME_EQUALS_A).matchNone(element -> element.id() == 3);
    }

    @Test
    @SuppressWarnings("unchecked")
    void matchAll_matchNone_vararg() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass
          .matchAll(PREDICATE_NAME_EQUALS_A)
          .matchNone(element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    void matchAll_vararg__matchAny() {
      // Arrange
      useAssertResult(B1);

      // Act & Assert
      testAssertClassObjectMatch
          .matchAll(element -> element.name().equals(B), element -> element.status().equals(NEW))
          .matchAny(element -> element.id() == 1);
    }

    @Test
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchAny_vararg() {
      // Arrange
      useAssertResult(B1);

      // Act & Assert
      testAssertClassObjectMatch
          .matchAll(element -> element.name().equals(B), element -> element.status().equals(NEW))
          .matchAny(element -> element.id() == 1, element -> element.id() == 2);
    }

    @Test
    void matchAll_vararg_matchNone() {
      // Arrange
      useAssertResult(B1);

      // Act & Assert
      testAssertClassObjectMatch
          .matchAll(element -> element.name().equals(B))
          .matchNone(element -> element.id() == 3);
    }

    @Test
    @SuppressWarnings("unchecked")
    void matchAll_vararg_matchNone_vararg() {
      // Arrange
      useAssertResult(B1);

      // Act & Assert
      testAssertClassObjectMatch
          .matchAll(element -> element.name().equals(B))
          .matchNone(element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    void matchAll_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAll(PREDICATE_NAME_EQUALS_A).headers();
    }

    @Test
    void matchAll_vararg_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAll(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1).headers();
    }
  }

  @Nested
  class combinationMatchAny {

    @Test
    void matchAny_matchNone() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAny(PREDICATE_ID_EQUALS_1).matchNone(element -> element.id() == 3);
    }

    @Test
    @SuppressWarnings("unchecked")
    void matchAny_matchNone_vararg() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass
          .matchAny(PREDICATE_ID_EQUALS_1)
          .matchNone(element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    void matchAny_vararg_matchNone() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass
          .matchAny(PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2)
          .matchNone(element -> element.id() == 3);
    }

    @Test
    @SuppressWarnings("unchecked")
    void matchAny_vararg_matchNone_vararg() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass
          .matchAny(PREDICATE_ID_EQUALS_1, PREDICATE_ID_EQUALS_2)
          .matchNone(element -> element.id() == 3, element -> element.id() == 4);
    }

    @Test
    void matchAny_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAny(PREDICATE_NAME_EQUALS_A).headers();
    }

    @Test
    void matchAny_vararg_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchAny(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1).headers();
    }
  }

  @Nested
  class combinationMatchNone {

    @Test
    void matchNone_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass.matchNone(element -> element.name().equals(B)).headers();
    }

    @Test
    void matchNone_vararg_headers() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      testAssertClass
          .matchNone(element -> element.name().equals(B), PREDICATE_ID_EQUALS_2)
          .headers();
    }
  }
}
