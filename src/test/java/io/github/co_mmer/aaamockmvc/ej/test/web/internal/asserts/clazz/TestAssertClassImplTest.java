package io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
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

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext();
    this.useContext(context);
    this.testAssertClass = new TestAssertClassImpl<>(context);
  }

  @Nested
  class constructor {

    @Test
    @SuppressWarnings("ConstantConditions")
    void GIVEN_null_WHEN_call_constructor_THEN_throw_Exception() {
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
    void WHEN_isEqualTo_Null_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> testAssertClass.isEqualTo(null));
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A1_THEN_normalize_object_is_called() {
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
    void GIVEN_null_WHEN_matchAll_THEN_throw_Exception() {
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
    void GIVEN_null_WHEN_matchAll_vararg_THEN_throw_Exception() {
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
    void GIVEN_null_WHEN_matchAny_vararg_THEN_throw_Exception() {
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
    void GIVEN_null_WHEN_matchNone_vararg_THEN_throw_Exception() {
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
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
