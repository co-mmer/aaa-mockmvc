package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.collection;

import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A1;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObject.TEST_LIST_A1_A2;
import static io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestValue.STEP_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.AAAAssert;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.model.AssertValue;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.TestAssertBase;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.asserts.head.TestAssertHeadImpl;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestContext;
import io.github.co_mmer.aaamockmvc.ej.testdata.testutil.TestObjectSimple;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TestAssertCollectionImplTest extends TestAssertBase {

  private static final Predicate<TestObjectSimple> PREDICATE_A =
      element -> element.name().equals(A);

  private static final AssertValue<Collection<TestObjectSimple>, List<String>> ASSERT_VALUE_A1_A2 =
      AssertValue.expectedCollection(TEST_LIST_A1_A2);

  private TestAssertCollectionImpl<TestObjectSimple> impl;

  @BeforeEach
  void setUp() {
    var context = TestContext.createContext(STEP_NAME);
    this.useContext(context);
    this.impl = new TestAssertCollectionImpl<>(context);
  }

  private void assertCall(Runnable actCall, Consumer<AAAAssert<?, ?>> verifyCall) {
    var mockInstance = Mockito.mock(AAAAssert.class);
    var mockClass = Mockito.mockStatic(AAAAssert.class);
    mockClass.when(() -> AAAAssert.expect(any(), any())).thenReturn(mockInstance);

    actCall.run();

    mockClass.verify(
        () -> AAAAssert.expect(getContext().getStep(), getContext().getAssertOperand()),
        Mockito.times(1));

    verifyCall.accept(mockInstance);
    mockClass.close();
  }

  @Test
  void WHEN_isNotEmpty_THEN_notToBeEmpty_is_called() {
    assertCall(() -> impl.isNotEmpty(), mock -> verify(mock, times(1)).notToBeEmpty());
  }

  @Test
  void WHEN_isEmpty_THEN_toBeEmpty_is_called() {
    assertCall(() -> impl.isEmpty(), mock -> verify(mock, times(1)).toBeEmpty());
  }

  @Test
  void WHEN_hasSize_THEN_toHaveSize_is_called() {
    assertCall(
        () -> impl.hasSize(1),
        mock -> verify(mock, times(1)).toHaveSize(AssertValue.expectedSize(1)));
  }

  @Test
  void WHEN_isEqualTo_THEN_toEqual_is_called() {
    assertCall(
        () -> impl.isEqualTo(TEST_LIST_A1_A2),
        mock -> verify(mock, times(1)).toEqual(ASSERT_VALUE_A1_A2));
  }

  @Test
  void WHEN_contains_THEN_toContain_is_called() {
    assertCall(
        () -> impl.contains(TEST_LIST_A1_A2),
        mock -> verify(mock, times(1)).toContain(ASSERT_VALUE_A1_A2));
  }

  @Test
  void WHEN_contains_varargs_THEN_toContain_is_called() {
    assertCall(
        () -> impl.contains(A1, A2), mock -> verify(mock, times(1)).toContain(ASSERT_VALUE_A1_A2));
  }

  @Test
  void WHEN_containsAnyOrder_THEN_toContainExactlyInAnyOrder_is_called() {
    assertCall(
        () -> impl.containsAnyOrder(TEST_LIST_A1_A2),
        mock -> verify(mock, times(1)).toContainExactlyInAnyOrder(ASSERT_VALUE_A1_A2));
  }

  @Test
  void WHEN_notContains_THEN_notToContain_is_called() {
    assertCall(
        () -> impl.notContains(TEST_LIST_A1_A2),
        mock -> verify(mock, times(1)).notToContain(ASSERT_VALUE_A1_A2));
  }

  @Test
  void WHEN_notContains_varargs_THEN_notToContain_is_called() {
    assertCall(
        () -> impl.notContains(A1, A2),
        mock -> verify(mock, times(1)).notToContain(ASSERT_VALUE_A1_A2));
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
  void WHEN_matchAny_THEN_matchAny_is_called() {
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
