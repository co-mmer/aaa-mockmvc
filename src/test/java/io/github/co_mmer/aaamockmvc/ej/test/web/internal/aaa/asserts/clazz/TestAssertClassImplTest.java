package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer.normalizeObject;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.utils.StringUtils.EMPTY;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.B;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.match.TestAssertMatch;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.string.TestArrangeNormalizer;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S2699")
class TestAssertClassImplTest extends TestAssertBase {

  private static final Predicate<TestObjectSimple> PREDICATE_A =
      element -> element.name().equals(A);
  private static final Predicate<TestObjectSimple> PREDICATE_NULL = null;
  private static final Predicate<TestObjectSimple>[] PREDICATES_NULL = null;
  private static final Predicate<TestObjectSimple> PREDICATE_NAME_EQUALS_A =
      element -> element.name().equals(A);
  private static final Predicate<TestObjectSimple> PREDICATE_ID_EQUALS_2 =
      element -> element.id() == 2;
  private static final Predicate<TestObjectSimple> PREDICATE_ID_EQUALS_1 =
      element -> element.id() == 1;

  private TestAssertClassImpl<TestObjectSimple> impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.impl = new TestAssertClassImpl<>(context);
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
    void GIVEN_A1_WHEN_isNotNull_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.isNotNull();
    }

    @Test
    void GIVEN_null_WHEN_isNotNull_THEN_assert_false() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, impl::isNotNull);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isNull {

    @Test
    void GIVEN_null_WHEN_isNull_THEN_assert_true() {
      // Arrange
      useAssertResult(null);

      // Act & Assert
      impl.isNull();
    }

    @Test
    void GIVEN_A1_WHEN_isNull_THEN_return_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, impl::isNull);
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }
  }

  @Nested
  class isEqualTo {

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.isEqualTo(A1);
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A2_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> impl.isEqualTo(A2));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    @SuppressWarnings("all")
    void WHEN_isEqualTo_Null_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.isEqualTo(null));
    }

    @Test
    void GIVEN_A1_WHEN_isEqualTo_A1_THEN_normalize_object_is_called() {
      // Arrange
      var mockTestArrangeNormalizer = mockStatic(TestArrangeNormalizer.class);
      useAssertResult(A1);

      // Act
      impl.isEqualTo(A1);

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
      assertThrows(NullPointerException.class, () -> impl.matchAll(PREDICATE_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchAll_A_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.matchAll(PREDICATE_NAME_EQUALS_A);
    }

    @Test
    void GIVEN_A1_WHEN_matchAll_A_1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.matchAll(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1);
    }

    @Test
    void GIVEN_A1_WHEN_matchAll_A_2_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class,
              () -> impl.matchAll(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_2));
      assertThat(ex.getMessage(), not(EMPTY));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchAll_vararg_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.matchAll(PREDICATES_NULL));
    }

    @Test
    @SuppressWarnings("unchecked")
    void GIVEN_A1_WHEN_matchAll_N_Predicate_THEN_success() {
      // Arrange
      var mocked = mockStatic(TestAssertMatch.class);
      var match = mock(TestAssertMatch.class);
      mocked.when(() -> TestAssertMatch.assertThat(STEP, A1)).thenReturn(match);
      useAssertResult(A1);

      // Act
      impl.matchAll(PREDICATE_A);

      // Assert
      var p1 = new Predicate[] {PREDICATE_A};
      verify(match).matchAll(p1);

      impl.matchAll(PREDICATE_A, PREDICATE_A);
      var p2 = new Predicate[] {PREDICATE_A, PREDICATE_A};
      verify(match).matchAll(p2);

      impl.matchAll(PREDICATE_A, PREDICATE_A, PREDICATE_A);
      var p3 = new Predicate[] {PREDICATE_A, PREDICATE_A, PREDICATE_A};
      verify(match).matchAll(p3);

      mocked.close();
    }
  }

  @Nested
  class matchAny {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchAny_THEN_throw_NullPointerException() {
      assertThrows(NullPointerException.class, () -> impl.matchAny(PREDICATE_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.matchAny(PREDICATE_ID_EQUALS_1);
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_B_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class, () -> impl.matchAny(element -> element.name().equals(B)));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_B_1_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.matchAny(element -> element.name().equals(B), PREDICATE_ID_EQUALS_1);
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchAny_vararg_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.matchAny(PREDICATES_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchAny_B_3_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class,
              () ->
                  impl.matchAny(element -> element.name().equals(B), element -> element.id() == 3));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    @SuppressWarnings("unchecked")
    void GIVEN_A1_WHEN_matchAll_N_Predicate_THEN_success() {
      // Arrange
      var mocked = mockStatic(TestAssertMatch.class);
      var match = mock(TestAssertMatch.class);
      mocked.when(() -> TestAssertMatch.assertThat(STEP, A1)).thenReturn(match);
      useAssertResult(A1);

      // Act
      impl.matchAny(PREDICATE_A);

      // Assert
      var p1 = new Predicate[] {PREDICATE_A};
      verify(match).matchAny(p1);

      impl.matchAny(PREDICATE_A, PREDICATE_A);
      var p2 = new Predicate[] {PREDICATE_A, PREDICATE_A};
      verify(match).matchAny(p2);

      impl.matchAny(PREDICATE_A, PREDICATE_A, PREDICATE_A);
      var p3 = new Predicate[] {PREDICATE_A, PREDICATE_A, PREDICATE_A};
      verify(match).matchAny(p3);

      mocked.close();
    }
  }

  @Nested
  class matchNone {

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchNone_THEN_throw_NullPointerException() {
      assertThrows(NullPointerException.class, () -> impl.matchNone(PREDICATE_NULL));
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_2_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.matchNone(PREDICATE_ID_EQUALS_2);
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_A_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex = assertThrows(AssertionError.class, () -> impl.matchNone(PREDICATE_NAME_EQUALS_A));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_B_2_THEN_assert_true() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      impl.matchNone(element -> element.name().equals(B), PREDICATE_ID_EQUALS_2);
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_A_3_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class,
              () -> impl.matchNone(PREDICATE_NAME_EQUALS_A, element -> element.id() == 3));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    void GIVEN_A1_WHEN_matchNone_A_1_THEN_assert_false() {
      // Arrange
      useAssertResult(A1);

      // Act & Assert
      var ex =
          assertThrows(
              AssertionError.class,
              () -> impl.matchNone(PREDICATE_NAME_EQUALS_A, PREDICATE_ID_EQUALS_1));
      assertThat(ex.getMessage(), containsString(STEP_NAME));
    }

    @Test
    @SuppressWarnings("all")
    void GIVEN_null_WHEN_matchNone_vararg_THEN_throw_Exception() {
      assertThrows(NullPointerException.class, () -> impl.matchNone(PREDICATES_NULL));
    }

    @Test
    @SuppressWarnings("unchecked")
    void GIVEN_A1_WHEN_matchAll_N_Predicate_THEN_success() {
      // Arrange
      var mocked = mockStatic(TestAssertMatch.class);
      var match = mock(TestAssertMatch.class);
      mocked.when(() -> TestAssertMatch.assertThat(STEP, A1)).thenReturn(match);
      useAssertResult(A1);

      // Act
      impl.matchNone(PREDICATE_A);

      // Assert
      var p1 = new Predicate[] {PREDICATE_A};
      verify(match).matchNone(p1);

      impl.matchNone(PREDICATE_A, PREDICATE_A);
      var p2 = new Predicate[] {PREDICATE_A, PREDICATE_A};
      verify(match).matchNone(p2);

      impl.matchNone(PREDICATE_A, PREDICATE_A, PREDICATE_A);
      var p3 = new Predicate[] {PREDICATE_A, PREDICATE_A, PREDICATE_A};
      verify(match).matchNone(p3);

      mocked.close();
    }
  }

  @Nested
  class nextStep {

    @Test
    void WHEN_headers_THEN_return_expected_class() {
      // Act
      var headers = impl.headers();

      // Assert
      assertThat(headers, instanceOf(TestAssertHeadImpl.class));
    }
  }
}
