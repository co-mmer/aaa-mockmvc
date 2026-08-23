package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.clazz;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TestAssertClassImplTest extends TestAssertBase {

  private static final Predicate<TestObjectSimple> PREDICATE_A =
      element -> element.name().equals(A);

  private static final AssertValue<TestObjectSimple, String> ASSERT_VALUE_A1 =
      AssertValue.expectedResponse(A1);

  private TestAssertClassImpl<TestObjectSimple> impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.impl = new TestAssertClassImpl<>(context);
  }

  @SuppressWarnings("unchecked")
  private void assertCall(Runnable actCall, Consumer<AAAAssert<?, ?>> verifyCall) {
    var mockInstance = Mockito.mock(AAAAssert.class);
    var mockClass = Mockito.mockStatic(AAAAssert.class);

    mockClass.when(() -> AAAAssert.expect(any(), any())).thenReturn(mockInstance);
    Mockito.when(mockInstance.toHaveSameTypeAndValueAs(any())).thenReturn(mockInstance);

    actCall.run();

    mockClass.verify(
        () -> AAAAssert.expect(getContext().getStep(), getContext().getAssertOperand()),
        Mockito.times(1));

    verifyCall.accept(mockInstance);
    mockClass.close();
  }

  @Test
  void WHEN_isNotNull_THEN_toBePresent_is_called() {
    assertCall(() -> impl.isNotNull(), mock -> verify(mock, times(1)).toBePresent());
  }

  @Test
  void WHEN_isNull_THEN_toBeAbsent_is_called() {
    assertCall(() -> impl.isNull(), mock -> verify(mock, times(1)).toBeAbsent());
  }

  @Test
  void WHEN_isEqualTo_THEN_toHaveSameTypeAndValueAs_and_toEqual_are_called() {
    assertCall(
        () -> impl.isEqualTo(A1),
        mock -> {
          verify(mock, times(1)).toHaveSameTypeAndValueAs(ASSERT_VALUE_A1);
          verify(mock, times(1)).toEqual(ASSERT_VALUE_A1);
        });
  }

  @Test
  void WHEN_matchAll_THEN_toMatchAll_is_called() {
    assertCall(() -> impl.matchAll(PREDICATE_A), mock -> verify(mock, times(1)).toMatchAll(any()));
  }

  @Test
  void WHEN_matchAll_2_THEN_toMatchAll_is_called() {
    assertCall(
        () -> impl.matchAll(PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchAll(any()));
  }

  @Test
  void WHEN_matchAll_3_THEN_toMatchAll_is_called() {
    assertCall(
        () -> impl.matchAll(PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchAll(any()));
  }

  @Test
  void WHEN_matchAll_varargs_THEN_toMatchAll_is_called() {
    assertCall(
        () -> impl.matchAll(PREDICATE_A, PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchAll(any()));
  }

  @Test
  void WHEN_matchAny_THEN_toMatchAny_is_called() {
    assertCall(() -> impl.matchAny(PREDICATE_A), mock -> verify(mock, times(1)).toMatchAny(any()));
  }

  @Test
  void WHEN_matchAny_2_THEN_toMatchAny_is_called() {
    assertCall(
        () -> impl.matchAny(PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchAny(any()));
  }

  @Test
  void WHEN_matchAny_3_THEN_toMatchAny_is_called() {
    assertCall(
        () -> impl.matchAny(PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchAny(any()));
  }

  @Test
  void WHEN_matchAny_varargs_THEN_toMatchAny_is_called() {
    assertCall(
        () -> impl.matchAny(PREDICATE_A, PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchAny(any()));
  }

  @Test
  void WHEN_matchNone_THEN_toMatchNone_is_called() {
    assertCall(
        () -> impl.matchNone(PREDICATE_A), mock -> verify(mock, times(1)).toMatchNone(any()));
  }

  @Test
  void WHEN_matchNone_2_THEN_toMatchNone_is_called() {
    assertCall(
        () -> impl.matchNone(PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchNone(any()));
  }

  @Test
  void WHEN_matchNone_3_THEN_toMatchNone_is_called() {
    assertCall(
        () -> impl.matchNone(PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchNone(any()));
  }

  @Test
  void WHEN_matchNone_varargs_THEN_toMatchNone_is_called() {
    assertCall(
        () -> impl.matchNone(PREDICATE_A, PREDICATE_A, PREDICATE_A, PREDICATE_A),
        mock -> verify(mock, times(1)).toMatchNone(any()));
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
